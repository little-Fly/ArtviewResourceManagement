package com.grosup.ttzy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.LogBean;
import com.grosup.ttzy.dao.LogDao;
import com.grosup.ttzy.util.CodeUtil;
import com.grosup.ttzy.util.GrosupException;

@Controller
@RequestMapping("/log")
public class LogController {
    
    private static Logger logger = Logger.getLogger(LogController.class);
    
    @Autowired
    private LogDao logDao;
    
    @RequestMapping(method = RequestMethod.GET, value = "/query.do")
    @ResponseBody
    public JSONObject queryLogByPage(@RequestParam int pageSize,@RequestParam int pageNumber) {
        JSONObject result = new JSONObject();
        try {
            Map<String, Integer> pageMap = new HashMap<String, Integer>();
            pageMap.put("indexStart", pageSize * pageNumber);
            pageMap.put("pageSize", pageSize);
            List<LogBean> logs = logDao.queryLogByPage(pageMap);
            
            result.put("data", logs);
            result.put("code", CodeUtil.SUCCESS);
        } catch (GrosupException e) {
            result.put("code", CodeUtil.ERROR);
            result.put("message", "系统错误");
            logger.error("人员注册异常", e);
        }
        return result;
    }
}
