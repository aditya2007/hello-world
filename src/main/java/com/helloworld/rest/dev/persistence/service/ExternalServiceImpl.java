package com.helloworld.rest.dev.persistence.service;

import com.helloworld.rest.dev.dto.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class ExternalServiceImpl implements ExternalService {

	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
	}

	@Override
	public <T> ResponseEntity<T> execute(String url, Class<T> responseType, Object... uriVariables) {
		if (!StringUtils.isEmpty(uriVariables)) {
			url += "?userId=" + uriVariables;
		}
		return restTemplate.getForEntity(url, responseType);
	}

}
