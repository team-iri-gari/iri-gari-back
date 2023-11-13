package com.ssafy.member.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.member.model.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public void joinMember(MemberDto member) {
		memberMapper.insertMember(member);
	}

	@Override 
	public MemberDto loginMember(MemberDto member) {
		return memberMapper.selectMemberByDto(member);
	} 

	@Override
	public MemberDto detailMember(String id) {
		return memberMapper.selectMemberById(id);
	}

	@Override
	public void deleteMember(String no) {
		memberMapper.deleteMember(no);
	}

	@Override
	public void updateMember(MemberDto member) {
		memberMapper.updateMember(member);
	}
}
