package toby.querydsl.domain.entity;

import javax.annotation.Generated;

/**
 * Book is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class Book {

	private String author;

	private toby.querydsl.domain.enums.BookCategory category;

	private java.time.LocalDateTime createTime;

	private Integer flagBit;

	private Long id;

	private String name;

	private java.math.BigDecimal price;

	private Long skuCode;

	private java.time.LocalDateTime updateTime;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public toby.querydsl.domain.enums.BookCategory getCategory() {
		return category;
	}

	public void setCategory(toby.querydsl.domain.enums.BookCategory category) {
		this.category = category;
	}

	public java.time.LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.time.LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Integer getFlagBit() {
		return flagBit;
	}

	public void setFlagBit(Integer flagBit) {
		this.flagBit = flagBit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public Long getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(Long skuCode) {
		this.skuCode = skuCode;
	}

	public java.time.LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.time.LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "author = " + author + ", category = " + category + ", createTime = " + createTime + ", flagBit = "
				+ flagBit + ", id = " + id + ", name = " + name + ", price = " + price + ", skuCode = " + skuCode
				+ ", updateTime = " + updateTime;
	}

}
