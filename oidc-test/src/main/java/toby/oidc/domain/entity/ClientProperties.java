package toby.oidc.domain.entity;

import javax.annotation.Generated;
import toby.oidc.domain.base.IdAssignEntity;

/**
 * ClientProperties is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class ClientProperties implements IdAssignEntity<Long> {

    private Long clientId;

    private String clientSecret;

    private java.time.LocalDateTime createTime;

    private Long id;

    private java.time.LocalDateTime updateTime;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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

    public java.time.LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.time.LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
         return "clientId = " + clientId + ", clientSecret = " + clientSecret + ", createTime = " + createTime + ", id = " + id + ", updateTime = " + updateTime;
    }

}

