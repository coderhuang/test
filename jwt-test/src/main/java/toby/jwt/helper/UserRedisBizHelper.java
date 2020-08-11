package toby.jwt.helper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import toby.jwt.domain.User;

@Service
public final class UserRedisBizHelper {

	public static final long EXPIRATION = 1800;

	public static final String KEY_PREFIX = "jwt-test:user:";

	public static final String KEY_INFO_PREFIX = KEY_PREFIX + "info:";

	public static final String KEY_REFRESH_TOKEN_PREFIX = KEY_PREFIX + "refresh_token:";

	public static final String KEY_TOKEN_BIZ_INFO_KEY = "token_biz_info";

	public static final String KEY_TOKEN_NONCE_KEY = "token_nonce";

	public static final String KEY_TOKEN_SUBJECT_MAP_KEY = "token_subject";

	public static final ResourceScriptSource SIGN_ON_SCRIPT = new ResourceScriptSource(
			new ClassPathResource("templates" + File.separator + "lua" + File.separator + "signOn.lua"));

	private String signOnScriptString;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public UserRedisBizHelper() throws IOException {

		signOnScriptString = SIGN_ON_SCRIPT.getScriptAsString();
	}

	private String userInfoKey(String userCode) {

		return KEY_INFO_PREFIX + userCode;
	}

	public Long signOn(User user, String uuid, long expiration, String refreshTokenUuid, long refreshTokenExpiration) {

		String key = userInfoKey(user.getCode());
		String refreshTokenKey = refreshTokenBizInfoKey(refreshTokenUuid);
		// 目前这个脚本是假设是在sentinel的情况下执行。如果是cluster的话可以是使用hashtags的方案避免多node操作，不过无法避免rehash或者reshard时的问题
		return redisTemplate.execute(new DefaultRedisScript<Long>(signOnScriptString, Long.class),
				List.of(key, refreshTokenKey), KEY_TOKEN_BIZ_INFO_KEY, user, KEY_TOKEN_NONCE_KEY, uuid, expiration,
				user.getCode(), refreshTokenExpiration);
	}

	public void setUserInfo(User user, String uuid, long expiration) {

		String key = userInfoKey(user.getCode());

		Map<String, Object> hash = new HashMap<>(4);
		hash.put(KEY_TOKEN_BIZ_INFO_KEY, user);
		hash.put(KEY_TOKEN_NONCE_KEY, uuid);
		// 保证多操作的原子性,可使用lua脚本来实现,目前暂时不实现
		redisTemplate.opsForHash().putAll(key, hash);
		redisTemplate.expire(key, expiration, TimeUnit.SECONDS);
	}

	public ImmutablePair<User, String> getUserInfo(String userCode) {

		String key = userInfoKey(userCode);
		List<Object> objs = redisTemplate.opsForHash().multiGet(key,
				List.of(KEY_TOKEN_BIZ_INFO_KEY, KEY_TOKEN_NONCE_KEY));
		if (CollectionUtils.isEmpty(objs)) {
			return null;
		}

		Object userObj = objs.get(0);
		if (Objects.isNull(userObj)) {
			return null;
		}

		if (Objects.isNull(objs.get(1))) {
			return null;
		}

		return ImmutablePair.of(objectMapper.convertValue(userObj, User.class), (String) objs.get(1));
	}

	public void delUserInfo(String userCode) {

		redisTemplate.delete(userInfoKey(userCode));
	}

	public void setValueWithExpiration(String key, Object value, Long expiration) {

		redisTemplate.opsForValue().set(key, value, expiration, TimeUnit.SECONDS);
	}

	private String refreshTokenBizInfoKey(String uuid) {

		return KEY_REFRESH_TOKEN_PREFIX + uuid;
	}

	public void setRefreshTokenBizInfo(String uuid, String userCode, Long expiration) {

		String refreshTokenKey = refreshTokenBizInfoKey(uuid);
		redisTemplate.opsForValue().set(refreshTokenKey, userCode, expiration, TimeUnit.SECONDS);
	}

	public String getRefreshTokenBizInfo(String uuid) {

		String refreshTokenKey = refreshTokenBizInfoKey(uuid);
		return (String) redisTemplate.opsForValue().get(refreshTokenKey);
	}

	public void delRefreshTokenBizInfo(String uuid) {

		redisTemplate.delete(refreshTokenBizInfoKey(uuid));
	}

}
