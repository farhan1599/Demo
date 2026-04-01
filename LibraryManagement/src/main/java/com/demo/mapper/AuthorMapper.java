package com.demo.mapper;

import org.modelmapper.ModelMapper;

import com.demo.dto.AuthorDto;
import com.demo.entities.AuthorEntity;

public class AuthorMapper {

	private static final ModelMapper mapper = new ModelMapper();

	public static AuthorDto convertToDto(AuthorEntity entity) {
		return mapper.map(entity, AuthorDto.class);
	}

	public static AuthorEntity convertToEntity(AuthorDto dto) {
		return mapper.map(dto, AuthorEntity.class);
	}

}
