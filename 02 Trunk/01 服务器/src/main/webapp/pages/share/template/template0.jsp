<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String shareKey = request.getParameter("sharekey");
%> 

<html>

	<head>
		<title></title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8">
		<link rel="icon" type="image/x-icon" href="../../../static/favicon.ico"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" /> 
		<script src="../../../static/js/jquery-3.3.1.js"></script>
		<script src="../share-v1r1c00.js"></script>
		<link href="../share-v1r1c00.css" rel='stylesheet'/>
		<style type="text/css"> 

		</style>
		
		
		<script>
			var shareKey = "<%=shareKey%>";		
		</script>
	</head>
	<body>
		<div id='shareDiv' class='swipe'>
			<table class="table" border="0">
				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<tr id="table_default">
						<td>&nbsp;</td>
						<td>&nbsp;</td>						 
					</tr> 
					<tr id="table_share">
						<td>&nbsp;</td>
						<td>&nbsp;</td>						 
					</tr> 
				</tbody>
				 
			</table>
		</div>
		<div id='noshare'>
			内容不存在，资源已下线。
		</div>
	</body>
</html>