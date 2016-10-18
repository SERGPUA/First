package com.shop.interfaces;

import java.util.List;

import com.shop.objects.Product;

public interface IProductDAO {
	public List<Product> getAllProducts();

	public Product getProductById(int productId);

	public int updateProduct(Product product);

	public int deleteProduct(int productId);

	public int createNewProduct(Product product);
}
