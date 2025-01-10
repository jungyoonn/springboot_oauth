package com.eeerrorcode.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eeerrorcode.club.security.dto.AuthMemberDto;


@Controller
@RequestMapping("sample")
@Log4j2
public class SampleController {
  @GetMapping("all")
  public void exAll(@AuthenticationPrincipal AuthMemberDto dto) {
    // 인증, 인가에 필요한 객체들
    // UsernamePasswordAuthenticationToken token;
    // AuthenticationManager manager;
    // AuthenticationProvider provider;
    log.info(dto);
    log.info("ex all");
  }
  
  @GetMapping("member")
  public void exMember(@AuthenticationPrincipal AuthMemberDto dto) {
    log.info(dto);
    log.info("ex member");
  }
  
  @GetMapping("admin")
  @PreAuthorize("hasRole('ADMIN')")
  public void exAdmin(@AuthenticationPrincipal AuthMemberDto dto) {
    log.info(dto);
    log.info("ex admin");
  }
  
  @GetMapping("api")
  @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
  // @PreAuthorize("isAnnonymous()") // 익명의 사용자만 접근 가능
  @ResponseBody
  public AuthMemberDto getMethodName(@AuthenticationPrincipal AuthMemberDto dto) {
    return dto;
  }
  
}
