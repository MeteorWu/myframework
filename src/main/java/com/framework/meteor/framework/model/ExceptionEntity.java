package com.framework.meteor.framework.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
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

    @Column(length = 100)
    private String method;

    public ExceptionEntity() {
    }

    public ExceptionEntity(String status, String message, String timestamp, String error, String path, String userId, String clientType, String version, String method) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
        this.userId = userId;
        this.clientType = clientType;
        this.version = version;
        this.method = method;
    }
}
