package br.com.p2pservicos.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.p2pservicos.course.entities.User;
import br.com.p2pservicos.course.repositories.UserRepository;

//Esta classe, assim como todos os "Serviços" tem por objetivo armazenar as regras de negócio da aplicação
@Service  //Registra esta classe no Spring como serviço para que ela possa ser injetada nas outras classes OBS: é necessário
public class UserService {
	
	//Injetando a dependência do repositório
	@Autowired
	private UserRepository repository;
	
	//Metodo para retornar todos os usuários
	public List<User> findAll(){
		return repository.findAll();
	}
	
	//Metodo para retornar um usuário pelo ID
	public User findById(Long id) {
		//Optional indica que pode retornar um objeto ou não
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}

}
