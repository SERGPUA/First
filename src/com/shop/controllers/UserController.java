package com.shop.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.shop.dao.UserDAO;
import com.shop.interfaces.IUserDAO;
import com.shop.objects.User;

@WebServlet(name = "UserServlet", urlPatterns = { "*.user" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataSource dataSource;
	Connection con;
	HttpServletRequest req;
	HttpServletResponse resp;

	@Override
	public void init() throws ServletException {

		try {
			Context initContext = new InitialContext();
			dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/shop");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		req = request;
		resp = response;
		String responsePage = "index.html";
		switch (req.getServletPath()) {
		case "/showAllUsers.user":
			getAllUsers();
			break;

		default:
			responsePage = "errorPage.html";
		}
	}

	private void getAllUsers() throws ServletException, IOException {
		getConnectionFromDataSource();
		IUserDAO userDAO = new UserDAO(con);
		List<User> users = userDAO.getUsers();
		req.setAttribute("users", users);
		req.setAttribute("content", "vistas/admin/products.jsp");
		req.getRequestDispatcher("/adminca.jsp").forward(req, resp);

	}

	private void getConnectionFromDataSource() {
		try {
			con = dataSource.getConnection();
			System.out.println("Beru conection z data source: " + con);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
