package com.compass.portalcompass.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.compass.portalcompass.feignclients.request.EmailDTO;
import com.compass.portalcompass.feignclients.response.Email;


@Component
@FeignClient(name = "email", path = "/")
public interface EmailFeignClient {

	@PostMapping(value = "emails")
	public ResponseEntity<Email> sendEmail(@RequestBody EmailDTO emailDTO);
}