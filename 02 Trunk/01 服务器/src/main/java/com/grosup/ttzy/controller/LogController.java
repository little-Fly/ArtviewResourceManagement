package com.grosup.ttzy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.LogBean;
import com.grosup.ttzy.beans.ReportBean;
import com.grosup.ttzy.dao.LogDao;
import com.grosup.ttzy.util.CodeUtil;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.ObjectUtil;

@Controller
@RequestMapping("/wx/log")
public class LogController {
    
    private static Logger logger = Logger.getLogger(LogController.class);
    
    @Autowired
    private LogDao logDao;
    
    @RequestMapping(method = RequestMethod.GET, value = "/query.do")
    @ResponseBody
    public JSONObject queryLogByPage(@RequestParam int pageSize,@RequestParam int pageNumber) {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        try {
            Map<String, Integer> pageMap = new HashMap<String, Integer>();
            pageMap.put("indexStart", pageSize * (pageNumber-1));
            pageMap.put("pageSize", pageSize);
            List<LogBean> logs = logDao.queryLogByPage(pageMap);
            if (ObjectUtil.isNull(logs)) {
                result.put("code", CodeUtil.NODATA);
                return result;
            }
            
            for (LogBean logBean : logs) {
                JSONObject json = new JSONObject();
                json.put("remark", logBean.getRemark());
                data.add(json);
            }
            
            result.put("data", data);
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            result.put("message", "系统错误");
            logger.error("日志查询异常", e);
        }
        return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/queryReport.do")
    @ResponseBody
    public JSONObject queryReportByPage(@RequestParam long uid,@RequestParam int pageSize,@RequestParam int pageNumber) {
        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        try {
            Map<String, Object> pageMap = new HashMap<String, Object>();
            pageMap.put("indexStart", pageSize * (pageNumber-1));
            pageMap.put("pageSize", pageSize);
            pageMap.put("uid", uid);
            List<ReportBean> reports = logDao.queryReport(pageMap);
            for (ReportBean reportBean : reports) {
                JSONObject json = new JSONObject();
                json.put("report", reportBean.getReport());
                json.put("date", reportBean.getDate());
                data.add(json);
            }
            result.put("data", data);
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            result.put("message", "系统错误");
            logger.error("人员注册异常", e);
        }
        return result;
    }
}
