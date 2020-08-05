package toby.oidc.common.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Objects;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.auth0.jwt.algorithms.Algorithm;

public final class AlgorithmUtil {

	private AlgorithmUtil() {
	}

	public static Pair<PublicKey, PrivateKey> generateRsaPairKey() {

		// 获取指定算法的密钥对生成器
		KeyPairGenerator gen;
		try {
			gen = KeyPairGenerator.getInstance("RSA");
			// 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
			gen.initialize(2048);

			// 随机生成一对密钥（包含公钥和私钥）
			KeyPair keyPair = gen.generateKeyPair();

			// 获取 公钥 和 私钥
			PublicKey pubKey = keyPair.getPublic();
			PrivateKey priKey = keyPair.getPrivate();

			return new ImmutablePair<>(pubKey, priKey);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static void saveRSAPairKey(PublicKey pubKey, PrivateKey priKey) throws IOException {

		String publicKeyString = Base64.getEncoder().encodeToString(pubKey.getEncoded());
		String privateKeyStrig = Base64.getEncoder().encodeToString(priKey.getEncoded());

		Files.writeString(Paths.get("src/main/resource/key/id_rsa.pub"), publicKeyString, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
		Files.writeString(Paths.get("src/main/resource/key/id_rsa"), privateKeyStrig, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}

	public static Algorithm rsaAlgorithm() {

		Pair<PublicKey, PrivateKey> keyPair = generateRsaPairKey();
		Objects.requireNonNull(keyPair);

		RSAPublicKey rsaPubKey = (RSAPublicKey) keyPair.getLeft();
		RSAPrivateKey rsaPriKey = (RSAPrivateKey) keyPair.getRight();
		Algorithm rsaAlgorithm = Algorithm.RSA512(rsaPubKey, rsaPriKey);

		return rsaAlgorithm;
	}

	public static Algorithm hmacAlgorithm() {

		try {
			return Algorithm.HMAC512("1234567890");
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			return null;
		}
	}

}
