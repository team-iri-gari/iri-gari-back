package com.ssafy.tag.model.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tag.model.TagDto;
import com.ssafy.tag.model.service.TagService;

@CrossOrigin("*")
@RestController
@RequestMapping("/tag")
public class TagController {
	
	private final TagService tagService;

	public TagController(TagService tagService) {
		super();
		this.tagService = tagService;
	}
	
	@GetMapping("/recommend")
	public ResponseEntity<List<TagDto>> recommend() {
		return ResponseEntity.ok(tagService.recommend());
	}
	
}
