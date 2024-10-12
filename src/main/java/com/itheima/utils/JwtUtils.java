package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "itheimaewfasjdhniqauwhdasduhwasdhqwiudasiudwasdadsadasd";
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)//自定义的载荷(令牌携带的数据)
                .signWith(SignatureAlgorithm.HS256, signKey)//设置签名算法类型和密钥(长串的是密钥要有256个字节用保护签名,不是Base64编码得出的)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .build().parseClaimsJws(jwt).
                getBody();
        return claims;
    }
}
