package com.ssafy.neighbor.model.mapper;

import java.util.List;

import com.ssafy.board.model.BoardDto;
import com.ssafy.neighbor.model.NeighborDto;

public interface NeighborMapper {

	List<NeighborDto> selectNeighborsById(String id);

	void insertNeighbor(String requesterId, String addresseeId);

	void deleteNeighbor(String id, String neighborId);

	void insertNeighborRequest(String requesterId, String addresseeId);
	
	List<NeighborDto> selectNeighborRequestsById(String id);

	void deleteNeighborRequest(String requesterId, String addresseeId);

	List<BoardDto> selectLatestPostsFromNeighbors(String id);

}
