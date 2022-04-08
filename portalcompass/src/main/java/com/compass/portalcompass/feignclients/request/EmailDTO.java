package com.compass.portalcompass.feignclients.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

	private String ownerRef;
	private String emailFrom;
	private String emailTo;
	private String subject;
	private String text;
}
