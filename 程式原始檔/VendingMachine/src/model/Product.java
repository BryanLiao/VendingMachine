package model;

public class Product {
	int id;
	private String name;
	private int price;
	private int stockQty;

	public Product() {
		super();
	}

	public Product(int id, String name, int price, int stockQty) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQty = stockQty;
	}
	
	public Product(String name, int price, int stockQty) {
		super();
		this.name = name;
		this.price = price;
		this.stockQty = stockQty;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
