package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {
	  
	   public static void main(String[] args) {  
	  
	      // Create a variable for the connection string.  
	      //String connectionUrl = "jdbc:sqlserver://bd;" +  
	      //   "databaseName=jugadores;user=jugadores;password=jugadores";
		   
	      /*
	       * DataBase= Interactive_Applications User= TP_2018 Password= yuM;3ZT3
	       */
		   
		   
		  //String connectionUrl = "jdbc:sqlserver://pollux.database.windows.net:1433;" +
			//	  "database=Interactive_Applications;user=TP_2018;password={yuM;3ZT3};";
	  
		   String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=servicioReclamos;user=sa;password={Some_password123};";
	      // Declare the JDBC objects.  
	      Connection con = null;  
	      Statement stmt = null;  
	      ResultSet rs = null;  
	  
	      try {  
	         // Establish the connection. 
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	         con = DriverManager.getConnection(connectionUrl);  
	  
	         // Create and execute an SQL statement that returns some data.  
	         String SQL = "SELECT  * FROM Clubes";  
	         stmt = con.createStatement();  
	         rs = stmt.executeQuery(SQL);  
	         //rs.getMetaData()
	         // Iterate through the data in the result set and display it.
	         System.out.println("here");
	         System.out.println(rs);
	         while (rs.next()) {  
	            System.out.println(rs.getInt(1) + " " + rs.getString(2));  
	         }
	      }  
	  
	      // Handle any errors that may have occurred.  
	      catch (Exception e) {  
	         e.printStackTrace();  
	      }  
	      finally {  
	         if (rs != null) try { rs.close(); } catch(Exception e) {}  
	         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	         if (con != null) try { con.close(); } catch(Exception e) {}  
	      }  
	   }  
}
