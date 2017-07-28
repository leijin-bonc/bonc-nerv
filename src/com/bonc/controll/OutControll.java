package com.bonc.controll;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bonc.model.Ftp;
import com.bonc.service.IndexService;
import com.bonc.util.ExportExcelUtil;
import com.bonc.util.JdbcUtil;

@WebServlet("/out")
public class OutControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcUtil jd = new JdbcUtil();
	IndexService dao = new IndexService();
	Connection con = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String date = request.getParameter("date");
		if (!date.equals("")) {

			date = date.substring(0, 4) + date.substring(5, 7) + date.substring(8);
		}
		String[] excelHeader = { "系统日期", "租户", "账户", "类型", "目录", "文件数", "文件夹数", "小于20M文件数", "[20M-50M]文件数",
				"(50M-100M]文件数", "大于100M" };
		List<Ftp> dataList = new ArrayList<Ftp>();
		try {
			con = jd.getCon();
			ResultSet rs = dao.findAll(con, date);
			while (rs.next()) {
				Ftp ftp = new Ftp();
				ftp.setSysdata(rs.getString("sysdata"));
				ftp.setHold(rs.getString("zuhu"));
				ftp.setAccout(rs.getString("zhanghao"));
				ftp.setType(rs.getString("type"));
				ftp.setName(rs.getString("name"));
				ftp.setFiles(rs.getString("files"));
				ftp.setDfiles(rs.getString("dfiles"));
				ftp.setFiles20(rs.getString("files20"));
				ftp.setFiles50(rs.getString("files50"));
				ftp.setFiles100(rs.getString("files100"));
				ftp.setMaxfiles(rs.getString("maxfiles"));
				dataList.add(ftp);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		List<List<Ftp>> dataD = assortment(dataList);
		try {
			ExportExcelUtil.export(response, "数据单", excelHeader, dataD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<List<Ftp>> assortment(List<Ftp> dataList) {
		List<Ftp> ftpList1 = new ArrayList<Ftp>();
		List<Ftp> hbaseList2 = new ArrayList<Ftp>();
		List<Ftp> mappList3 = new ArrayList<Ftp>();
		List<Ftp> hivesparkList4 = new ArrayList<Ftp>();
		List<List<Ftp>> data = new ArrayList<List<Ftp>>();
		Ftp fp = null;

		Iterator<Ftp> it = dataList.iterator();
		while (it.hasNext()) {
			fp = it.next();
			if (fp.getType().equals("ftp")) {
				ftpList1.add(fp);
			}
			if (fp.getType().equals("hbase")) {
				hbaseList2.add(fp);
			}
			if (fp.getType().equals("hive")) {
				hivesparkList4.add(fp);
			}
			if (fp.getType().equals("xcloud")) {
				mappList3.add(fp);
			}
		}
		if (ftpList1.size() > 0) {
			data.add(ftpList1);
		}
		if (hbaseList2.size() > 0) {
			data.add(hbaseList2);
		}
		if (mappList3.size() > 0) {
			data.add(mappList3);
		}
		if (hivesparkList4.size() > 0) {
			data.add(hivesparkList4);
		}
		return data;
	}
}
