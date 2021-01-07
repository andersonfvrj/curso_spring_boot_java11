package br.com.p2pservicos.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.p2pservicos.course.entities.User;
import br.com.p2pservicos.course.repositories.UserRepository;
import br.com.p2pservicos.course.services.exceptions.ResourceNotFoundException;

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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); //Se não encontrar o id, vai gerar uma exceção
	}
	
	//Metodo para inserir um usuário
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	//Método para excluir um usuário
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	//Método para atualizar um usuário
	public User update(Long id, User obj) {
		User entity = repository.getOne(id); //Apenas monitora o objeto mas não tráz de imediato
		updateData(entity, obj);
		return repository.save(entity);
	}

	//Método para alterar de fato os dados do usuário
	private void updateData(User entity, User obj) {
		// Permitindo a atualização de apenas 3 campos
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());		
	}

}
