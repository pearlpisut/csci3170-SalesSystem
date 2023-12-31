package database;

import java.sql.*;

public class Db {

    public Connection conn;
	
    private static String dbURL = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db22?autoReconnect=true&useSSL=false";
	private static String dbUser = "Group22";
	private static String dbPass = "CSCI3170";

    // this function connects to DB.
	public void getConnection() throws ClassNotFoundException, SQLException {
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception x) {
            System.err.println("Unable to load the driver class in getConnection()");
        }
        this.conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
        System.out.println("Conn is linked to DB\n");
	}
}