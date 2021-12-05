<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    String dburl="jdbc:mysql://localhost:3306/username_available_db"; //database url string the "username_available_db" is database name
    String dbusername="john"; //database username   
    String dbpassword ="john1234"; //database password
    
    if(request.getParameter("uname")!=null) //get "uname" jQuery & Ajax part this line 'data:"uname="+username' from index.jsp file check not null
    {
        String user_name=request.getParameter("uname"); //getable "uname" store in new "user_name variable"
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); //load driver
            Connection con=DriverManager.getConnection(dburl,dbusername,dbpassword); //create connection
           
            PreparedStatement pstmt=null; //create statement
            
            pstmt=con.prepareStatement("SELECT * FROM tbl_user WHERE username=? "); //sql select query
            pstmt.setString(1,user_name); //set where cluase condition username set is new user_name variable
            ResultSet rs=pstmt.executeQuery(); //execute query and set in ResultSet object "rs".
            
            if(rs.next())               
            {  
                %>
                <span class="text-danger">Sorry, <%=rs.getString("username")%> Already Exists ! </span>
                <%
            }
            else
            {
                %>
                <span class="text-success">Username is available</span>
                <%
            }

            con.close(); //close connection
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
%>