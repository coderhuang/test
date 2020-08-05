package toby.jwt.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7817610319719737713L;

	private Long id;

	private String name;

	private String password;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

}
