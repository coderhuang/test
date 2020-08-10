package toby.jwt.common.enums.user;

import toby.jwt.common.enums.IEnumStringValue;

public enum UserHttpConstant implements IEnumStringValue {

	AUTHORITY_TOKEN_HEADER_KEY("authorization"),

	REFRESH_TOKEN_PARAMETER_KEY("refresh_token");

	private String value;

	private UserHttpConstant(String value) {

		this.value = value;
	}

	@Override
	public String value() {

		return value;
	}

}
