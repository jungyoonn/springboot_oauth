package com.eeerrorcode.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "tbl_attach")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attach extends BaseEntity{
  @Id
  private String uuid;

  private String origin;

  private boolean image;

  private String path;

  private long size;

  private String mime;

  private String fileName;

  private String ext;

  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  private Note note;
}
