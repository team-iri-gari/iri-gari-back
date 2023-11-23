package com.ssafy.member.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	void insertMember(MemberDto member);
	
	List<MemberDto> selectMembers();

	MemberDto selectMember(int no);

	void deleteMember(String no);

	void updateMember(MemberDto member);

	MemberDto selectMemberByDto(MemberDto member);

	MemberDto selectMemberById(String id);

	MemberDto selectMemberByName(String name);
}

