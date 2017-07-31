package com.bonc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
	//private String DbUrl="jdbc:mysql://192.168.50.3:3306/web_bmonitor";
	private String DbUrl="jdbc:mysql://localhost:3306/lvkaihua";	
	private String DbUserName="root";
	private String password="123456";
	private String jdbcName="com.mysql.jdbc.Driver";
	
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(DbUrl,DbUserName,password);
		return con;
	} 
	
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	 
	public static void main(String[] args) throws Exception {
		JdbcUtil jd = new JdbcUtil();
		jd.getCon();
		System.out.println("aaa");
	}
}
