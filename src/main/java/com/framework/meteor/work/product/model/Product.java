package com.framework.meteor.work.product.model;

import com.framework.meteor.framework.model.BaseModel;
import com.framework.meteor.work.order.model.Order;
import com.framework.meteor.work.user.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 产品
 *
 * @author Meteor.wu
 * @since 2017/11/29 15:58
 */
//@Entity
//@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseModel  {

    @Id
    @Column(length = 32)
    private String productId;

    @Column(length = 32)
    private String name;

//    @ManyToOne
//    @LazyToOne(LazyToOneOption.PROXY)  此写法同下一行
//    @Fetch(FetchMode.SELECT)  此写法同下一行

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey =@ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT ))
    private User createUser;

    //mappBy表示关系被维护端,只有关系端有权去更新外键,OneToMany默认的加载方式是赖加载,配置中以Many结尾默认为懒加载
    @OneToMany(cascade={CascadeType.ALL},mappedBy ="product", fetch=FetchType.LAZY)
    private Set<Order> orderSet = new HashSet<Order>();

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }
}
