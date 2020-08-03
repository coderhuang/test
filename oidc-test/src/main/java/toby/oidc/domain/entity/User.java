package toby.oidc.domain.entity;

import javax.annotation.Generated;
import toby.oidc.domain.base.IdAssignEntity;

/**
 * User is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class User implements IdAssignEntity<Long> {

    private java.time.LocalDateTime createTime;

    private Long id;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.time.LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.time.LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
         return "createTime = " + createTime + ", id = " + id + ", name = " + name + ", updateTime = " + updateTime;
    }

}

