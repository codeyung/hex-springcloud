package com.hex.code.order.model;

import java.util.Date;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-11.22:49
 */
public class Order {

    private long orderId;
    private long goodsId;
    private long userId;
    private Date createTime;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
