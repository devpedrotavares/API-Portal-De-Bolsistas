package com.compass.portalcompass.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class EstagiarioSprintId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long estagiarioId;
	private Long sprintId;
}