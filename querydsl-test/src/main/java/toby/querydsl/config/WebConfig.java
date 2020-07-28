package toby.querydsl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import toby.querydsl.config.interceptor.BizContextInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new BizContextInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
