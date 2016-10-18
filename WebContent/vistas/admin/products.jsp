<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List, java.util.ArrayList, com.shop.objects.Product" %>
    <%List<Product> products = (ArrayList<Product>)request.getAttribute("products");%>
<%for(int i = 0; i < products.size(); i++){ %>
	<div class="wrapper-product">
		<div class="picture-product">
			Picture
		</div>
		<div class="update-prod">
			<a href="goToNewProductForm.prod?id=<%=products.get(i).getProductID()%>">
				<img src="img/update.png" alt="Редагувати" width="30" height="30"/>
			</a>
		</div>
		<div class="remove-prod">
			<a href="deleteProduct.prod?id=<%=products.get(i).getProductID()%>">
				<img src="img/delete.png" alt="Видалити" width="30" height="30"/>
			</a>
		</div >
		<div class="name-product">
			<b><%=products.get(i).getName()%></b>
		</div>
		<div class="discription-product">
			<%=products.get(i).getDescription()%>
		</div>
		<div class="price-product">
			<h4><%=products.get(i).getPrice()%> &euro;</h4>
		</div>
		<div class="clearfix"></div>
	</div>
	<%}%>
	<div id="new-product">
		<a href="goToNewProductForm.prod">
			<img src="img/new.png" alt="Новий продукт"/>
		</a>
	</div>

