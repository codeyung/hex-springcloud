package com.hex.code.tools;

import com.hex.code.config.RedisService;
import com.hex.code.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-13.22:21
 */
@Repository
public class Session {

    @Autowired
    private RedisService redisService;


    public String create(UserVo user) {
        String token = JwtUtil.createToken(user);
        redisService.set(token, user, -1);
        return token;
    }

    public UserVo getUser(String token) {
        UserVo user = (UserVo) redisService.get(token);
        return user;
    }


}
