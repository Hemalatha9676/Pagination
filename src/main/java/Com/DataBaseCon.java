package Com;



import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseCon  {
	public static Connection getCon() throws Exception{ 
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hema","root","123456");
		return con;
	}
	
}
