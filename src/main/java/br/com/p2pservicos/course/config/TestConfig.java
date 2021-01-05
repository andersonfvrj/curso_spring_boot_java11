package br.com.p2pservicos.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.p2pservicos.course.entities.Category;
import br.com.p2pservicos.course.entities.Order;
import br.com.p2pservicos.course.entities.OrderItem;
import br.com.p2pservicos.course.entities.Product;
import br.com.p2pservicos.course.entities.User;
import br.com.p2pservicos.course.entities.enums.OrderStatus;
import br.com.p2pservicos.course.repositories.CategoryRepository;
import br.com.p2pservicos.course.repositories.OrderItemRepository;
import br.com.p2pservicos.course.repositories.OrderRepository;
import br.com.p2pservicos.course.repositories.ProductRepository;
import br.com.p2pservicos.course.repositories.UserRepository;

//Esta classe tem por objetivo criar configurações de teste na aplicação sem afetar o ambiente produtivo
@Configuration //Indica que é uma classe de configuração
@Profile("test") //Indica que deve usar o perfil de teste que está configurado no application-test.properties. OBS: o nome usado deve ser idêntico
public class TestConfig implements CommandLineRunner { //Macete para criar os dados no BD no início do programa, tem que implementar a classe CommandLineRunner
	
	//Injeção de dependência no Spring é feita de forma automática usando os comandos abaixo
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	//Método que será executado na inicialização devido ao CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		//Primeiro chamamos para salvar os produtos
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));		
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		//Para salvar os relacionamentos, é necessário que os objetos que se relacionarão estejam salvos antes
		//Depois chamamos para salvar os relacionamentos das categorias
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		//Mandando salvar no banco
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));	
		
		//Itens do pedido
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		
	}

}
