package toby.test.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

class GuavaTest {

	static Cache<String, String> cacher;

	@BeforeAll
	static void init() {

		cacher = CacheBuilder.newBuilder().expireAfterWrite(Duration.ofHours(6L))// 缓存6小时后失效
				.maximumSize(100)// 设置缓存的最大容量
				.concurrencyLevel(2)// 设置并发级别为2
				.recordStats() // 开启缓存统计
				.build();
	}

	@Test
	void testCachePut() throws Exception {

		cacher.put("1", "100");
		@Nullable
		String ifPresent = cacher.getIfPresent("1");
		assertNotNull(ifPresent);
		System.err.println(ifPresent);
	}

	@Test
	void testGet() throws Exception {

		String value = cacher.get("k1", () -> "V1");
		assertTrue(StringUtils.isNotBlank(value));
		System.err.println(value);
	}

	@Test
	void testCach6eLoader() throws Exception {

		LoadingCache<String, String> testLoaderCacher = CacheBuilder.newBuilder()
				.expireAfterWrite(Duration.ofSeconds(2))// 缓存6小时后失效
				.maximumSize(100)// 设置缓存的最大容量
				.concurrencyLevel(2)// 设置并发级别为10
				.recordStats() // 开启缓存统计
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {

						return key + "-value";
					}
				});

		String value = testLoaderCacher.get("m1");
		assertTrue(StringUtils.isNotBlank(value));
		System.err.println(value);
	}

	@Test
	void testInvalid() throws Exception {

		String k1 = "k1";
		String k1Val = cacher.get(k1, () -> "v1");
		String k2 = "k2";
		String k2Val = cacher.get(k2, () -> "v2");
		String k3 = "k3";
		String k3Val = cacher.get(k3, () -> "v3");

		assertTrue(StringUtils.isNoneBlank(k1Val, k2Val, k3Val));
		System.err.println("初始缓存:" + List.of(k1Val, k2Val, k3Val));

		System.err.println("删除k1和k2");
		cacher.invalidateAll(List.of(k1, k2));
		k1Val = cacher.getIfPresent(k1);
		k2Val = cacher.getIfPresent(k2);
		System.err.println(k1 + ":" + k1Val);
		System.err.println(k2 + ":" + k2Val);
		k3Val = cacher.getIfPresent(k3);
		System.err.println(k3 + ":" + k3Val);

		System.err.println("删除所有");
		cacher.invalidateAll();
		k3Val = cacher.getIfPresent(k3);
		System.err.println(k3 + ":" + k3Val);
	}

	@Test
	void testRefresh() throws Exception {

		String k1 = "k1";
		String k1Val = cacher.get(k1, () -> "v1");
		String k2 = "k2";
		String k2Val = cacher.get(k2, () -> "v2");
		String k3 = "k3";
		String k3Val = cacher.get(k3, () -> "v3");

		assertTrue(StringUtils.isNoneBlank(k1Val, k2Val, k3Val));
		System.err.println("初始缓存:" + List.of(k1Val, k2Val, k3Val));

		System.err.println("刷新k1和k2");
		cacher.put(k1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		cacher.put(k2, LocalDateTime.now().minusMinutes(1L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		k1Val = cacher.getIfPresent(k1);
		k2Val = cacher.getIfPresent(k2);
		System.err.println(k1 + ":" + k1Val);
		System.err.println(k2 + ":" + k2Val);

		LoadingCache<String, String> testLoaderCacher = CacheBuilder.newBuilder()
				.expireAfterWrite(Duration.ofSeconds(2))// 缓存6小时后失效
				.maximumSize(100)// 设置缓存的最大容量
				.concurrencyLevel(2)// 设置并发级别为10
				.recordStats() // 开启缓存统计
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {

						return key + "-value"
								+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					}
				});
		String m1 = "m1";
		String m1Val = testLoaderCacher.get(m1);
		System.err.println(m1 + ":" + m1Val);

		Thread.sleep(1000);
		testLoaderCacher.refresh(m1);
		m1Val = testLoaderCacher.getIfPresent(m1);
		System.err.println("刷新后");
		System.err.println(m1 + ":" + m1Val);
	}
}
