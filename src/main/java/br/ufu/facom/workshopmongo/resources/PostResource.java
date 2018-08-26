package br.ufu.facom.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufu.facom.workshopmongo.domain.Post;
import br.ufu.facom.workshopmongo.resources.util.URL;
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
	
	@RequestMapping(value="/titlesearch",method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text); 
		List<Post> list = service.findByTitle(text);
		
		return ResponseEntity.ok().body(list); // "ok" indica que a requisicao http foi um sucesso
	}
	
	@RequestMapping(value="/fullsearch",method=RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		
		
		text = URL.decodeParam(text); 
		Date min = URL.convertDate(minDate, new Date(0L)); // Esse new Date(OL) cria a data minima do sistema, a qual eh 1 de janeiro de 1970
		Date max = URL.convertDate(maxDate, new Date()); // Esse new Date() gera a data atual do sistema
		
		List<Post> list = service.fullSearch(text, min, max);
		
		return ResponseEntity.ok().body(list); // "ok" indica que a requisicao http foi um sucesso
	}
	
 }
