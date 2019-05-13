package com.hex.code.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hex.code.vo.UserVo;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-13.21:05
 */
public class JwtUtil {
    final static Algorithm algorithm = Algorithm.HMAC256("123");


    public static String deJWT(String token) {
        DecodedJWT jwt = JWT.decode(token);
        long claims = jwt.getClaim("userId").asLong();
        System.out.println(claims);
        return null;
    }

    public static long deJWTUserId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        long claims = jwt.getClaim("userId").asLong();
        return claims;
    }

    public static String createToken(UserVo user) {
//        String subject = "code";
        String token = JWT.create()
//                .withIssuer(subject)
                .withClaim("userId", user.getUserId())
//                .withClaim("code", code.getCode())
                .sign(algorithm);
        return token;
    }

    public static void main(String[] args) {
        UserVo userVo = new UserVo();
        userVo.setUserId(15);
        String token = JwtUtil.createToken(userVo);
        System.out.println(token);
        System.out.println(deJWTUserId(token));
    }


}
