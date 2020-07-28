package toby.querydsl.common.utils.converter;

import java.time.LocalTime;

import org.springframework.core.convert.converter.Converter;

public class StringToLocalTimeConverter implements Converter<String, LocalTime> {

	@Override
	public LocalTime convert(String source) {
		
		return LocalTime.parse(source);
	}

}
