package br.ufu.facom.workshopmongo.repository;

import java.util.Date;
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
	
	// O metodo abaixo pesquisa uma string tanto no titulo, no post ou nos comentarios e em um intervalo de tempo definido
	// O query indica que vai procurar nas opcoes: ( > date.min) && (< date.max) && (pertence ao texto OR body OR comments.text)
	// gte = maior que, lte = menor que
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	// Fim do metodo
}
