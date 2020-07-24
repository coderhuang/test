package toby.querydsl.util.json.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import toby.querydsl.domain.enums.base.StringBaseEnumInterface;

public class StringBaseEnumSerializer<T extends StringBaseEnumInterface> extends JsonSerializer<T> {

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		
		gen.writeString(value.getCode());
	}

}
