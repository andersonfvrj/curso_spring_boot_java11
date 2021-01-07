package br.com.p2pservicos.course.services.exceptions;

//Classe para retornar tratamento de erros personalizados
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	//Construtor padrão com mensagem inicial
	public ResourceNotFoundException(Object id) {
		super("Recurso não encontrado! Id: " + id);
	}
}
