package toby.redisson;

import org.junit.jupiter.api.BeforeAll;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

class BaseTest {

	public static RedissonClient redissonClient;

	public static RedissonReactiveClient redissonReactiveClient;

	public static RedissonRxClient redissonRxClient;

	@BeforeAll
	public static void config() {

		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);
		objectMapper.enable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		Config config = new Config();
		config.setCodec(new JsonJacksonCodec(objectMapper));
//		config.setLockWatchdogTimeout(3000L);
		config.useSingleServer().setAddress("redis://192.168.21.141:6379").setDatabase(8);

		redissonClient = Redisson.create(config);
		redissonReactiveClient = Redisson.createReactive(config);
		redissonRxClient = Redisson.createRx(config);
	}
}
