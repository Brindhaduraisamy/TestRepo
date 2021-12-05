<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Page</title>
</head>
<body>

	<div align="center">
		<h3 align="center">Root Admin Page</h3>
		 <h2 align="center"> 
            <a href="home" align="center">logout</a>
             
        </h2>
		<form action="initialize" method="post">
			<table border="0" width="70%" align="center">
				<tr>
					<td><input type="submit" value="Initialize Database" /></td>
				</tr>
			</table>
		</form>
		
		<br><br>

		<table border="1" width="70%" align="center">
			<tr>
				<th>Name</th>
				<th>PPS Balance</th>
			</tr>
			<tr>
				<td> : ${userid}</td>
				<td> 1000000000</td>
			</tr>
		</table>
	</div>
</body>
</html>