

	$(document).ready(function(){
		
		init();
		$("#table_default").hide();
		$.getJSON("../../../rs/share/get.do", { "sharekey": escape(escape(shareKey)), "time": getMyTime() },
	          function(data){
			  if(data[0].state=="successful")
			  {
				  share(data[0].data);
			  }else
			  {
				  error(data[0]);
			  }
		});
	});

	function init()
	{
		$("#noshare").css("width",document.body.clientWidth);
		$("#noshare").hide();
		$("#shareDiv").hide();
	}

	function getResource(resourceKey)
	{
		$.getJSON("../../../rs/share/getresource.do", { resourceKey: resourceKey, time: getMyTime() },
          function(data){

		  if(data[0].state=="successful")
		  {
			  addShare(data[0].data);
		  }else
		  {
			  error(data[0]);
		  }
		});
	}

	function share(jsonstr)
	{
		var data = $.parseJSON(jsonstr)[0];
		$("#shareDiv").css("width",document.body.clientWidth);
		$("#noshare").hide();
		$("#shareDiv").show();
		document.title=data.shareName;
		getResource(data.resourceListJson)

	}

	function error(jsonstr)
	{
		var data = $.parseJSON(jsonstr)[0];
		$("#noshare").css("width",document.body.clientWidth);
		$("#noshare").show();
		$("#shareDiv").hide();
		$("#shareDiv").append("<br>" + data.message);
	}
	
    function addShare(jsonstr)
	{
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		for(var i=0;i<len;i+=1)
		{
			if(data[i].attrType == "default")
			{
				$($("#table_share").children()[1]).append("<p>"+data[i].attrName+" : "+data[i].attrValue+"</p>");
			}else if(data[i].attrType == "1")
			{
				var $td = $("#table_default").clone();       //增加一行,克隆第一个对象
				$td.show();
				$($td.children[0]).append(data[i].attrName);
				$($td.children[1]).append(data[i].attrValue);
				$(".table").append($td);
			}
		}
	}
	
	function getMyTime()
	{
		return (new Date()).getTime();
	}