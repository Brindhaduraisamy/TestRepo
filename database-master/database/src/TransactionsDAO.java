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
import java.sql.Timestamp;
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
@WebServlet("/TransactionsDAO")
public class TransactionsDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public TransactionsDAO() {

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
    		
      	     System.out.println("Initialize Transaction Table: 000000000000000000000000000");
      	     
    		statement = (Statement) connect.createStatement();
    		statement = connect.createStatement();
    		
    		statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
    		statement.executeUpdate("DROP TABLE IF EXISTS transactions");
    		statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
    		
    		String createUserAccTable = "CREATE TABLE transactions( "
    				+ "	 	transactionNumber int NOT NULL, "
    				+ "     transactionType  varchar(10), "
    				+ "     PPSAmount double, "
    				+ "     originId varchar(30) NOT NULL, "
    				+ "     destinationId varchar(30) NOT NULL, "
    				+ "     transTimestamp timestamp, "
    				+ "     FOREIGN KEY (originId) REFERENCES user(userid), "
    				+ "     FOREIGN KEY (destinationId) REFERENCES user(userid) "
    				+ ")"; 
    		
    		
    	    statement.executeUpdate(createUserAccTable);
   	     	System.out.println("Initialize transactions Table done: 1111111111111111111111111111111");
   	     
    	}catch (Exception e) {
    		System.out.println(e);
    	}finally { 
    	      statement.close();
    	}
    }
    
	// Deposit Dollars
	public boolean recordTransaction(String from, String to, String transactionType, double dollarAmount, double ppsAmount) throws SQLException {
		connect_func();   
		statement = (Statement) connect.createStatement();
		statement = connect.createStatement();
		statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
		
		
		String sql = "INSERT INTO transactionTable (transactionType, PPSAmount, DollarAmount,originId,destinationId,transTimestamp) VALUES (?, ?, ?, ?, ?, ?)";
		 
		
		// Date date = new Date();
		long now = System.currentTimeMillis();
        Timestamp sqlTimestamp = new Timestamp(now);
        
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, transactionType);
		preparedStatement.setDouble(2, ppsAmount);
		preparedStatement.setDouble(3, dollarAmount);
		preparedStatement.setString(4, from);
		preparedStatement.setString(5, to);
		preparedStatement.setTimestamp(6, sqlTimestamp);
		
		boolean rowInserted = preparedStatement.executeUpdate() > 0;
		
		
		statement.close();
		preparedStatement.close();
		
		return rowInserted;
	}
    
    public List<Transactions> listAllTransactions(String userid) throws SQLException {
        List<Transactions> listTransactions = new ArrayList<Transactions>();        
        String sql = "SELECT * FROM transactionTable WHERE originId = ? OR destinationId= ?";
        connect_func();      
        
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userid);
		preparedStatement.setString(2, userid);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		 
         
        while (resultSet.next()) {
            int transactionNumber = resultSet.getInt("transactionNumber");
            String transactionType = resultSet.getString("transactionType");
            Double PPSAmount = resultSet.getDouble("PPSAmount");
            Double DollarAmount = resultSet.getDouble("DollarAmount");
            String originId = resultSet.getString("originId");
            String destinationId = resultSet.getString("destinationId");
            Timestamp transTimestamp = resultSet.getTimestamp("transTimestamp");
            
            Transactions transaction = new Transactions(transactionNumber, transactionType, PPSAmount, DollarAmount,  originId,
            		destinationId,  transTimestamp);
            listTransactions.add(transaction);
        }        
        resultSet.close();
        preparedStatement.close();         
        disconnect();        
        return listTransactions;
    }
    
}


