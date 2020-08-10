package toby.jwt.common.utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import toby.jwt.domain.User;

public class JwtUtil {

	private JwtUtil() {
	}

//	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	private static final String SECRET = "1234567890";

	public static String createUserInfoToken(User user, String uuid, long expiration) {

		Map<String, Object> headerMap = createJwtHeaderMap();

		return JWT.create().withHeader(headerMap).withIssuer("https://account.mysite.com").withAudience("123456")
				.withIssuedAt(LocalDateTimeUtil.asDate(LocalDateTime.now())).withSubject(user.getCode())
				.withExpiresAt(LocalDateTimeUtil.asDate(LocalDateTime.now().plusSeconds(expiration))).withJWTId(uuid)
				.withClaim("name", user.getName()).sign(Algorithm.HMAC512(SECRET));
	}

	public static String createUserRefreshToken(String refreshUuid, long expiration) {

		Map<String, Object> headerMap = createJwtHeaderMap();

		return JWT.create().withHeader(headerMap).withIssuer("https://account.mysite.com").withAudience("123456")
				.withIssuedAt(LocalDateTimeUtil.asDate(LocalDateTime.now())).withSubject(refreshUuid)
				.withExpiresAt(LocalDateTimeUtil.asDate(LocalDateTime.now().plusSeconds(expiration)))
				.sign(Algorithm.HMAC512(SECRET));
	}

	public static Map<String, Object> createJwtHeaderMap() {

		Map<String, Object> headerMap = new HashMap<>(4);
		headerMap.put("alg", "HS512");
		headerMap.put("type", "JWT");

		return headerMap;
	}

	public static Map<String, Claim> verifyToken(String token) {

		return JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token).getClaims();
	}

}
