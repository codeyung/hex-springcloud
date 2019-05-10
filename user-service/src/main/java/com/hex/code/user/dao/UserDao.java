package com.hex.code.user.dao;

import com.hex.code.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-09.22:49
 */
public interface UserDao {
    UserVo getUserByPhone(@Param("phoneNumber") String phoneNumber);

    int addUser(UserVo user);
}
