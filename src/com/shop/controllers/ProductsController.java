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

import com.shop.dao.ProductDAO;
import com.shop.dao.ProductTypeDAO;
import com.shop.interfaces.IProductDAO;
import com.shop.interfaces.IProductTypeDAO;
import com.shop.objects.Product;
import com.shop.objects.ProductType;

@WebServlet(name = "ProductController", urlPatterns = { "*.prod" })
public class ProductsController extends HttpServlet {
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
		case "/showAllProducts.prod":
			getAllProducts();
			break;
		case "/goToNewProductForm.prod":
			goToNewProductForm();
			break;
		case "/creatNewProduct.prod":
			createNewProduct();
			break;
		case "/updateProduct.prod":
			updateProduct();
		case "/deleteProduct.prod":
			deleteProduct();
			break;
		default:
			responsePage = "errorPage.html";
		}

	}

	private void updateProduct() throws ServletException, IOException {
		int result;
		ProductType type = new ProductType();
		Product product = new Product();
		if (req.getParameter("productId") != null)
			product.setProductID(Integer.parseInt(req.getParameter("productId")));
		if (req.getParameter("product_name") != null)
			product.setName(req.getParameter("product_name"));
		if (req.getParameter("product_color") != null)
			product.setColor(req.getParameter("product_color"));
		if (req.getParameter("product_size") != null)
			product.setSize(req.getParameter("product_size"));
		if (req.getParameter("product_price") != null)
			product.setPrice(Double.parseDouble(req.getParameter("product_price")));
		if (req.getParameter("product_description") != null)
			product.setDescription(req.getParameter("product_description"));
		if (req.getParameter("product_type") != null)
			type.setName(req.getParameter("product_type"));
		product.setType(type);
		getConnectionFromDataSource();
		IProductDAO productDAO = new ProductDAO(con);
		result = productDAO.updateProduct(product);
		if (result > 0)
			req.setAttribute("resultString", "Операція оновленя пройшла успішно!");
		else
			req.setAttribute("resultString", "При виконанні операції оновленя сталася помилка! Будь ласка зверніться до адміністратора");
		req.setAttribute("content", "vistas/admin/resultPage.jsp");
		req.getRequestDispatcher("/adminca.jsp").forward(req, resp);

	}

	private void createNewProduct() throws ServletException, IOException {
		int result;
		ProductType type = new ProductType();
		Product product = new Product();
		if (req.getParameter("product_name") != null)
			product.setName(req.getParameter("product_name"));
		if (req.getParameter("product_color") != null)
			product.setColor(req.getParameter("product_color"));
		if (req.getParameter("product_size") != null)
			product.setSize(req.getParameter("product_size"));
		if (req.getParameter("product_price") != null)
			product.setPrice(Double.parseDouble(req.getParameter("product_price")));
		if (req.getParameter("product_description") != null)
			product.setDescription(req.getParameter("product_description"));
		if (req.getParameter("product_type") != null)
			type.setName(req.getParameter("product_type"));
		product.setType(type);
		getConnectionFromDataSource();
		IProductDAO productDAO = new ProductDAO(con);
		result = productDAO.createNewProduct(product);
		if (result > 0)
			req.setAttribute("resultString", "Операція створення пройшла успішно!");
		else
			req.setAttribute("resultString", "При виконанні операції створення сталася помилка! Будь ласка зверніться до адміністратора");
		req.setAttribute("content", "vistas/admin/resultPage.jsp");
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

	private void getAllProducts() throws ServletException, IOException {
		getConnectionFromDataSource();
		IProductDAO productDAO = new ProductDAO(con);
		List<Product> products = productDAO.getAllProducts();
		req.setAttribute("products", products);
		req.setAttribute("content", "vistas/admin/products.jsp");
		req.getRequestDispatcher("/adminca.jsp").forward(req, resp);
	}

	private void goToNewProductForm() throws ServletException, IOException {
		getConnectionFromDataSource();
		if (req.getParameter("id") != null) {
			IProductDAO productDAO = new ProductDAO(con);
			Product product = productDAO.getProductById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("product", product);
		}
		IProductTypeDAO typeDAO = new ProductTypeDAO();
		getConnectionFromDataSource();
		List<ProductType> productTypes = typeDAO.getProductTypes(con);
		req.setAttribute("productTypes", productTypes);
		req.setAttribute("content", "vistas/admin/newProductForm.jsp");
		req.getRequestDispatcher("/adminca.jsp").forward(req, resp);
	}

	private void deleteProduct() throws ServletException, IOException {
		int result = 0;
		getConnectionFromDataSource();
		IProductDAO productDAO = new ProductDAO(con);
		result = productDAO.deleteProduct(Integer.parseInt(req.getParameter("id")));
		if (result > 0)
			req.setAttribute("resultString", "Операція видалення пройшла успішно!");
		else
			req.setAttribute("resultString", "При виконанні операції видалення сталася помилка! Будь ласка зверніться до адміністратора");
		req.setAttribute("content", "vistas/admin/resultPage.jsp");
		req.getRequestDispatcher("/adminca.jsp").forward(req, resp);
	}
}
