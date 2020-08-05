package toby.jwt.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import toby.jwt.common.enums.BizContext;
import toby.jwt.config.RedisConfig;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@GetMapping("/info")
	public Object obtainUserInfo(HttpServletResponse response) {

		String redisKeyUserSuffixString = (String) BizContext.INSTANCE.getValue("redisKeyUserSuffix");
		if (StringUtils.isEmpty(redisKeyUserSuffixString)) {

			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}

		String key = RedisConfig.REDIS_KEY_PREFIX + redisKeyUserSuffixString;

		return redisTemplate.opsForValue().get(key);
	}
}
