package com.hex.code.user.controller;

import com.hex.code.exception.CommonException;
import com.hex.code.feign.MallFeign;
import com.hex.code.feign.OrderFeign;
import com.hex.code.tools.Session;
import com.hex.code.user.Result;
import com.hex.code.user.service.UserService;
import com.hex.code.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.22:52
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Session session;

    @Autowired
    MallFeign mallFeign;

    @Autowired
    OrderFeign orderFeign;


    @PostMapping("session")
    public Result session(@RequestBody UserVo user) {
        return userService.session(user);
    }

    @PostMapping("register")
    public Result register(@RequestBody UserVo user) {
        return userService.add(user);
    }

    @GetMapping("user/ss")
    public Result get(HttpServletRequest request) {
        UserVo user = session.getUser(request.getHeader("authorization"));
        Enumeration<String> s = request.getHeaderNames();
        while (s.hasMoreElements()) {
            String key = s.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + "----" + value);
        }
        return new Result<>(orderFeign.ss(1));
    }

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