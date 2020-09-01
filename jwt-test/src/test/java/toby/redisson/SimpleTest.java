package toby.redisson;

import static org.junit.Assert.assertTrue;

import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RAtomicLongRx;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;

import io.reactivex.Single;
import reactor.core.publisher.Mono;

class SimpleTest extends BaseTest {

	@Test
	void testSyncCas() throws InterruptedException, ExecutionException {

		// 同步的方式
		RAtomicLong atomicLong = redissonClient.getAtomicLong("aha");
		atomicLong.compareAndSet(0, 1);

		RFuture<Boolean> rfuture = atomicLong.compareAndSetAsync(1, 2);
		assertTrue(rfuture.get());
	}

	@Test
	void testReactiveCas() {

		RAtomicLongReactive longObject = redissonReactiveClient.getAtomicLong("mylong");
		// reactive way
		Mono<Boolean> result = longObject.compareAndSet(0, 5);
		result.doOnSuccess(re -> assertTrue(re));

		RAtomicLongRx longObject1 = redissonRxClient.getAtomicLong("myLong");
		// RxJava2 way
		Single<Boolean> result1 = longObject1.compareAndSet(5, 6);
		result1.doOnSuccess(re -> assertTrue(re));
	}

	private String distributeLockKey = "toby:distributelock";

	@Test
	void testLock() {

		RLock lock = redissonClient.getLock(distributeLockKey);
		try {

			lock.lock();
		} catch (Exception e) {

		} finally {

			lock.unlock();
		}
	}

	@Test
	void testLeaseTimeLock() {

		RLock lock = redissonClient.getLock(distributeLockKey);
		try {

			lock.lock(5, TimeUnit.SECONDS);
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}

		try {

			lock.tryLock(3, 10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {

			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	@Test
	void testLockAsync() {

		RLock lock = redissonClient.getLock(distributeLockKey);
		CompletionStage<Void> completeStage = null;
		try {

			RFuture<Void> rfuture = lock.lockAsync();
			completeStage = rfuture.whenCompleteAsync((v, e) -> {

				if (Objects.isNull(e)) {

					throw new RuntimeException(e);
				}

				assertTrue(true);
			});
		} finally {
			if (Objects.isNull(completeStage)) {
				lock.unlock();
			} else {
				completeStage.thenAccept(v -> lock.unlock());
			}
		}
	}

	@Test
	void testMultiLock() {

		RLock lock = redissonClient.getLock(distributeLockKey);
		RLock lock1 = redissonClient.getLock(distributeLockKey + "1");
		RLock lock2 = redissonClient.getLock(distributeLockKey + "2");

		RLock multiLock = redissonClient.getMultiLock(lock, lock1, lock2);
		try {

			multiLock.lock();
		} finally {

			multiLock.unlock();
		}
	}

	@Test
	void testRedLock() {
		// 没有多个redis实例，假装模拟一下
		RLock lock = redissonClient.getLock(distributeLockKey);
		RLock lock1 = redissonClient.getLock(distributeLockKey + "1");
		RLock lock2 = redissonClient.getLock(distributeLockKey + "2");

		RLock multiLock = redissonClient.getRedLock(lock, lock1, lock2);
		try {

			multiLock.lock();
		} finally {

			multiLock.unlock();
		}
	}

	private String countDownLatchKey = "toby:distributelock:countDownLatch";
	@Test
	void testCountDownLatch() throws InterruptedException {

		RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(countDownLatchKey);
		rCountDownLatch.trySetCount(3);
		rCountDownLatch.await();
		System.err.println("啊哈");
	}

	@Test
	@RepeatedTest(3)
	void testCountCountDownLatchInOtherThread() {

		RCountDownLatch rCountDownLatch = redissonClient.getCountDownLatch(countDownLatchKey);
		rCountDownLatch.countDown();
	}

}
