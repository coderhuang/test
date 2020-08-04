package toby.oidc.common.utils;

import com.auth0.jwt.algorithms.Algorithm;

public final class JwtUtil {

	private JwtUtil() {
	}

	public static final Algorithm RSA_ALGORITHM = AlgorithmUtil.rsaAlgorithm();

	public static final Algorithm HMA_ALGORITHM = AlgorithmUtil.hmacAlgorithm();
	
	public static final String ISSUER_IDENTIFIER = "https://account.sisyphe.com.cn";
	
	
//	public static String creatJwtToken(String accountCode) {
//		
//		String token = 
//	}

}
