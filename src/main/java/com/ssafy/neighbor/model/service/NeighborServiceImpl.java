package com.ssafy.neighbor.model.service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.neighbor.model.NeighborDto;
import com.ssafy.neighbor.model.mapper.NeighborMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NeighborServiceImpl implements NeighborService {

    private final NeighborMapper neighborMapper;

    public NeighborServiceImpl(NeighborMapper neighborMapper) {
        this.neighborMapper = neighborMapper;
    }

    public List<NeighborDto> getNeighbors(String id) {
        return neighborMapper.selectNeighborsById(id);
    }

    @Transactional
    public void deleteNeighbor(String id, String neighborId) {
        neighborMapper.deleteNeighbor(id, neighborId);
    }

    @Transactional
    public void requestNeighbor(String requesterId, String addresseeId) {
        neighborMapper.insertNeighborRequest(requesterId, addresseeId);
    }
    
    public List<NeighborDto> getNeighborRequests(String id) {
        return neighborMapper.selectNeighborRequestsById(id);
    }

    @Transactional
    public void acceptNeighborRequest(String requesterId, String addresseeId) {
        neighborMapper.deleteNeighborRequest(requesterId, addresseeId);
        
        neighborMapper.insertNeighbor(requesterId, addresseeId);
        neighborMapper.insertNeighbor(addresseeId, requesterId);
    }

    @Transactional
    public void deleteNeighborRequest(String requesterId, String addresseeId) {
        neighborMapper.deleteNeighborRequest(requesterId, addresseeId);
    }

	@Override
	public List<BoardDto> getLatestNeighborPosts(String id) {
		return neighborMapper.selectLatestPostsFromNeighbors(id);
	}
	

}
