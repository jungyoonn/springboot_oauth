package com.eeerrorcode.club.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eeerrorcode.club.security.util.JWTUtil;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class JWTTests {
  private JWTUtil jwtUtil;

  @BeforeEach
  public void testBefore() {
    log.info("*********** test before ****************");
    jwtUtil = new JWTUtil();
  }

  // 로그인 성공하면 이렇게 해서 줌
  // 이메일을 받아서 토큰을 줌
  @Test
  public void testEncode() throws Exception{
    String email = "user100@eeerrorcode.com";
    String str = jwtUtil.generateToken(email);
    log.info(str); // 매번 다른 값이 나온다 (토큰은 계속 바뀜)
  }

  // 토큰을 받아서 이메일을 줌
  @Test
  public void testValidate() throws Exception {
    String email = "user100@eeerrorcode.com";
    String str = jwtUtil.generateToken(email);

    Thread.sleep(5000);

    String resultEmail = jwtUtil.validateAndExtract(str);

    log.info(resultEmail);
  }
}
