<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List, java.util.ArrayList, com.shop.objects.ProductType" %>
    <%List<ProductType> productTypes = (ArrayList<ProductType>)request.getAttribute("productTypes");%>
<table>
<tr><th>Тип товару</th><th>Редагувати</th><th>Видалити</th></tr>
<%for(int i = 0; i < productTypes.size(); i++){ %>
	<tr><td><%=productTypes.get(i).getName()%></td><td class="update"><a href="goToUpdateTypeForm.type?id=<%=productTypes.get(i).getTypeId()%>"><img src="img/update.png" alt="Редагувати" width="30" height="30"></a></td><td class="remove"><a href="deleteType.type?id=<%=productTypes.get(i).getTypeId()%>"><img src="img/delete.png" alt="Видалити" width="30" height="30"></a></td></tr>
	<%}%>
	<tr><td id="new-type" colspan="3"><a href="goToNewTypeForm.type"><img src="img/new.png" alt="Новий тип"/></a></td></tr>
</table>