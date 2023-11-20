package com.ssafy.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.JwtUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final JwtUtil jwtUtil;

	public MemberController(MemberService memberService, JwtUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping
	public ResponseEntity<String> joinMember(@RequestBody MemberDto member) {
		memberService.joinMember(member);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/{id}")
	public String loginMember(@PathVariable String id, @RequestBody MemberDto member) {
		member.setId(id);
		MemberDto result = memberService.loginMember(member);
		
		if(result == null)
			return null;
		else {
			result.setPassword("");
			return jwtUtil.createToken(result);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<MemberDto> detailMember(@PathVariable String id) {
		return ResponseEntity.ok(memberService.detailMember(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable String id) {
		memberService.deleteMember(id);
		
		return ResponseEntity.ok("ok");
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateMember(@PathVariable String id, @RequestBody MemberDto member) {
		member.setId(id);
		memberService.updateMember(member);
		
		return ResponseEntity.ok("ok");
	}

}
