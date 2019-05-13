package com.hex.code.order.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.hex.code.order.dao.OrderDao;
import com.hex.code.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.23:14
 */
@Repository
public class OrderService {

    @Autowired
    OrderDao orderDao;


    @LcnTransaction
    @Transactional
    public boolean add(long userId, long goodsId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setGoodsId(goodsId);
        order.setCreateTime(new Date());

        if (orderDao.addOrder(order) < 1) {
            return false;
        }
        return true;

    }


    public Order get(long orderId) {
        return orderDao.getOrderById(orderId);
    }

    public List<Order> getOrders(long userId) {
        return orderDao.getOrders(userId);
    }


}
