package com.prueba.accenture.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Phone {
	
	@NotBlank
    @Size(min = 4, max = 40)
    private String number;
	
	@NotBlank
    @Size(min = 4, max = 40)
    private String citycode;
	
	@NotBlank
    @Size(min = 4, max = 40)
    private String contrycode;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	

}
