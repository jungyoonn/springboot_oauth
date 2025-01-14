package com.eeerrorcode.club.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eeerrorcode.club.entity.Note;
import com.eeerrorcode.club.entity.dto.NoteDto;
import com.eeerrorcode.club.repository.NoteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService{
  @Autowired
  private NoteRepository repository;

  @Override
  public Long register(NoteDto dto) {
    Note note = toEntity(dto);
    repository.save(note);
    return note.getNum();
  }

  @Override
  public Optional<NoteDto> get(Long num) {
    return repository.findById(num).map(this::toDto);
  }

  @Override
  public void modify(NoteDto dto) {
    repository.save(toEntity(dto));
  }

  @Override
  public void remove(Long num) {
    repository.deleteById(num);
  }

  @Override
  public List<NoteDto> list(String memberEmail) {
    List<Note> list = repository.findByMemberEmail(memberEmail);
    return list.stream().map(note -> toDto(note)).toList();
  }
  
}
