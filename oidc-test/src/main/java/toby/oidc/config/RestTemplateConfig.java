package toby.oidc.config;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
//@ConditionalOnClass(HttpProperties.class)
@EnableConfigurationProperties(HttpProperties.class)
public class RestTemplateConfig {

	@Bean
	@ConditionalOnMissingBean(RestTemplate.class)
	public RestTemplate restTemplate4Core(HttpProperties httpProperties, ObjectMapper objectMapper) {

		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
		// 由RestTemplate初始化源码可知converters不可能为null和空，不校验空了
		for (int i = 0; i < converters.size(); i++) {
			
			HttpMessageConverter<?> converter = converters.get(i);
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				// 使用应用的jackson配置
				converters.set(i, jackson2HttpMessageConverter);
			} else if (converter instanceof StringHttpMessageConverter) {
				// 使用应用的字符配置
				((StringHttpMessageConverter) converter).setDefaultCharset(httpProperties.getEncoding().getCharset());
			}
		}
		
		return restTemplate;
	}
}
