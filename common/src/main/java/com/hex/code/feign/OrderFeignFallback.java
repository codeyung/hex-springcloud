package com.hex.code.feign;

import org.springframework.stereotype.Component;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.17:14
 */

@Component
public class OrderFeignFallback implements OrderFeign {

    @Override
    public Boolean addOrder(long orderId) {
        return false;
    }

    @Override
    public Boolean ss(long goodsId) {
        return false;
    }
}
