package com.compass.portalcompass.enums;

public enum TipoBolsa {
	REACT_NATIVE,
	SPRING_BOOT;

	TipoBolsa getEnum(String tipoBolsa) {
		 for (TipoBolsa x : TipoBolsa.values()) {
	            if (tipoBolsa.equals(x.name())) {
	                return x;
	            }
		 }
		return null;
		
	}
}
