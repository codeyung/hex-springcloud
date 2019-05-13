package com.hex.code.mall.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.hex.code.mall.dao.GoodsDao;
import com.hex.code.mall.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.13:23
 */
@Repository
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public Goods get(long goodsId) {
        return goodsDao.getGoodsById(goodsId);
    }

    public List<Goods> getGoods() {
        return goodsDao.getGoods();
    }

    public Goods add(Goods goods) {
        goodsDao.addGoods(goods);
        return goods;

    }

    @LcnTransaction
    @Transactional
    public int updateStock(long goodsId) {
        return goodsDao.updateStock(goodsId);
    }
}
