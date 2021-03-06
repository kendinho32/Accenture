package com.prueba.accenture.payload;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
	
	@NotBlank
    @Size(min = 4, max = 40)
    private String name;
	
	@NotBlank
    @Size(min = 4, max = 40)
    private String email;
	
	@NotBlank
    @Size(min = 6, max = 20)
    private String password;
	
	@NotBlank
    private List<Phone> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}
