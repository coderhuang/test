package toby.querydsl.domain.entity;

import javax.annotation.Generated;

/**
 * SkuProperty is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class SkuProperty {

	private java.time.LocalDateTime createTime;

	private Long id;

	private Long skuCode;

	private String skuName;

	private java.time.LocalDateTime updateTime;

	public java.time.LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.time.LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(Long skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public java.time.LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.time.LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "createTime = " + createTime + ", id = " + id + ", skuCode = " + skuCode + ", skuName = " + skuName
				+ ", updateTime = " + updateTime;
	}

}
