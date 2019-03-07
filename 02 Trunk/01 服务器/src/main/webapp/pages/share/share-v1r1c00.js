



	$(document).ready(function(){
		
		init();
		$("#table_default").hide();
		$("#shareDiv").css("width",document.body.clientWidth-31);
		loading("");
		$.getJSON("../../../rs/share/getresource.do", { "sharekey": escape(escape(shareKey)), "time": getMyTime() },
	          function(data){
			  if(data[0].state=="successful")
			  {
				  addShare(data[0].data);
			  }else
			  {
				  errorAll(data[0]);
			  }
		});
		show();
	});

	function share(jsonstr)
	{
		var data = $.parseJSON(jsonstr)[0];
		$("#shareDiv").css("width",document.body.clientWidth-31);
		loading(data.shareName);
		getResource(data.resourceListJson)
		show();
	}

	
	function getResource(resourceKey)
	{
		$.getJSON("../../../rs/share/getresource.do", { "resourcekey": resourceKey, time: getMyTime() },
          function(data){

		  if(data[0].state=="successful")
		  {
			  addShare(data[0].data);
		  }else
		  {
			  errorAll($.parseJSON(data[0])[0]);
		  }
		});
	}
	
    function addShare(jsonstr)
	{
		var data = $.parseJSON(jsonstr);
		var len = data.length;
		for(var i=0;i<len;i+=1)
		{
			if(data[i].attrType == "default")
			{
				addTextResources(data[i].resourceKey, data[i].attrName, data[i].attrValue);
			}else if(data[i].attrType == "picture")
			{
				addpictureResources(data[i].resourceKey, data[i].attrName, data[i].attrValue);
			}else if(data[i].attrType == "video")
			{
				addVideoResources(data[i].resourceKey, data[i].attrName, data[i].attrValue);
			}
		}
		if(len ==0)
		{
			errorAll(jsonstr);
		}
	}
	
	function getMyTime()
	{
		return (new Date()).getTime();
	}