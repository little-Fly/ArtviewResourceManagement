package com.grosup.ttzy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.util.TtzyUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class TestController {
	@RequestMapping(method = RequestMethod.GET,value = "helloword.do")
	public void helloworld(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
}
