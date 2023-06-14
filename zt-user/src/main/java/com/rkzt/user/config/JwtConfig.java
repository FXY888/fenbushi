package com.rkzt.user.config;


import com.rkzt.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtConfig {
    public static String getoken(User comsumer){
        Jwts.builder();//生成
        Jwts.parser();//解密

        JwtBuilder jwtBuilder=Jwts.builder()
                .setId(comsumer.getUserId()+"")//设置需要加密的内容
                .setSubject(comsumer.getUserEmail())
                .setIssuedAt(new Date())//token保留时间
                .signWith(SignatureAlgorithm.HS256,"Wzyyy").setExpiration(
                        new Date(new Date().getTime()+36000)
                );
//        System.out.println(jwtBuilder.compact());
        return jwtBuilder.compact();
    }


    public static User jiemi(String token){
        User user = new User();
        Claims claims = Jwts.parser()
                .setSigningKey("Wzyyy")
                .parseClaimsJws(token)
                .getBody();

        user.setUserId(Integer.valueOf(claims.getId()));
        user.setUserEmail(claims.getSubject());
        return user;
    }
}

