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

import com.shop.dao.ProductTypeDAO;
import com.shop.interfaces.IProductTypeDAO;
import com.shop.objects.ProductType;

@WebServlet(name = "TypeController", urlPatterns = { "*.type" })
public class TypeController extends HttpServlet {
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
		case "/showAllTypes.type":
			getAllTypes();
			break;
		case "/goToNewTypeForm.type":
			goNewTypeForm();
			break;
		case "/createNewType.type":
			createNewType();
			break;
		case "/goToUpdateTypeForm.type":
			goUpdateTypeForm();
			break;
		case "/updateType.type":
			updateType();
			break;
		case "/deleteType.type":
			deleteType();
			break;
		default:
			responsePage = "errorPage.html";
		}
	}

	private void goNewTypeForm() throws ServletException, IOException {
		req.setAttribute("content", "vistas/admin/newTypeForm.jsp");
		req.getRequestDispatcher("adminca.jsp").forward(req, resp);

	}

	private void goUpdateTypeForm() throws ServletException, IOException {
		int typeId = Integer.parseInt(req.getParameter("id"));
		int result = 0;
		Connection con = null;
		ProductType prodType;
		try {
			con = (Connection) dataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("zfpros po id: " + typeId);
		IProductTypeDAO typeDAO = new ProductTypeDAO();
		prodType = typeDAO.getProductTypeById(typeId, con);
		System.out.println("Product type: " + prodType);
		if (prodType != null) {
			req.setAttribute("typeObject", prodType);
			req.setAttribute("content", "vistas/admin/newTypeForm.jsp");
			req.getRequestDispatcher("adminca.jsp").forward(req, resp);
		} else
			req.setAttribute("resultString", "При виконанні операції редагування типу сталася помилка! Будь ласка зверніться до адміністратора");
		req.setAttribute("content", "vistas/admin/resultPage.jsp");
		req.getRequestDispatcher("adminca.jsp").forward(req, resp);
	}

	private void createNewType() throws ServletException, IOException {
		String typeName = req.getParameter("typeName");
		System.out.println(typeName);
		int result = 0;
		Connection con = null;
		try {
			con = (Connection) dataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		IProductTypeDAO typeDAO = new ProductTypeDAO();
		result = typeDAO.createProductType(typeName, con);
		if (result > 0)
			req.setAttribute("resultString", "Операція створення нового типу пройшла успішно!");
		else
			req.setAttribute("resultString", "При виконанні операції створення типу сталася помилка! Будь ласка зверніться до адміністратора");
		req.setAttribute("content", "vistas/admin/resultPage.jsp");
		req.getRequestDispatcher("adminca.jsp").forward(req, resp);

	}

	private void updateType() throws ServletException, IOException {
		ProductType type = new ProductType();
		String newTypeName = req.getParameter("typeName");
		int typeId = Integer.parseInt(req.getParameter("id"));
		System.out.println("Nove imya: " + newTypeName);
		System.out.println("id: " + typeId);
		type.setName(newTypeName);
		type.setTypeId(typeId);
		System.out.println("Obiekt : " + type);
		int result = 0;
		Connection con = null;
		try {
			con = (Connection) dataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		IProductTypeDAO typeDAO = new ProductTypeDAO();
		result = typeDAO.updateProductType(type, con);
		if (result > 0)
			req.setAttribute("resultString", "Операція редагування  типу пройшла успішно!");
		else
			req.setAttribute("resultString", "При виконанні операції редагування типу сталася помилка! Будь ласка зверніться до адміністратора");
		req.setAttribute("content", "vistas/admin/resultPage.jsp");
		req.getRequestDispatcher("adminca.jsp").forward(req, resp);

	}

	private void deleteType() throws ServletException, IOException {
		int typeId = Integer.parseInt(req.getParameter("id"));
		int result = 0;
		Connection con = null;
		try {
			con = (Connection) dataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		IProductTypeDAO typeDAO = new ProductTypeDAO();
		result = typeDAO.deleteProductType(typeId, con);
		if (result > 0)
			req.setAttribute("resultString", "Операція видалення пройшла успішно!");
		else
			req.setAttribute("resultString", "При виконанні операції видалення сталася помилка! Будь ласка зверніться до адміністратора");
		req.setAttribute("content", "vistas/admin/resultPage.jsp");
		req.getRequestDispatcher("adminca.jsp").forward(req, resp);
	}

	private void getAllTypes() throws ServletException, IOException {
		Connection con = null;
		try {
			con = (Connection) dataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		IProductTypeDAO typeDAO = new ProductTypeDAO();
		List<ProductType> productTypes = typeDAO.getProductTypes(con);
		req.setAttribute("productTypes", productTypes);
		req.setAttribute("content", "vistas/admin/types.jsp");
		req.getRequestDispatcher("adminca.jsp").forward(req, resp);
	}
}
