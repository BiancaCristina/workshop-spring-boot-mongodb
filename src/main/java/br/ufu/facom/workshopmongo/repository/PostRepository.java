package br.ufu.facom.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.ufu.facom.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	// Extends "MongoRepository" pra indicar que estamos usando o BD MongoDB
}
