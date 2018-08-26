package br.ufu.facom.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufu.facom.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	// Extends "MongoRepository" pra indicar que estamos usando o BD MongoDB
	
	List<Post> findByTitleContainingIgnoreCase(String text);  // Retorna posts que contenham esse title ignorando o fato de ser maiscula ou minuscula

	// O metodo abaixo eh um outro jeito de realizar o "findByTitleContainingIgnoreCase", mas manualmente!
	// Nao precisa ter um nome padronizado (como no caso do metodo "findByTitleContainingIgnoreCase"
	// "?0" indica que estou usando o primeiro parametro da funcao
	// options = "i" ignora letra maiuscula e minuscula
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle (String text);
	// Fim do metodo
}
