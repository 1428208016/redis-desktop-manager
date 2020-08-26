package com.lingzhen.myproject.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;

/**
 * Json Web Token 工具
 * @date 2019-10-31
 * @author lingz
 */
public class JWTUtil {
    //JWT的secret（密匙）
    private static final String JWT_TOKEN_SECRET = "jingzhi_token_secret";
    //JWT的user
    private static final String JWT_TOKEN_USER = "auth0";
    //JWT的 Claim1：key
    public static final String JWT_CLAIM_KEY = "key";
    //token过期时间 秒
    private static final int TOKEN_EXPIRE_SECOND = 60 * 30;
    //key:token
    public static final String TOKEN = "token";

    /**
     * 创建Token
     * @param key
     * @return
     */
    public static String createToken(String key) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND,TOKEN_EXPIRE_SECOND); //过期时间30分钟
        return createToken(key,c.getTime());
    }

    /**
     * 创建token
     * @param key Key
     * @param date 过期时间
     * @return
     */
    public static String createToken (String key,Date date){
        Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN_SECRET);
        String token = JWT.create()
                //发出者
                .withIssuer(JWT_TOKEN_USER)
                //携带的信息
                .withClaim(JWT_CLAIM_KEY,key)
                .withExpiresAt(date)
                .sign(algorithm);
        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static boolean verifyToken(String token){
        boolean b = true;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_TOKEN_USER)
                    .build(); //Reusable verifier instance
            verifier.verify(token);//验证不通过会报错
        } catch (JWTVerificationException exception){
            b = false;
        }
        return b;
    }

    public static String getValue(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String value = jwt.getClaim(JWTUtil.JWT_CLAIM_KEY).asString();
        return value;
    }

}
