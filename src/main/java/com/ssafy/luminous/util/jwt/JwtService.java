package com.ssafy.luminous.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

@Component
public class JwtService {

    private static final String SALT = "ssafySalt";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    // AccessToken 생성
    public <T> String createAccessToken(String key, Long data) {
        String subject = "access-token";
        long expire = 1000 * 60 * 60 * 24 * 356; // 1년

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

    // 키생성
    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("지원하지 않는 인코딩입니다.");
        }

        return key;
    }

    // 토큰에서 id값 가져오기
    public Long getIdFromToken(HttpServletRequest request){
        String accessToken = resolveToken(request);

        Integer id = (Integer)Jwts.parser().setSigningKey(SALT.getBytes()).parseClaimsJws(accessToken).getBody().get("id");

        return new Long(id);
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }

    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader(AUTHORIZATION_HEADER, accessToken);
    }

}
