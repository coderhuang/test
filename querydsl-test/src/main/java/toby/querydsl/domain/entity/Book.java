package toby.querydsl.domain.entity;

import javax.annotation.Generated;

import toby.querydsl.domain.enums.BookCategory;

/**
 * Book is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class Book {

	private String author;

	private BookCategory category;

	private java.time.LocalDateTime createTime;

	private Long id;

	private String name;

	private Integer flagBit;
	
	private Long skuCode;

	private java.math.BigDecimal price;

	private java.time.LocalDateTime updateTime;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlagBit() {
		return flagBit;
	}

	public void setFlagBit(Integer flagBit) {
		this.flagBit = flagBit;
	}

	public java.math.BigDecimal getPrice() {
		return price;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public java.time.LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.time.LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Long getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(Long skuCode) {
		this.skuCode = skuCode;
	}

	@Override
	public String toString() {
		return "author = " + author + ", category = " + category.getLabel() + ", createTime = " + createTime + ", id = "
				+ id + ", skuCode = " + skuCode + ", name = " + name + ", price = " + price + ", updateTime = "
				+ updateTime + ", flagBit = " + flagBit;
	}

}
