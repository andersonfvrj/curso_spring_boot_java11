package br.com.p2pservicos.course.entities.enums;

public enum OrderStatus {

	//Foi criado com o número entre parênteses para evitar que no futuro alguém mude a ordem das palavras e altere os indices
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);

	//Como o ENUM foi criado com o índice definido, é necessário implementar os métodos abaixo.
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) {
			if (value.code == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid order status code!");
	}
}
