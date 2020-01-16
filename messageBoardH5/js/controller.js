$(document).ready(function(){
	var userId = $.cookie('userId');
	if(userId!=""){
	var url = "http://114.215.196.31:8080/messageBoard/user/queryUserId";
		
		$.ajax({
			url:url,
			type:"post",
			dataType:"json",
			data:{"userId":userId},
			success:function (data) {
				var nickname = data.nickname.trim();
				$.cookie('nickname', nickname);
				$("#message").empty();
				$("#message").append('<li class="dropdown"><a href="#" class="dropdown-toggle " data-toggle="dropdown">'+nickname+'<b class="caret"></b></a><ul class="dropdown-menu"><li><a href="myselfSetting.html">个人设置</a></li><li><a href="editPassword.html">密码修改</a></li><li><a href="#" id="exit">退出登录</a></li></ul></li>');
			    $("#exit").click(function(){
			    	$("#message").empty();
			    	$("#message").append('<li><a href="register.html">注册</a></li><li><a href="login.html">登录</a></li>');
					$.removeCookie('userId');
			    	$.removeCookie('nickname');
			    	window.location.href="fristPage.html";
			    	});
			},
			error:function () {
				console.log("error");
			}
		})
	}else{
		$("#message").empty();
		$("#message").append('<li><a href="register.html">注册</a></li><li><a href="login.html">登录</a></li>');
	}
		
});