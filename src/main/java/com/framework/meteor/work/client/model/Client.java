package com.framework.meteor.work.client.model;

import com.framework.meteor.framework.model.BaseModel;

import javax.persistence.*;

/**
 * 客户模块
 *
 * @author Meteor.wu
 * @since 2017/12/4 16:44
 */
@Entity
public class Client extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long clientId;

    @Column(length = 32)
    private String clientName;

    @Column(length = 32)
    private String phone;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
