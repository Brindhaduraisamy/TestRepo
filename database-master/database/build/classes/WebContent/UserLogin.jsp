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
            <form action="login" method="post">
       			 <table border="0" cellpadding="5">
		            <caption>
		                <h2>
		                        Login 
		                </h2>
		            </caption>         
		                
		             <tr>
		                <th>User Name: </th> <!-- username -->
		                <td>
		                    <input type="text" name="userid" id="userid" size="30"  />
		                </td> 
		            </tr>
		            <tr>
		                <th>Password: </th>
		                <td>
		                    <input type="password" name="password" id="password" size="30"  />
		                </td>
		            </tr>
		       
		            <tr>
		                <td colspan="2" align="center">
		                    <input type="submit" value="login" />
		                </td>
		                
		            </tr>
		            <tr>
		                <td colspan="2" align="center">
		                 	<c:if test="${errorMsg != null}">
                    			<input type="text" name="error" size="45" border="0"
		                            value="<c:out value='${errorMsg}' />"  />        
		                    </c:if>
		                  </td>		                
		            </tr>	            
		              
		        </table>
        </form>
    </div>   
</body>
</html>