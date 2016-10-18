package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.interfaces.IProductTypeDAO;
import com.shop.objects.ProductType;

public class ProductTypeDAO implements IProductTypeDAO {

	@Override
	public int createProductType(String productName, Connection con) {

		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "INSERT INTO `shop`.`product_types` (`name`) VALUES (?);";

			ps = con.prepareStatement(sql);
			ps.setString(1, productName);
			count = ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Pomilka metoda createProductType!!!");
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
	public List<ProductType> getProductTypes(Connection con) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductType> productTypes = new ArrayList<>();

		try {
			String sql = "SELECT * FROM shop.product_types;";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ProductType prodType = new ProductType();
				prodType.setTypeId((rs.getInt("product_type_id")));
				prodType.setName(rs.getString("name"));
				productTypes.add(prodType);
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
		return productTypes;
	}

	@Override
	public int updateProductType(ProductType productType, Connection con) {

		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "UPDATE `shop`.`product_types` SET `name`=? WHERE `product_type_id`=?;";

			ps = con.prepareStatement(sql);
			ps.setInt(2, productType.getTypeId());
			ps.setString(1, productType.getName());
			count = ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Pomilka metoda updateProductType!!!");
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
	public int deleteProductType(int typeId, Connection con) {
		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "DELETE FROM `shop`.`product_types` WHERE `product_type_id`=?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, typeId);
			count = ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Pomilka metoda deleteProductType!!!");
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
	public ProductType getProductTypeById(int typeId, Connection con) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductType prodType = new ProductType();
		try {
			String sql = "SELECT * FROM shop.product_types WHERE product_type_id=?;";

			ps = con.prepareStatement(sql);
			ps.setInt(1, typeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				prodType.setTypeId((rs.getInt("product_type_id")));
				prodType.setName(rs.getString("name"));
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
		return prodType;
	}

}
