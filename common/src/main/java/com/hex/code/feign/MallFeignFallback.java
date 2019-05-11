package com.hex.code.feign;

import org.springframework.stereotype.Component;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.17:14
 */

@Component
public class MallFeignFallback implements MallFeign {

    @Override
    public String getGoods() {
        return "请求失败";
    }

    @Override
    public String updateStock(long goodsId) {
        return "更新库存失败";
    }
}
