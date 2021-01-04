package br.com.p2pservicos.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.p2pservicos.course.entities.Category;
import br.com.p2pservicos.course.repositories.CategoryRepository;

//Esta classe, assim como todos os "Serviços" tem por objetivo armazenar as regras de negócio da aplicação
@Service  //Registra esta classe no Spring como serviço para que ela possa ser injetada nas outras classes OBS: é necessário
public class CategoryService {
	
	//Injetando a dependência do repositório
	@Autowired
	private CategoryRepository repository;
	
	//Metodo para retornar todos as categorias
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	//Metodo para retornar uma categoria pelo ID
	public Category findById(Long id) {
		//Optional indica que pode retornar um objeto ou não
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}

}
