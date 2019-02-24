<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="/pages/share/favicon.ico" />
<title>资源查看</title>
<script src="/pages/share/jquery-3.3.1.js"></script>
<script type="text/javascript" >
	$(document).ready(function(){
	
	$.getJSON("../rs/def/getall.do", { typekey: "RESOURCE_DEF示例表ID", time: getMyTime() },
		function(data){
	
			  if(data[0].state=="successful")
			  {
				  addDef(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
	
	$.getJSON("/rs/attr/getall.do", { typekey: "RESOURCE_DEF示例表ID", time: getMyTime() },
		function(data){
	
			  if(data[0].state=="successful")
			  {
				  addAttr(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
		
	
	$.getJSON("/rs/detail/getall.do", { typekey: "RESOURCE_DEF示例表ID", time: getMyTime() },
		function(data){
	
			  if(data[0].state=="successful")
			  {
				  addDetail(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
	
	$.getJSON("/rs/share/getall.do", { typekey: "RESOURCE_DEF示例表ID", time: getMyTime() },
		function(data){
	
			  if(data[0].state=="successful")
			  {
				  addShare(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
	
	$.getJSON("/rs/file/getall.do", { time: getMyTime() },
		function(data){
	
			  if(data[0].state=="successful")
			  {
				  addFile(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
	
	$.getJSON("/rs/sharetemp/getall.do", { time: getMyTime() },
		function(data){
	
			  if(data[0].state=="successful")
			  {
				  addShareTemp(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
	
	$.getJSON("/rs/search/searchbyuser.do", { time: getMyTime(),typekey:'RESOURCE_DEF示例表ID',searchkey:'{"RESOURCE_ATTR示例表头ID1":"1", "RESOURCE_ATTR示例表头ID2":"2"}'},
		function(data){
		
		  if(data[0].state=="successful")
		  {
			  addsearch(data[0].data);
		  }else
		  {
			  error(data[0]);
		  }
		});
	
	});
	
	function addsearch(jsonstr)
	{
		$("body").append("<b>search：{\"RESOURCE_ATTR示例表头ID1\":\"1\", \"RESOURCE_ATTR示例表头ID2\":\"2\"}</b></br>");
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str+=data[i].resourceKey;
			str+=": ";
			str+=data[i].attrKey;
			str+=", ";
			str+=data[i].attrValue;
			str+="， 状态：";
			str+=data[i].attrState;
			str+="</br>";
		}
		str+="</br>";
		$("body").append(str);
	}
	
	function addDef(jsonstr)
	{
		$("body").append("<b>表名：</b></br>");
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str+=data[i].typeKey;
			str+=": ";
			str+=data[i].name;
			str+="</br>";
		}
		str+="</br>";
		$("body").append(str);
	}
	
	function addAttr(jsonstr)
	{
		$("body").append("<b>表头：</b></br>");
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str+=data[i].attrKey;
			str+=": ";
			str+=data[i].attrName;
			str+="</br>";
		}
		str+="</br>";
		$("body").append(str);
	}
	
	function addDetail(jsonstr)
	{
		$("body").append("<b>表值：</b></br>");
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str+=data[i].resourceKey;
			str+=": ";
			str+=data[i].attrValue;
			str+="， 状态：";
			str+=data[i].attrState;
			str+="</br>";
		}
		str+="</br>";
		$("body").append(str);
	}
	
	function addShare(jsonstr)
	{
		$("body").append("<b>分享：</b></br>");
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str+=data[i].shareKey;
			str+=": ";
			str+=data[i].resourceListJson;
			str+="</br>";
		}
		str+="</br>";
		$("body").append(str);
	}
	
	function addFile(jsonstr)
	{
		$("body").append("<b>文件列表：</b></br>");
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str+=data[i].fileKey;
			str+=": ";
			str+=data[i].relativePath;
			str+="</br>";
		}
		str+="</br>";
		$("body").append(str);
	}
	
	function addShareTemp(jsonstr)
	{
		$("body").append("<b>分享模板：</b></br>");
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str+=data[i].tempKey;
			str+=": ";
			str+=data[i].tempName;
			str+=", ";
			str+=data[i].tempFilePath;
			str+="</br>";
		}
		str+="</br>";
		$("body").append(str);
	}
	
	function error(data)
	{
		$("body").html(data);
	}
	
	function getMyTime()
	{
		return (new Date()).getTime();
	}
</script>
</head>
<body>
<a href="https://www.hwyst.net/pages/share/template/template0.jsp?sharekey=RSrtemplatekey" >template</a>
<a href="https://www.hwyst.net/rs/search/searchbyuser.do?typekey=RDf%E7%A4%BA%E4%BE%8B%E8%A1%A8ID&searchkey={%22RAt%E7%A4%BA%E4%BE%8B%E8%A1%A8%E5%A4%B4ID1%22:%22%E8%A1%8C2%E5%80%BC1%22}" >searchbyuser</a>
</br>
</body>
</html>