package com.ssafy.neighbor.model.mapper;

import java.util.List;

import com.ssafy.neighbor.model.NeighborDto;

public interface NeighborMapper {

	public List<NeighborDto> selectNeighbor(String id);

}
