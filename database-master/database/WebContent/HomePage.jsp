<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User Login Page</title>
    
</head>
<body>

<%-- need to debut this part of the code to make it work, ideally we would like to see 
 all people are listed intially when the page is run as the entry page.
 
<%
if(request.getParameter("listPeople") == null) { // we want to make sure that we already have all the people
	PeopleDAO peopleDAO = new PeopleDAO();        // listed in attribute 'listPeople'
	List<People> listPeople = peopleDAO.listAllPeople();
	request.setAttribute("listPeople", listPeople);       
}
%>
--%>

    <center>
        <h1>PPS Crypto Main Menu</h1>
        <h2>
            <a href="new">Sign up</a>
            &nbsp;&nbsp;&nbsp;
            <a href="home">Login</a>
             
        </h2>
    </center>
   <div align="center">
    </div>   
</body>
</html>