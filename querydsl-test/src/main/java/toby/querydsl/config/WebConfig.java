package toby.querydsl.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import toby.querydsl.common.enums.base.IntegerBaseEnumInterface;
import toby.querydsl.common.utils.converter.IntegerBaseEnumConverterFactory;
import toby.querydsl.common.utils.converter.StringBaseEnumConverterFactory;
import toby.querydsl.common.utils.converter.StringToIntegerBaseEnumConverterFactory;
import toby.querydsl.common.utils.converter.StringToLocalDateConverter;
import toby.querydsl.common.utils.converter.StringToLocalDateTimeConverter;
import toby.querydsl.common.utils.converter.StringToLocalTimeConverter;
import toby.querydsl.common.utils.json.jackson.IntegerBaseEnumDeserializer;
import toby.querydsl.common.utils.json.jackson.IntegerBaseEnumSerializer;
import toby.querydsl.common.utils.json.jackson.StringBaseEnumDeserializer;
import toby.querydsl.common.utils.json.jackson.StringBaseEnumSerializer;
import toby.querydsl.config.interceptor.BizContextInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("/index");// 默认视图跳转
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new BizContextInterceptor()).addPathPatterns("/**").excludePathPatterns("/error",
				"/resources");
		super.addInterceptors(registry);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		var simpleModule = new SimpleModule();
		simpleModule.addSerializer(new IntegerBaseEnumSerializer<>());
		simpleModule.addSerializer(new StringBaseEnumSerializer<>());
		simpleModule.addDeserializer(IntegerBaseEnumInterface.class, new IntegerBaseEnumDeserializer<>());
		simpleModule.addDeserializer(StringBaseEnumSerializer.class, new StringBaseEnumDeserializer<>());

		ObjectMapper objectMapper = jackson2ObjectMapperBuilder.build();
		objectMapper.registerModule(simpleModule);

		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(
				objectMapper);
		List<MediaType> mediaTypeList = new ArrayList<>(2);
		mediaTypeList.add(MediaType.APPLICATION_JSON);
		mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypeList);

		converters.add(mappingJackson2HttpMessageConverter);
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {

		registry.addConverter(new StringToLocalDateTimeConverter());
		registry.addConverter(new StringToLocalDateConverter());
		registry.addConverter(new StringToLocalTimeConverter());

		registry.addConverterFactory(new IntegerBaseEnumConverterFactory());
		registry.addConverterFactory(new StringBaseEnumConverterFactory());
		registry.addConverterFactory(new StringToIntegerBaseEnumConverterFactory());
	}
}
