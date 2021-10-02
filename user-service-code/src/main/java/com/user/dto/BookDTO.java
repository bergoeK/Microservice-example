package com.user.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.user.model.Author;

public class BookDTO implements Serializable {
	
	private Long id;
	
	private String name;
	
	private AuthorDTO authorDTO;

	private LocalDateTime publishedDate;

	private Set<Author> authors;

}
