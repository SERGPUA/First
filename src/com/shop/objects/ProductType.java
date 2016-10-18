package com.shop.objects;

public class ProductType {
	private int typeId;
	private String name;

	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductType(int typeId, String name) {
		super();
		this.typeId = typeId;
		this.name = name;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductType [typeId=" + typeId + ", name=" + name + "]";
	}

}
