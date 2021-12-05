<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Application</title>

<script> 
          
    var check = function() {
    	  if (document.getElementById('password').value ==
    	    document.getElementById('password2').value) {
    	    document.getElementById('message').style.color = 'green';
    	    document.getElementById('message').innerHTML = 'matching';
    	  } else {
    	    document.getElementById('message').style.color = 'red';
    	    document.getElementById('message').innerHTML = 'not matching';
    	  }
    	}
   </script>


</head>
<body>
	<center>
		<h1>User Registeration</h1>
		<h2>
			<a href="login">login</a>
		</h2>
	</center>

	<div align="center">
		<form action="insert" method="post">
			<table border="1" cellpadding="5">
				<caption>
					<h2>User Sign-up Form</h2>
				</caption>
				<c:if test="${user != null}">
					<input type="hidden" name="id" />
				</c:if>

				<tr>
					<th align="left">User Name:</th>
					<!-- username -->
					<td><input type="text" name="userid" size="45"
						value="<c:out value='${user.userid}' />" />
						<input type="button" value="Check Availability" /></td>
				</tr>
				<tr>
					<th>Password:</th>
					<td><input type="password" name="password" id="password"
						size="45" onkeyup='check();' value="" /></td>
				</tr>
				<tr>
					<th>Re-Enter Password:</th>
					<td><input type="password" name="password2" id="password2"
						size="45" onkeyup='check();' /> <span id='message'></span></td>

				</tr>

				<tr>
					<th>First Name:</th>
					<!-- name -->
					<td><input type="text" name="firstname" size="45"
						value="<c:out value='${user.firstname}' />" />
					</td>
				</tr>
				<tr>
					<th>Second Name:</th>
					<td><input type="text" name="lastname" size="45"
						value="<c:out value='${user.lastname}' />" />
					</td>
				</tr>
				<tr>
					<th>Age:</th>
					<td><input type="text" name="age" size="5"
						value="<c:out value='${user.age}' />" /></td>
				</tr>

				<tr>
					<th>Street Number:</th>
					<td><input type="text" name="streetnumber" size="25"
						value="<c:out value='${user.streetnumber}' />" />
					</td>
				</tr>

				<tr>
					<th>Street:</th>
					<td><input type="text" name="street" size="45"
						value="<c:out value='${user.street}' />" />
					</td>
				</tr>

				<tr>
					<th>City:</th>
					<td><input type="text" name="city" size="25"
						value="<c:out value='${user.city}' />" /></td>
				</tr>

				<tr>
					<th>state:</th>
					<td><input type="text" name="state" size="25"
						value="<c:out value='${user.state}' />" /></td>
				</tr>

				<tr>
					<th>Zipcode:</th>
					<td><input type="text" name="zipcode" size="25"
						value="<c:out value='${user.zipcode}' />" />
					</td>
				</tr>



				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
