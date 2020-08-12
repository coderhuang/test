package toby.jwt.common.dto;

import java.io.Serializable;
import java.util.Arrays;

public class VerifyImageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3276079596437774496L;

	private byte[] image;
	
	private String randomString;

	@Override
	public String toString() {
		return "VerifyImageVo [image=" + Arrays.toString(image) + ", randomString=" + randomString + "]";
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getRandomString() {
		return randomString;
	}

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}
}
