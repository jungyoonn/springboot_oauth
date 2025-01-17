package com.eeerrorcode.club.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eeerrorcode.club.entity.Member;
import com.eeerrorcode.club.entity.Note;
import com.eeerrorcode.club.entity.dto.LikesDto;
import com.eeerrorcode.club.repository.MemberRepository;
import com.eeerrorcode.club.repository.NoteRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LikesServiceTests {
  @Autowired
  private LikesService service;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private NoteRepository noteRepository;

  @Test
  public void testToggle() {
    LikesDto dto = LikesDto.builder().mno(100L).num(1L).build();
    service.toggle(dto);
  }

  @Test
  public void testGet() {
    LikesDto dto = LikesDto.builder().mno(100L).num(1L).build();
    log.info(service.get(dto));
  }

  @Test
  public void testInsert() {
    List<Long> mnos = new ArrayList<>(memberRepository.findAll().stream().map(Member::getMno).toList());
    List<Long> nums = new ArrayList<>(noteRepository.findAll().stream().map(Note::getNum).toList());

    Collections.shuffle(mnos);
    Collections.shuffle(nums);
    
    // mnos.subList(0, (int)(mnos.size() * .2));

    List<LikesDto> dto = new ArrayList<>();
    for(int i = 0; i < mnos.size(); i++) {
      for(int j = 0; j < nums.size(); j++) {
        dto.add(LikesDto.builder().mno(mnos.get(i)).num(nums.get(j)).build());
      }
    }

    log.info(dto.size());

    dto = dto.subList(0, dto.size() / 5);

    log.info("====================== 자르기 ======================");
    log.info(dto.size());

    dto.forEach(service::toggle);
  }
}
