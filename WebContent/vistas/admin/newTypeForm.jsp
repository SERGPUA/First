<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.shop.objects.ProductType" %> 
   
    <%
    ProductType productType = null;
    String value = "";
    String buttonName = "Зберегти тип";
    String link = "createNewType.type";
    String idForm ="";
    if(request.getAttribute("typeObject")!=null){
    	productType = (ProductType) request.getAttribute("typeObject");
    	value = productType.getName();
    	idForm = "<input type='hidden' name='id' value='" + productType.getTypeId() + "'/>";
    	buttonName = "Змінити";
   		link = "updateType.type";
    }
    	%>
    <form action="<%=link %>" method="POST">
<Label>Тип товару<input type="text" name="typeName" value="<%=value%>" /></Label>
<%=idForm %>
<input type="submit" value="<%=buttonName %>"/>
</form>
