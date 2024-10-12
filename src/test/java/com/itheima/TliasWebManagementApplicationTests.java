package com.itheima;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.PageBean;
import com.itheima.service.DeptService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {
//   测试JWT令牌的生成
   /* @Test
    public void testGenJWT() {
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "itheimayqcxyyaksdnasnfdfghfrgthfghjbnsdfsdDSFSDF")//设置签名算法类型和密钥(长串的是密钥要有256个字节用保护签名,不是Base64编码得出的)
                .setClaims(claims)//自定义内容(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置令牌有效期为1小时
                .compact();//调用此方法可以返回一个字符串类型的返回值
        System.out.println(jwt);
    }

    //解析jwt令牌(jwt令牌的任意一个发生任何变动都解析不出来,会报错,过期也会报错)
        @Test
        public void testParseJwt(){
            Claims claims = Jwts.parser().setSigningKey("itheimayqcxyyaksdnasnfdfghfrgthfghjbnsdfsdDSFSDF")
                    .build().parseSignedClaims("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcyNzEwNzExOX0.StRZiO8-SF487ihHfim6oA_T0dMDGTs941eQVj1YsN0")
                    .getBody();//获得自定义的内容(jwt的第二部分)
            System.out.println(claims);
        }*/




}
