package com.eeerrorcode.club.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeerrorcode.club.entity.composite.LikesId;
import com.eeerrorcode.club.entity.dto.LikesDto;
import com.eeerrorcode.club.repository.LikesRepository;
import com.eeerrorcode.club.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LikesServiceImpl implements LikesService{
  @Autowired
  private LikesRepository repository;
  @Autowired
  private MemberRepository memberRepository;

  @Override
  public void toggle(LikesDto dto) {
    if(get(dto)) {
      repository.delete(toEntity(dto));
    } else {
      repository.save(toEntity(dto)); 
    }
    
    // delete의 반환 타입이 void이므로 save의 반환 타입과 달라서 삼항을 쓸 수 없다
    // get(dto) ? repository.delete(toEntity(dto)) : repository.save(toEntity(dto));
  }

  @Override
  public boolean get(LikesDto dto) {
    if(dto.getMno() == null) {
      Long mno = memberRepository.findByEmail(dto.getEmail()).getMno();
      dto.setMno(mno);
    }
    
    // optional.get() 했을 때 null이면 터진다!
    return repository.findById(LikesId.builder()
      .member(dto.getMno())
      .note(dto.getNum())
    .build()).isPresent();

    // 엔티티에 생성자를 정의한 후 이렇게 해도 된다
    // return repository.findById(new LikesId(dto)).isPresent();
  }
  
}
