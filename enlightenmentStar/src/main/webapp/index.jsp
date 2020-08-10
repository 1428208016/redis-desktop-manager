<html>
<header>

</header>
<body>
<h2>Hello World!</h2>
<h5><a onclick="go()"> go </a></h5>
<h5><a onclick="get()"> get </a></h5>

<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
	
	function get(){
		var _url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1e57d343b6cc5ba0&secret=e5b27bb9cce30efbe43bc6409489d509&code=123&grant_type=authorization_code";
		var xhr = new XMLHttpRequest();
		xhr.open('POST', _url, true);
		xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
		//xhr.setRequestHeader('Access-Control-Allow-Methods', 'POST, GET');
		//xhr.setRequestHeader('Access-Control-Max-Age', 1000);
		//xhr.setRequestHeader('Access-Control-Allow-Headers', '*');
		//xhr.setRequestHeader('Content-type', 'application/json');		
		xhr.send();
		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4 && xhr.status == 200) {
				alert(xhr.responseText);
	        }else {
	        	alert(xhr.statusText);
	        }
	    }
		console.info(xhr);
	}
	
	
	
	
	function go(){
		var _url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1e57d343b6cc5ba0&secret=e5b27bb9cce30efbe43bc6409489d509&code=123&grant_type=authorization_code";
		$.ajax({
			url:_url,
			data:"",
			type:"POST",
			dataType:"JSON",
			success:function(res){
				alert(res);
			}
		});
	} 
</script>
</body>
</html>
