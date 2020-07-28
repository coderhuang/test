package toby.querydsl.common.utils.converter;

import org.springframework.core.convert.converter.Converter;

import toby.querydsl.common.enums.base.IntegerBaseEnumInterface;

public class IntegerToIntegerBaseEnumConverter<T extends IntegerBaseEnumInterface> implements Converter<Integer, T> {

	private Class<T> clazz;

	public IntegerToIntegerBaseEnumConverter(Class<T> clazz) {

		this.clazz = clazz;
	}

	@Override
	public T convert(Integer source) {

		return IntegerBaseEnumInterface.getEnum(clazz, source);
	}

}
