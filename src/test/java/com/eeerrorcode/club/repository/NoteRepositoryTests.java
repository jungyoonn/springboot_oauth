package com.eeerrorcode.club.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.eeerrorcode.club.entity.Member;
import com.eeerrorcode.club.entity.Note;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
@Transactional
public class NoteRepositoryTests {
  @Autowired
  private NoteRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  @Rollback(false)
  public void testInsert() {
    LongStream.rangeClosed(1, 5).boxed().map(l -> 
      Note.builder()
      .title("제목" + l)
      .content("내용" + l)
      .member(Member.builder().mno(100L).build())
      .build()
    ).forEach(repository::save);
  }

  @Test
  public void testOne() {
    log.info(repository.findById(1L));
  }

  @Test
  public void testList() {
    repository.findByMemberMno(100L).forEach(log::info);
  }

  @Test
  public void testList2() {
    repository.findByMemberEmail("user100@eeerrorcode.com").forEach(log::info);
  }

  @Test
  public void testListJQPL() {
    // repository.findNotesBy("user100@eeerrorcode.com").forEach(o -> { log.info(Arrays.toString(o)); });
    repository.findNotes().forEach(log::info);
  }
}
