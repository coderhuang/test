package toby.jwt.config.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.Claim;

import toby.jwt.common.enums.BizContext;
import toby.jwt.common.enums.user.UserBizContextConstant;
import toby.jwt.common.enums.user.UserHttpConstant;
import toby.jwt.common.utils.JwtUtil;
import toby.jwt.helper.UserRedisBizHelper;

@WebFilter(filterName = "JWTFilter", urlPatterns = "/*")
public class JWTFilter implements Filter {

	private UserRedisBizHelper userRedisBizHelper;

	private AnnotationConfigServletWebServerApplicationContext wac;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		wac = (AnnotationConfigServletWebServerApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		if (wac != null && userRedisBizHelper == null)
			userRedisBizHelper = wac.getBean(UserRedisBizHelper.class);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		BizContext.INSTANCE.clear();
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		response.setCharacterEncoding("UTF-8");
		if ("OPTIONS".equals(request.getMethod())) {

			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
			return;
		}
		// Except OPTIONS, other request should be checked by JWT
		// 获取 header里的token
		final String token = request.getHeader(UserHttpConstant.AUTHORITY_TOKEN_HEADER_KEY.value());

		if (token == null) {

//				response.getWriter().write("没有token！");
			chain.doFilter(req, res);
			return;
		}

		Map<String, Claim> userData;
		try {

			userData = JwtUtil.verifyToken(token);
		} catch (TokenExpiredException e) {

			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			response.getWriter().write("用户登录态已过期");
			return;
		}

		if (userData == null) {

			response.getWriter().write("token不合法！");
			return;
		}

		String name = userData.get("name").asString();
		String redisKeyUserSuffix = userData.get(PublicClaims.SUBJECT).asString();
		String jwtId = userData.get(PublicClaims.JWT_ID).asString();

		BizContext.INSTANCE.setValue(UserBizContextConstant.KEY_NAME.value(), name);
		BizContext.INSTANCE.setValue(UserBizContextConstant.KEY_REDIS_USER_SUFFIX.value(), redisKeyUserSuffix);
		BizContext.INSTANCE.setValue(UserBizContextConstant.KEY_JWT_ID.value(), jwtId);

		chain.doFilter(req, res);

		BizContext.INSTANCE.clear();
	}

}
