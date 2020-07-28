package toby.querydsl.common.enums.base;

import org.apache.commons.lang.ArrayUtils;

public interface StringBaseEnumInterface {

	/**
	 * 获取枚举的存储值
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * 获取枚举的标记字符串
	 * 
	 * @return
	 */
	String getLabel();
	
	@SuppressWarnings("unchecked")
	static <T extends StringBaseEnumInterface> T getEnum(Class<T> enumClass, String value) {

		if (!StringBaseEnumInterface.class.isAssignableFrom(enumClass)) {
			throw new IllegalArgumentException("参数非法");
		}

		StringBaseEnumInterface[] enumsConstants = (StringBaseEnumInterface[]) enumClass.getEnumConstants();
		if (ArrayUtils.isEmpty(enumsConstants)) {
			throw new IllegalArgumentException("参数非法");
		}

		for (StringBaseEnumInterface integerBaseEnumInterface : enumsConstants) {
			
			if (integerBaseEnumInterface.getCode().equals(value)) {
				return (T) integerBaseEnumInterface;
			}
		}

		throw new IllegalArgumentException("参数非法");
	}
}
