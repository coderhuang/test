package toby.jwt.common.vo;

import java.io.Serializable;

public class VerifyVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5700019691675416283L;

	private String signature;
	
	private String payload;

	public VerifyVo() {
	}

	public VerifyVo(String signature, String payload) {
		super();
		this.signature = signature;
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "VerifyVo [sign=" + signature + ", payload=" + payload + "]";
	}

	public String getSignature() {
		return signature;
	}


	public void setSignature(String signature) {
		this.signature = signature;
	}


	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
}
