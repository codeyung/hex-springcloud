package com.hex.code.user.service;

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


}
