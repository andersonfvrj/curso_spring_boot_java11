package br.com.p2pservicos.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.p2pservicos.course.services.exceptions.DatabaseException;
import br.com.p2pservicos.course.services.exceptions.ResourceNotFoundException;

//Classe para interceptar e tratar as exceções
@ControllerAdvice //Este comando faz com que a aplicação intercepte as exceções que ocorrerem
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) //Indica que toda exceção desse tipo será capturada e tratada aqui
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND; //Indica que o objeto buscado não foi encontrado
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class) //Indica que toda exceção desse tipo será capturada e tratada aqui
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Erro de integridade no banco de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST; //Indica que o objeto buscado não pode ser excluído
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
