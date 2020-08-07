package toby.jwt.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import toby.jwt.common.utils.JwtUtil;
import toby.jwt.config.RedisConfig;
import toby.jwt.domain.User;

@RestController
public class SignOnAndUpController {

	static final Random random = new Random(System.currentTimeMillis());

	static final Map<Long, User> IN_MEMORY_USERS = new HashMap<>();
	static {

		AtomicInteger atomI = new AtomicInteger(0);
		AtomicLong atomL = new AtomicLong();
		for (int i = 0; i < 1000; i++) {

			User user = new User();
			LocalDateTime now = LocalDateTime.now();

			user.setId(atomL.incrementAndGet());
			user.setName("啊哈-" + atomI.incrementAndGet());
			user.setPassword("123456");
			user.setCreateTime(now);
			user.setUpdateTime(now);

			IN_MEMORY_USERS.put(user.getId(), user);
		}
	}

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

//	@PostMapping("/sign-on")
//	public String signOn(@RequestParam String name, @RequestParam String password, HttpServletResponse response) {
//
//		final Pair<User, User> pair = new MutablePair<>();
//		IN_MEMORY_USERS.entrySet().stream().filter(entry -> {
//
//			User user = entry.getValue();
//			return !(!name.equals(user.getName()) || !password.equals(user.getPassword()));
//		}).findAny().ifPresent(entry -> pair.setValue(entry.getValue()));
//
//		User user = pair.getValue();
//		if (Objects.isNull(user)) {
//
//			response.setStatus(HttpStatus.BAD_REQUEST.value());
//			return StringUtils.EMPTY;
//		}
//
//		String uuid = UUID.randomUUID().toString();
//		String key = RedisConfig.REDIS_KEY_PREFIX + uuid;
//		redisTemplate.opsForValue().set(key, user, 1800, TimeUnit.SECONDS);
//
//		return JwtUtil.createToken(uuid, user);
//	}
	
	@PostMapping("/sign-on")
	public String signOn(@RequestParam String name, @RequestParam String password, HttpServletResponse response) {

		var optionalEntry = IN_MEMORY_USERS.entrySet().stream().filter(entry -> {

			User user = entry.getValue();
			return name.equals(user.getName()) && password.equals(user.getPassword());
		}).findAny();

		if (!optionalEntry.isPresent()) {

			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return StringUtils.EMPTY;
		}

		User user = optionalEntry.get().getValue();
		String uuid = UUID.randomUUID().toString();
		String key = RedisConfig.REDIS_KEY_PREFIX + uuid;
		redisTemplate.opsForValue().set(key, user, 1800, TimeUnit.SECONDS);

		return JwtUtil.createToken(uuid, user);
	}
}
