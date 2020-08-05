package toby.oidc.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class ObjectUtil {

	private ObjectUtil() {
	}

	private static final ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.registerModule(new JavaTimeModule());
	}

	/**
	 * 转换json字符串
	 * 
	 * @param source
	 * @return
	 */
	public static String toJSONString(Object source) {

		try {

			return MAPPER.writeValueAsString(source);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
}
