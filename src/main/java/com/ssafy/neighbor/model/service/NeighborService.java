package com.ssafy.neighbor.model.service;

import java.util.List;

import com.ssafy.board.model.BoardDto;
import com.ssafy.neighbor.model.NeighborDto;

public interface NeighborService {

	List<NeighborDto> getNeighbors(String id);

	void deleteNeighbor(String id, String neighborId);

	void requestNeighbor(String requesterId, String addresseeId);

	List<NeighborDto> getNeighborRequests(String id);
	
	void acceptNeighborRequest(String requesterId, String addresseeId);

	void deleteNeighborRequest(String requesterId, String addresseeId);

	List<BoardDto> getLatestNeighborPosts(String id);

}
