package com.eeerrorcode.club.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

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

  @Default
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "note", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Attach> attachs = new ArrayList<>();
}
