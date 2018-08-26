package br.ufu.facom.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException{
	// Diferenca entre RuntimeException e Exception eh que na Exception eu sou obrigada a tratar
	// No caso desse projeto, o tratamento ocorrera em uma classe especifica
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException (String msg) {
		super(msg);
	}
}
