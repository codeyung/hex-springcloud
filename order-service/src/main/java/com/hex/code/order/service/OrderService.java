package com.hex.code.order.service;

import com.hex.code.order.dao.OrderDao;
import com.hex.code.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


    public Order add(long userId, long goodsId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setGoodsId(goodsId);
        order.setCreateTime(new Date());

        if (orderDao.addOrder(order) < 1) {
            return null;
        }
        return order;

    }


    public Order get(long orderId) {
        return orderDao.getOrderById(orderId);
    }

    public List<Order> getOrders(long userId) {
        return orderDao.getOrders(userId);
    }


}
