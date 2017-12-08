package com.framework.meteor.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 模型公共属性
 * eg：enabled createTime etc.
 *
 * @author Meteor.wu
 * @since 2017/11/29 15:39
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public class BaseModel implements Serializable {

    @JsonIgnore
    @CreatedDate
    private Date createTime;

    @JsonIgnore
    @Column(columnDefinition = "tinyint(1) default 1")
    private Integer enabled = 1;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
