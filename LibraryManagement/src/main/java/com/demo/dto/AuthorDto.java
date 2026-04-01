package com.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class AuthorDto {
	
	private String name;
	private String email;
	private String gender;
	private Long phone;
	private List<BookDto> books;

}
