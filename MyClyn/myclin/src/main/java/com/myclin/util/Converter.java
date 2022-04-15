package com.myclin.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Converter {
	
	public BigDecimal converter(String value) {
		if(value == null) {
			return null;
		}
		value = value.replace(".", "").replace(",", ".");
		return new BigDecimal(value);
	}

}
