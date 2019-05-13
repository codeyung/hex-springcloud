package com.hex.code.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-13.21:05
 */
public class Password {

    private final static BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();

    public static String encode(String password) {
        String pwd = BCrypt.encode(password);
        return pwd;
    }


    public static Boolean decode(String rawPassword, String encodedPassword) {
        return BCrypt.matches(rawPassword, encodedPassword);
    }


    public static void main(String[] args) {
        System.out.println(encode("123456a"));
        System.out.println(decode("123456a",encode("123456a")));
    }
}
