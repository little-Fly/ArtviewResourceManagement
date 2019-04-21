<%-- <%@ page language="java" contentType="text/html; charset=utf-8" --%>
<%--     pageEncoding="utf-8"%> --%>
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
    String redirect_uri = java.net.URLEncoder.encode("http%3A%2F%2Fwww.grosup.com%2F", "utf-8");
    System.out.println(redirect_uri);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>

<body>
	<form action="login">
		<input id="username">用户名
		<input id="password">密码
	</form>
	<a href="https://open.weixin.qq.com/connect/qrconnect?appid=wxb41c2d9e3682c984&redirect_uri=http%3A%2F%2Fwww.hwyst.net%2Fttzy%2Findex.html&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect">微信登录</a>

	<button id="submit" onclick="login"></button>
</body>
</html>