package com.eeerrorcode.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.eeerrorcode.club.entity.Likes;
import com.eeerrorcode.club.entity.Member;
import com.eeerrorcode.club.entity.Note;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LikesRepositoryTests {
  @Autowired
  private LikesRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testInsert() {
    Likes likes = Likes.builder()
      .member(Member.builder().mno(100L).build())
      .note(Note.builder().num(2L).build())
    .build();
    repository.save(likes);
  }

  @Test
  public void testDelete() {
    repository.delete(
      Likes.builder()
        .member(Member.builder().mno(100L).build())
        .note(Note.builder().num(2L).build())
      .build()
    );
  }
}
