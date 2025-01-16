package com.eeerrorcode.club.service;

import java.util.List;
import java.util.Optional;

import com.eeerrorcode.club.entity.Attach;
import com.eeerrorcode.club.entity.Member;
import com.eeerrorcode.club.entity.Note;
import com.eeerrorcode.club.entity.dto.NoteDto;

public interface NoteService {
  Long register(NoteDto dto);

  Optional<NoteDto> get(Long num);

  void modify(NoteDto dto);

  void remove(Long num);

  List<NoteDto> list(String memberEmail);

  List<NoteDto> listAll();

  default Note toEntity(NoteDto dto) {
    Note note = Note.builder()
    .num(dto.getNum())
    .title(dto.getTitle())
    .content(dto.getContent())
    .member(Member.builder().email(dto.getMemberEmail()).mno(dto.getMno()).build())
    .build();

    note.setAttachs(dto.getAttachDtos().stream().map(a -> Attach.builder()
      .uuid(a.getUuid())
      .origin(a.getOrigin())
      .image(a.isImage())
      .path(a.getPath())
      .fileName(a.getFileName())
      .ext(a.getExt())
      .mime(a.getMime())
      .url(a.getUrl())
      .size(a.getSize())
      .note(note)
      .build()).toList()
    );

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
