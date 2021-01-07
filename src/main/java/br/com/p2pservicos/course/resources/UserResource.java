package br.com.p2pservicos.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.p2pservicos.course.entities.User;
import br.com.p2pservicos.course.services.UserService;

@RestController //é obrigatório ter esta anotation
@RequestMapping(value = "/users") //é obrigatório dar um nome
public class UserResource {
	
	//Injetando a dependência do serviço
	@Autowired
	private UserService service;
	
	@GetMapping //Indica que é um método que responde a uma chamada GET
	public ResponseEntity<List<User>> findAll(){
		//Metodo que retorna todos os usuários
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list); //Retorno para a camada do usuário será uma lista de usuários
	}
	
	//Metodo que retorna um usuário específico
	//ResponseEntity é o comando usado para retornar uma entidade
	@GetMapping(value = "/{id}") //Indica que o parâmetro virá pela URL
	public ResponseEntity<User> findById(@PathVariable Long id) {
		//O pathvariable permite pegar o valor passado na URL
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Metodo para inserir o usuário
	@PostMapping //Indica que a chamada deve ser feita via post
	public ResponseEntity<User> insert (@RequestBody User obj) { //ResquestBody transform o objeto em Json
		obj = service.insert(obj);
		//A forma correta de retorno do Rest para inserção, é o código 201, para isso, é preciso converter para URI
		//usando as linhas abaixo
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj); //Retorna OK com o código 201 e a URL do usuário inserido
	}
	
	//Metodo para excluir um usuário
	@DeleteMapping(value = "/{id}") //Indica que o parâmetro virá pela URL //Indica que a chamada http deve ser um delete
	public ResponseEntity<Void> delete (@PathVariable Long id) { //O pathvariable permite pegar o valor passado na URL
		service.delete(id);
		return ResponseEntity.noContent().build(); //nocontent indica que não haverá retorno Json, apenas o código 204 de OK para deleção
	}
	
	//Método para atualizar um usuário PUT em Rest
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update (@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	

}
