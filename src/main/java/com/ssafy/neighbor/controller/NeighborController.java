package com.ssafy.neighbor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.board.model.BoardDto;
import com.ssafy.neighbor.model.NeighborDto;
import com.ssafy.neighbor.model.service.NeighborService;

import java.util.List;

@CrossOrigin("/")
@RestController
@RequestMapping("/neighbor")
public class NeighborController {

    private final NeighborService neighborService;

    public NeighborController(NeighborService neighborService) {
        this.neighborService = neighborService;
    }

    // 이웃 조회
    @GetMapping("/{id}")
    public ResponseEntity<List<NeighborDto>> getNeighbors(@PathVariable String id) {
        List<NeighborDto> neighbors = neighborService.getNeighbors(id);
        return ResponseEntity.ok().body(neighbors);
    }
    
    // 이웃 삭제
    @DeleteMapping("/{id}/{neighborId}")
    public ResponseEntity<String> deleteNeighbor(@PathVariable String id, @PathVariable String neighborId) {
        neighborService.deleteNeighbor(id, neighborId);
        return ResponseEntity.ok("Neighbor deleted.");
    }

    // 이웃 신청
    @PostMapping("/request/{requesterId}/{addresseeId}")
    public ResponseEntity<String> requestNeighbor(@PathVariable String requesterId, @PathVariable String addresseeId) {
        neighborService.requestNeighbor(requesterId, addresseeId);
        return ResponseEntity.ok("Neighbor request sent.");
    }
    
    // 이웃 신청 조회
    @GetMapping("/request/{id}")
    public ResponseEntity<List<NeighborDto>> getNeighborRequests(@PathVariable String id) {
        List<NeighborDto> requests = neighborService.getNeighborRequests(id);
        return ResponseEntity.ok().body(requests);
    }

    // 이웃 신청 수락
    @PostMapping("/accept/{requesterId}/{addresseeId}")
    public ResponseEntity<String> acceptNeighborRequest(@PathVariable String requesterId, @PathVariable String addresseeId) {
        neighborService.acceptNeighborRequest(requesterId, addresseeId);
        return ResponseEntity.ok("Neighbor request accepted.");
    }

    // 이웃 신청 삭제
    @DeleteMapping("/request/{requesterId}/{addresseeId}")
    public ResponseEntity<String> deleteNeighborRequest(@PathVariable String requesterId, @PathVariable String addresseeId) {
        neighborService.deleteNeighborRequest(requesterId, addresseeId);
        return ResponseEntity.ok("Neighbor request deleted.");
    }
    
    // 이웃 최신 게시물 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<List<BoardDto>> getLatestNeighborPosts(@PathVariable String id) {
        List<BoardDto> latestPosts = neighborService.getLatestNeighborPosts(id);
        return ResponseEntity.ok().body(latestPosts);
    }
}
