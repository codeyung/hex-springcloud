package com.hex.code.mall.dao;

import com.hex.code.mall.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.22:49
 */
public interface GoodsDao {

    Goods getGoodsById(@Param("goodsId") long goodsId);

    List<Goods> getGoods();

    int addGoods(Goods goods);

    int updateStock(@Param("goodsId") long goodsId);
}
