package com.eeerrorcode.club.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class SecurityTests {
  @Autowired
  private PasswordEncoder encoder;

  @Test
  public void testEncoder() {
    log.info(encoder);
    log.info(encoder.getClass().getName());

    String pw = "1234";
    String encoded = encoder.encode(pw);

    log.info(pw);
    log.info(encoded);

    // $2a$10$.JMquKR6u49eIKcQhbVLiuruKllowii8v0EdgET3qfyhljt/LOI3K
    // $2a$10$QKs2grX.OHspBwJ8y3nLPuNwC/vIpLKqFq2QRKjhT2pGNzKFGxDkq

    assertTrue(encoder.matches(pw, "$2a$10$QKs2grX.OHspBwJ8y3nLPuNwC/vIpLKqFq2QRKjhT2pGNzKFGxDkq")); // true
    assertTrue(encoder.matches(pw, "$2a$10$.JMquKR6u49eIKcQhbVLiuruKllowii8v0EdgET3qfyhljt/LOI3K")); // true
  }
}
