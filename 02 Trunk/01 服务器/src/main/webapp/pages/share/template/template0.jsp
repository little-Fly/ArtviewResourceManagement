<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String shareKey = request.getParameter("sharekey");
%>

<html>

<head>
<title></title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link rel="icon" type="image/x-icon" href="/pages/share/favicon.ico" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<script type="text/javascript" src="/pages/share/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/pages/share/share-v1r1c00.js"></script>
<link href="/pages/share/share-v1r1c00.css" rel='stylesheet' />
<style type="text/css">
</style>


<script>

	var shareKey = "<%=shareKey%>";
 	var resourceKey = '';
	function init() {
		$("#noshare").css("width", document.body.clientWidth);
		$("#noshare").hide();
		$("#loading").hide();
		$("#shareDiv").hide();
	}

	function loading(title) {
		$("#noshare").hide();
		$("#shareDiv").hide();
		$("#loading").show();
		document.title = title;
	}

	function show() {
		$("#noshare").hide();
		$("#loading").hide();
		$("#shareDiv").show();
	}

	function errorAll(data) {
		//请求出现异常
		$("#noshare").css("width", document.body.clientWidth);
		$("#loading").hide();
		$("#noshare").show();
		$("#shareDiv").show();
		$("#shareDiv").append("<br>error with data.message:" + data.message);
	}
	function phasing(id, table){
		if(id != resourceKey){
			if(resourceKey){
				table.css("border-top","1px solid #f56c6c");
			}
			resourceKey = id;
		}
              	}
	function addTextResources(id, name, value) {
		var $tr = $("#share_text").clone(); //增加一行,克隆第一个对象
		$($tr).attr('id', "share" + name);
		var $table = checkAndAdd(id);

		$table.append($tr);
		$($tr.children()[0]).append("<p>" + name + " : </p>");
		$($tr.children()[1]).append("<p>" + value + "</p>");
	}

	function addpictureResources(id, name, url) {
		var $tr = $("#share_picture").clone(); //增加一行,克隆第一个对象
		$($tr).attr('id', "share" + name);
		var $table = checkAndAdd(id);

		$table.append($tr);
		$($tr.children()[0])
				.append("<p>" + name + ": </p>");
		$($tr.children()[0]).append("<img src='"+url+"' style='max-width: " + (document.body.clientWidth -33) + "px;'  />");

	}

	function addVideoResources(id, name, url) {
		var $tr = $("#share_video").clone(); //增加一行,克隆第一个对象
		$($tr).attr('id', "share" + name);
		var $table = checkAndAdd(id);

		$table.append($tr);
		$($tr.children()[0])
				.append("<p><a>" + name + "</a></p>");
		$($tr.children()[0])
			.append(
			"<video src='"+url+"' controls='controls' style='width: " + (document.body.clientWidth -33) + "px;'>您的浏览器不支持 video 标签。</video>");
	}

	function checkAndAdd(id) {
		var id = "Share"+id;
		if ($("#" + id).length > 0) {
			return $("#" + id);
		} else
		{
			var $table = $("#shareTable").clone(); //增加一行,克隆第一个对象
			$($table).attr('id', id);
			$("#shareDiv").append($table);
              			phasing(id, $($table));
			return $($table);
		}
		
	}
</script>
</head>
<body>
	<div id='shareDiv'></div>
	<div id='loading'>数据请求中。</div>
	<div id='noshare'>内容不存在，资源已下线。</div>
	<table id='shareTable' class="table" border="0">
		<thead>
			<tr>
				<th width="100px;" height="0">&nbsp;</th>
				<th width="auto" height="0">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<table class="table" border="0">
		<thead>
			<tr>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<tr id="share_text">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr id="share_picture">
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr id="share_video">
				<td colspan=2>&nbsp;</td>
			</tr>
		</tbody>
	</table>
</body>
</html>