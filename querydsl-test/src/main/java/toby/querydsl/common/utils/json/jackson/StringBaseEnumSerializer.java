package toby.querydsl.common.utils.json.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import toby.querydsl.common.enums.base.StringBaseEnumInterface;

public class StringBaseEnumSerializer<T extends StringBaseEnumInterface> extends JsonSerializer<T> {

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> handledType() {

		return (Class<T>) StringBaseEnumInterface.class;
	}

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		gen.writeString(value.getCode());
	}

}
