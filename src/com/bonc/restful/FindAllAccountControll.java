/*
 * 文件名：FindAllAccountControll.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：xiyan
 * 修改时间：2017年7月28日
 */

package com.bonc.restful;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bonc.model.Ftp;
import com.bonc.model.Person;
import com.bonc.util.JdbcUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/findAllAccount")
public class FindAllAccountControll extends HttpServlet {
    JdbcUtil jdbcUtil = new JdbcUtil();
    Connection con = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        
        JSONObject object = new JSONObject();
        try {
            ResultSet rs = findAllAccount();
            ArrayList<Person> list = new ArrayList<Person>();
            JSONObject accSeqs = new JSONObject();
            while (rs.next()) {
                Person pr = new Person();
                
                pr.setType(rs.getString("type"));
                pr.setTenantName(rs.getString("zuhu"));
                pr.setTenantAccount(rs.getString("zhanghao"));
                pr.setSeqName(rs.getString("name"));
                
                list.add(pr);
               
            }
            accSeqs.put("accSeqs",list);
            
            object.put("message","ok");
            object.put("success",true);
            object.put("data",accSeqs);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            object.put("message","接口出现了异常");
            object.put("success",false);
            e.printStackTrace();
        }
        //设置编码
        resp.setContentType("text/html; charset=utf-8");
        //向前台
        PrintWriter out;
        try {
            out = resp.getWriter();
            out.println(object);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public ResultSet findAllAccount() throws SQLException {
        StringBuffer sql = new StringBuffer("select * from person") ;
        try {
            con = jdbcUtil.getCon();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        PreparedStatement ps =con.prepareStatement(sql.toString());
        ResultSet rs = ps.executeQuery();
        
        return rs;
    }
   
    
}
