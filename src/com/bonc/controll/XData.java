package com.bonc.controll;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bonc.model.Ftp;
import com.bonc.service.IndexService;
import com.bonc.util.JdbcUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/XData")
public class XData extends HttpServlet {

	private static final long serialVersionUID = 1L;
	JdbcUtil jd = new JdbcUtil();
	IndexService dao = new IndexService();
	Connection con = null;
	String[] str = { "ftp", "hbase", "hive", "xcloud" };

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		String dt = request.getParameter("dt");
		String tm = request.getParameter("tm");
		if (dt == null) {
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmm"); // 格式化当前系统日期
			dt = dateFm.format(new java.util.Date());
		} else {
			dt = dt.substring(0, 4) + dt.substring(5, 7) + dt.substring(8) + tm;
		}
		String num = request.getParameter("num");
		try {
			con = jd.getCon();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs;
		JSONArray json = new JSONArray();
		for (int y = 0; y < str.length; y++) {
			try {
				rs = dao.XData(con, dt, num, str[y]);
				ArrayList<Ftp> list = new ArrayList<Ftp>();
				JSONObject object = new JSONObject();
				while (rs.next()) {
					Ftp pt = new Ftp();
					pt.setName(rs.getString("zuhu"));
					pt.setFiles(rs.getString("files"));
					list.add(pt);
				}
				object.put("bonc", list);
				json.add(object);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/*
		 * JSONObject object=new JSONObject();
		 * object.put("name",rs.getString("name"));
		 * object.put("num",rs.getString("files")); json.add(object);
		 * 
		 * ArrayList<Test> list = new ArrayList<Test>(); Test te=new Test();
		 * te.setAge("13"); te.setName("haha"); Test te1=new Test();
		 * te1.setAge("13"); te1.setName("haha"); list.add(te); list.add(te1);
		 * for(int i=0;i<4;i++){ JSONObject object=new JSONObject();
		 * object.put("num",list); json.add(object); }
		 */

		response.setContentType("text/html; charset=utf-8");
		// 调用JSONArray.fromObject方法把array中的对象转化为JSON格式的数组
		System.out.println(json.toString());
		// 返回给前段页面
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
