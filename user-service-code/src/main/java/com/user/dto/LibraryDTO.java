package com.user.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class LibraryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank
	private String name;

	private Set<BookDTO> bookDTO = new HashSet<>();

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

	public Set<BookDTO> getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(Set<BookDTO> bookDTO) {
		this.bookDTO = bookDTO;
	}

}
