package toby.querydsl.common.utils.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import toby.querydsl.common.enums.base.StringBaseEnumInterface;

public class StringBaseEnumConverterFactory implements ConverterFactory<String, StringBaseEnumInterface> {

	@Override
	public <T extends StringBaseEnumInterface> Converter<String, T> getConverter(Class<T> targetType) {
		
		return new StringToStringBaseEnumConverter<>(targetType);
	}

}
