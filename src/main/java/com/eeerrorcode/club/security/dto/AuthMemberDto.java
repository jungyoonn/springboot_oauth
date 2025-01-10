package com.eeerrorcode.club.security.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthMemberDto  extends User implements OAuth2User{
  private Long mno;
  private String email;
  private String name;
  private Boolean fromSocial;
  private Map<String, Object> attr;
  private String pw;

  // security 자체 로그인 호출 생성자
  public AuthMemberDto(String username, String password, Long mno, Boolean fromSocial, String name,Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.name = name;
    this.mno = mno;
    this.email = username;
    this.fromSocial = fromSocial;
    pw = password;
  }
  
  // OAuth2 호출 생성자
  public AuthMemberDto(String username, String password, Long mno, Boolean fromSocial, String name,Collection<? extends GrantedAuthority> authorities
  , Map<String, Object> attr) {
    super(username, password, authorities);
    this.name = name;
    this.mno = mno;
    this.email = username;
    this.fromSocial = fromSocial;
    this.attr = attr;
    pw = password;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attr;
  }
}
