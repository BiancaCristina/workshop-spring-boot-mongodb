package br.ufu.facom.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufu.facom.workshopmongo.domain.User;
import br.ufu.facom.workshopmongo.dto.UserDTO;
import br.ufu.facom.workshopmongo.repository.UserRepository;
import br.ufu.facom.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj	.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		this.findById(id); // Verifica se o user existe antes de deletar
		
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId()); // Procura o usuario com o ID do parametro
		updateData(newObj, obj); // Chama a funcao dessa classe que realiza o update
		return repo.save(newObj); // Salva o newObj no banco de dados (alterando)
	}

	private void updateData(User newObj, User obj) {
		// Esse metodo povoa o "newObj" e povoa ele com o obj que foi passado como parametro no metodo "update" 
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(),objDTO.getName(),objDTO.getEmail());
	}
}
