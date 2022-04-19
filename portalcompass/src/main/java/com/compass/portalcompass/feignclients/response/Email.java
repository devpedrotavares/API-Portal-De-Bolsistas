package com.compass.portalcompass.feignclients.response;

import java.io.Serializable;

import java.time.LocalDateTime;

import com.compass.portalcompass.feignclients.request.StatusEmail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long emailId;
	private String ownerRef;
	private String emailFrom;
	private String emailTo;
	private String subject;
	private String text;
	private LocalDateTime sendDateEmail;
	private StatusEmail statusEmail;

}
