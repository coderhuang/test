package toby.oidc.authen.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import toby.oidc.common.utils.ObjectUtil;

@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		logger.info("登录失败");
		// 设置状态码
		response.setStatus(500);
		response.setContentType("application/json;charset=UTF-8");
		// 将 登录失败 信息打包成json格式返回
		response.getWriter().write(ObjectUtil.toJSONString(new ImmutablePair<String, String>("登录失败", "static")));
	}

}
