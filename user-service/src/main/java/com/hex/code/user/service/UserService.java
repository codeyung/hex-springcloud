package com.hex.code.user.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.hex.code.feign.MallFeign;
import com.hex.code.feign.OrderFeign;
import com.hex.code.user.dao.UserDao;
import com.hex.code.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.23:14
 */
@Repository
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    MallFeign mallFeign;

    @Autowired
    OrderFeign orderFeign;


    public UserVo getUserByPhone(UserVo user) {
        return userDao.getUserByPhone(user.getPhoneNumber());
    }


    public UserVo add(UserVo user) {
        if (user.getPhoneNumber() == null ||
                user.getPassword() == null ||
                userDao.getUserByPhone(user.getPhoneNumber()) != null) {
            return null;
        }
        if (userDao.addUser(user) < 1) {
            return null;
        }
        return user;

    }

    @LcnTransaction
    public Boolean addOrder(long goodsId) {

        Boolean flag1 = mallFeign.updateStock(goodsId);
        System.out.println("更新库存结果 " + flag1);

        Boolean flag2 = orderFeign.addOrder(goodsId);
        System.out.println("生成订单结果 " + flag2);
//        flag=false;
        // 置异常标志，DTX 回滚
        if (!flag1 == flag2 == true) {
            throw new IllegalStateException("by exFlag");
        }
        return true;
    }
}
