package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dto.AuthorDto;
import com.demo.entities.AuthorEntity;
import com.demo.mapper.AuthorMapper;
import com.demo.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepo;

	public AuthorServiceImpl(AuthorRepository authorRepo) {
		this.authorRepo = authorRepo;
	}

	@Override
	@Transactional
	public Boolean saveAuthor(AuthorDto authorDto) {

		AuthorEntity entity = AuthorMapper.convertToEntity(authorDto);
		if (entity.getBooks() != null) {
			entity.getBooks().forEach(book -> book.setAuthor(entity));
		}
		AuthorEntity savedAuthor = authorRepo.save(entity);
		if (savedAuthor.getId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public AuthorDto getAuthorById(Integer authorId) {

		Optional<AuthorEntity> authorById = authorRepo.findById(authorId);

		if (authorById.isPresent()) {
			AuthorEntity authorEntity = authorById.get();
			AuthorDto authorDto = AuthorMapper.convertToDto(authorEntity);
			return authorDto;
		}

		return null;
	}

	@Override
	public List<AuthorDto> getAuthorsByName(String authorName) {
		List<AuthorEntity> authorsByName = authorRepo.findByNameContaining(authorName);
		List<AuthorDto> authors = new ArrayList<>();
		if (!authorsByName.isEmpty()) {
			for (AuthorEntity authorEntity : authorsByName) {
				AuthorDto authorDto = AuthorMapper.convertToDto(authorEntity);
				authors.add(authorDto);
			}
			return authors;
		}
		return null;
	}
	
	@Override
	public List<AuthorDto> getAllAuthors() {
		List<AuthorDto> authors = new ArrayList<>();
		List<AuthorEntity> authorsEntity = authorRepo.findAll();
		if(!authorsEntity.isEmpty()) {
			for (AuthorEntity authorEntity : authorsEntity) {
				AuthorDto authorDto = AuthorMapper.convertToDto(authorEntity);
				authors.add(authorDto);}
			return authors;
		}
		return null;
	}

}
