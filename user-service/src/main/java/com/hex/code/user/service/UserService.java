package com.hex.code.user.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.hex.code.config.RedisService;
import com.hex.code.exception.CommonException;
import com.hex.code.feign.MallFeign;
import com.hex.code.feign.OrderFeign;
import com.hex.code.tools.Session;
import com.hex.code.user.Result;
import com.hex.code.user.dao.UserDao;
import com.hex.code.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.23:14
 */
@Repository
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MallFeign mallFeign;

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private RedisService redisService;

    @Autowired
    private Session session;


    public UserVo getUserByPhone(UserVo user) {
        return userDao.getUserByPhone(user.getPhoneNumber());
    }

    public Result session(UserVo user) {
        UserVo uv = userDao.getUserByPhone(user.getPhoneNumber());
        if (uv == null) {
            return new Result(new CommonException("用户不存在"));
        }
        if (!uv.getPassword().equals(user.getPassword())) {
            return new Result(new CommonException("密码错误"));
        }
        HashMap map = new HashMap();
        String token = session.create(uv);
        map.put("user", uv);
        map.put("token", token);
        return new Result<>(map);
    }


    public Result add(UserVo user) {
        if (user.getPhoneNumber() == null ||
                user.getPassword() == null ||
                userDao.getUserByPhone(user.getPhoneNumber()) != null) {
            return new Result(new CommonException("用户已存在"));
        }
        if (userDao.addUser(user) < 1) {
            return new Result(new CommonException("用户添加失败"));
        }
        HashMap map = new HashMap();
        String token = session.create(user);
        map.put("user", user);
        map.put("token", token);
        return new Result<>(map);
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
