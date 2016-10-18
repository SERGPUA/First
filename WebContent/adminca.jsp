<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>-->
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Сторінка Адміністратора</title>
<link href="css/adminca.css" rel="stylesheet">
</head>
<body>
	<% String content = "vistas/admin/hello.jsp"; 
	String menu ="vistas/admin/menu.jsp"; 
	if (request.getAttribute("content") != null)
	content = (String) request.getAttribute("content");
	if(request.getAttribute("menu") != null)
	content = (String)	request.getAttribute("menu"); %>
	<div id="wrapper">
		<div id="header"><div id="logo"><img src="img/logo.png" alt="Ivifit" /></div></div>
		<div id="inner-wrapper">
		<div id="menu">
			<jsp:include page="<%=menu%>"/>
		</div>
		<div id="content">
			<jsp:include page="<%=content%>"/>
			</div>
		<div class="clearfix"></div>
		</div>
		<div id="footer"></div>
	</div>

</body>
</html>