package com.ssafy.member.model.service;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	void joinMember(MemberDto member);

	MemberDto loginMember(MemberDto member);

	MemberDto detailMember(String no);

	void deleteMember(String no);

	void updateMember(MemberDto member);
	
}
