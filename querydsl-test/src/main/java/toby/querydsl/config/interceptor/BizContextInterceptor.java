package toby.querydsl.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import toby.querydsl.common.constants.BizContextConstant;
import toby.querydsl.common.enums.BizContext;

public class BizContextInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("业务上下文-拦截器preHandle：begin");
		// 线程池复用的情况下,在本次请求的之前执行过程中,可能异常退出导致没有清空原有请求的上下文的情况,所以方法执行前,先清空一次上下文
		BizContext.INSTANCE.clear();

		String userCode = request.getHeader(BizContextConstant.USER_CODE_HEADER_NAME);
		String userName = request.getHeader(BizContextConstant.USER_NAME_HEADER_NAME);

		BizContext.INSTANCE.setValue("userName", userName);
		BizContext.INSTANCE.setValue("userCode", userCode);

//		if (HandlerMethod.class.equals(handler.getClass())) {
//
//			HandlerMethod method = (HandlerMethod) handler;
//			Object controller = method.getBean();
//		}

		logger.info("业务上下文-拦截器preHandle：end");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

		logger.info("业务上下文-拦截器postHandle：begin");

		logger.info("业务上下文-拦截器postHandle：end");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {

		BizContext.INSTANCE.clear();
		logger.info("业务上下文-拦截器afterCompletion：啊哈哈哈");
	}
}
