package com.eeerrorcode.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eeerrorcode.club.entity.dto.LikesDto;
import com.eeerrorcode.club.service.LikesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@Log4j2
@RequestMapping("api/v1/likes")
public class LikesController {
  @Autowired
  private LikesService service;

  @GetMapping
  public boolean get(@RequestBody LikesDto dto) {
    return service.get(dto);
  }

  @PostMapping
  public void postMethodName(@RequestBody LikesDto dto) {
    service.toggle(dto);
  }
  
}
