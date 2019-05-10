package com.hex.code.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.17:05
 */

//@FeignClient(value = "customer-service"s)
@FeignClient(value = "mall-service", fallback = MallFeignFallback.class)
public interface MallFeign {

    @GetMapping("/goods")
    String getGoods();

}
