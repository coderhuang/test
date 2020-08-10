package toby.jwt.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import toby.jwt.common.enums.BizContext;
import toby.jwt.common.enums.user.UserBizContextConstant;
import toby.jwt.domain.User;
import toby.jwt.helper.UserRedisBizHelper;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRedisBizHelper userRedisBizHelper;

	@GetMapping("/info")
	public Object obtainUserInfo(HttpServletResponse response) {

		String redisKeyUserSuffixString = (String) BizContext.INSTANCE
				.getValue(UserBizContextConstant.KEY_REDIS_USER_SUFFIX.value());
		if (StringUtils.isEmpty(redisKeyUserSuffixString)) {

			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		ImmutablePair<User, String> userInfoRedisVal = userRedisBizHelper.getUserInfo(redisKeyUserSuffixString);
		if (Objects.isNull(userInfoRedisVal) || Objects.isNull(userInfoRedisVal.getLeft()) || Objects.isNull(userInfoRedisVal.getRight())) {
			
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return "登录态已过期，或其他终端已登录";
		}
		
		String jwtIdString = (String) BizContext.INSTANCE.getValue(UserBizContextConstant.KEY_JWT_ID.value());
		if (!jwtIdString.equals(userInfoRedisVal.getRight())) {
			
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return "登录态已失效，其他终端已登录";
		}

		return userInfoRedisVal.getLeft();
	}
}
