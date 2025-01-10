package com.eeerrorcode.club.service;

import java.util.List;

import com.eeerrorcode.club.entity.Member;
import com.eeerrorcode.club.entity.Note;
import com.eeerrorcode.club.entity.dto.NoteDto;

public interface NoteService {
  Long register(NoteDto dto);

  NoteDto get(Long num);

  void modify(NoteDto dto);

  void remove(Long num);

  List<NoteDto> list(String memberEmail);

  default Note toEntity(NoteDto dto) {
    Note note = Note.builder()
    .num(dto.getNum())
    .title(dto.getTitle())
    .content(dto.getContent())
    .member(Member.builder().email(dto.getMemberEmail()).mno(dto.getMno()).build())
    .build();

    return note;
  }

  default NoteDto toDto(Note note) {
    NoteDto dto = NoteDto.builder()
    .num(note.getNum())
    .title(note.getTitle())
    .content(note.getContent())
    .mno(note.getMember().getMno())
    .memberEmail(note.getMember().getEmail())
    .regDate(note.getRegDate())
    .modDate(note.getModDate())
    .build();

    return dto;
  }
}
