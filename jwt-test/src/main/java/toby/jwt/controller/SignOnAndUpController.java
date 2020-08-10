package toby.jwt.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.Claim;

import toby.jwt.common.enums.user.UserHttpConstant;
import toby.jwt.common.utils.JwtUtil;
import toby.jwt.domain.User;
import toby.jwt.helper.UserRedisBizHelper;

@RestController
public class SignOnAndUpController {

	static final Random random = new Random(System.currentTimeMillis());
	static final long DEFAULT_EXPIRATION = 1800;

	static final Map<Long, User> IN_MEMORY_USERS = new HashMap<>();
	static {

		AtomicInteger atomI = new AtomicInteger(0);
		AtomicLong atomL = new AtomicLong();
		for (int i = 0; i < 1000; i++) {

			User user = new User();
			LocalDateTime now = LocalDateTime.now();

			user.setId(atomL.incrementAndGet());
			user.setName("啊哈-" + atomL.get());
			user.setCode(String.valueOf(atomI.incrementAndGet()));
			user.setPassword("123456");
			user.setCreateTime(now);
			user.setUpdateTime(now);

			IN_MEMORY_USERS.put(user.getId(), user);
		}
	}

	@Autowired
	private UserRedisBizHelper userRedisHelper;

	@PostMapping("/sign-on")
	public ImmutablePair<String, String> signOn(@RequestParam String name, @RequestParam String password,
			HttpServletResponse response) {

		var optionalEntry = IN_MEMORY_USERS.entrySet().stream().filter(entry -> {

			User user = entry.getValue();
			return name.equals(user.getName()) && password.equals(user.getPassword());
		}).findAny();

		if (!optionalEntry.isPresent()) {

			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}

		long userInfoTokenExpiration = DEFAULT_EXPIRATION;
		// 刷新token的有效期10倍于用户登录态的有效期，降低客户端登录态丢失的几率
		long refreshTokenExpiration = userInfoTokenExpiration * 10;

		User user = optionalEntry.get().getValue();
		String refreshUuid = UUID.randomUUID().toString();

		return createSignOnToken(user, userInfoTokenExpiration, refreshUuid, refreshTokenExpiration);
	}

	private ImmutablePair<String, String> createSignOnToken(User user, long userInfoTokenExpiration, String refreshUuid,
			long refreshTokenExpiration) {

		String uuid = UUID.randomUUID().toString();
		userRedisHelper.signOn(user, uuid, userInfoTokenExpiration, refreshUuid, refreshTokenExpiration);
//		userRedisHelper.setUserInfo(user, uuid, userInfoTokenExpiration);
//		userRedisHelper.setRefreshTokenBizInfo(refreshUuid, user.getCode(), refreshTokenExpiration);

		String userInfoToken = JwtUtil.createUserInfoToken(user, uuid, userInfoTokenExpiration);
		String refreshToke = JwtUtil.createUserRefreshToken(refreshUuid, refreshTokenExpiration);

		return ImmutablePair.of(userInfoToken, refreshToke);
	}

	@GetMapping("/refresh-user-authority")
	public ImmutablePair<String, String> refreshUserAuthority(HttpServletRequest request,
			HttpServletResponse response) {

		final String refreshToken = request.getHeader(UserHttpConstant.REFRESH_TOKEN_PARAMETER_KEY.value());
		Map<String, Claim> refreshData;
		try {

			refreshData = JwtUtil.verifyToken(refreshToken);
		} catch (TokenExpiredException e) {

			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			return ImmutablePair.of("用户登录态刷新令牌已过期", "请重新登录");
		}

		String uuid = refreshData.get(PublicClaims.SUBJECT).asString();
		String userCode = userRedisHelper.getRefreshTokenBizInfo(uuid);
		if (StringUtils.isEmpty(userCode)) {

			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			return ImmutablePair.of("用户登录态刷新令牌已过期", "请重新登录");
		}

		// 从业务角度来说,必然存在用户信息
		var user = IN_MEMORY_USERS.entrySet().stream().filter(entry -> userCode.equals(entry.getValue().getCode()))
				.findAny().get().getValue();

		long userInfoTokenExpiration = DEFAULT_EXPIRATION;
		// 刷新token的有效期10倍于用户登录态的有效期，降低客户端登录态丢失的几率
		long refreshTokenExpiration = userInfoTokenExpiration * 10;
		String refreshUuid = UUID.randomUUID().toString();

		userRedisHelper.delUserInfo(userCode);
		ImmutablePair<String, String> tokenPair = createSignOnToken(user, userInfoTokenExpiration, refreshUuid,
				refreshTokenExpiration);
		userRedisHelper.delRefreshTokenBizInfo(uuid);
		return tokenPair;
	}

}
