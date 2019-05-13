package com.hex.code.order.controller;

import com.hex.code.order.model.Order;
import com.hex.code.order.service.OrderService;
import com.hex.code.tools.Session;
import com.hex.code.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.22:52
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Session session;


    @PostMapping("order")
    public Boolean addOrder(@RequestParam("goodsId") long goodsId, HttpServletRequest request) {
        UserVo user = session.getUser(request);
        if (user == null) {
            return false;
        }
        return orderService.add(user.getUserId(), goodsId);
    }


    @GetMapping("/order/{orderId}")
    public Order get(@PathVariable("orderId") long orderId) {
        return orderService.get(orderId);
    }

    @GetMapping("/order")
    public List<Order> getOrders(HttpServletRequest request) {
        UserVo user = session.getUser(request);
        if (user == null) {
            return null;
        }
        return orderService.getOrders(user.getUserId());
    }


}