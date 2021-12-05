import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;        
    private AccountDAO accountDAO;
    private FollowDAO followDAO;
    private PPSDAO ppsDAO;
    private TransactionsDAO transcationsDAO;
    
    public void init() {
        userDAO = new UserDAO(); 
        accountDAO = new AccountDAO(); 
        followDAO = new FollowDAO(); 
        transcationsDAO = new TransactionsDAO(); 
        ppsDAO = new PPSDAO();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost started: 000000000000000000000000000");
        doGet(request, response);
        System.out.println("doPost finished: 11111111111111111111111111");
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet started: 000000000000000000000000000"); 
     
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {              
	            case "/initialize":
	                System.out.println("The action: initialize database");
	                InitializeDatabase(request, response);
	                break; 
	            case "/home":
	                System.out.println("The action is loading: home Page");
	                showHomePage(request, response);
	                break; 
	            case "/welcome":
	                System.out.println("The action is loading: Welcome Page");
	                showWelcomePage(request, response);
	                break; 
	            case "/banking":
	                System.out.println("The action is loading: Banking Page (Deposit/withdraw)");
	                showBankingPage(request, response);
	                break; 
	            case "/buysell":
	                System.out.println("The action is loading: Buy/Sell/Transfer Page ");
	                showBuySellPage(request, response);
	                break;
	            case "/deposit":
	                System.out.println("The action is: Deposit Dollars action");
	                depositDollars(request, response);
	                break; 
	            case "/withdraw":
	                System.out.println("The action is: Withdraw Dollars action");
	                withdrawDollars(request, response);
	                break; 
	                // listTransaction
	            case "/activity":
	                System.out.println("The action is: Activity page (for transacations)");
	                listTransaction(request, response);
	                break;
	            case "/login":
	                System.out.println("The action is: login page");
	            	loginUser(request, response);
	                break;     
	            case "/insert":
	                System.out.println("The action is: insert user");
	            	   insertUser(request, response);
	                break;
	            case "/new":
	                System.out.println("The action is: new user form");
	                showNewForm(request, response);
	                break;
	            case "/buypps":
	                System.out.println("The action is: buypps");
	                buyPPS(request, response);
	                break; 
	            case "/sellpps":
	                System.out.println("The action is: sellpps");
	                sellPPS(request, response);
	                break; 
	            case "/sendpps":
	                System.out.println("The action is: sendpps");
	                sendPPS(request, response);
	                break;
	            default:
	                System.out.println("Not sure which action, we will treat it as the show home page");
	                showHomePage(request, response);         	
	                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        System.out.println("doGet finished: 111111111111111111111111111111111111");
    }
    
    
    private void buyPPS(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        System.out.println("buyPPS Dao started: 000000000000000000000000000");
        
        HttpSession session = request.getSession();
        
        // Root user id
        String rootUser = "root";        
        // get current user
        String userid = (String)session.getAttribute("userid");    
        // get balance
        double dollarBalanceUser = accountDAO.checkBalance(userid); 
        double dollarBalanceRoot = accountDAO.checkBalance(rootUser);
        double ppsBalanceUser = accountDAO.checkPPSBalance(userid);
        double ppsBalanceRoot = accountDAO.checkPPSBalance(rootUser);
        // get current PPS price
        double ppsPrice = ppsDAO.getPrice();
        // Amount of PPS user wants to buy        
        double ppsbuycount = Double.parseDouble(request.getParameter("ppsbuycount")); 
        // Estimate cost to buy the PPS 
        double estimatedCost = ppsPrice * ppsbuycount;
        // Transaction Details
        String from = rootUser;
        String to = userid;
        String transactionType = "BUY";
        
        if( estimatedCost > dollarBalanceUser || ppsbuycount > ppsBalanceRoot) {
        	// return to the same page with error
        	request.setAttribute("msgBuyPPS", "Dollar balance is too low to make the purchase or PPSCount is too high"); 
        	System.out.println("Failure: Purchase of PPS:" + ppsbuycount + " by user:" + userid + " for Amount: " + estimatedCost  + " is NOT sucessfull");
        }else {  
        	// buy PPS from root to user
        	accountDAO.buyPPS(userid, ppsbuycount, estimatedCost); 
        	// Record Transaction
        	transcationsDAO.recordTransaction(from, to, transactionType, estimatedCost, ppsbuycount );        	
        	
        	System.out.println("Sucess: Purchase of PPS:" + ppsbuycount + " by user:" + userid + " for Amount: " + estimatedCost  + " is sucessfull");
        	request.setAttribute("msgBuyPPS", "Purchase Sucessfull !"); 
        }
        
        // return to same page after success or failure
        showBuySellPage(request,response);
        
        System.out.println("buyPPS DAO finished: 1111111111111111111111111111111");
    } 
    
    
    private void sellPPS(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        System.out.println("sell PPS DAO started: 000000000000000000000000000");
        
        HttpSession session = request.getSession();        
        // Root user id
        String rootUser = "root";        
        // get current user
        String userid = (String)session.getAttribute("userid");    
        // get balance        
        double ppsBalanceUser = accountDAO.checkPPSBalance(userid);
        // get current PPS price
        double ppsPrice = ppsDAO.getPrice();
        // Amount of PPS user wants to buy        
        double ppssellcount = Double.parseDouble(request.getParameter("ppssellcount")); 
        // Estimate cost to buy the PPS 
        double estimatedCost = ppsPrice * ppssellcount;
        // Transaction Details
        String from = userid;
        String to = rootUser;
        String transactionType = "SELL";
        
        if( ppssellcount > ppsBalanceUser ) {
        	// return to the same page with error
        	request.setAttribute("msgSellPPS", "PPS balance is low than the requested sell amount"); 
        	System.out.println("Failure: Selling of PPS:" + ppssellcount + " by user:" + userid + " for Amount: " + estimatedCost  + " is NOT sucessfull");
        }else {  
        	// Sell PPS from root to user
        	accountDAO.sellPPS(userid, ppssellcount, estimatedCost); 
        	// Record Transaction
        	transcationsDAO.recordTransaction(from, to, transactionType, estimatedCost, ppssellcount );        	
        	System.out.println("Sucess: Purchase of PPS:" + ppssellcount + " by user:" + userid + " for Amount: " + estimatedCost  + " is sucessfull");
        	request.setAttribute("msgSellPPS", "Sell PPS is Sucessfull !"); 
        }
        
        // return to same page after success or failure
        showBuySellPage(request,response);
        
        System.out.println("sellPPS Dao finished: 1111111111111111111111111111111");
    } 
    
    private void sendPPS(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        System.out.println("sell PPS DAO started: 000000000000000000000000000");
        
        HttpSession session = request.getSession();        
    
        String userid = (String)session.getAttribute("userid");  
        // get balance        
        double ppsBalanceUser = accountDAO.checkPPSBalance(userid);
        
        String recieverid = request.getParameter("recieverid"); 
      
        double sendppscount = Double.parseDouble(request.getParameter("transferPPSCount")); 
        
        String from = userid;
        String to = recieverid;
        String transactionType = "TRANSFER";
        Double depositAmount = 0.0;
        
        
        if( sendppscount > ppsBalanceUser ) {
        	// return to the same page with error
        	request.setAttribute("msgSendPPS", "PPS balance is low than the requested send amount"); 
        	System.out.println("Failure: Sending PPS:" + sendppscount + " by user:" + userid + " to user: " + recieverid  + " is NOT sucessfull");
        }else {  
            accountDAO.transferPPS(userid, recieverid, sendppscount);
            transcationsDAO.recordTransaction(from, to, transactionType, sendppscount, depositAmount );    	
        	System.out.println("Success: Sending PPS:" + sendppscount + " by user:" + userid + " to user: " + recieverid  + " is sucessfull");
        	request.setAttribute("msgSendPPS", "Sending PPS is Sucessfull !"); 
        }
        
        
        
    
        // return to same page after success or failure
        showBuySellPage(request,response);
        
        System.out.println("sellPPS Dao finished: 1111111111111111111111111111111");
    } 
    
    
    
    
    private void showBuySellPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
    	System.out.println("show banking page started: 000000000000000000000000000");
        HttpSession session = request.getSession();
        // get current user
        String userid = (String)session.getAttribute("userid");         
        // get current user's balance
        double dollarBalance = accountDAO.checkBalance(userid);
        // get current user's PPS balance
        double ppsBalance = accountDAO.checkPPSBalance(userid);
        // get current PPS price
        double ppsPrice = ppsDAO.getPrice();
        // pps price in string format for JSP
        String ppspriceStr = String.format("%.12f", ppsPrice);  
        // pps balance in string format for JSP
        String ppsBalanceStr = String.format("%.12f", ppsBalance); 
        
        request.setAttribute("userid", userid); 
        request.setAttribute("dollarBalance", dollarBalance);
        request.setAttribute("ppsBalance", ppsBalanceStr);
        request.setAttribute("ppsPrice", ppspriceStr);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("buysell.jsp");
        dispatcher.forward(request, response); 
     
        System.out.println("Banking page finished: 1111111111111111111111111111111");
    }    
    
    
    
    
    
   
    private void InitializeDatabase(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	
        System.out.println("Initialize Database started: 000000000000000000000000000");
        System.out.println("Initialize USer Table");
        userDAO.createDatabase();
        System.out.println("Initialize User table done");
        System.out.println("Initialize Account table");
		accountDAO.createDatabase();
		System.out.println("Initialize Account table done");
		System.out.println("Initialize Followers table");
		followDAO.createDatabase();
		System.out.println("Initialize Followers table done");
		transcationsDAO.createDatabase();
        
		request.setAttribute("userid", "ROOT"); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");
        dispatcher.forward(request, response);
        System.out.println("The user sees the Root Admin Page again after Initializing DB");
     
        System.out.println("Initialize Database finished: 1111111111111111111111111111111");
    }
    
    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("loginUser started: 00000000000000000000000000000000000");
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        boolean isValidLogin = userDAO.checkLogin(userid,password);
        
        if(isValidLogin) {    
        	
        	User currentUser= userDAO.getUser(userid);
        	
        	double ppsbalance = accountDAO.checkPPSBalance(userid);
            // get current user's balance
            double dollarBalance = accountDAO.checkBalance(userid);
            
        	String currentUserId = currentUser.userid ;
        	if(currentUserId.equalsIgnoreCase("root")) {
        		request.setAttribute("userid", "ROOT"); 
        		session.setAttribute("userid", "ROOT");
        		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminPage.jsp");       
	            dispatcher.forward(request, response);
        	}else {
        		request.setAttribute("userid", currentUserId); 
        		session.setAttribute("userid", currentUserId);

        		request.setAttribute("ppsbalance", ppsbalance);  
        		request.setAttribute("dollarBalance", dollarBalance);  
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");       
	            dispatcher.forward(request, response);
        	}	
        }else {
           request.setAttribute("errorMsg", "invalid login"); 
    	   RequestDispatcher dispatcher = request.getRequestDispatcher("UserLogin.jsp");       
           dispatcher.forward(request, response);
        }
         
        System.out.println("loginUser finished: 111111111111111111111111111111111111");
    }
 
    // to insert a user
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("showNewForm started: 000000000000000000000000000");
     
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertUserForm.jsp");
        dispatcher.forward(request, response);
        System.out.println("The user sees the UserLogin page now.");
     
        System.out.println("showNewForm finished: 1111111111111111111111111111111");
    }
    
    // to insert a user
    private void showHomePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("showHomePage started: 000000000000000000000000000");
     
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserLogin.jsp");
        dispatcher.forward(request, response);
        System.out.println("The user sees the Home Main page now.");
     
        System.out.println("showHomePage finished: 1111111111111111111111111111111");
    }
    
    // to insert a user
    private void showWelcomePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        System.out.println("Welcome started: 000000000000000000000000000");
        HttpSession session = request.getSession();
        // get current user
        String userid = (String)session.getAttribute("userid");         
        // get current user's balance
        double dollarBalance = accountDAO.checkBalance(userid);
        // get current user's PPS balance
        double ppsbalance = accountDAO.checkPPSBalance(userid);
        
        request.setAttribute("userid", userid); 
        request.setAttribute("dollarbalance", dollarBalance);
        request.setAttribute("ppsbalance", ppsbalance); 
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");
        dispatcher.forward(request, response);
        System.out.println("The user sees the welcome page now.");
     
        System.out.println("Welcome finished: 1111111111111111111111111111111");
    }
    
 

    // after the data of a user are inserted, this method will be called to insert the new user into the DB
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("insertUser started: 000000000000000000000000000");
     
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname"); 
        int age = Integer.parseInt(request.getParameter("age"));
        int streetnumber = Integer.parseInt(request.getParameter("streetnumber"));
        String street = request.getParameter("street"); 
        String city = request.getParameter("city"); 
        String state = request.getParameter("state"); 
        int zipcode = Integer.parseInt(request.getParameter("zipcode"));
        
        System.out.println("userid:" + userid + ", "        		
        		+ "firstname: "+ firstname + ", "
        		+ "lastname: "+ lastname + ", "
        		+ "age:" + age + " , "
        		+ "streetnumber: "+ streetnumber + ", "
        		+ "street: "+ street + ", "
        		+ "city: "+ city + ", "
        		+ "state: "+ state + ", "
        		+ "zipcode: "+ zipcode + ", "
        		+ "password: ******* " + ", "
        		);
        	User user = new User(userid, firstname, lastname, age, streetnumber, street, city, state,  zipcode, password ); 
        
        // Check for duplicate User
        boolean isUserNameExist = userDAO.checkUserName(userid);
        
        if(isUserNameExist == false) {        	
        	 userDAO.insert(user);         	
             request.setAttribute("user", user); 
             request.setAttribute("userid", userid); 
      	     RequestDispatcher dispatcher = request.getRequestDispatcher("Welcome.jsp");       
             dispatcher.forward(request, response);
        }
        else {
            request.setAttribute("errorMsg", "User id already taken, choose another user id"); 
             
     	    RequestDispatcher dispatcher = request.getRequestDispatcher("InsertUserForm.jsp");       
            dispatcher.forward(request, response);
    	}
        
        System.out.println("Ask the browser to call the list action next automatically");
         
        System.out.println("insertUser finished: 11111111111111111111111111");   
    }
 
    
    private void showBankingPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        System.out.println("show banking page started: 000000000000000000000000000");
        HttpSession session = request.getSession();
        String userid=(String)session.getAttribute("userid"); 
        // get user balance
        double dollarBalance = accountDAO.checkBalance(userid);
        
        System.out.println("Dollar Balance: " + dollarBalance + " for: "+userid);
        request.setAttribute("dollarBalance", dollarBalance);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Banking.jsp");
        dispatcher.forward(request, response); 
     
        System.out.println("Banking page finished: 1111111111111111111111111111111");
    } 
    
 
 
    
    private void depositDollars(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("deposit page started: 000000000000000000000000000");
     
        String userid = request.getParameter("userid"); 
        double depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
        
        
        double ppsAmount = 0.0;
        String from = "Bank";
        String to = userid;
        String transactionType = "Deposit";
        
        		
        accountDAO.depositDollars(userid, depositAmount);
        transcationsDAO.recordTransaction(from, to, transactionType, ppsAmount, depositAmount );
        //ppsDAO.updatePrice(depositAmount);
        
        System.out.println("User id:" + userid + ", "  + "Deposit Amount: "+ depositAmount +""	);  
        showBankingPage(request,response);
    }

    
    
    private void withdrawDollars(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("withdraw Amount started: 000000000000000000000000000");
        String userid = request.getParameter("userid"); 
        double currentBalance = accountDAO.checkBalance(userid);
        request.setAttribute("dollarBalance", currentBalance);
        
        double withdrawAmount = Double.parseDouble(request.getParameter("withdrawAmount"));;
        double ppsAmount = 0.0;
        String from = userid;
        String to = "bank";
        String transactionType = "Withdraw";
     
        if(withdrawAmount > currentBalance) {
            request.setAttribute("errorMsg", "Withdraw amount is higher than the balance, please withdraw less"); 
            showBankingPage(request,response);
        }else {
            accountDAO.withdrawDollars(userid, withdrawAmount);
            transcationsDAO.recordTransaction(from, to, transactionType, ppsAmount, withdrawAmount );
            showBankingPage(request,response);
            System.out.println("User id:" + userid + ", "  + "Withdraw Amount: "+ withdrawAmount +""	);  	
        }
    }
    
    private void listTransaction(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("list transaction started: 00000000000000000000000000000000000");
        HttpSession session = request.getSession();
        String userid=(String)session.getAttribute("userid");  
        List<Transactions> listTransactions = transcationsDAO.listAllTransactions(userid);
        request.setAttribute("listTransactions", listTransactions); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("Activity.jsp");       
        dispatcher.forward(request, response);
        System.out.println("listPeople finished: 111111111111111111111111111111111111");
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        System.out.println("deleteUser started: 000000000000000000000000000");
     
        //  int id = Integer.parseInt(request.getParameter("id"));
        //  User user = new User(id);
        //  userDAO.delete(id);
        System.out.println("Ask the browser to call the list action next automatically");
        response.sendRedirect("list"); 
     
        System.out.println("deleteUser finished: 1111111111111111111111111111111");
    }

}
