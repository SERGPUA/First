package com.shop.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataSource datasource = null;
	HttpServletRequest req;
	HttpServletResponse resp;

	@Override
	public void init() throws ServletException {

		try {
			Context initContext = new InitialContext();
			datasource = (DataSource) initContext.lookup("java:comp/env/jdbc/shop");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String responsePage = "index.html";

		switch (req.getServletPath()) {
		case "/createUser.do":
			createNewUser(req, resp);
			break;

		default:
			responsePage = "errorPage.html";
		}

	}

	private void createNewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = null;
		System.out.println("connection before: " + con);
		int result = 0;
		try {
			con = (Connection) datasource.getConnection();

		} catch (SQLException e) {
			System.out.println("connection after: " + con);
			System.out.println("Pomilka connectiona");

			e.printStackTrace();
		}

		User user = new User(req.getParameter("login"), req.getParameter("password"));
		IUserDAO userDAO = new UserDAO();
		result = userDAO.createUser(user, con);
		if (result > 0) {
			req.setAttribute("sucsess", "true");
		} else {
			req.setAttribute("sucsess", "false");
		}
		req.getRequestDispatcher("result.jsp").forward(req, resp);

	}

}
