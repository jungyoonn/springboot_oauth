package com.eeerrorcode.club.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.eeerrorcode.club.security.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter{
  private JWTUtil jwtUtil;

  public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {
    super(defaultFilterProcessesUrl);
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    log.info("======================= login filter on ========================");

    String email = request.getParameter("email");
    String pw = "1234";

    // if(email == null) {
    //   throw new BadCredentialsException("email cannot be null");
    // }
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, pw);
    Authentication authentication = getAuthenticationManager().authenticate(authenticationToken);
    log.info(authentication.getPrincipal());
    return authentication;
  }
  
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
    log.info("=============== Api Login filter ===================");
    log.info("================ successful Authentication :::: " + authResult + " =====================");
    log.info(authResult.getPrincipal());

    String email = authResult.getName();
    try {
      String token = jwtUtil.generateToken(email);
      response.setContentType("text/plain");
      response.getOutputStream().write(token.getBytes());
      log.info("success token :: " + token);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
