package br.com.p2pservicos.course.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.p2pservicos.course.entities.pk.OrderItemPK;

@Entity
@Table(name =  "tb_order_item")
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId //Indica que esse ID é composto
	private OrderItemPK id = new OrderItemPK(); //Variavel do tipo da classe auxiliar que criamos para o relacionamento
	//como é uma chave composta, o objeto deve ser iniciado de cara
	
	private Integer quantity;
	
	private Double price;
	
	public OrderItem() {
		
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore //Para ignorar a chamada redundante do relacionamento com os itens do pedido
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
		
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product prod) {
		id.setProduct(prod);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	//Metodo para obter o subtotal dos itens do pedido
	public Double getSubTotal() { //No Java Enterprise, tem que usar o get na frente para ele saber que deve retornar valor
		return price * quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
