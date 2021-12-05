<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Deposit/Withdraw Page</title>
</head>
<body>
<h1>Deposit / Withdraw Page</h1>
	<center>
		
		<table  width="80%" align="left">
			<tr>
				<td>
					<h3>
						<a href="welcome" align="center">Welcome Page</a>
					</h3>
				</td>
				<td>
					<h3>
						<a href="banking" align="center">Deposit/Withdraw</a>
					</h3>
				</td>
				<td>
					<h3>
						<a href="activity" align="center">Activity</a>
					</h3>
				</td>
				<td>
					<h3>
						<a href="buysell" align="center">Buy/Sell</a>
					</h3>
				</td>
				<td>
					<h3>
						<a href="home" align="center">Logout</a>
					</h3>
				</td>
			</tr>
		</table>
	</center>
	
	<div align="center"> 
	
	<form action="deposit" method="post">
		<input type = "hidden" name="userid" value = '${userid}'>
		<table border="1" width="70%" align="left">
			<tr>
               <td colspan="2" align="left">
                   Deposit US Dollars ($)
               </td>
            </tr>
			<tr>
                <td> 
						Username: ${userid} 
                </td>
                <td> 
						Dollar Balance: ${dollarBalance} 
                </td>
            </tr>
			<tr>
			
				<td>US Dollars</td>
				<td> <input type="text" name="depositAmount" size="45" />
                        </td>
			</tr> 
			<tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Deposit" />
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
	<br>
	<br>
	
	
	
	<div align="center"> 
	
	
	
	<form action="withdraw" method="post"> 
		<input type = "hidden" name="userid" value = '${userid}'>
		<table border="1" width="70%" align="left">
			<tr>
               <td colspan="2" align="left">
                   Withdraw US Dollars ($)
               </td>
            </tr>
			<tr>
                <td> 
						Username: ${userid} 
                </td>
                <td> 
						Dollar Balance: ${dollarBalance} 
                </td>
            </tr>
			<tr>
			
				<td  align="left">US Dollars</td>
				<td  align="left"> 
						<input type="text" name="withdrawAmount" size="45"
                            value="<c:out value='${dollarbalance}' />"
                        /> </td>
			</tr> 
			<tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Withdraw" />
                </td>
            </tr>
		</table>
		</form>
	</div>



</body>
</html>