package com.eeerrorcode.club.service;

import com.eeerrorcode.club.entity.Likes;
import com.eeerrorcode.club.entity.Member;
import com.eeerrorcode.club.entity.Note;
import com.eeerrorcode.club.entity.dto.LikesDto;

public interface LikesService {
  boolean toggle(LikesDto dto);

  boolean get(LikesDto dto);

  default LikesDto toDto(Likes likes) {
    return LikesDto.builder()
      .mno(likes.getMember().getMno())
      .email(likes.getMember().getEmail())
      .num(likes.getNote().getNum())
      .regDate(likes.getRegDate())
      .modDate(likes.getModDate())
    .build();
  }

  default Likes toEntity(LikesDto dto) {
    return Likes.builder()
      .member(Member.builder().mno(dto.getMno()).build())
      .note(Note.builder().num(dto.getNum()).build())
    .build();
  }
}
