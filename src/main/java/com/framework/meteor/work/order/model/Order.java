package com.framework.meteor.work.order.model;

import com.framework.meteor.framework.model.BaseModel;
import com.framework.meteor.work.product.model.Product;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author Meteor.wu
 * @since 2017/11/29 16:26
 */
//@Entity
//@Table(name = "`order`")
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseModel {

    @Id
    @Column(length = 32)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private Product product;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
