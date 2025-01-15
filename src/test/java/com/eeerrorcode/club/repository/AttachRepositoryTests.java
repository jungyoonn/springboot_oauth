package com.eeerrorcode.club.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.eeerrorcode.club.entity.Attach;
import com.eeerrorcode.club.entity.Note;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Transactional
@Log4j2
public class AttachRepositoryTests {
  @Autowired
  private AttachRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  @Rollback(false)
  public void testInsert() {
    LongStream.rangeClosed(1, 5).boxed().map(i -> 
      Attach.builder()
        .uuid("" + i)
        .origin("대충첨부파일입니다" + i)
        .image(false)
        .path(null)
        .note(Note.builder().num(1L).build())
        .build()
    ).forEach(repository::save);
    // Attach attach = Attach.builder()
    //   .origin("test.png")
    //   .note(Note.builder().num(1L).build())
    //   .build();
    // repository.save(attach);
  }
}
