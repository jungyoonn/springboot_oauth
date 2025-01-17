package com.eeerrorcode.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eeerrorcode.club.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{
  Note findByNum(Long num);

  List<Note> findByMemberMno(Long mno);
  
  List<Note> findByMemberEmail(String email);

  @Query(
        "select n, count(DISTINCT l) as likescnt, count(DISTINCT a) as attachcnt \r\n" + //
        "from tbl_note n left join tbl_likes l on l.note.num = n.num left join tbl_attach a on a.note.num = n.num \r\n" + //
        "where n.member.email = :email group by n")
  // @Query("select n, COUNT(DISTINCT l.note.num, l.member.mno) as likescnt, COUNT(DISTINCT uuid) as attachcnt from tbl_note n \r\n" + //
  //       "left join tbl_member m on n.member.mno = m.mno and email = :email\r\n" + //
  //       "left join tbl_likes l on l.note.num = n.num \r\n" + //
  //       "left join tbl_attach a on a.note.num = n.num \r\n" + //
  //       "group by n.num")
  List<Object[]> findNotesBy(@Param("email") String email);

  @Query(
        "select n, count(DISTINCT l) as likescnt, count(DISTINCT a) as attachcnt \r\n" + //
        "from tbl_note n left join tbl_likes l on l.note.num = n.num left join tbl_attach a on a.note.num = n.num \r\n" + //
        "group by n")
  List<Object[]> findNotes();
}
