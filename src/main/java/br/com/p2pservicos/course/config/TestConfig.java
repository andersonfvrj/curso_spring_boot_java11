package br.com.p2pservicos.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.p2pservicos.course.entities.User;
import br.com.p2pservicos.course.repositories.UserRepository;

//Esta classe tem por objetivo criar configurações de teste na aplicação sem afetar o ambiente produtivo
@Configuration //Indica que é uma classe de configuração
@Profile("test") //Indica que deve usar o perfil de teste que está configurado no application-test.properties. OBS: o nome usado deve ser idêntico
public class TestConfig implements CommandLineRunner { //Macete para criar os dados no BD no início do programa, tem que implementar a classe CommandLineRunner
	
	//Injeção de dependência no Spring é feita de forma automática usando os comandos abaixo
	@Autowired
	private UserRepository userRepository;

	//Método que será executado na inicialização devido ao CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//Mandando salvar no banco
		userRepository.saveAll(Arrays.asList(u1,u2));
		
	}

}
