<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList, com.shop.objects.Product, com.shop.objects.ProductType" %>
	<%
	List<ProductType> productTypes = (List<ProductType>) request.getAttribute("productTypes");
	Product product = null;
	int productId = 0;
	String name = "";
	String color = "";
	String size = "";
	String price = "";
	String description = "";
	String button = "Зберегти";
	String link = "creatNewProduct.prod";
	if(request.getAttribute("product")!=null){
			product = (Product)request.getAttribute("product");
			productId = product.getProductID();
			name = product.getName();
			color = product.getColor();
			size = product.getSize();
			price = product.getPrice().toString();
			description = product.getDescription();
			button = "Змінити";
			link = "updateProduct.prod";
	}%>
<table>
<form action="<%=link%>" method="POST">
	<input type="hidden" name="productId" value="<%=productId%>">
	<label><tr><td>Назва</td><td><input type="text" name="product_name" value="<%=name%>"/></td></tr></label>
	<label><tr><td>Колір</td><td><input type="text" name="product_color" value="<%=color%>"/></td></tr></label>
	<label><tr><td>Розмір</td><td><input type="text" name="product_size" value="<%=size%>"/></td></tr></label>
	<label><tr><td>Ціна</td><td><input type="text" name="product_price" value="<%=price%>"/></td></tr></label>
	<label><tr><td>Опис</td><td><input type="text" name="product_description" value="<%=description%>"/></td></tr></label>
	<label><tr><td>Тип</td><td><select name="product_type">
	<%for(int i = 0; i < productTypes.size(); i++){%>
		<option value="<%=productTypes.get(i).getTypeId()%>" <%if(productTypes.get(i).getTypeId()== productId){%>selected<%}%>><%=productTypes.get(i).getName()%></option>
		<%} %>
	</select></td></tr></label>
	<tr><td><input type="submit" value="<%=button%>"></td></tr>
</form>
</table>
