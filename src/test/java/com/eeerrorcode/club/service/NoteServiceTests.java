package com.eeerrorcode.club.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eeerrorcode.club.entity.dto.NoteDto;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class NoteServiceTests {
  @Autowired
  private NoteService service;

  @Test
  public void testRegister() {
    Long num = service.register(NoteDto.builder().title("테스트 제목").content("테스트 내용").mno(1L).build());
    log.info(num);
  }

  @Test
  public void testModify() {
    service.modify(NoteDto.builder().num(1L).title("제목 수정").content("내용 수정").mno(100L).build());
    log.info(service.get(1L));
  }

  @Test
  public void testRead() {
    NoteDto dto = service.get(46L).get();
    dto.getAttachDtos().forEach(log::info);
  }
}
