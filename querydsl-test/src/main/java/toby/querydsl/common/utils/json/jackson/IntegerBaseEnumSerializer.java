package toby.querydsl.common.utils.json.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import toby.querydsl.common.enums.base.IntegerBaseEnumInterface;

public class IntegerBaseEnumSerializer<T extends IntegerBaseEnumInterface> extends JsonSerializer<T> {

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> handledType() {
		
		return (Class<T>) IntegerBaseEnumInterface.class;
	}

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		gen.writeNumber(value.getCode());
	}

}
