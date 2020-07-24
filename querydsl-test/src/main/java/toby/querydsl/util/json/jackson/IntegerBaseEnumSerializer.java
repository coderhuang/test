package toby.querydsl.util.json.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import toby.querydsl.domain.enums.base.IntegerBaseEnumInterface;

public class IntegerBaseEnumSerializer<T extends IntegerBaseEnumInterface> extends JsonSerializer<T> {

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		
		gen.writeNumber(value.getCode());
	}

}
