package com.ssafy.luminous.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

@Component
public class JwtService {

    private static final String SALT = "ssafySalt";

    public <T> String createAccessToken(String key, Long data) {
        String subject = "access-token";
        long expire = 1000 * 60 * 60 * 24 * 356 * 5; // 5년

        Claims claims = Jwts.claims()
                // 토큰 제목 설정 ex) access-token
                .setSubject(subject)
                // 생성일 설정
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 만료일 설정
                .setExpiration(new Date(System.currentTimeMillis() + expire));

        claims.put(key, data);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, this.generateKey())
                .compact();

        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("지원하지 않는 인코딩입니다.");
        }

        return key;
    }

}
