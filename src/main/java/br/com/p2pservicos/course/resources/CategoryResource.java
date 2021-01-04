package br.com.p2pservicos.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.p2pservicos.course.entities.Category;
import br.com.p2pservicos.course.services.CategoryService;

@RestController //é obrigatório ter esta anotation
@RequestMapping(value = "/categories") //é obrigatório dar um nome
public class CategoryResource {
	
	//Injetando a dependência do serviço
	@Autowired
	private CategoryService service;
	
	@GetMapping //Indica que é um método que responde a uma chamada GET
	public ResponseEntity<List<Category>> findAll(){
		//Metodo que retorna todos os usuários
		List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list); //Retorno para a camada do usuário será uma lista de usuários
	}
	
	//Metodo que retorna um usuário específico
	//ResponseEntity é o comando usado para retornar uma entidade
	@GetMapping(value = "/{id}") //Indica que o parâmetro virá pela URL
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		//O pathvariable permite pegar o valor passado na URL
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

}
