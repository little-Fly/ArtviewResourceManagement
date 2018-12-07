<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源查看</title>
		<script src="../static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" >
	$(document).ready(function(){
	
	$.getJSON("../rs/def/getall.do", { typekey: "RDf示例表ID", time: getMyTime() },
	        function(data){
	
			  if(data[0].state=="successful")
			  {
				  addDef(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
	
	$.getJSON("../rs/attr/getall.do", { typekey: "RDf示例表ID", time: getMyTime() },
	        function(data){
	
			  if(data[0].state=="successful")
			  {
				  addAttr(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
		
	
	$.getJSON("../rs/detail/getall.do", { typekey: "RDf示例表ID", time: getMyTime() },
	        function(data){
	
			  if(data[0].state=="successful")
			  {
				  addDetail(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
	
	$.getJSON("../rs/share/getall.do", { typekey: "RDf示例表ID", time: getMyTime() },
	        function(data){
	
			  if(data[0].state=="successful")
			  {
				  addShare(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
			});
	});
	
	function addDef(jsonstr)
	{
		$("body").append("表名：</br>");
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
		
		$("body").append(str);
	}
	
	function addAttr(jsonstr)
	{
		$("body").append("表头：</br>");
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
		
		$("body").append(str);
	}
	
	function addDetail(jsonstr)
	{
		$("body").append("表值：</br>");
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str+=data[i].resourceKey;
			str+=": ";
			str+=data[i].attrValue;
			str+="</br>";
		}
		
		$("body").append(str);
	}
	
	function addShare(jsonstr)
	{
		$("body").append("分享：</br>");
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

</body>
</html>