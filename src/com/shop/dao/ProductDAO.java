package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.interfaces.IProductDAO;
import com.shop.objects.Product;
import com.shop.objects.ProductType;

public class ProductDAO implements IProductDAO {
	Connection con;

	public ProductDAO() {
	}

	public ProductDAO(Connection con) {
		this.con = con;
		System.out.println("Conection v constructori dao: " + this.con);
	}

	@Override
	public List<Product> getAllProducts() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> products = new ArrayList<>();
		ProductType type = new ProductType();

		try {
			String sql = "SELECT * FROM shop.products;";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProductID((rs.getInt("product_id")));
				product.setName(rs.getString("name"));
				product.setColor(rs.getString("color"));
				product.setSize(rs.getString("size"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				type.setName(rs.getString("productType"));
				product.setType(type);
				product.setSize(rs.getString("size"));
				products.add(product);
			}

		} catch (Exception ex) {
			System.out.println("Pomilka metoda getAllProducts!!!");
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return products;

	}

	@Override
	public Product getProductById(int productId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = new Product();
		ProductType type = new ProductType();
		try {
			String sql = "SELECT * FROM shop.products WHERE product_id=?;";

			ps = con.prepareStatement(sql);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while (rs.next()) {
				product.setProductID((rs.getInt("product_id")));
				product.setName(rs.getString("name"));
				product.setColor(rs.getString("color"));
				product.setSize(rs.getString("size"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				type.setName(rs.getString("productType"));
				product.setType(type);
				product.setSize(rs.getString("size"));
			}

		} catch (Exception ex) {
			System.out.println("Pomilka metoda getProductTypes!!!");
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return product;

	}

	@Override
	public int updateProduct(Product product) {
		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "UPDATE `shop`.`products` SET `name`=?, `color`=?, `size`=?, `price`=?, `description`=?, `productType`=? WHERE `product_id`=?;";

			ps = con.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getColor());
			ps.setString(3, product.getSize());
			ps.setDouble(4, product.getPrice());
			ps.setString(5, product.getDescription());
			ps.setString(6, product.getType().getName());
			ps.setInt(7, product.getProductID());
			count = ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Pomilka metoda updateProduct!!!");
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return count;

	}

	@Override
	public int deleteProduct(int productId) {
		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "DELETE FROM `shop`.`products` WHERE `product_id`=?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, productId);
			count = ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Pomilka metoda deleteProduct!!!");
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int createNewProduct(Product product) {
		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "INSERT INTO `shop`.`products` (`name`, `color`, `size`, `price`, `description`, `productType`) VALUES (?,?,?,?,?,?);";

			ps = con.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getColor());
			ps.setString(3, product.getSize());
			ps.setDouble(4, product.getPrice());
			ps.setString(5, product.getDescription());
			ps.setString(6, product.getType().getName());

			count = ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Pomilka metoda createNewProduct!!!");
			ex.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return count;

	}

}
