$(document).ready(function(){
	$("#hide").hide();
	
	$("#savePassword").click(function(){
		var ipwd1=$("#inputPassword2").val();
		var ipwd2=$("#inputPassword3").val();
		if(ipwd1!=""&&ipwd2!=""&&ipwd1==ipwd2){
			var url = "http://114.215.196.31:8080/messageBoard/user/updatePassword";
			var userId = $.cookie('userId');
			var userPassword = $("#inputPassword2").val();
			$.ajax({
				url:url,
				type:"post",
				dataType:"text",
				contentType:"application/json",
				data:JSON.stringify({"userId":userId,"userPassword":userPassword}),
				success:function (data) {
					$("#hide").text("成功！很好地完成了提交。");
					$("#hide").fadeIn(1000);
					$("#hide").fadeOut(1000);
				},
				error:function () {
					$("#hide").text("错误！发生了错误!。");
					$("#hide").fadeIn(1000);
					$("#hide").fadeOut(1000);
				}
			});
		}else{
			$("#hide").text("错误！输入的两次密码不一致!。");
			$("#hide").fadeIn(1000);
			$("#hide").fadeOut(1000);
		}
	});
	
	
	$("#inputPassword1").click(function(e){
		$("#inputPassword1").focus();
		$("#inputPassword1").blur(function(){
			var ipwd1=$("#inputPassword1").val();
			var size=ipwd1.length;
			if(ipwd1!=""&&size>=3){
				$("#ipw1").removeClass("has-error");
				$("#ipw1").addClass("has-success");
				$("#ipwd1").text ("合法密码!");
				$("#ipwd1").css("color","#A2A2A2");
			}else{
				$("#ipw1").addClass("has-error");
				$("#ipwd1").text("密码格式不正确,请输入大于3个字符的密码!");
				$("#ipwd1").css("color","red");
			}
		});	
	});
	
	$("#inputPassword2").click(function(e){
		$("#inputPassword2").focus();
		$("#inputPassword2").blur(function(){
			var ipwd2=$("#inputPassword2").val();
			var size=ipwd2.length;
			if(ipwd2!=""&&size>=3){
				$("#ipw2").removeClass("has-error");
				$("#ipw2").addClass("has-success");
				$("#ipwd2").text ("合法密码!");
				$("#ipwd2").css("color","#A2A2A2");
			}else{
				$("#ipw2").addClass("has-error");
				$("#ipwd2").text("密码格式不正确,请输入大于3个字符的密码!");
				$("#ipwd2").css("color","red");
			}
		});	
	});
	
	$("#inputPassword3").click(function(e){
		$("#inputPassword3").focus();
		$("#inputPassword3").blur(function(){
			var ipwd1=$("#inputPassword2").val();
			var ipwd2=$("#inputPassword3").val();
			if(ipwd1!=""&&ipwd2!=""&&ipwd1==ipwd2){
				$("#ipw3").removeClass("has-error");
				$("#ipw3").addClass("has-success");
				$("#ipwd3").text ("合法密码!");
				$("#ipwd3").css("color","#A2A2A2");
			}else{
				$("#ipw3").addClass("has-error");
				$("#ipwd3").text("两次密码输入不一致!");
				$("#ipwd3").css("color","red");
			}
		});	
	});
});