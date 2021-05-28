import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class connSql {
	
	public static void main(String args[])throws Exception{
	try {

		Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

		e.printStackTrace();

		}// Mysql driver
		//jdbc\:mysql\://127.0.0.1\:3306/ipv6?noAccessToProcedureBodies\=true
		Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://202.118.75.100/sdn1","root", "root");

		String sql="select * from tab_auditor limit 100 ";
		
		//String sql= "insert into  d_parse_ip(start_ip,start_ip_num,end_ip,end_ip_num) values ('1.1.1.1','200','2.2.2.2','300')";

		PreparedStatement sss=(PreparedStatement) conn.prepareStatement(sql);

		ResultSet rs = sss.executeQuery();

		
/*		   InputStream input = new FileInputStream(f);
		   sss.setString(1, "Li");
		   sss.setBinaryStream(2, input, f.length());
		   sss.executeUpdate();*/
		   
	while (rs.next()) {

		System.out.print(rs.getString(1));

		System.out.print(rs.getString(2));

		System.out.print(rs.getString(3));

	
		System.out.println();

		}

		rs.close();

		sss.close();

		conn.close();
	}
}
