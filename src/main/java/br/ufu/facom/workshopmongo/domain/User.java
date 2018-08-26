package br.ufu.facom.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

// collection="user"eh opcional, caso nao coloque o Spring Data vai mapear a colecao com o nome da classe so que iniciando com letra minuscula!
@Document(collection="user") // Indica que eh uma colecao do MongoDB 
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String name;
	private String email;
	
	@DBRef(lazy=true) // Essa anotacoa indica que no "conjunto" do User, os posts serao uma colecao de referencias
	// O lazy faz com que os posts so sejam carregados caso eu explicitamente acessa-los, do contrario quando eu acessar o usuario vira apenas os dados basicos do user
	private List<Post> posts = new ArrayList<>();
	
	public User() {	
	}

	public User(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
