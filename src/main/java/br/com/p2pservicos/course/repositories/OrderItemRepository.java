package br.com.p2pservicos.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.p2pservicos.course.entities.OrderItem;

//Esta classe passa como parâmetro a entidade e o tipo da chave primária, no caso o campo ID que é Long
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
