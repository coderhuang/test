package toby.querydsl.common.utils.converter;

import org.springframework.core.convert.converter.Converter;

import toby.querydsl.common.enums.base.StringBaseEnumInterface;

public class StringToStringBaseEnumConverter<T extends StringBaseEnumInterface> implements Converter<String, T> {

	private Class<T> clazz;

	public StringToStringBaseEnumConverter(Class<T> clazz) {

		this.clazz = clazz;
	}

	@Override
	public T convert(String source) {

		return StringBaseEnumInterface.getEnum(clazz, source);
	}

}
