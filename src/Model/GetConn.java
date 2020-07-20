package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConn {
	public Connection conn = null;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/film?useSSL=false&serverTimezone=GMT";
			String user = "root";
			String passWord = "18838060418"; 
			conn = DriverManager.getConnection(url, user, passWord);
			if (conn!=null) {
				System.out.println("数据库连接成功!");
			}
			}
		catch(Exception e) {
			e.getStackTrace();
		}
		return conn;
	}
	
}
