package br.com.p2pservicos.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.p2pservicos.course.entities.Product;
import br.com.p2pservicos.course.services.ProductService;

@RestController //é obrigatório ter esta anotation
@RequestMapping(value = "/products") //é obrigatório dar um nome
public class ProductResource {
	
	//Injetando a dependência do serviço
	@Autowired
	private ProductService service;
	
	@GetMapping //Indica que é um método que responde a uma chamada GET
	public ResponseEntity<List<Product>> findAll(){
		//Metodo que retorna todos os usuários
		List<Product> list = service.findAll();
		
		return ResponseEntity.ok().body(list); //Retorno para a camada do usuário será uma lista de usuários
	}
	
	//Metodo que retorna um usuário específico
	//ResponseEntity é o comando usado para retornar uma entidade
	@GetMapping(value = "/{id}") //Indica que o parâmetro virá pela URL
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		//O pathvariable permite pegar o valor passado na URL
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

}
