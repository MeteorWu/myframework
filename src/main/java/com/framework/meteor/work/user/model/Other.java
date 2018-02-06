package com.framework.meteor.work.user.model;

import com.framework.meteor.framework.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Meteor.wu
 * @since 2018/1/15 16:40
 */
@Entity
@Setter
@Getter
public class Other extends BaseModel {
    @Id
    @Column(length = 32)
    private String otherId;

    @Column(length = 32)
    private String otherString;

    @Column(length = 32)
    private String userId;
}
