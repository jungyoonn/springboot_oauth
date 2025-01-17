package com.eeerrorcode.club.entity.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LikesDto {
  private Long num;
  private Long mno;
  private String email;
  private LocalDateTime regDate, modDate;
}
