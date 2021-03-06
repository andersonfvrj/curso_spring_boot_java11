package br.com.p2pservicos.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.p2pservicos.course.entities.enums.OrderStatus;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id //indica que é um campo identificador (Ex: chave primária)
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Indica ao JPA que será um campo de auto-incremento
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") //Comando para mostrar a data formatada
	private Instant moment; //a partir do Java 8 é melhor que a classe date para datas

	private Integer orderStatus; //Está sendo declarado como integer para dizer explicitamente que será gravado no BD como inteiro	
	
	//Estas anotations são necessárias para que o hibernate/JPA crie os relacionamentos no banco de dados
	@ManyToOne //Indica um relacionamento muitos para um no BD
	@JoinColumn(name = "client_id") //Indica entre as tabelas será feito pelo campo "client_id"
	private User client;
	
	@OneToMany(mappedBy = "id.order") //Indica que se relaciona com a classe OrderItensPK pelo campo id.order
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) //Indica que se relaciona com a outra classe a partir do
	//campo order e o cascadeALL vai dar o mesmo ID do pedido para o pagamento
	private Payment payment;
	
	public Order() {		
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
		setOrderStatus(orderStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	//Está sendo feito dessa forma para converter o ENUM para inteiro do BD
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	//Está sendo feito dessa forma para converter o inteiro que vem do BD para Enum
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	//Metodo para retornar o total do pedido
	public Double getTotal() { //Usando o get na frente para indicar ao Java EE que deve retornar valor
		Double sum = 0.0;
		for (OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
