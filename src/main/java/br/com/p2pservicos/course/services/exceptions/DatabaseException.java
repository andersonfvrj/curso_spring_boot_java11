package br.com.p2pservicos.course.services.exceptions;

//Classe para tratar error de banco de dados de forma personalizada
public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	//Construtor padrão
	public DatabaseException(String msg) {
		super(msg);
	}

}
