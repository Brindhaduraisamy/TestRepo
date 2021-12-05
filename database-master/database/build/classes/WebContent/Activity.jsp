<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Activity Page</title>
</head>
<body>
<input type = "hidden" name="userid" value = '${userid}'>
	<center>
		<h1>Activity Info Page</h1>
		<table  width="70%" align="left">
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
						<a href="buysell" align="center">Buy/Sell/Send</a>
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
		<div align="center"> 
			<table border="1" width="70%" align="left">
				<tr>
					<th>Transaction Number</th>
					<th>Transaction Type</th>
					<th>PPS Amount</th>
					<th>Dollar Amount</th>
					<th>From </th>
					<th>To </th>
					<th>transaction timestamp</th>
				</tr>
			
				<c:forEach items="${listTransactions}" var="transactions">				
				<tr>
					<td>${transactions.transactionNumber}</td>
					<td>${transactions.transactionType}</td> 
					<td>${transactions.ppsbalance}</td>
					<td>${transactions.dollarbalance}</td>
					<td>${transactions.orginid}</td>
					<td>${transactions.destinationid}</td>
					<td>${transactions.transtimestamp}</td>
				</tr>
				</c:forEach>
		</table>
	</div>
</body>
</html>