package com.framework.meteor.framework.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * http请求异常
 * ClassName  : HttpException
 * Author     : meteor.jacky.wu
 * CreateDate : 2019:11:15 11:50
 * Version    : 1.0
 * Company    : D球
 */
@Data
@Entity
public class HttpException extends BaseModel{
    @Id
    @Column(length = 32)
    private String userId;

    @Column(length = 128)
    private String url = "";

    @Column(length = 256)
    private String params = "";

    @Column(length = 128)
    private String message = "";

    @Column(length = 64)
    private String error = "";

    public HttpException() {
    }

    public HttpException(String url, String params, String message, String error) {
        this.url = url;
        this.params = params;
        this.message = message;
        this.error = error;
    }

}
