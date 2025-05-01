package com.esun.social.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ 固定密鑰（避免每次重啟後 token 失效，可改成從 application.yml 讀）
    private final Key key = Keys.hmacShaKeyFor("1234567890abcdef1234567890abcdef".getBytes()); // 至少 256-bit
    private final long expirationMs = 1000 * 60 * 60; // 1 小時

    // ✅ 產生 token，payload 含 userId，sub 是 phone
    public String generateToken(String phone, Long userId) {
        return Jwts.builder()
                .claim("userId", userId)            // 自定義 payload 欄位
                .setSubject(phone)                  // 預設 sub 是手機號碼
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    // ✅ 從 token 取得 phone（sub）
    public String getPhoneFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // ✅ 從 token 取得 userId
    public Long getUserIdFromToken(String token) {
        Object id = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("userId");
        return id != null ? Long.parseLong(id.toString()) : null;
    }

    // ✅ 驗證 token 有效性
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
