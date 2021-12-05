/*
 *
 The purpose of exercise 3 is to learn how to execute a CREATE/INSERT/SELECT/UPDATE/DELETE statement in Java over a table located at a local MySQL database server. 
     1) Execute each SQL statement from ch2's slides in Java. You will need to understand how method writeResultSet() works and modify it to display your results correctly. 
     2) Understand the difference between dynamic SQLs and static SQLs. 
 *
 * */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class test3  {
  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;



 public static void main(String[] args) {

    String dropSQL = "DROP TABLE IF EXISTS Student";
    String createSQL = "CREATE TABLE IF NOT EXISTS Student "
    		+ "(id INTEGER not NULL AUTO_INCREMENT, "
    		+ "Name VARCHAR(20), "
    		+ " Address VARCHAR(50), "
    		+ "Status VARCHAR(10),  "
    		+ "PRIMARY KEY ( id ))";  
//queries from chapter 2 slide
    String insertQuery1 ="insert into Student (id, Name, Address, Status) VALUES (1111,'John','123 Main','fresh')";
    String insertQuery2 ="insert into Student  (id, Name, Address, Status) VALUES(2222, 'Mary', '321 Oak', 'senior')";
    String insertQuery3 ="insert into Student (id, Name, Address, Status) VALUES(1234, 'Joan', '777 Grand', 'senior')";
    String insertQuery4 ="INSERT INTO  Student (Id, Name, Address, Status) VALUES  (111111111, 'Bill', '245 main', 'senior')";
    String selectQuery1 = "SELECT Name FROM  Student WHERE  id >= 1111";
    String selectQuery2 ="SELECT Id, Name FROM Student";
    String selectQuery3 ="SELECT Id, Name FROM Student WHERE Status = 'senior'";
    String selectQuery4 ="SELECT * FROM Student WHERE Status = 'senior'";
    String selectQuery5 ="SELECT COUNT(*) as total FROM Student WHERE Status = 'senior'";
    String updateQuery ="UPDATE  Student SET  Status = 'soph' WHERE  id = 111111111"; 
    String sq32="INSERT INTO  Student (Id, Name, Address, Status) VALUES  (999999999, 'Bill', '432 Pine', 'senior')";
    String deleteQuery="DELETE FROM  Student WHERE  Id = 111111111";
    
    try {
      System.out.println("Select a table and then print out its content.");
      // This will load the MySQL driver, each DB has its own driver
      // Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/testdb?"
              + "useSSL=false&user=john&password=john1234");

        

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();

      System.out.println("---------------------------------------------------");
      System.out.println("Drop Table if exist: " + dropSQL);
      System.out.println("---------------------------------------------------");
      statement.executeUpdate(dropSQL);
      
      System.out.println("---------------------------------------------------");
      System.out.println("Create Table Query : " + createSQL );
      System.out.println("---------------------------------------------------");
      statement.executeUpdate(createSQL);

      System.out.println("---------------------------------------------------");
      System.out.println("Insert 4 different rows into the table");
      System.out.println("---------------------------------------------------");
      statement.executeUpdate(insertQuery1);
      statement.executeUpdate(insertQuery2);
      statement.executeUpdate(insertQuery3);
      statement.executeUpdate(insertQuery4);
      
      resultSet = statement.executeQuery("select * from Student");
      writeResultSet(resultSet);
      System.out.println("---------------------------------------------------");
      
      System.out.println("---------------------------------------------------");
      System.out.println("Select Query Section: 5 Select queries output :");
      System.out.println("---------------------------------------------------");
      System.out.println("Select Query 1: SELECT Name FROM  Student WHERE  id >= 1111");
      
      resultSet= statement.executeQuery(selectQuery1);
      writeNameFromResultSet(resultSet);
      System.out.println("Select Query  1 output Ends :------------------------------");
	  

      System.out.println("---------------------------------------------------");
	  System.out.println("Select Query  2: SELECT Id, Name FROM Student");
      System.out.println("---------------------------------------------------");
	  resultSet= statement.executeQuery(selectQuery2);
	  writeIdAndNameFromResultSet(resultSet);
	  System.out.println("Select Query  2 output Ends:-------------------------------");
	  
	  System.out.println("---------------------------------------------------------------");  
	  System.out.println("Select Query  3: SELECT Id, Name FROM Student WHERE Status = 'senior'");
	  System.out.println("---------------------------------------------------------------");
	  resultSet= statement.executeQuery(selectQuery3);
	  writeIdAndNameFromResultSet(resultSet);
	  System.out.println("Select Query  3 output Ends:-------------------------------");
	  
	  System.out.println("------------------------------------------------------");
	  System.out.println("Select Query 4: SELECT * FROM Student WHERE Status = 'senior' ");
	  System.out.println("----------------------------------------------------------------------");
	  resultSet= statement.executeQuery(selectQuery4);
	  writeResultSet(resultSet);
	  System.out.println("Select Query 4 output Ends:-------------------------------");
	 
	  System.out.println("----------------------------------------------------------------------");
	  System.out.println("Select Query 5: SELECT COUNT(*) as totalCount FROM Student WHERE Status = 'senior' ");
	  System.out.println("----------------------------------------------------------------------");
      resultSet= statement.executeQuery(selectQuery5);
      resultSet.next();
      Integer count =  resultSet.getInt(1);
      System.out.println("Count:"+ count);
	  System.out.println("Select Query  5 output Ends:-------------------------------");

	  System.out.println("----------------------------------------------------------------------");
      System.out.println("UPDATE QUERY SECTION:");
      System.out.println("QUERY: UPDATE Student SET  Status = 'soph' WHERE  id = 111111111");
	  System.out.println("----------------------------------------------------------------------"); 
      statement.executeUpdate(updateQuery);      
      resultSet = statement.executeQuery("select * from Student");
      writeResultSet(resultSet);
	  System.out.println("----------------------------------------------------------------------");
      statement.executeUpdate(sq32);

      System.out.println("----------------------------------------------------------------------");
      System.out.println("DELETE QUERY SECTION:");
      System.out.println("QUERY: DELETE FROM  Student WHERE  id = 111111111 ");
	  System.out.println("----------------------------------------------------------------------");
      statement.executeUpdate(deleteQuery);
      resultSet = statement.executeQuery("select * from Student");
      writeResultSet(resultSet);
	  System.out.println("----------------------------------------------------------------------");
      
    } catch (Exception e) {
    
         System.out.println(e);
    } finally {
      close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }


  
  
  private static void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    System.out.println("print result from a table..");
    
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      Integer id = resultSet.getInt("id");
      String name = resultSet.getString("Name");
      String address = resultSet.getString("Address");
      String status = resultSet.getString("Status");
      System.out.println("id " + id);
      System.out.println("name: " + name);
      System.out.println("address: " + address);
      System.out.println("status: " + status);
      System.out.println("");
    }
  }

  
  private static void writeNameFromResultSet(ResultSet resultSet) throws SQLException {
	  // printing the Names from resultSet
      while (resultSet.next()) { 
	      String name = resultSet.getString("Name"); 
	      System.out.println("name: " + name); 
      }
}
  
  private static void writeIdAndNameFromResultSet(ResultSet resultSet) throws SQLException {
	  while (resultSet.next()) { 
    	  Integer id = resultSet.getInt("id");
	      String name = resultSet.getString("Name"); 
	      System.out.println("id " + id);
	      System.out.println("name: " + name); 
	  }
  }
  
  
  // You need to close the resultSet
  private static void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }
} 
