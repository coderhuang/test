package toby.querydsl.common.utils.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import toby.querydsl.common.enums.base.IntegerBaseEnumInterface;

public class StringToIntegerBaseEnumConverterFactory implements ConverterFactory<String, IntegerBaseEnumInterface> {

	@Override
	public <T extends IntegerBaseEnumInterface> Converter<String, T> getConverter(Class<T> targetType) {
		
		return new StringToIntegerBaseEnumConverter<>(targetType);
	}

}
