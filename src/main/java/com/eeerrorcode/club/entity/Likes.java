package com.eeerrorcode.club.entity;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.eeerrorcode.club.entity.composite.LikesId;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "tbl_likes")
@EnableJpaRepositories
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(LikesId.class)
public class Likes extends BaseEntity{
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;
  
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private Note note;
}
