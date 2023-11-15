package com.ssafy.neighbor.model.service;

import java.util.List;

import com.ssafy.neighbor.model.NeighborDto;
import com.ssafy.neighbor.model.mapper.NeighborMapper;

public class NeighborServiceImpl implements NeighborService {

	private final NeighborMapper neighborMapper;

	public NeighborServiceImpl(NeighborMapper neighborMapper) {
		super();
		this.neighborMapper = neighborMapper;
	}


	@Override
	public List<NeighborDto> getNeighbors(String id) {
		return null;
	}

	@Override
	public void deleteNeighbor(String id, String neighborId) {
		
	}

	@Override
	public void requestNeighbor(String requesterId, String addresseeId) {
		
	}

	@Override
	public void acceptNeighborRequest(String requesterId, String addresseeId) {
		
	}


	@Override
	public void deleteNeighborRequest(String requesterId, String addresseeId) {
		
	}

}
