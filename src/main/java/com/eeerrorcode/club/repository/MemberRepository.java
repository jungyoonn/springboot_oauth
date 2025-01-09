package com.eeerrorcode.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeerrorcode.club.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
  Member findByEmail(String email);
  Member findByEmailAndFromSocial(String email, boolean fromSocial);
}
