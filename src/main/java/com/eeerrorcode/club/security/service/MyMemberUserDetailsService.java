package com.eeerrorcode.club.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eeerrorcode.club.entity.Member;
import com.eeerrorcode.club.repository.MemberRepository;
import com.eeerrorcode.club.security.dto.AuthMemberDto;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MyMemberUserDetailsService implements UserDetailsService{
  @Autowired
  private MemberRepository repository;
  
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(username);
    Member member = repository.findByEmailAndFromSocial(username, false);

    if(member == null) {
      throw new UsernameNotFoundException(username);
    }

    AuthMemberDto authMemberDto = new AuthMemberDto(member.getEmail(), member.getPassword(), member.getMno(), member.getFromSocial()
    , member.getName(), member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList());
    return authMemberDto;
  }
  
}
