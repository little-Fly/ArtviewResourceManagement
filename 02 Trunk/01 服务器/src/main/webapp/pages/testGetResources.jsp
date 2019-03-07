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
	
		testAll();
	});
	
	function get()
	{
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
		
		search('{"RESOURCE_ATTR示例表头ID1":"1", "RESOURCE_ATTR示例表头ID2":"2"}');
	}
		

	

	
	function messDiv(mess)
	{
		$("#messDiv").append(mess+"</br>");
	}
	
	function errorDiv(error)
	{
		$("#errorDiv").append(error+"</br>");
	}
	
	function testAll()
	{
		testDef();
		testAttr();
		testDetail();
		setTimeout( function (){get();},1000);
		setTimeout( function (){getDef();},2000);
	}
	
	function testDetail()
	{
		$.getJSON("/rs/detail/add.do", { json:"[{'attrLevel':'0','attrName':'示例表头','attrType':'default','attrValue':'test1','typeKey':'RESOURCE_DEF示例表ID','attrKey':'RESOURCE_ATTR示例表头ID1'}]", time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/detail/add.do successful.");
				
				var data = $.parseJSON(data[0].object);
				var len = data.length;
				var str="";
				for(var i=0;i<len;i+=1)
				{
					testDetailUpdate(data[i].resourceKey);
					break;
				}
			  }else
			  {
				  errorDiv("/rs/detail/add.do error.");
			  }
			});
	}
	
	function testDetailUpdate(resourceKey)
	{		
		$.getJSON("/rs/detail/update.do", {"resourcekey" : resourceKey, json:"[{'attrLevel':'0','attrName':'示例表头','attrType':'default','attrValue':'test2','typeKey':'RESOURCE_DEF示例表ID','attrKey':'RESOURCE_ATTR示例表头ID1'}]", time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/detail/update.do successful.");
				testDetailDel(resourceKey);
			  }else
			  {
				  errorDiv("/rs/detail/update.do error.");
			  }
			});
	}
	
	function testDetailDel(resourceKey)
	{	
		$.getJSON("/rs/detail/del.do", {  "typekey":"RESOURCE_DEF示例表ID", "resourcekey" : resourceKey , time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/detail/del.do successful.");
				testDetailApprovaldel(resourceKey);
			  }else
			  {
				  errorDiv("/rs/detail/del.do error.");
			  }
			});
	}
	
	function testDetailApprovaldel(resourceKey)
	{	
		$.getJSON("/rs/detail/approvaldel.do", {  "typekey":"RESOURCE_DEF示例表ID", "resourcekey" : resourceKey, approvalmess:'test' , time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/detail/approvaldel.do successful.");
			  }else
			  {
				  errorDiv("/rs/detail/approvaldel.do error.");
			  }
			});
	}
	
	function testAttr()
	{
		$.getJSON("/rs/attr/add.do", { json:"{'attrKey':'RAt示例表头ID','attrLevel':'0','attrName':'test表头','attrType':'default','typeKey':'RESOURCE_DEF示例表ID'}", time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/attr/add.do successful.");
				var data = $.parseJSON(data[0].object);
				testAttrUpdate(data.attrKey);
			  }else
			  {
				  errorDiv("/rs/attr/add.do error.");
			  }
			});
	}
	
	function testAttrUpdate(attrKey)
	{		
		$.getJSON("/rs/attr/update.do", { "typekey":"RESOURCE_DEF示例表ID", "attrkey" : attrKey, json:"{'attrKey':'RAt示例表头ID','attrLevel':'0','attrName':'test示例表头2','attrType':'default','typeKey':'RESOURCE_DEF示例表ID'}", time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/attr/update.do successful.");
				testAttrDel(attrKey);
			  }else
			  {
				  errorDiv("/rs/attr/update.do error.");
			  }
			});
	}
	
	function testAttrDel(attrKey)
	{	
		$.getJSON("/rs/attr/del.do", {  "typekey":"RESOURCE_DEF示例表ID", "attrkey" : attrKey , time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/attr/del.do successful.");
			  }else
			  {
				  errorDiv("/rs/attr/del.do error.");
			  }
			});
	}
	
	function testDef()
	{
		$.getJSON("/rs/def/add.do", { json:"{'name':'图书','remark':'test图书'}", time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/def/add.do successful.");
				var data = $.parseJSON(data[0].object);
				testDefUpdate(data.typeKey);
			  }else
			  {
				  errorDiv("/rs/def/add.do error.");
			  }
			});
	}
	
	function testDefUpdate(typeKey)
	{		
		$.getJSON("/rs/def/update.do", { "typekey":typeKey, json:"{'name':'图书2','remark':'test图书'}", time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/def/update.do successful.");
				testDefDel(typeKey);
			  }else
			  {
				  errorDiv("/rs/def/update.do error.");
			  }
			});
	}
	
	function testDefDel(typeKey)
	{	
		$.getJSON("/rs/def/del.do", {  "typekey":typeKey, time: getMyTime() },	
				function(data){ 
			if(data[0].state=="successful") {
				messDiv("/rs/def/del.do successful.");
			  }else
			  {
				  errorDiv("/rs/def/del.do error.");
			  }
			});
	}
	
	function searchKey()
	{
		var key = $('#searchKey').val();
		search(key);
	}
	
	function search(key)
	{
		$.getJSON("/rs/search/search.do", { time: getMyTime(),typekey:'RESOURCE_DEF示例表ID',searchkey:key},
				function(data){
				
				  if(data[0].state=="successful")
				  {
					  addsearch(data[0].data);
				  }else
				  {
					  error(data[0]);
				  }
				});
	}
	
	function getDef()
	{
		$.getJSON("/rs/def/getall.do", { time: getMyTime() },
				function(data){
			
					  if(data[0].state=="successful")
					  {
						  addDef(data[0].data);
					  }else
					  {
						  error(data[0]);
					  }
					});
	}
	
	
	function getAttr(typekeyStr)
	{
		$.getJSON("/rs/attr/getall.do", { typekey: typekeyStr, time: getMyTime() },
			function(data){
		
				  if(data[0].state=="successful")
				  {
					  addAttr(data[0].data, typekeyStr);
				  }else
				  {
					  error(data[0]);
				  }
				});
		
	}
	
	function getDetail(typekeyStr)
	{
		$.getJSON("/rs/detail/getall.do", { typekey: typekeyStr,len:100, time: getMyTime() },
			function(data){
		
				  if(data[0].state=="successful")
				  {
					  addDetail(data[0].data, typekeyStr);
				  }else
				  {
					  error(data[0]);
				  }
				});
	
	}
	
	function addsearch(jsonstr)
	{
		$("#searchDiv").html("");
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
		$("#searchDiv").append(str);
	}
	
	function addDef(jsonstr)
	{
		
		
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		var str="";
		for(var i=0;i<len;i+=1)
		{
			str="";
			$("#resourceDiv").append("<div id="+data[i].typeKey+"></div>");
			$("#"+data[i].typeKey).append("<b>表名：</b></br>");
			str+=data[i].typeKey;
			str+=": ";
			str+=data[i].name;
			str+="</br>";
			str+="</br>";
			$("#"+data[i].typeKey).append(str);
			getAttr(data[i].typeKey);
		}

	}
	
	function addAttr(jsonstr, typekeyStr)
	{
		$("#"+typekeyStr).append("<b>表头：</b></br>");
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
		$("#"+typekeyStr).append(str);
		getDetail(typekeyStr);
	}
	
	function addDetail(jsonstr, typekeyStr)
	{
		$("#"+typekeyStr).append("<b>表值：</b></br>");
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
		$("#"+typekeyStr).append(str);
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

<div id="messDiv"></div>
<div id="errorDiv" style="font-color: #FF0000"></div>
</br><b>search：</b><input id='searchKey' type='text' value='{"RESOURCE_ATTR示例表头ID1":"1", "RESOURCE_ATTR示例表头ID2":"2"}' style='width:500px' /><input type='button' value='search' onclick='searchKey()'></br>
<div id="searchDiv"></div>
</br>
<div id="resourceDiv"></div>
</body>
</html>