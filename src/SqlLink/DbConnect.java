package SqlLink;

import java.sql.*;
public class DbConnect {
	public static Connection con;
	static final String JDBC_DRIVER ="com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/exam?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "12497";
	public static Connection getConnection(){
		try {
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
