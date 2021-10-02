package com.user.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class AuthorDTO implements Serializable {

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String vorname;

	@NotNull
	private Date dateOfBirth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
