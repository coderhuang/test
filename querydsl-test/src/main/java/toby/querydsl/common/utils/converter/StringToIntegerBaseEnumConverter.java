package toby.querydsl.common.utils.converter;

import org.springframework.core.convert.converter.Converter;

import toby.querydsl.common.enums.base.IntegerBaseEnumInterface;

public class StringToIntegerBaseEnumConverter<T extends IntegerBaseEnumInterface> implements Converter<String, T> {

	private Class<T> clazz;
	
	public StringToIntegerBaseEnumConverter(Class<T> clazz) {
		
		this.clazz = clazz;
	}
	
	@Override
	public T convert(String source) {
		
		Integer enumCodeVal = Integer.valueOf(source);
		return IntegerBaseEnumInterface.getEnum(clazz, enumCodeVal);
	}

}
