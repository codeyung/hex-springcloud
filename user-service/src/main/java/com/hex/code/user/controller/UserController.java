package com.hex.code.user.controller;

import com.hex.code.config.RedisService;
import com.hex.code.exception.CommonException;
import com.hex.code.feign.MallFeign;
import com.hex.code.feign.OrderFeign;
import com.hex.code.user.Result;
import com.hex.code.user.service.UserService;
import com.hex.code.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.22:52
 */
@RestController
public class UserController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;


    @PostMapping("session")
    public Result session(@RequestBody UserVo user) {
        UserVo uv = userService.getUserByPhone(user);
        if (uv == null) {
            return new Result<>(new CommonException("帐号不存在"), Result.FAIL);
        }
        if (!uv.getPassword().equals(user.getPassword())) {
            return new Result<>(new CommonException("密码错误"), Result.FAIL);
        }
        return new Result<>(uv);
    }


    @PostMapping("register")
    public Result register(@RequestBody UserVo user) {
        if (userService.add(user) == null) {
            return new Result<>(new CommonException("添加失败"), Result.FAIL);
        }
        return new Result<>(user);
    }

    @Autowired
    MallFeign mallFeign;

    @Autowired
    OrderFeign orderFeign;


    @GetMapping("goods")
    public Result getGoods() {
        return new Result<>(mallFeign.getGoods());
    }


    @PostMapping("order")
    public Result addOrder(@RequestParam("goodsId") long goodsId) {
        boolean flag;
        try {
            flag = userService.addOrder(goodsId);
            return new Result<>(flag);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(new CommonException("添加失败"), Result.FAIL);
        }
    }


}