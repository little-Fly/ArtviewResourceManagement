<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String redirect = request.getParameter("redirect");
	if(null == redirect){
		redirect = "";
	}
	String token = request.getParameter("access_token");
	String unionid = request.getParameter("unionid");
	String type = request.getParameter("type");
	String headImg = request.getParameter("headimgurl");
	String code = String.valueOf(request.getAttribute("code"));
%>
<html>
<head>
    <title>微信登录</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/base.css">
	<script type="text/javascript" src="<%=contextPath%>/js/jquery-1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/public/js/underStructure.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/js/login/login.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/weChat/js/weChatLogin.js"></script>
</head>

<body 
    oncontextmenu="return false"
    onselectstart="return false"
    ondragstart="return false"
    onselect="return false"
    oncopy="return false"
    onbeforecopy="return false"
    onload="weChatLogin.initial('<%=contextPath %>', '<%=token %>', '<%=unionid %>', '<%=redirect %>', '<%=code %>', '<%=headImg %>', '<%=type %>')">
<div id="body-backgrand">
	<div style="position:absolute; left:84px; top:19px;">
		<a href="http://www.grosup.com">
			<img id="Grosup-Img" alt="" src="<%=contextPath %>/image/dadaoLogo.png" />
		</a>
	</div>
   	<table cellpadding="0" cellspacing="0" border="0" style="width:100%; height:100%;" align="center">
		<tr>
			<td valign="middle">
				<table cellpadding="0" cellspacing="0" border="0" align="center" style="height:292px;">
					<tr>
						<td align="left">
							<table cellpadding="0" cellspacing="0" border="0" id="loginTitleArea" style="border:1px solid #d1d1d1;font-size:12pt; font-family:'微软雅黑';color:white; background-color:#1b7d99;width:330px;">
								<tr>
									<td align="center"></td>
									<td align="center"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="center" valign="top" style="padding-top:20px; position:relative;">
							<form name="weixinNewUserBindDadaoUser" method="post" action="weixinNewUserBindDadaoUser" onsubmit="return weChatLogin.submitOldUser()">
							<input name="redirect" type="hidden" value="<%=redirect%>" />
							<input name="access_token" type="hidden" value="<%=token%>" />
							<input name="unionid" type="hidden" value="<%=unionid%>" />
							<table id="loginTabLeft" cellpadding="0" cellspacing="0" border="0" style="display:none;">
								<tr>
									<td align="left">
										<table cellpadding="0" cellspacing="0" border="0" style="font-family:'微软雅黑'; font-size:9pt; width:328px;">
											<tr>
												<td style="width:312px; height:46px; border:1px solid #d1d1d1; padding-left:10px; background:#ffffff;">
													<input id="oldAccount" name="accountName" type="text" placeholder="账号/手机号" maxlength="12" onkeypress="weChatLogin.clearOldError()" style="font-family:'微软雅黑'; font-size:12px; color:#1b7d99; border:0; width:302px;"/>
												</td>
											</tr>
											<tr>
												<td style="height:10px;"></td>
											</tr>
											<tr>
												<td style="width:312px; height:46px; border:1px solid #d1d1d1; padding-left:10px; background:#ffffff;">
													<input id="oldPassword" name="password" type="password" placeholder="密码" maxlength="12" onkeypress="weChatLogin.clearOldError()" style="font-family:'微软雅黑'; font-size:12px; color:#1b7d99; border:0; width:302px;"/>
												</td>
											</tr>
											<tr>
												<td style="padding-top:10px;">
													<input type="submit" value="绑定" style="width:328px; height:46px; color:#ffffff; font-size:13pt; background:#1b7d99;cursor:pointer" />
												</td>
											</tr>
											<tr>
												<td align="left" id="oldError" style="color:#ff0000; height:20px;"></td>
											</tr>
											<tr>
												<td style="height:60px;"></td>
											</tr>
											<tr>
												<td>为确保您的账号安全，微信扫码登录必须要先绑定大道账号，用密码进行安全验证，验证之后方可在PC端用微信扫码登录。</td>
											</tr>
										</table>
									</td>
									<td style="width:20px;"></td>
									<td valign="top">
										<table cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td style="height:12px;"></td>
											</tr>
											<tr>
												<td>
													<img src="<%=contextPath %>/image/rightArrow.png">
												</td>
											</tr>
											<tr>
												<td style="height:7px;"></td>
											</tr>
											<tr>
												<td>
													<img src="<%=contextPath %>/image/leftArrow.png">
												</td>
											</tr>
										</table>
									</td>
									<td style="width:20px;"></td>
									<td valign="top">
										<img id="weChatHead-old" style="width:108px; height:108px;">
									</td>
								</tr>
							</table>
							</form>
							<form name="weixinNewUserRegister" method="post" action="weixinNewUserRegister" onsubmit="return weChatLogin.submitNewUser()">
							<input name="redirect" type="hidden" value="<%=redirect%>" />
							<input name="access_token" type="hidden" value="<%=token%>" />
							<input name="unionid" type="hidden" value="<%=unionid%>" />
							<table id="loginTabRight" cellpadding="0" cellspacing="0" border="0" style="display:none;">
								<tr>
									<td align="left">
										<table cellpadding="0" cellspacing="0" border="0" style="font-family:'微软雅黑'; font-size:9pt;width:328px;">
											<tr>
												<td style="width:312px; height:46px; border:1px solid #d1d1d1; padding-left:10px; background:#fff;">
													<input id="newAccount" type="text" name="accountName" maxlength="12" onkeypress="weChatLogin.clearNewError()" style="font-family:'微软雅黑'; font-size:12px; color:#1b7d99; border:0; width:302px;"/>
												</td>
											</tr>
											<tr>
												<td style="height:10px;"></td>
											</tr>
											<tr>
												<td style="width:312px; height:46px; border:1px solid #d1d1d1; padding-left:10px; background:#ffffff;">
													<input id="newPassword" type="password" name="password" placeholder="输入密码" maxlength="12" onkeypress="weChatLogin.clearNewError()" style="font-family:'微软雅黑'; font-size:12px; color:#1b7d99; border:0; width:302px;"/>
												</td>
											</tr>
											<tr>
												<td style="height:10px;"></td>
											</tr>
											<tr>
												<td style="width:312px; height:46px; border:1px solid #d1d1d1; padding-left:10px; background:#ffffff;">
													<input id="newPasswordCopy" type="password" name="password2" placeholder="再次输入密码" maxlength="12" onkeypress="weChatLogin.clearNewError()" style="font-family:'微软雅黑'; font-size:12px; color:#1b7d99; border:0; width:302px;"/>
												</td>
											</tr>
											<tr>
												<td style="padding-top:10px;">
													<input type="submit" value="绑定" style="width:328px; height:46px; color:#ffffff; font-size:13pt; background:#1b7d99;cursor:pointer" />
												</td>
											</tr>
											<tr>
												<td align="left" id="newError" style="color:#ff0000; height:20px;"></td>
											</tr>
											<tr>
												<td style="padding-top:32px;">为确保您的账号安全，微信扫码登录必须要先绑定大道账号，用密码进行安全验证，验证之后方可在PC端用微信扫码登录;若无账号，大道平台会自动生成一个账号，并设置密码完成绑定。</td>
											</tr>
										</table>
									</td>
									<td style="width:20px;"></td>
									<td valign="top">
										<table cellpadding="0" cellspacing="0" border="0" style="font-family:'微软雅黑'; font-size:9pt;">
											<tr>
												<td style="padding-top:17px;">账号规则：账号只能是4-12位以字母开头的字母、数字、下划线。</td>
											</tr>
											<tr>
												<td style="padding-top:36px;">密码规则：6-12位，必须由大小写字母和数字组成，可带,.;?_!@#$%&增加强度。</td>
											</tr>
										</table>
									</td>
									<td style="width:60px;"></td>
									<td valign="top">
										<table cellpadding="0" cellspacing="0" border="0">
											<tr>
												<td style="height:12px;"></td>
											</tr>
											<tr>
												<td>
													<img src="<%=contextPath %>/image/rightArrow.png">
												</td>
											</tr>
											<tr>
												<td style="height:7px;"></td>
											</tr>
											<tr>
												<td>
													<img src="<%=contextPath %>/image/leftArrow.png">
												</td>
											</tr>
										</table>
									</td>
									<td style="width:20px;"></td>
									<td valign="top">
										<img id="weChatHead-new" style="width:108px; height:108px;">
									</td>
								</tr>
							</table>
							</form>
						</td>
					</tr>	
				</table>
			</td>
		</tr>
	</table>
</div>
<div style="width:100%;min-width:963px;text-align:center;position:absolute;bottom:25px;font-family:'微软雅黑';font-size:9pt;margin:0 auto;">
	深圳市九天云软件有限公司 版权所有 <a href="http://www.miitbeian.gov.cn" target="_blank">粤ICP备14009662号-1</a> 推荐使用浏览器：ie 11(或更高版本)，firefox 30(或更高版本) 客服：0755 - 2267 6079 eMail：service@grosup.com
</div>
</body>
</html>
