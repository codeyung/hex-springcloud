package com.hex.code.order.dao;

import com.hex.code.order.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.22:49
 */
public interface OrderDao {

    Order getOrderById(@Param("orderId") long orderId);

    List<Order> getOrders(@Param("userId") long userId);

    int addOrder(Order order);


}
