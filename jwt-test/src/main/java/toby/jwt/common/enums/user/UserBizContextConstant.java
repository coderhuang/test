package toby.jwt.common.enums.user;

import toby.jwt.common.enums.IEnumStringValue;

public enum UserBizContextConstant implements IEnumStringValue {

	KEY_ID("id"),

	KEY_NAME("name"),

	KEY_REDIS_USER_SUFFIX("KeyUserSuffix"),

	KEY_REDIS_FRESH_TOKEN_SUFFIX("KeyUserRefreshTokenSuffix"),
	
	KEY_JWT_ID("jwtId");

	private String value;

	private UserBizContextConstant(String value) {

		this.value = value;
	}

	@Override
	public String value() {

		return value;
	}

}
