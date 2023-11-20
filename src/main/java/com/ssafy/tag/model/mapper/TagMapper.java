package com.ssafy.tag.model.mapper;

import org.apache.ibatis.annotations.*;

import com.ssafy.tag.model.HashTagDto;
import com.ssafy.tag.model.TagDto;

import java.util.List;

@Mapper
public interface TagMapper {

	TagDto selectTag(String name);

	void insertTag(String name);

	void insertHashTag(HashTagDto hashTagDto);

	List<TagDto> selectHashTag(String boardId);

	void deleteHashTag(HashTagDto hashTagDto);

	void insertTag(TagDto tagDto);
}
