package com.eeerrorcode.club.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.eeerrorcode.club.security.handler.LoginSuccessHandler;


@Configuration
// @EnableWebSecurity
@EnableMethodSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(); // 단방향 인코딩만 가능하다
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf ->  csrf.disable()) // CSRF 비활성화 (필요에 따라 활성화) // 토큰 방식 비활성화라는 뜻
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/sample/all").permitAll() // `/public/` 경로는 인증 없이 접근 가능
            .requestMatchers("/sample/member").hasRole("USER")
            .requestMatchers("/api/**").permitAll()
            // .requestMatchers("/sample/admin").hasRole("ADMIN")
            .anyRequest().authenticated() // 나머지는 인증 필요
        )
        .formLogin(f -> f.permitAll()) // 기본 로그인 폼 활성화
        .logout(l -> l.logoutUrl("/member/signout"))
        .oauth2Login(o -> o.successHandler(loginSuccessHandler())) 
        .rememberMe(r -> r.tokenValiditySeconds(60 * 60 * 24 * 14) // 토큰 유지 시간 (밀리초)
          .userDetailsService(userDetailsService)
          .rememberMeCookieName("remember-id")
        );
        return http.build();
  }

  @Bean
  public LoginSuccessHandler loginSuccessHandler() {
    return new LoginSuccessHandler(encoder());
  }

  // 자동으로 등록이 됨!
  // @Bean
  // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
  //   return authenticationConfiguration.getAuthenticationManager();
  // }
}
