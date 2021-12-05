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
@WebServlet("/UserDAO")
public class UserDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public UserDAO() {

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
					.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?" + "useSSL=false&user=john&password=john1234");
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

			System.out.println("Initialize User Table: 000000000000000000000000000");

			statement = (Statement) connect.createStatement();
			statement = connect.createStatement();

			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
			statement.executeUpdate("DROP TABLE IF EXISTS user");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

			String createUserTable = "CREATE TABLE user (" + "userid varchar(30) NOT NULL,"
					+ "firstname varchar(20) NOT NULL," + "lastname varchar(20)," + "age int," + "streetnumber int,"
					+ "street varchar(30)," + "city varchar(20)," + "state varchar(20)," + "zipcode int,"
					+ "loginpassword varchar(20)," + "PRIMARY KEY (userid)" + ")";

			statement.executeUpdate(createUserTable);
			System.out.println("Initialize user Table done: 1111111111111111111111111111111");

			
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('root', 'root', 'root', '1', '1', 'w wayne', 'detroit', 'mi', '48141', 'pass1234')");

			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('dbrindha94@gmail.com', 'brindha', 'duraisamy', '27', '255', 'w wayne st', 'dearborn', 'mi', '48141', 'brindha1234')");
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('anu94@gmail.com', 'anu', 'duraisamy', '25', '255', 'w wayne st', 'dearborn', 'mi', '48141', 'anu1234')");
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('magesh@gmail.com', 'magesh', 'mohan', '32', '255', 'w wayne st', 'dearborn', 'mi', '48141', 'magesh1234')");
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('annamalai@gmail.com', 'annamalai', 'malai', '27', '123', 'w wayne st', 'detroit', 'mi', '48001', 'malai1234')");
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('john@gmail.com', 'john', 'david', '25', '955', 'w wayne st', 'detroit', 'mi', '48001', 'john1234')");
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('johnny@gmail.com', 'johnny', 'davidson', '25', '955', 'w wayne st', 'detroit', 'mi', '48001', 'johnny1234')");
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('anna@gmail.com', 'anna', 'kimble', '30', '430', 'w wayne st', 'detroit', 'mi', '48001', 'anna1234')");
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('anni@gmail.com', 'anni', 'kimble', '31', '430', 'w wayne st', 'detroit', 'mi', '48001', 'anni1234')");
			statement.executeUpdate("INSERT INTO `testdb`.`user` (`userid`, `firstname`, `lastname`, `age`, `streetnumber`, `street`, `city`, `state`, `zipcode`, `loginpassword`) "
					+ "VALUES ('george@gmail.com', 'george', 'david', '30', '230', 'w wayne st', 'detroit', 'mi', '48001', 'george1234')");
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			statement.close();
		}
	}

	public boolean insert(User user) throws SQLException {
		connect_func();

		boolean duplicateUserName = checkUserName(user.userid);

		if (duplicateUserName == true) {
			return false; // userid already exist
		}

		String sql = "insert into  user(userid, firstname, lastname, age, streetnumber, street, city, state, zipcode, loginpassword) values (?,?,?,?,?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.userid);
		preparedStatement.setString(2, user.firstname);
		preparedStatement.setString(3, user.lastname);
		preparedStatement.setInt(4, user.age);
		preparedStatement.setInt(5, user.streetnumber);
		preparedStatement.setString(6, user.street);
		preparedStatement.setString(7, user.city);
		preparedStatement.setString(8, user.state);
		preparedStatement.setInt(9, user.zipcode);
		preparedStatement.setString(10, user.password);
		boolean rowInserted = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();

		return rowInserted;
	}

	public boolean delete(int userid) throws SQLException {
		String sql = "DELETE FROM student WHERE id = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, userid);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
//        disconnect();
		return rowDeleted;
	}

	public boolean checkUserName(String userid) throws SQLException {

		String sql = "SELECT * FROM user WHERE userid = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userid);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return true;
		}

		resultSet.close();
		preparedStatement.close();

		return false;
	}

	public boolean checkLogin(String userid, String loginpassword) throws SQLException {

		User user = null;
		String sql = "SELECT * FROM user WHERE userid = ? AND loginpassword = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userid);
		preparedStatement.setString(2, loginpassword);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return true;
		}

		resultSet.close();
		preparedStatement.close();

		return false;
	}
	
	public double checkBalance(String userid) throws SQLException {
		String sql = "SELECT balanceDollar FROM useraccount WHERE accountId = ?";
		connect_func();
		double balanceDollar = 0.0;
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userid);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()) {
			balanceDollar = resultSet.getInt("balanceDollar");
		}
		
		resultSet.close();
		preparedStatement.close();
		return balanceDollar;
	}

	

	public User getUser(String userid) throws SQLException {
		User user = null;
		String sql = "SELECT * FROM user WHERE userid = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, userid);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) { 
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			int age = resultSet.getInt("age");
			int streetnumber =resultSet.getInt("streetnumber");
			String street = resultSet.getString("street");
			String city = resultSet.getString("city");
			String state = resultSet.getString("state");
			int zipcode =resultSet.getInt("zipcode");
			user = new User(userid, firstname, lastname, age, streetnumber, street, city, state,  zipcode ); 
		}
		resultSet.close();
		preparedStatement.close();
		return user;
	}
}
