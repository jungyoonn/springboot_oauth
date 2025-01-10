package com.eeerrorcode.club.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthMemberDto  extends User{
  private Long mno;
  private String email;
  private String name;
  private Boolean fromSocial;

  public AuthMemberDto(String username, String password, Long mno, Boolean fromSocial, String name,Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.name = name;
    this.mno = mno;
    this.email = username;
    this.fromSocial = fromSocial;
  }
}
