package toby.querydsl.common.utils.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import toby.querydsl.common.enums.base.IntegerBaseEnumInterface;

public class IntegerBaseEnumConverterFactory implements ConverterFactory<Integer, IntegerBaseEnumInterface> {
	
	@Override
	public <T extends IntegerBaseEnumInterface> Converter<Integer, T> getConverter(Class<T> targetType) {
		
		return new IntegerToIntegerBaseEnumConverter<>(targetType);
	}

}
