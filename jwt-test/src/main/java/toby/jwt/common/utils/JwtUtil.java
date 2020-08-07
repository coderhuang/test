package toby.jwt.common.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import toby.jwt.domain.User;

public class JwtUtil {

	private JwtUtil() {
	}

	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	private static final String SECRET = "1234567890";

	private static final long EXPIRATION = 1800;

	public static String createToken(String userRedisKeySuffix, User user) {

		Map<String, Object> headerMap = new HashMap<>(4);
		headerMap.put("alg", "HS512");
		headerMap.put("type", "JWT");

		return JWT.create().withHeader(headerMap).withIssuer("https://account.mysite.com").withAudience("123456")
				.withIssuedAt(LocalDateTimeUtil.asDate(LocalDateTime.now())).withSubject(userRedisKeySuffix)
				.withExpiresAt(LocalDateTimeUtil.asDate(LocalDateTime.now().plusSeconds(EXPIRATION)))
				.withClaim("id", user.getId()).withClaim("name", user.getName()).sign(Algorithm.HMAC512(SECRET));
	}

	public static Map<String, Claim> verifyToken(String token) {

		return JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token).getClaims();
	}

}
