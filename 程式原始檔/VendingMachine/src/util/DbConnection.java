package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static void main(String[] args) {
		//System.out.println(DbConnection.getDb());
	}
	
	private static String host, db,  dbPort, userAccount, userPassword;
	
	private static String getConfigPara(String paraName) {
		String result = "";
		try (BufferedReader br = new BufferedReader(new FileReader("config.ini"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    if (paraName.equals(parts[0])) {
                    	result = parts[1].trim();
                    	break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;
	}
	
	private static void getConfig() {
		host = getConfigPara("host");
		db = getConfigPara("db");
		dbPort = getConfigPara("dbPort");
		userAccount = getConfigPara("userAccount");
		userPassword = getConfigPara("userPassword");
	}
	
	public static Connection getDb()
	{
		getConfig();
		String url = "jdbc:mysql://localhost:" + dbPort + "/" + db;
		Connection conn=null;
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection(url, userAccount, userPassword);
			} catch (ClassNotFoundException e) {
				System.out.println("no connection");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("no Driver");
				e.printStackTrace();
			}
		
		return conn;
	}
}
