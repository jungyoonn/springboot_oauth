package com.eeerrorcode.club.entity.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.Builder.Default;

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

  private long LikesCnt;
  private long attachCnt;

  @Default
  private List<AttachDto> attachDtos = new ArrayList<>();
}
