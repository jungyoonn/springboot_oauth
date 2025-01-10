package com.eeerrorcode.club.entity.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteDto {
  private Long num;
  private String title;
  private String content;
  private Long mno;
  private String memberEmail;
  private LocalDateTime regDate, modDate;

}
