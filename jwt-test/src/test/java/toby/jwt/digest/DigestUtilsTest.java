package toby.jwt.digest;

import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class DigestUtilsTest {

	@Test
	void sha1Test() {

		String message = "phoneNo13312345678userCode512345678901234createTime202008201202";
		String encryptedMessage = DigestUtils.sha1Hex(message);
		assertTrue(StringUtils.isNotEmpty(encryptedMessage));
		System.err.println(encryptedMessage);
	}

	@Test
	void sha256Test() {

		String message = "phoneNo13312345678userCode512345678901234createTime202008201202";
		String encryptedMessage = DigestUtils.sha256Hex(message);
		assertTrue(StringUtils.isNotEmpty(encryptedMessage));
		System.err.println(encryptedMessage);
	}

	@Test
	void sha3_224HexTest() {

		String message = "123456userNametomsonsessionIdAAAAage24methoduser.createsexmaleformatjsonlocalezh_CN123456";
		String encryptedMessage = DigestUtils.sha3_224Hex(message);
		assertTrue(StringUtils.isNotEmpty(encryptedMessage));
		System.err.println(encryptedMessage);
	}

	@Test
	void sha3_256HexTest() {

		String message = "123456userNametomsonsessionIdAAAAage24methoduser.createsexmaleformatjsonlocalezh_CN123456";
		String encryptedMessage = DigestUtils.sha3_256Hex(message);
		assertTrue(StringUtils.isNotEmpty(encryptedMessage));
		System.err.println(encryptedMessage);
	}

	/**
	 * SHAd实现, 参考↓ 
	 * https://zh.wikipedia.org/wiki/SHA%E5%AE%B6%E6%97%8F
	 * 
	 */
	@Test
	void sha3_256DHexTest() {

		String message = "123456userNametomsonsessionIdAAAAage24methoduser.createsexmaleformatjsonlocalezh_CN123456";
		byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
		String encryptedMessage = DigestUtils.sha3_256Hex(DigestUtils.sha3_256(messageBytes));
		assertTrue(StringUtils.isNotEmpty(encryptedMessage));
		System.err.println(encryptedMessage);
	}

}
