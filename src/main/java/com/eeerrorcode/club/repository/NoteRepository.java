package com.eeerrorcode.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeerrorcode.club.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{
  Note findByNum(Long num);

  List<Note> findByMemberMno(Long mno);
  
  List<Note> findByMemberEmail(String email);
}
