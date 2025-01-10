package com.eeerrorcode.club.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.eeerrorcode.club.security.dto.AuthMemberDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
  private RedirectStrategy strategy = new DefaultRedirectStrategy();

  private PasswordEncoder encoder;

  public LoginSuccessHandler(PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    Authentication authentication) throws IOException, ServletException {
    
    log.info("========================================");
    log.info("on authentication success!");

    AuthMemberDto authMember = ((AuthMemberDto)authentication.getPrincipal());
    boolean fromSocial = authMember.getFromSocial();
    boolean passwordResult = encoder.matches("1234", authMember.getPw());

    if(fromSocial && passwordResult) {
      strategy.sendRedirect(request, response,  "/member/modify?from=social");
    }
  }
  
}
