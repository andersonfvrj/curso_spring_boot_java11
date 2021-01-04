package br.com.p2pservicos.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.p2pservicos.course.entities.Product;
import br.com.p2pservicos.course.repositories.ProductRepository;

//Esta classe, assim como todos os "Serviços" tem por objetivo armazenar as regras de negócio da aplicação
@Service  //Registra esta classe no Spring como serviço para que ela possa ser injetada nas outras classes OBS: é necessário
public class ProductService {
	
	//Injetando a dependência do repositório
	@Autowired
	private ProductRepository repository;
	
	//Metodo para retornar todos os produtos
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	//Metodo para retornar um produto pelo ID
	public Product findById(Long id) {
		//Optional indica que pode retornar um objeto ou não
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}

}
