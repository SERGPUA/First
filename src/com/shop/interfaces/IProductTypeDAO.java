package com.shop.interfaces;

import java.sql.Connection;
import java.util.List;

import com.shop.objects.ProductType;

public interface IProductTypeDAO {

	public int createProductType(String typeName, Connection con);

	public List<ProductType> getProductTypes(Connection con);

	public ProductType getProductTypeById(int typeId, Connection con);

	public int updateProductType(ProductType productType, Connection con);

	public int deleteProductType(int typeId, Connection con);

}
