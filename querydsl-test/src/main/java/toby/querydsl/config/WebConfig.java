package toby.querydsl.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import toby.querydsl.config.interceptor.BizContextInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new BizContextInterceptor()).addPathPatterns("/**").excludePathPatterns("/error");
		super.addInterceptors(registry);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(
				jackson2ObjectMapperBuilder.build());
		List<MediaType> mediaTypeList = new ArrayList<>(2);
		mediaTypeList.add(MediaType.APPLICATION_JSON);
		mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypeList);

		converters.add(mappingJackson2HttpMessageConverter);
	}
}
