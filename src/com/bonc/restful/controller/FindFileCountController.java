/*
 * 文件名：FindFileCountController.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Jingege
 * 修改时间：2017年7月28日
 */

package com.bonc.restful.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bonc.restful.service.FindFileCountService;
import com.bonc.restful.service.impl.FindFileCountServiceImpl;

@WebServlet("/findFileCount")
public class FindFileCountController extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        FindFileCountService findFileCountService = new FindFileCountServiceImpl();
        String sysDate = req.getParameter("sysDate");
        String result = findFileCountService.FindFileCount(sysDate);
        resp.setContentType("application/json; charset=utf-8");  
        resp.setCharacterEncoding("UTF-8");  
        PrintWriter out = null;
        out = resp.getWriter();
        out.write(result);
        out.close();
    }
}
