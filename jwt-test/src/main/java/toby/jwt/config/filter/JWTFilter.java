package toby.jwt.config.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.impl.PublicClaims;
import com.auth0.jwt.interfaces.Claim;

import toby.jwt.common.enums.BizContext;
import toby.jwt.common.utils.JwtUtil;

@WebFilter(filterName = "JWTFilter", urlPatterns = "/*")
public class JWTFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		BizContext.INSTANCE.clear();
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		response.setCharacterEncoding("UTF-8");
		// 获取 header里的token
		final String token = request.getHeader("authorization");

		if ("OPTIONS".equals(request.getMethod())) {

			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
			return;
		}
		// Except OPTIONS, other request should be checked by JWT

		if (token == null) {

//				response.getWriter().write("没有token！");
			chain.doFilter(req, res);
			return;
		}

		Map<String, Claim> userData = JwtUtil.verifyToken(token);
		if (userData == null) {

			response.getWriter().write("token不合法！");
			return;
		}

		Integer id = userData.get("id").asInt();
		String name = userData.get("name").asString();
		String redisKeyUserSuffix = userData.get(PublicClaims.SUBJECT).asString();

		BizContext.INSTANCE.setValue("id", id);
		BizContext.INSTANCE.setValue("name", name);
		BizContext.INSTANCE.setValue("redisKeyUserSuffix", redisKeyUserSuffix);

		chain.doFilter(req, res);

		BizContext.INSTANCE.clear();

	}

}
