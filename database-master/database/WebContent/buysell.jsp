<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Deposit/Withdraw Page</title>
<script>
function updateTotal() {
	var ppsbuycount = document.getElementById("ppsbuycount").value;
    var ppsbuyPrice = document.getElementById("ppsbuyprice").value;
    
    var total = ppsbuycount * ppsbuyPrice; 
    document.getElementById("totalBuyAmount").value = total;
}

function updateSellTotal() {
	var ppsbuycount = document.getElementById("ppssellcount").value;
    var ppsbuyPrice = document.getElementById("ppssellPrice").value;
    
    var total = ppsbuycount * ppsbuyPrice; 
    document.getElementById("totalsellAmount").value = total;
}

</script>

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
	
	<form action="buypps" method="post">
		<input type = "hidden" name="userid" value = '${userid}'>
		<table border="1" width="70%" align="left">
			<tr>
               <td colspan="2" align="left">
                   Buy PPS 
               </td>
            </tr>
			<tr>
                <td>Username: ${userid} </td> <td> Dollar Balance: ${dollarBalance} </td>
            </tr>
			<tr>
				<td>PPS Count</td><td> <input type="text" name="ppsbuycount" id="ppsbuycount" size="45" onkeyup='updateTotal();' />
                </td>
			</tr> 
			<tr>
				<td>PPS Price</td><td>  <input type="text" name="ppsbuyprice" id="ppsbuyprice" size="100" value="${ppsPrice}" />
                </td>
			</tr>
			<tr>
				<td>Total Price</td><td> <input type="text" name="totalBuyAmount" id="totalBuyAmount" size="45" value=""/>
                </td>
			</tr>
			<tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Buy" />
                </td>
            </tr>
	  		<tr>
                <td colspan="2" align="center">
                 	<c:if test="${msgBuyPPS != null}">
                  			<input type="text" name="error" size="45" border="0"
                            value="<c:out value='${msgBuyPPS}' />"  />        
                    </c:if>
                  </td>		                
            </tr>  
	
		</table>
		</form>
	</div>
	<br>
	<br>
	
	
	
	<div align="center"> 
	
	
	
	<form action="sellpps" method="post"> 
		<input type = "hidden" name="userid" value = '${userid}'>
		<table border="1" width="70%" align="left">
			<tr>
               <td colspan="2" align="left">
                   Sell PPS
               </td>
            </tr>
			<tr>
                <td> 
						Username: ${userid} </td><td> PPS Balance: ${ppsBalance} </td>
            </tr>
			
			<tr>
				<td>PPS Count</td><td> <input type="text" name="ppssellcount" id="ppssellcount" size="45" onkeyup='updateSellTotal();'/></td>
			</tr> 
			<tr>
				<td>PPS Price</td><td> <input type="text" name="ppssellPrice" id="ppssellPrice" size="45" value="${ppsPrice}"/></td>
			</tr>
			<tr>
				<td>Total Price</td><td> <input type="text" name="totalsellAmount"  id="totalsellAmount" size="45" value=""/></td>
			</tr>
			<tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Sell" />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                 	<c:if test="${msgSellPPS != null}">
                  			<input type="text" name="error2" size="45" border="0"
                            value="<c:out value='${msgSellPPS}' />"  />        
                    </c:if>
                  </td>		                
            </tr>  
		</table>
	</form>
	</div>
	
	<div align="center"> 
	
	
	
	<form action="sendpps" method="post"> 
		<input type = "hidden" name="userid" value = '${userid}'>
		<table border="1" width="70%" align="left">
			<tr>
               <td colspan="2" align="left">
                   Send PPS
               </td>
            </tr>
			<tr>
                <td> 
						Username: ${userid} </td><td> PPS Balance: ${ppsBalance} </td>
            </tr>
			
			<tr>
				<td>Reciever address</td><td> <input type="text" name="recieverid" id="recieverid" size="45"/></td>
			</tr> 
			<tr>
				<td>PPS Count</td><td> <input type="text" name="transferPPSCount" id="transferPPSCount" size="45"  /></td>
			</tr> 
			<tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Send" />
                </td>
            </tr>
              <tr>
                <td colspan="2" align="center">
                 	<c:if test="${msgSendPPS != null}">
                  			<input type="text" name="error2" size="45" border="0"
                            value="<c:out value='${msgSendPPS}' />"  />        
                    </c:if>
                  </td>		                
            </tr> 
		</table>
	</form>
	</div>



</body>
</html>