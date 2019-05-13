package com.hex.code.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.17:05
 */

@FeignClient(value = "order-service", configuration = FeignConfiguration.class, fallback = OrderFeignFallback.class)
public interface OrderFeign {

    @PostMapping("order")
    Boolean addOrder(@RequestParam("goodsId") long goodsId);

    @GetMapping("/order/ss")
    Boolean ss(@RequestParam("goodsId") long goodsId);
}
