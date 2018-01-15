package com.framework.meteor.work.user.model;

import com.framework.meteor.framework.model.BaseModel;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

/**
 * 用户对象
 *
 * @author Meteor.wu
 * @since 2017/11/29 15:25
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseModel {
    @Id
    @Column(length = 32)
    private String userId;

    @Column(length = 32)
    private String username;

    @Column(length = 32)
    private String password;

    @Column(length = 32)
    private Integer sex;

    @Column(length = 32)
    private String phone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
