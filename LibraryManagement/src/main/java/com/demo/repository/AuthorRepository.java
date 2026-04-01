package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
	
	public List<AuthorEntity> findByNameContaining(String authorName);
	
	

}
