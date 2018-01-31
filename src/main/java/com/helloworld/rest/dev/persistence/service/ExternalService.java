package com.helloworld.rest.dev.persistence.service;

import com.helloworld.rest.dev.dto.Post;
import org.springframework.http.ResponseEntity;

public interface ExternalService {

	<T> ResponseEntity<T> execute(String url, Class<T> responseType, Object... uriVariables);

}
