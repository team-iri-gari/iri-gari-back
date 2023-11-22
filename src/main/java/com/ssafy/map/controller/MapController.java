package com.ssafy.map.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.map.model.UrlDto;

@CrossOrigin("*")
@RestController
@RequestMapping("map")
public class MapController {
	@Value("${naver.client.key}")
	private String clientKey;
	
	@Value("${naver.client.secret}")
	private String secretKey;
	
	@PostMapping("find")
	public String getPath(@RequestBody UrlDto target) throws Exception {
		System.out.println(target.getUrl());
		String urlPath = target.getUrl();
		
		URI uri = new URI(urlPath);
		
		RestTemplate restTemp = new RestTemplate();
		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-NCP-APIGW-API-KEY-ID", clientKey)
				.header("X-NCP-APIGW-API-KEY", secretKey)
				.build();
		
		ResponseEntity<String> result = restTemp.exchange(req, String.class);
		return result.getBody();
	}
}
