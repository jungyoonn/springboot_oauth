package com.eeerrorcode.club.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.eeerrorcode.club.security.filter.ApiCheckFilter;
import com.eeerrorcode.club.security.filter.ApiLoginFilter;
import com.eeerrorcode.club.security.handler.ApiLoginFailHandler;
import com.eeerrorcode.club.security.handler.LoginSuccessHandler;
import com.eeerrorcode.club.security.util.JWTUtil;


@Configuration
// @EnableWebSecurity
@EnableMethodSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
    AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    builder.userDetailsService(userDetailsService)
      .passwordEncoder(encoder()).setBuilder(builder);
  
    AuthenticationManager authenticationManager = builder.build();
    return authenticationManager;
  }
  
  @Bean
  public ApiCheckFilter apiCheckFilter(){
    return new ApiCheckFilter("/api/v1/**", jwtUtil());
  }
  
  @Bean
  public JWTUtil jwtUtil() {
    return new JWTUtil();
  }

  @Bean
  public ApiLoginFilter apiLoginFilter(AuthenticationManager authenticationManager) throws Exception{
    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
    apiLoginFilter.setAuthenticationManager(authenticationManager);
    apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
    return apiLoginFilter;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(); // 단방향 인코딩만 가능하다
  }

  @Bean
  public CorsConfigurationSource configurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    
    config.setAllowCredentials(true);
    config.setAllowedOrigins(List.of("http://localhost:3000"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "FATCH", "OPTIONS"));
    config.setAllowedHeaders(List.of("*"));
    config.setExposedHeaders(List.of("*"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf ->  csrf.disable()) // CSRF 비활성화 (필요에 따라 활성화) // 토큰 방식 비활성화라는 뜻
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/sample/all").permitAll() // `/public/` 경로는 인증 없이 접근 가능
        .requestMatchers("/sample/member").hasRole("USER")
        .requestMatchers("swagger-ui.html").permitAll()
        // .requestMatchers("/api/**").permitAll()
        // .requestMatchers("/sample/admin").hasRole("ADMIN")
        // .anyRequest().authenticated() // 나머지는 인증 필요
        .anyRequest().permitAll()
      )
      // .formLogin(f -> f.permitAll()) // 기본 로그인 폼 활성화
      .logout(l -> l.logoutUrl("/member/signout"))
      .oauth2Login(o -> o.successHandler(loginSuccessHandler())) 
      .cors(c -> c.configurationSource(configurationSource()))
      .rememberMe(r -> r.tokenValiditySeconds(60 * 60 * 24 * 14) // 토큰 유지 시간 (밀리초)
        .userDetailsService(userDetailsService)
        .rememberMeCookieName("remember-id")
      );
    http
      .addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class) // Json 객체 형식이 나오지 않고 필터 적용 로그만 찍힘
      .addFilterBefore(apiLoginFilter(authenticationManager(http)), UsernamePasswordAuthenticationFilter.class);
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
