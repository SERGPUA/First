package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop.interfaces.IUserDAO;
import com.shop.objects.User;

public class UserDAO implements IUserDAO {
	Connection con;

	public UserDAO() {
	}

	public UserDAO(Connection con) {
		this.con = con;
	}

	@Override
	public List<User> getUsers() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();

		try {
			String sql = "SELECT * FROM shop.users;";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("userId"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setCantry(rs.getString("cantry"));
				user.setCity(rs.getString("city"));
				user.setAddress(rs.getString("address"));
				user.setPostCode(rs.getInt("postCode"));
				user.setRegistratinDate(rs.getDate("registratinDate"));
				user.setLastEnterDate(rs.getDate("lastEnterDate"));
				users.add(user);
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
		return users;

	}

	@Override
	public User getUserById(User user) {
		PreparedStatement ps = null;
		ResultSet rs;
		try {
			String sql = "SELECT * FROM shop.users WHERE userId=?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserID());
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setUserID(rs.getInt("userId"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setCantry(rs.getString("cantry"));
				user.setCity(rs.getString("city"));
				user.setAddress(rs.getString("address"));
				user.setPostCode(rs.getInt("postCode"));
				user.setRegistratinDate(rs.getDate("registratinDate"));
				user.setLastEnterDate(rs.getDate("lastEnterDate"));
			}
		} catch (Exception ex) {
			System.out.println("Pomilka metoda getUserById!!!");
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
		return user;

	}

	@Override
	public int updateUser(User user) {

		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "UPDATE `shop`.`users` SET `login`=?, `password`=?, `firstName`=?, `lastName`=?, `cantry`=?, `city`=?, `address`=?, `postCode`=?, `registratinDate`=?, `lastEnterDate`=? WHERE `userId`=?;";

			ps = con.prepareStatement(sql);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getCantry());
			ps.setString(6, user.getCity());
			ps.setString(7, user.getAddress());
			ps.setInt(8, user.getPostCode());
			ps.setDate(9, (Date) user.getRegistratinDate());
			ps.setDate(10, (Date) user.getLastEnterDate());
			ps.setInt(11, user.getUserID());
			count = ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Pomilka metoda updateUser!!!");
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
	public int deleteUser(User user) {

		PreparedStatement ps = null;
		int count = 0;
		try {
			String sql = "DELETE FROM `shop`.`users` WHERE `userId`=?;";

			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserID());
			count = ps.executeUpdate();
		} catch (Exception ex) {
			System.out.println("Pomilka metoda deleteUser!!!");
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
	public int createUser(User user) {
		System.out.println("------------------------------------------");
		System.out.println("Start metoda newUser...");
		System.out.println("------------------------------------------");

		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "INSERT INTO `shop`.`users` (`login`, `password`) VALUES (?,?);";

			ps = con.prepareStatement(sql);
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			System.out.println("------------------------------------------");
			System.out.println("Zapros gotov...");
			System.out.println("------------------------------------------");
			count = ps.executeUpdate();

			System.out.println("------------------------------------------");
			System.out.println("Zapros pishov...");
			System.out.println("------------------------------------------");
			System.out.println("Rezultat: " + count);
		} catch (Exception ex) {
			System.out.println("------------------------------------------");
			System.out.println("Pomilka metoda newUser!!!");
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
		return count;

	}

	@Override
	public User getUserByLogin(User user) {
		PreparedStatement ps = null;
		ResultSet rs;
		try {
			String sql = "SELECT * FROM shop.users WHERE login=?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getLogin());
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setUserID(rs.getInt("userId"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setCantry(rs.getString("cantry"));
				user.setCity(rs.getString("city"));
				user.setAddress(rs.getString("address"));
				user.setPostCode(rs.getInt("postCode"));
				user.setRegistratinDate(rs.getDate("registratinDate"));
				user.setLastEnterDate(rs.getDate("lastEnterDate"));
			}
		} catch (Exception ex) {
			System.out.println("Pomilka metoda getUserByLogin!!!");
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
		return user;

	}

}
