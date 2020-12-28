package br.com.p2pservicos.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.p2pservicos.course.entities.User;

@RestController //é obrigatório ter esta anotation
@RequestMapping(value = "/users") //é obrigatório dar um nome
public class UserResource {
	
	@GetMapping //Indica que é um método que responde a uma chamada GET
	public ResponseEntity<User> findAll(){
		User u = new User(1L,"Anderson","afv@mail.com","999999999","12345");
		return ResponseEntity.ok().body(u);
	}

}
