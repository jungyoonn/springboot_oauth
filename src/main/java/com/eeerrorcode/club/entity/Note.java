package com.eeerrorcode.club.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tbl_note")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "member")
public class Note extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long num;

  private String title;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;
}
