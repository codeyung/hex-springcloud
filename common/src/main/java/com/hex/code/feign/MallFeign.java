package com.hex.code.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.17:05
 */

//@FeignClient(value = "customer-service"s)
@FeignClient(value = "mall-service", configuration = FeignConfiguration.class, fallback = MallFeignFallback.class)
public interface MallFeign {

    @GetMapping("/goods")
    String getGoods();

    @PutMapping("/goods/{goodsId}/stock")
    Boolean updateStock(@PathVariable("goodsId") long goodsId);
}
