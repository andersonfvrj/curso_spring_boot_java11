package br.com.p2pservicos.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.p2pservicos.course.entities.Order;
import br.com.p2pservicos.course.services.OrderService;

@RestController //é obrigatório ter esta anotation
@RequestMapping(value = "/orders") //é obrigatório dar um nome
public class OrderResource {
	
	//Injetando a dependência do serviço
	@Autowired
	private OrderService service;
	
	@GetMapping //Indica que é um método que responde a uma chamada GET
	public ResponseEntity<List<Order>> findAll(){
		//Metodo que retorna todos os pedidos
		List<Order> list = service.findAll();
		
		return ResponseEntity.ok().body(list); //Retorno para a camada do usuário será uma lista de pedidos
	}
	
	//Metodo que retorna um usuário específico
	//ResponseEntity é o comando usado para retornar uma entidade
	@GetMapping(value = "/{id}") //Indica que o parâmetro virá pela URL
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		//O pathvariable permite pegar o valor passado na URL
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

}
