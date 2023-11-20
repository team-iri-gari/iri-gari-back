package com.ssafy.tag.model.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
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
	public ResponseEntity<MemberDto> detailMember(@PathVariable String id) {
		return null;
	}
	
}
