package com.hex.code.order.controller;

import com.hex.code.config.RedisService;
import com.hex.code.exception.CommonException;
import com.hex.code.order.Result;
import com.hex.code.order.model.Order;
import com.hex.code.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.22:52
 */
@RestController
public class OrderController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private OrderService orderService;


    @PostMapping("order")
    public Result addOrder(long goodsId) {
        long userId = 1;
        if (orderService.add(userId, goodsId) == null) {
            return new Result<>(new CommonException("添加失败"), Result.FAIL);
        }
        return new Result<>(true);
    }


    @GetMapping("/order/{orderId}")
    public Order get(@PathVariable("orderId") long orderId) {
        return orderService.get(orderId);
    }

    @GetMapping("/order")
    public List<Order> getOrders(@RequestParam("userId") long userId) {
        return orderService.getOrders(userId);
    }


}