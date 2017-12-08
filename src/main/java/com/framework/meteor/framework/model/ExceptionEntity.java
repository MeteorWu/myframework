package com.framework.meteor.framework.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "system_exception")
public class ExceptionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11)")
    private Long exceptionId;//主键ID

    @Column(length = 10)
    private String status;

    @Column(length = 500)
    private String message;
    private String timestamp;
    private String error;
    private String path;

    @Column(length = 32)
    private String userId;

    @Column(length = 10)
    private String clientType;

    @Column(length = 10)
    private String version;

    public ExceptionEntity() {
    }

    public ExceptionEntity(String status, String message, String timestamp, String error, String path, String userId, String clientType, String version) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
        this.userId = userId;
        this.clientType = clientType;
        this.version = version;
    }

    public Long getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Long exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
