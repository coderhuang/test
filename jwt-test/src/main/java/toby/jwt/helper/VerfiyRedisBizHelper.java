package toby.jwt.helper;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public final class VerfiyRedisBizHelper {

	private VerfiyRedisBizHelper() {
	}

	private static final String VERFIY_REDIS_KEY_PREFIX = "jwt-test:verify:";

	private static final String VERIFY_REDIS_IMAGE_PREFIX = VERFIY_REDIS_KEY_PREFIX + "image:";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public String mobileSignupVerifyImageKey(String userCode) {

		return VERIFY_REDIS_IMAGE_PREFIX + userCode;
	}

	public void saveMobileSignupVerifyImageCode(String userCode, String randomCode, long expiration) {

		String key = mobileSignupVerifyImageKey(userCode);
		redisTemplate.opsForValue().set(key, randomCode, expiration, TimeUnit.SECONDS);
	}

	public String getMobileSignupVerifyImageCode(String userCode) {

		String key = mobileSignupVerifyImageKey(userCode);
		return (String) redisTemplate.opsForValue().get(key);
	}
}
