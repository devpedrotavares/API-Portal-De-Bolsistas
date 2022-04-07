package com.compass.portalcompass.feignclients;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.compass.email.dto.EmailDTO;
import com.compass.email.entities.Email;

@Component
@FeignClient(name = "email", path = "/emails")
public interface EmailFeignClient {

	@PostMapping(value = "/emails")
	@Transactional
	public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDTO emailDTO);
}
