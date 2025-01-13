package com.eeerrorcode.club.security.util;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
  private String secretKey = "eeerrorcode12345678eeerrorcode12345678eeerrorcode12345678";
  private SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

  public String generateToken(String content) throws Exception {
    return Jwts.builder()
      .issuedAt(new Date())
      .expiration(Date.from(ZonedDateTime.now()
        .plusMonths(1L)
        .toInstant()))
      .claim("sub", content)
      // .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
      .signWith(key)
      .compact();
  }

  public String validateAndExtract(String tokenStr) throws Exception {
    String contentValue = null;

    try {
      // DefaultJws<?> defaultJws = (DefaultJws) Jwts.parser()
      //   .setSigningKey(secretKey.getBytes("utf-8"))
      //   .build()
      //   .parseClaimsJws(tokenStr);
      
      Jws<Claims> defaultJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(tokenStr);
      Claims claims = defaultJws.getPayload();

      log.info(claims.getSubject());
      log.info(claims.getIssuedAt()); // 토큰 발급 시각
      log.info(claims.getExpiration()); // 토큰 종료 시각

      contentValue = claims.getSubject();

    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage());
      contentValue = null;
    }

    return contentValue;
  }
}
