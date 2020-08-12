package toby.jwt.common.utils;

import java.util.Base64;

public final class BytesUtil {

	private BytesUtil() {
	}

	public static byte[] base64String2Bytes(String base64String) {
		
		return Base64.getUrlDecoder().decode(hexStringToBytes(base64String));
	}

	public static String bytesToBase64Hex(byte[] image) {

		return BytesUtil.bytes2Hex(Base64.getUrlEncoder().encode(image)).toUpperCase();
	}

	public static String bytes2Hex(byte[] bytes) {

		StringBuilder sb = new StringBuilder();
		String tmp = null;
		for (byte b : bytes) {

			tmp = Integer.toHexString(0xFF & b);
			if (tmp.length() == 1) {

				tmp = "0" + tmp;
			}
			sb.append(tmp);
		}

		return sb.toString();
	}

	public static byte[] hexStringToBytes(String hexString) {

		String valueString = "0123456789abcdef";
		hexString = hexString.toLowerCase();

		byte[] bytes = new byte[hexString.length() >> 1];
		for (int i = 0, j = 0; i < hexString.length() && j < bytes.length; i++, j++) {

			String s = hexString.substring(i, i + 2);
			int higherBitsVal = (0x0F & valueString.indexOf(s.charAt(0))) << 4;
			int lowerBitsVal = (0x0F & valueString.indexOf(s.charAt(1)));
			byte b = (byte) (higherBitsVal | lowerBitsVal);
			bytes[j] = b;
		}

		return bytes;
	}
}
