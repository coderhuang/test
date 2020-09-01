package toby.jwt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

class AppleIdentifierTokenDecodeTest {

	private static String identifierToken;

	@BeforeAll
	static void init() {

		identifierToken = "eyJraWQiOiJBSURPUEsxIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmZ1bi5BcHBsZUxvZ2luIiwiZXhwI"
				+ "joxNTY4NzIxNzY5LCJpYXQiOjE1Njg3MjExNjksInN1YiI6IjAwMDU4MC4wODdjNTU0ZGNlMzU0NjZmYTg1YzVhNWQ1OTRkNTI4YS4wODAxIiwiY19oYXNoIjoiel9KY0RscFczQjJwN3"
				+ "ExR0Nna1JaUSIsImF1dGhfdGltZSI6MTU2ODcyMTE2OX0.WmSa4LzOzYsdwTqAJ_8mub4Ls3eyFkxZoGLoy-U7DatsTd_JEwAs3_OtV4ucmj6ENT3153iCpYY6vBxSQromOMcXsN74I"
				+ "rUQew24y_zflN2g4yU8ZVvBCbTrR_6p9f2fbeWjZiyNcbPCha0dv45E3vBjyHhmffWnk3vyndBBiwwuqod4pyCZ3UECf6Vu-o7dygKFpMHPS1ma60fEswY5d-_TJA"
				+ "Fk1HaiOfFo0XbL6kwqAGvx8HnraIxyd0n8SbBVxV_KDxf15hdotUizJDW7N2XMdOGQpNFJim9SrEeBhn9741LWqkWCgkobcvYBZsrvnUW6jZ87SLi15rvIpq8_fw";
	}

	@Test
	void decode() {

		DecodedJWT decodeJwt = JWT.decode(identifierToken);
		String kid = decodeJwt.getHeaderClaim("kid").asString();
		String appleUserId = decodeJwt.getSubject();

		System.err.println(kid);
		System.err.println(appleUserId);
		
		System.err.println(decodeJwt.getHeader());
		System.err.println(decodeJwt.getPayload());
		System.err.println(decodeJwt.getAlgorithm());
		
	}
}
