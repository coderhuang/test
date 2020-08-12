package toby.jwt.common.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public final class HMACUtil {

	private HMACUtil() {
	}

	// 获取 HMAC Key
	public static byte[] getHmacKey(String type) {

		try {
			// 1、创建密钥生成器
			KeyGenerator keyGenerator = KeyGenerator.getInstance(type);
			// 2、产生密钥
			SecretKey secretKey = keyGenerator.generateKey();
			// 3、获取密钥
			byte[] key = secretKey.getEncoded();

			return key;
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}

	// 获取 HmacMD5 Key
	public static byte[] getHmacMd5Key() {
		return getHmacKey("HmacMD5");
	}

	// 获取 HmacSHA256
	public static byte[] getHmacSha256Key() {

		return getHmacKey("HmacSHA256");
	}

	// 获取 HmacSHA512
	public static byte[] getHmacSha512Key() {
		return getHmacKey("HmacSHA512");
	}

	// HMAC MD5 加密
	public static String encryptHmacMD5(byte[] data, byte[] key) {
		return encryptHmac(data, key, "HmacMD5");
	}

	// HMAC SHA256 加密
	public static String encryptHmacSHA256(byte[] data, byte[] key) {
		return encryptHmac(data, key, "HmacSHA256");
	}

	// HMAC SHA521 加密
	public static String encryptHmacSHA512(byte[] data, byte[] key) {
		return encryptHmac(data, key, "HmacSHA512");
	}

	// 基础MAC 算法
	public static String encryptHmac(byte[] data, byte[] key, String type) {

		try {
			// 1、还原密钥
			SecretKey secretKey = new SecretKeySpec(key, type);
			// 2、创建MAC对象
			Mac mac = Mac.getInstance(type);
			// 3、设置密钥
			mac.init(secretKey);
			// 4、数据加密
			byte[] bytes = mac.doFinal(data);
			// 5、生成数据
			String rs = BytesUtil.bytes2Hex(bytes).toUpperCase();
			
			return rs;
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}
	}


}
