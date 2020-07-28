package toby.querydsl.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import toby.querydsl.common.enums.BizContext;

public class BizContextInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("业务上下文拦截器：begin");

		String userName = request.getHeader("x-username");
		String userCode = request.getHeader("x-usercode");

		BizContext.INSTANCE.setValue("userName", userName);
		BizContext.INSTANCE.setValue("userCode", userCode);
		
//		if (HandlerMethod.class.equals(handler.getClass())) {
//
//			HandlerMethod method = (HandlerMethod) handler;
//			Object controller = method.getBean();
//		}

		logger.info("业务上下文拦截器：end");

		return true;
	}
}
