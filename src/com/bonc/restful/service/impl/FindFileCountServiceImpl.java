/*
 * 文件名：FindFileCountServiceImpl.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：Jingege
 * 修改时间：2017年7月30日
 */

package com.bonc.restful.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bonc.restful.service.FindFileCountService;
import com.bonc.util.JdbcUtil;

import net.sf.json.JSONObject;

public class FindFileCountServiceImpl implements FindFileCountService{
    JdbcUtil jdbcUtil;
    Connection conn;
    PreparedStatement ps;
    ResultSet resultSet;
    List resultList = new ArrayList();
    
    public String FindFileCount(String date){
        jdbcUtil = new JdbcUtil();
        JSONObject jsonObjectresult = new JSONObject();
        
        /**
         * 判断输入参数是否为空
         */
        if (date!= null && !date.equals("")) {
            /**
             * 判断输入参数是否包含除数字以外的字符
             */
            for (int i = 0;i < date.length(); i ++) {
                if (!Character.isDigit(date.charAt(i))) {
                    jsonObjectresult.put("message", "接口出现了异常");
                    jsonObjectresult.put("success", false);  
                    return jsonObjectresult + "";
                } 
            }
            
            
            /**
             * 判断输入参数长度是否大于12位
             */
            if (date.length() > 12){
                jsonObjectresult.put("message", "接口出现了异常");
                jsonObjectresult.put("success", false);  
                return jsonObjectresult + "";
            }
            
            
            /**
             * 开始查询
             */
            StringBuffer sql = new StringBuffer
                ("select sysdata,type,name,files,dfiles from lf_files where sysdata like  '%"+date+"%';");        
                try{
                    conn = jdbcUtil.getCon();
                    ps = conn.prepareStatement(sql.toString());
                    ResultSet rs = ps.executeQuery();
                    while( rs.next()) {       
                        Map<String,Object> map = new HashMap<>();
                        map.put("sysDate", rs.getString("sysdata"));
                        map.put("type", rs.getString("type"));
                        map.put("seqName", rs.getString("name"));
                        map.put("fileNum", rs.getInt("files"));
                        map.put("dfileNum", rs.getInt("dfiles"));
                        resultList.add(map);
                    }
                    jdbcUtil.closeCon(conn);
                    
                    /**
                     *判断查询结果是否为空
                     */
                    if (resultList.size() < 1){
                        jsonObjectresult.put("data","查询结果为空");
                        jsonObjectresult.put("message", "ok");
                        jsonObjectresult.put("success", true);
                        return jsonObjectresult + "";
                    }
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("fileCounts", resultList);                  
                    jsonObjectresult.put("data", jsonObject);
                    jsonObjectresult.put("message", "ok");
                    jsonObjectresult.put("success", true);
                    return jsonObjectresult + "";
                    
                }
                catch ( Exception e)
                {
                    // TODO Auto-generated catch block
                    jsonObjectresult.put("message", "接口出现了异常");
                    jsonObjectresult.put("success", false);
                    e.printStackTrace();   
                    return jsonObjectresult + "";
                }
        } else {
            jsonObjectresult.put("message", "接口出现了异常");
            jsonObjectresult.put("success", false);  
            return jsonObjectresult + "";
        }
    }
}
