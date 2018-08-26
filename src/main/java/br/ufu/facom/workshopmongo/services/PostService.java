package br.ufu.facom.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufu.facom.workshopmongo.domain.Post;
import br.ufu.facom.workshopmongo.repository.PostRepository;
import br.ufu.facom.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj	.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}
