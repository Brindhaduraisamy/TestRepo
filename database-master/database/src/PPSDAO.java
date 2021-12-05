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
@WebServlet("/PPSDAO")
public class PPSDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public PPSDAO() {

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
         
  
   
	
   
    
    
    
	public double getPrice() throws SQLException {
		String sql = "SELECT currentPrice FROM pps";
		connect_func();
		double  currentPrice = 0;
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			currentPrice = resultSet.getDouble("currentPrice");
		}
		
		resultSet.close();
		preparedStatement.close();
		return currentPrice;
	}
	
	
	public double updatePrice(double depositAmount) throws SQLException {
		String sql = "SELECT currentPrice FROM pps";
		connect_func();
		double  currentPrice = 0;
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			currentPrice = resultSet.getDouble("currentPrice");
		}
		
		resultSet.close();
		preparedStatement.close();
		return currentPrice;
	}
	
	 public void createDatabase() throws SQLException {
	    	try {
	    		connect_func();    
	    	    
	    		System.out.println("Initialize Account Table: 000000000000000000000000000");
	    		
	    		statement = (Statement) connect.createStatement();
	    		statement = connect.createStatement();
	    		
	    		statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
	    		statement.executeUpdate("DROP TABLE IF EXISTS PPS");
	    		statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
	       		 
	    		System.out.println("created PPS Table");
	    		
	       		String PPSCreate = "CREATE TABLE PPS (currentPrice DECIMAL(15, 12) NOT NULL)"; 
	       	    statement.executeUpdate(PPSCreate);
	       	    statement.executeUpdate("INSERT INTO `testdb`.`PPS` (`currentPrice`) VALUES ('0.000001')");
	       		
	       	    System.out.println("Initialize Account Table done: 1111111111111111111111111111111");
	    	}catch (Exception e) {
	    		System.out.println(e);
	    	}finally { 
	    	      statement.close();
	    	}
	    }

	
}
