package com.demo.mapper;

import org.modelmapper.ModelMapper;

import com.demo.dto.BookDto;
import com.demo.entities.BookEntity;

public class BookMapper {

	private static final ModelMapper mapper = new ModelMapper();

	public static BookDto convertToDto(BookEntity entity) {
		return mapper.map(entity, BookDto.class);
	}
	
	public static BookEntity convertToEntity(BookDto dto) {
		return mapper.map(dto, BookEntity.class);
	}

}
