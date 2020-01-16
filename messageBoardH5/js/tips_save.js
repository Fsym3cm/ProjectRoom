$(document).ready(function(){
	$("#hide").hide();
	$("#aaa").hide();
	// 方法一
	function User(){
	    this.userName = $("#account").val();
	    this.userPassword = $("#inputPassword1").val();
	    this.userType = 2;
	    this.nickname = $("#nickname").val();
	}
	
	$("#register").click(function(){
		var account=$("#account").val();
		var ipwd1=$("#inputPassword1").val();
		var ipwd2=$("#inputPassword2").val();
		var authcode=$("#authCode").val();
		var flag=isNaN(parseInt(account));
		var url = "http://localhost:8080/messageBoard/user/register";
		
		if(account!=""&&flag==false){
			if(nickname!=""&&ipwd1!=""&&ipwd2!=""&&ipwd1==ipwd2){
				 var user = new User();
				 console.log(JSON.stringify(user));
				 // 方法二
				// var user={
				//     username : $("#account").val(),
				//     password : $("#inputPassword1").val(),
				//     usertype : 2,
				// 	nickname : $("#nickname").val(),
				// }
				$.ajax({					
					url:url,
					type:"post",
					dataType:"text",
					contentType:"application/json",
					data:JSON.stringify(user),
					success:function (data) {
						window.location.href="login.html";
					},
					error:function () {
						console.log("error");
					}
				})
			}else{
				$("#hide").text("错误！账户或密码错误");
				$("#hide").fadeIn(1000);
				$("#hide").fadeOut(1000);
			}
		}else{
			$("#hide").text("错误！账户只能为数字类型");
			$("#hide").fadeIn(1000);
			$("#hide").fadeOut(1000); 
		}
	});
	
	$("#saveMassage").click(function(){
		var url = "http://localhost:8080/messageBoard/user/updateUser";
		
		var user={
			userId : $.cookie('userId'),
		    name : $("#name").val(),
		    nickname : $("#nickname").val(),
		    telephone : $("#telephone").val(),
			email : $("#email").val(),
		}
		
		$.ajax({
			url:url,
			type:"post",
			dataType:"text",
			contentType:"application/json",
			data:JSON.stringify(user),
			success:function (data) {
				$("#hide").text("成功！很好地完成了提交。");
				$("#hide").fadeIn(1000);
				$("#hide").fadeOut(1000);
			},
			error:function () {
				console.log("error");
			},
		});
		
	});
	
	$("#save").click(function(){
		$("#hide").text("成功！保存草稿成功。");
		$("#hide").fadeIn(1000);
		$("#hide").fadeOut(1000);
	});
	
	$("#addTopics").click(function(){
		$("#hide").text("成功！很好地完成了提交。");
		$("#hide").fadeIn(1000);
		$("#hide").fadeOut(1000);
	});
	
	$("#addTopics").click(function(){
		window.location.href="addTopics.html"
	});
	
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
	
	$("#inputPassword2").click(function(e){
		$("#inputPassword2").focus();
		$("#inputPassword2").blur(function(){
			var ipwd1=$("#inputPassword1").val();
			var ipwd2=$("#inputPassword2").val();
			if(ipwd1!=""&&ipwd2!=""&&ipwd1==ipwd2){
				$("#ipw").removeClass("has-error");
				$("#ipw").addClass("has-success");
				$("#ipwd2").text ("合法密码!");
				$("#ipwd2").css("color","#A2A2A2");
			}else{
				$("#ipw").addClass("has-error");
				$("#ipwd2").text("两次密码输入不一致!");
				$("#ipwd2").css("color","red");
			}
		});	
	});
	
	$("#nickname").click(function(e){
		$("#nickname").focus();
		$("#nickname").blur(function(){
			var nickname=$("#nickname").val();
			if(nickname!=""){
				$("#n1").removeClass("has-error");
				$("#n1").addClass("has-success");
				$("#n2").text ("合法昵称!");
				$("#n2").css("color","#A2A2A2");
			}else{
				$("#n1").addClass("has-error");
				$("#n2").text("请输入的昵称不为空!");
				$("#n2").css("color","red");
			}
		});	
	});
	
	$("#name").click(function(e){
		$("#name").focus();
		$("#name").blur(function(){
			var name=$("#name").val();
			var patten = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/; //验证姓名正则
			if(patten.test(name)){
				$("#na1").removeClass("has-error");
				$("#na1").addClass("has-success");
				$("#na2").text ("输入正确!");
				$("#na2").css("color","#A2A2A2");			
			}else{
				$("#na1").addClass("has-error");
				$("#na2").text("无法识别该名称!");
				$("#na2").css("color","red");
				}
		});	
	});
	
	$("#telephone").click(function(e){
		$("#telephone").focus();
		$("#telephone").blur(function(){
			var telephone=$("#telephone").val();
			var patten = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/; //验证手机号正则
			if(patten.test(telephone)){
				$("#tphone1").removeClass("has-error");
				$("#tphone1").addClass("has-success");
				$("#tphone2").text ("输入正确!");
				$("#tphone2").css("color","#A2A2A2");			
			}else{
				$("#tphone1").addClass("has-error");
				$("#tphone2").text("手机号格式不正确!");
				$("#tphone2").css("color","red");
				}
		});	
	});
		
	$("#email").click(function(e){
		$("#email").focus();
		$("#email").blur(function(){
			var email=$("#email").val();
			var patten = /^[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}$/; //验证邮箱正则
			if(patten.test(email)){
				$("#em1").removeClass("has-error");
				$("#em1").addClass("has-success");
				$("#em2").text ("输入正确!");
				$("#em2").css("color","#A2A2A2");			
			}else{
				$("#em1").addClass("has-error");
				$("#em2").text("邮箱格式不正确!");
				$("#em2").css("color","red");
				}
		});	
	});
	  
	$("#optionsRadios1").click(function(e){
		$("#aaa").hide();
	});
	
	$("#optionsRadios2").click(function(e){
		$("#aaa").show();
	});
	
	
});