package toby.oidc.common.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public final class RsaKeyGenerator {

	private RsaKeyGenerator() {
	}

	public static Pair<PublicKey, PrivateKey> generatePairKey() throws NoSuchAlgorithmException {

		// 获取指定算法的密钥对生成器
		KeyPairGenerator gen;
		gen = KeyPairGenerator.getInstance("RSA");

		// 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
		gen.initialize(2048);

		// 随机生成一对密钥（包含公钥和私钥）
		KeyPair keyPair = gen.generateKeyPair();

		// 获取 公钥 和 私钥
		PublicKey pubKey = keyPair.getPublic();
		PrivateKey priKey = keyPair.getPrivate();

		return new ImmutablePair<>(pubKey, priKey);
	}
}
