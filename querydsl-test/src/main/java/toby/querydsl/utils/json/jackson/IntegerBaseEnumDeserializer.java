package toby.querydsl.utils.json.jackson;

import java.io.IOException;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import toby.querydsl.domain.enums.base.IntegerBaseEnumInterface;

public class IntegerBaseEnumDeserializer<T extends IntegerBaseEnumInterface> extends JsonDeserializer<T> {

	@Override
	public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		String currentName = jp.getCurrentName();
        Object currentValue = jp.getCurrentValue();
        @SuppressWarnings("unchecked")
		Class<T> findPropertyType = (Class<T>) BeanUtils.findPropertyType(currentName, currentValue.getClass());
        
        return IntegerBaseEnumInterface.getEnum(findPropertyType, jp.getIntValue());
	}

}
