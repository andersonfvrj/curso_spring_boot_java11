package br.com.p2pservicos.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Serializable é necessário para permitir que o objeto sera serializado e possa trafegar pela rede, BD, etc.
//A anotação entity, indica ao JPA que é uma entidade do banco de dados
@Entity
@Table(name = "tb_user") //Indica o nome da tabela que deve ser criado no BD, por padrão, o nome usado é o nome da classe
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id //indica que é um campo identificador (Ex: chave primária)
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Indica ao JPA que será um campo de auto-incremento
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@OneToMany(mappedBy = "client") //Indica um relacionamento 1 para muitos e que está mapeado pelo campo "client" na outra classe
	private List<Order> orders = new ArrayList<>();
	
	//No Spring é obrigatório criar o construtor da classe sem os atributos
	public User() {
		
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders() {
		return orders;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	

}
