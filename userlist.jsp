<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library Management System</title>
<link rel="stylesheet" href="bookstyle.css" >
</head>
<body>
<%@page import="java.sql.*,calldatabase.*" %>
<center>
<table class="center">
<tr>
<td>
<ul>
<li><a  href="Admin_home.html">Home</a></li>
<li><a  href="add.html">Add Books</a></li>
<li><a  href="delete.html">Delete Books</a></li>
<li><a  href="booklist.jsp">Book List</a></li>
<li><a class="active" href="userlist.jsp">User List</a></li>
<li><a href="Admin_login.html">Logout</a></li>
</ul>
</td>
</tr>
</table>
<h2 style="color:red">Admin Page</h2>
<table border="2">
<tr><th colspan="8">User List</th></tr><br>
<tr><td>ID</td><td>Name</td><td>Address</td><td>Phone Number</td><td>Mail Id</td><td>Gender</td><td>Password</td></tr>
<%
try{
	Connection cn=GetData.getCn();
	PreparedStatement ps=cn.prepareStatement("select * from  user");
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
%>
<tr>
<td><%=rs.getString(1) %></td>
<td><%=rs.getString(2) %></td>
<td><%=rs.getString(3) %></td>
<td><%=rs.getString(4) %></td>
<td><%=rs.getString(5) %></td>
<td><%=rs.getString(6) %></td>
<td><%=rs.getString(7) %></td>
</tr>
<%} %>
<%}catch(Exception e){
	out.print(e);
} %>
</table>
</center>
</body>
</html>