package toby.querydsl.domain.enums;

import toby.querydsl.domain.enums.base.IntegerBaseEnumInterface;

public enum BookCategory implements IntegerBaseEnumInterface {

	UNKOWN(0, "目前未知"),

	SICENCE(1, "科学"),

	IT_TECHNOLOGY(2, "IT技术");

	private BookCategory(Integer code, String label) {
		this.code = code;
		this.label = label;
	}

	private Integer code;

	private String label;

	@Override
	public Integer getCode() {

		return code;
	}

	@Override
	public String getLabel() {

		return label;
	}

}
