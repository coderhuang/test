package toby.jwt.helper;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scripting.support.ResourceScriptSource;

public final class RedisBaseHelper {

	private RedisBaseHelper() {
	}
	
	public static void evalScript(RedisTemplate<String, Object> redisTemplate, ResourceScriptSource script) {
		
//		redisTemplate.execute(script, keys, args)
	}
}
