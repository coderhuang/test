package toby.jwt.digest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import toby.jwt.Application;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { Application.class })
@DisplayName("redis命令验证测试")
class RedisTest {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	void keysTest() {
		
		String keyPattern = "jwt-test:user:*";
		var keySet = redisTemplate.keys(keyPattern);
		assertFalse(CollectionUtils.isEmpty(keySet));
		
		keySet.forEach(System.err::println);
	}
	
	@Test
	void keysDelTest() {
		
		String keyPattern = "jwt-test:user:*";
		var keySet = redisTemplate.keys(keyPattern);
		assertFalse(CollectionUtils.isEmpty(keySet));
		
		assertTrue(redisTemplate.delete(keySet) > 0);
	}
}
