package toby.jwt.common.utils;

import java.nio.ByteBuffer;
import java.util.Base64;

public final class BytesUtil {

	private BytesUtil() {
	}

	public static byte[] intConvert2Bytes(int i) {

		int intByteLength = Integer.BYTES;
		int byteSize = Byte.SIZE;
		byte[] bytes = new byte[intByteLength];
		for (int tempI = intByteLength; tempI > 0; tempI--) {

			int rightShiftBits = (intByteLength - tempI) * byteSize;
			bytes[tempI - 1] = (byte) (0xFF & (i >>> rightShiftBits));
		}

		return bytes;
	}

	public static int bytesConvert2Int(byte[] bytes) {

		int intByteLength = Integer.BYTES;
		int byteSize = Byte.SIZE;
		int returnI = 0;
		for (int i = intByteLength - 1; i >= 0; i--) {

			int leftShiftSize = i * byteSize;
			returnI |= bytes[i] << leftShiftSize;
		}

		return returnI;
	}

	public static byte[] intConvert2Bytes1(int i) {

		ByteBuffer bb = ByteBuffer.allocate(Integer.BYTES).putInt(i);
		return bb.array();
	}

	public static int bytesConvert2Int1(byte[] bytes) {

		ByteBuffer bb = ByteBuffer.wrap(bytes);
		return bb.getInt();
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
