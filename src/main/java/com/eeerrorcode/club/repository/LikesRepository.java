package com.eeerrorcode.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eeerrorcode.club.entity.Likes;
import com.eeerrorcode.club.entity.composite.LikesId;

public interface LikesRepository extends JpaRepository<Likes, LikesId>{
  
}
