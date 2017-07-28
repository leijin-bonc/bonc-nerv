package com.bonc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class IndexService {
	public ResultSet XData(Connection con,String dt,String num,String type) throws SQLException{
		StringBuffer sql=new StringBuffer("select zuhu,files FROM lf_files  LEFT JOIN person  on lf_files.name=person.name ");
		
		if(!type.equals("") && type != null){
			sql.append("where lf_files.type="+"'"+type+"'");
		}
		if(dt != null){
			sql.append(" and sysdata LIKE "+"'%"+dt+"%'");
		}
		if(num != null && !num.equals("")){
			sql.append(" ORDER BY files DESC LIMIT "+num+";");
		}else{
			sql.append(" ORDER BY files DESC LIMIT 5;");
		}
		System.out.println(sql.toString());
		PreparedStatement ps =con.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	public ResultSet YData(Connection con) throws SQLException{
		String sql=new String("select name from user");
		PreparedStatement ps =con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	/* 查询所有数据*/
	public ResultSet findAll(Connection con,String date) throws SQLException{
		StringBuffer sql=new StringBuffer("select * FROM lf_files  LEFT JOIN person  on lf_files.name=person.name where lf_files.type=person.type ");
		if(date.equals("")||date == null){
			sql.append(";");
		}else{
			sql.append("and lf_files.sysdata like "+"'%"+date+"%' ;");
		}
		System.out.println(sql.toString());
		PreparedStatement ps =con.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	/*匹配缺省缺省字段*/
	public ResultSet serach(Connection con,String date) throws SQLException{
		StringBuffer sql=new StringBuffer("select * FROM lf_files  LEFT JOIN person  on lf_files.`name`=person.`name` where lf_files.type=person.type ");
		if(date.equals("")||date == null){
			sql.append(";");
		}else{
			sql.append("sysdata="+"%"+date+";");
		}
		System.out.println(sql.toString());
		PreparedStatement ps =con.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public static void main(String[] args) {
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMdd"); //格式化当前系统日期
		String dateTime = dateFm.format(new java.util.Date());
		StringBuffer sql=new StringBuffer("select * FROM lf_files  LEFT JOIN person  on lf_files.`name`=person.`name` AND lf_files.type=person.type ");
		sql.append("and sysdata="+dateTime+";");
		System.out.println(sql.toString());
	}
}
