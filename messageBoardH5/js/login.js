$(document).ready(function(){
	$("#hide").hide();
	
	$("#login").click(function(){
		var url = "http://114.215.196.31:8080/messageBoard/user/login";
		var userName = $('#account').val();
		var userPassword = $('#inputPassword1').val();
		
			$.ajax({
				url:url,
				type:"post",
				dataType:"json",
				data:{"userName":userName,"userPassword":userPassword},
				success:function (data) {
					if (data!="") {
						$.cookie('userId', data);
						$(location).attr('href', 'fristPage.html');
					}else {
						$("#hide").text("请注册后登录");
						$("#hide").fadeIn(1000);
						$("#hide").fadeOut(1000);
					}
				},
				error:function () {
					$("#hide").text("账号或密码发生错误!");
					$("#hide").fadeIn(1000);
					$("#hide").fadeOut(1000);
				}
			})
		})
	
	$("#account").click(function(){
		$("#account").focus();
		$("#account").blur(function(){
			var accout=$("#account").val();
			var flag=isNaN(Number(accout));
			if(accout!=""&&flag==false){
				$("#act").removeClass("has-error");
				$("#act").addClass("has-success");
				$("#accountTips").text ("合法账号!");
				$("#accountTips").css("color","#A2A2A2");
			}else{
				$("#act").addClass("has-error");
				$("#accountTips").text("账号格式不正确,请输入数字作为账号!");
				$("#accountTips").css("color","red");
			}
		});	
	});
	
	$("#inputPassword1").click(function(e){
		$("#inputPassword1").focus();
		$("#inputPassword1").blur(function(){
			var inputPassword1=$("#inputPassword1").val();
			var size=inputPassword1.length;
			if(inputPassword1!=""&&size>=3){
				$("#ipwd").removeClass("has-error");
				$("#ipwd").addClass("has-success");
				$("#ipwd1").text ("合法密码!");
				$("#ipwd1").css("color","#A2A2A2");
			}else{
				$("#ipwd").addClass("has-error");
				$("#ipwd1").text("密码格式不正确,请输入大于3个字符的密码!");
				$("#ipwd1").css("color","red");
			}
		});	
	});

});