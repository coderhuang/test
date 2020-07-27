package toby.querydsl.utils;

public class BitOperationUtil {

	private BitOperationUtil() {
	}

	public static final int BASE = 1;

	public static boolean containVal(int flagBit, int val) {

		return (flagBit & val) == val;
	}
	
	public static boolean containValOfBitPostion(int flagBit, short position) {
		
		return containVal(flagBit, valOfBitPosition(position));
	}
	
	public static boolean containValOfBitIndex(int flagBit, short index) {
		
		return containVal(flagBit, valOfBitIndex(index));
	}

	public static int valOfBitPosition(short position) {

		return position == 1 ? BASE : BASE << (position - 1);
	}
	
	public static int valOfBitIndex(short index) {
		
		return index == 0 ? BASE : BASE << index;
	}
}
