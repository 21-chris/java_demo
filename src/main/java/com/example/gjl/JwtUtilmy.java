package com.example.gjl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtilmy {
    private static final String SING = "Qdfdfdg%#@*$^*";
//    使用加密获得一个信息token
    public  static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
//        创建jwt工具类
        instance.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
    }
    public static void verify(String token){
        JWTVerifier jwtVerifier =JWT.require(Algorithm.HMAC256(SING)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
    }
//    获取token信息方法
    public static DecodedJWT getTokenInformation(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}
