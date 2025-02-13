package model;

import java.sql.Timestamp;

public class OrderDetail {
	private int id;
	private int orderId;
	private int productId;
	private String productName;
	private int price;
	private int qty;
	private Timestamp createdAt;
	
	public OrderDetail() {
		super();
	}

	public OrderDetail(int orderId, int productId, String productName, int price, int qty) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.qty = qty;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
