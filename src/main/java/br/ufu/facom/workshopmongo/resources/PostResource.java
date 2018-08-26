package br.ufu.facom.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufu.facom.workshopmongo.domain.Post;
import br.ufu.facom.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service; 
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		
		// Esse ResponseEntity eh pra encapsular quando recebe resposta http
		return ResponseEntity.ok().body(obj); // "ok" indica que a requisicao http foi um sucesso
	}
	
 }
