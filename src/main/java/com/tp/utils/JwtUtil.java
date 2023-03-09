package com.tp.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Jwt工具类
 */
public class JwtUtil {
    //有效期
    public static final Long JWT_TTL = 60 * 60 * 1000L; // 一小时
    //设置秘钥明文
    public static final String JWT_KEY = "tp";

    /**
     * 简化 UUID 去除下划线
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("_", "");
    }

    /**
     * 生成 jwt jwt加密
     *
     * @param subject token中要存放的数据（json格式）
     */
    public static String createJwt(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());//设置过期时间
        return builder.compact();
    }

    /**
     * 生成 jwt jwt加密
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token的超时时间
     */
    public static String createJwt(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());//设置过期时间
        return builder.compact();
    }

    /**
     * 生成 jwt jwt加密
     *
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJwt(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);//设置过期时间
        return builder.compact();
    }

    public static JwtBuilder getJwtBuilder(String subject, Long ttlMills, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMills == null) {
            ttlMills = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMills;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid) //唯一ID
                .setSubject(subject) //主题 可以是JSON数据
                .setIssuer("tp") //签发者
                .setIssuedAt(now) //签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名，第二个参数为秘钥
                .setExpiration(expDate); //到期时间
    }

    /**
     * 自测方法
     */
//    public static void main(String[] args) throws Exception {
//        //jwt加密
//        String jwt = createJwt("123456");
//
//        //jwt解密
//        Claims claims = parseJWT(jwt);
//        String subject = claims.getSubject();
//
//        System.out.println(jwt);
//        System.out.println(subject);
//    }

    /**
     * 生成加密后的秘钥 secretKey 用来验证的
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解密 JWT
     */
    public static Claims parseJWT(String jwt) throws Exception{
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}