package com.shop.objects;

public class Product {
	private int productID;
	private String name;
	private String color;
	private String size;
	private Double price;
	private String description;
	private int quantity = 1;
	private ProductType type;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String name, String color, String size, Double prise, String description, int quantity, ProductType type) {
		super();
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = prise;
		this.description = description;
		this.quantity = quantity;
		this.type = type;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double prise) {
		this.price = prise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", name=" + name + ", quantity=" + quantity + "]";
	}

}
