package com.eeerrorcode.club.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.eeerrorcode.club.entity.Likes;
import com.eeerrorcode.club.entity.Member;
import com.eeerrorcode.club.entity.Note;
import com.eeerrorcode.club.entity.dto.NoteDto;
import com.eeerrorcode.club.repository.LikesRepository;
import com.eeerrorcode.club.repository.MemberRepository;
import com.eeerrorcode.club.repository.NoteRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class NoteServiceImpl implements NoteService{
  @Autowired
  private NoteRepository repository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private LikesRepository likesRepository;

  @Override
  public Long register(NoteDto dto) {
    Member member = memberRepository.findByEmail(dto.getMemberEmail());
    dto.setMno(member.getMno());
    log.info(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    log.info(dto.getAttachDtos());
    return repository.save(toEntity(dto)).getNum();
  }

  @Override
  public Optional<NoteDto> get(Long num) {
    Long count = likesRepository.count(Example.of(Likes.builder().note(Note.builder().num(num).build()).build()));
    log.info(count);
    return repository.findById(num).map(this::toDto).map(d -> { d.setLikesCnt(count); return d; });
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

  @Override
  public List<NoteDto> listAll() {
    return repository.findAll().stream().map(this::toDto).toList();
  }
  
}
