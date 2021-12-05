import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/AccountDAO")
public class AccountDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public AccountDAO() {

    }
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "useSSL=false&user=john&password=john1234");
            System.out.println(connect);
        }
    }
    
  
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    
    public void createDatabase() throws SQLException {
    	try {
    		connect_func();    
    	     System.out.println("Initialize Account Table: 000000000000000000000000000");
    		statement = (Statement) connect.createStatement();
    		statement = connect.createStatement();
    		
    		statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
    		System.out.println("Account Table: SET FOREIGN_KEY_CHECK temporary removed");
    		statement.executeUpdate("DROP TABLE IF EXISTS useraccount");
    		System.out.println("Drop Account Table: Done");
    		statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
    		System.out.println("Account Table: SET FOREIGN_KEY_CHECK again added");
    		
    		String createUserAccTable = "CREATE TABLE useraccount ("
    	        		+ "accountId varchar(30) NOT NULL,"
    	        		+ "balancePPS double,"
    	        		+ "balanceDollar double,"
    	        		+ "FOREIGN KEY (accountId) REFERENCES user(userid)"
    	        		+ ")"; 
    		
    		
    	    statement.executeUpdate(createUserAccTable);
    	    
    	    System.out.println("created Account Table new again"); 
    		
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('root', '1000000000', '10000')");
    		
    		
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('dbrindha94@gmail.com', '0', '0')");
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('anu94@gmail.com', '0', '0')");
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('magesh@gmail.com', '0', '0')");
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('annamalai@gmail.com', '0', '0')");
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('john@gmail.com', '0', '0')");
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('johnny@gmail.com', '0', '0')");
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('anna@gmail.com', '0', '0')");
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('anni@gmail.com', '0', '0')");
    		statement.executeUpdate("INSERT INTO `testdb`.`useraccount` (`accountId`, `balancePPS`, `balanceDollar`) "
					+ "VALUES ('george@gmail.com', '0', '0')");
    		
    
    	    
       		
    	 
       		
       		
     	     System.out.println("Initialize Account Table done: 1111111111111111111111111111111");
    	}catch (Exception e) {
    		System.out.println(e);
    	}finally { 
    	      statement.close();
    	}
    }

	public double checkBalance(String userid) throws SQLException {
		String sql = "SELECT balanceDollar FROM useraccount WHERE accountId = ?";

		connect_func();
		double balanceDollar = 0.0;
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userid);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			balanceDollar = resultSet.getDouble("balanceDollar");
		}
		
		resultSet.close();
		preparedStatement.close();
		
		return balanceDollar;
	}
	
	public double checkPPSBalance(String userid) throws SQLException {
		String sql = "SELECT balancePPS FROM useraccount WHERE accountId = ?";

		connect_func();
		double balancePPS = 0.0;
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userid);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			balancePPS = resultSet.getDouble("balancePPS");
		}
		
		resultSet.close();
		preparedStatement.close();
		
		return balancePPS;
	}
    
	
	// Deposit Dollars
	public boolean depositDollars(String userid, double depositAmount) throws SQLException {
		
		String sql = "UPDATE useraccount SET balanceDollar = ? WHERE accountId =  ?";
		connect_func(); 
		double currentBalance = checkBalance(userid);
		double updateBalance = currentBalance  + depositAmount;
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setDouble(1, updateBalance);
		preparedStatement.setString(2, userid);
	
		boolean rowInserted = preparedStatement.executeUpdate() > 0;
		
		preparedStatement.close();
		
		return rowInserted;
	}
	
	// Buy PPS from ROOT
	public boolean buyPPS(String userid, double buyPPSCount, double estimatedCost) throws SQLException {
		
		connect_func();
		
		String rootUser = "root";
		double rootDollarBalance = checkBalance(rootUser);		
		double userDollarBalance = checkBalance(userid);
		
		double rootPPSBalance = checkPPSBalance(rootUser);
		double userPPSBalance = checkPPSBalance(userid);
		
		// doing the Calculations needed for buying operations, which is calculating the updated pps and dollar balance for user and root
		double updatedRootDollarBal = rootDollarBalance + estimatedCost;		
		double updatedRootPPSBal = rootPPSBalance - buyPPSCount;
		
		double updatedUserDollarBal = userDollarBalance - estimatedCost;
		double updatedUserPPSBal = userPPSBalance + buyPPSCount;
		
		
		
		// first reduce PPS from root and increase the dollar balance for the root
		String updateRootBalanceSql = "UPDATE useraccount SET balancePPS =   ?, balanceDollar =  ?  WHERE accountId = ?";
	 	preparedStatement = (PreparedStatement) connect.prepareStatement(updateRootBalanceSql);
		preparedStatement.setDouble(1, updatedRootPPSBal);
		preparedStatement.setDouble(2, updatedRootDollarBal);
		preparedStatement.setString(3, rootUser);
		boolean rowsUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		
		// second increase PPS for user and decrease the dollar balance for the user
		String updateUserBalanceSQL = "UPDATE useraccount SET balancePPS = ?, balanceDollar =   ? WHERE accountId =  ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(updateUserBalanceSQL);
		preparedStatement.setDouble(1, updatedUserPPSBal);
		preparedStatement.setDouble(2, updatedUserDollarBal);
		preparedStatement.setString(3, userid);
		rowsUpdated = preparedStatement.executeUpdate() > 0;		
		
		preparedStatement.close();
		return rowsUpdated;
	}
	
	

	public boolean transferPPS(String sender, String reciever, double transferPPSCount) throws SQLException {
		
		connect_func();
		 
		double updatedSenderPPSBalance = checkPPSBalance(sender) - transferPPSCount;
		double updatedRecieverPPSBalance = checkPPSBalance(reciever) + transferPPSCount;
		
		// First reduce from sender
		String updateRootBalanceSql = "UPDATE useraccount SET balancePPS =  ?  WHERE accountId = ?";
	 	preparedStatement = (PreparedStatement) connect.prepareStatement(updateRootBalanceSql);
		preparedStatement.setDouble(1, updatedSenderPPSBalance); 
		preparedStatement.setString(2, sender);
		boolean rowsUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		
		// Add the pps
		String updateUserBalanceSQL = "UPDATE useraccount SET balancePPS = ? WHERE accountId =  ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(updateUserBalanceSQL);
		preparedStatement.setDouble(1, updatedRecieverPPSBalance); 
		preparedStatement.setString(2, reciever);
		rowsUpdated = preparedStatement.executeUpdate() > 0;		
		
		preparedStatement.close();
		
		return rowsUpdated;
	
	}
	// Sell PPS
	public boolean sellPPS(String userid, double SellPPSCount, double estimatedCost) throws SQLException {
		
		connect_func();
		
		String rootUser = "root";
		// first update root
		double rootDollarBalance = checkBalance(rootUser);		
		double userDollarBalance = checkBalance(userid);
		
		double rootPPSBalance = checkPPSBalance(rootUser);
		double userPPSBalance = checkPPSBalance(userid);
		
		// doing the Calculations needed for sell operations, which is calculating the updated pps and dollar balance for user and root
		double updatedRootDollarBal = rootDollarBalance - estimatedCost;		
		double updatedRootPPSBal = rootPPSBalance + SellPPSCount;
		
		double updatedUserDollarBal = userDollarBalance + estimatedCost;
		double updatedUserPPSBal = userPPSBalance - SellPPSCount;
		
		// First we update root . reduce dollar balance and increase pps
		String updateRootBalanceSql = "UPDATE useraccount SET balancePPS =  ?, balanceDollar = ?  WHERE accountId = ?";
	 	preparedStatement = (PreparedStatement) connect.prepareStatement(updateRootBalanceSql);
		preparedStatement.setDouble(1, updatedRootPPSBal);
		preparedStatement.setDouble(2, updatedRootDollarBal);
		preparedStatement.setString(3, rootUser);
		boolean rowsUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		
		// Second we update user . reduce PPS balance and increase Dollar balance
		String updateUserBalanceSQL = "UPDATE useraccount SET balancePPS = ?, balanceDollar = ? WHERE accountId =  ?";
		preparedStatement = (PreparedStatement) connect.prepareStatement(updateUserBalanceSQL);
		preparedStatement.setDouble(1, updatedUserPPSBal);
		preparedStatement.setDouble(2, updatedUserDollarBal);
		preparedStatement.setString(3, userid);
		rowsUpdated = preparedStatement.executeUpdate() > 0;		
		
		preparedStatement.close();
		return rowsUpdated;
	}

	
	
	// Withdraw Amount
	public boolean withdrawDollars(String userid, double withdrawAmount) throws SQLException {
		
		String sql = "UPDATE useraccount SET balanceDollar = ? WHERE accountId =  ?";
		connect_func(); 
		double currentBalance = checkBalance(userid);
		double updateBalance = currentBalance - withdrawAmount;
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setDouble(1, updateBalance);
		preparedStatement.setString(2, userid);
	
		boolean rowInserted = preparedStatement.executeUpdate() > 0;
		 
		preparedStatement.close();
		
		return rowInserted;
	}
    
  
}


