package toby.querydsl.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import toby.querydsl.common.enums.BizContext;

public class BizContextInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("业务上下文-拦截器preHandle：begin");
		
		BizContext.INSTANCE.clear();

		String userName = request.getHeader("x-username");
		String userCode = request.getHeader("x-usercode");

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
		
		BizContext.INSTANCE.clear();
		logger.info("业务上下文-拦截器postHandle：end");
	}
}
