$(document).ready(function() {
	randomNum(0, 9);
	randomColor(0, 255);

	/**生成一个随机数**/
	function randomNum(min, max) {
		return Math.floor(Math.random() * (max - min) + min);
	}
	/**生成一个随机色**/
	function randomColor(min, max) {
		var r = randomNum(min, max);
		var g = randomNum(min, max);
		var b = randomNum(min, max);
		return "rgb(" + r + "," + g + "," + b + ")";
	}
	/**绘制验证码图片**/
	function drawPic() {
		var canvas = document.getElementById("canvas");
		var width = 150;
		var height = 33;
		//获取该canvas的2D绘图环境 
		var code = "";
		if (canvas != null) {
			var ctx = canvas.getContext('2d');
			ctx.textBaseline = 'bottom';
			/**绘制背景色**/
			ctx.fillStyle = randomColor(180, 240);
			//颜色若太深可能导致看不清
			ctx.fillRect(0, 0, width, height);
			/**绘制文字**/
			var str = 'ABCEFGHJKLMNPQRSTWXY123456789';

			//生成四个验证码
			for (var i = 1; i <= 4; i++) {
				var txt = str[randomNum(0, str.length)];
				code = code + txt;
				ctx.fillStyle = randomColor(50, 160);
				//随机生成字体颜色
				ctx.font = randomNum(15, 40) + 'px SimHei';
				//随机生成字体大小
				var x = 10 + i * 25;
				var y = randomNum(25, 35);
				var deg = randomNum(-45, 45);
				//修改坐标原点和旋转角度
				ctx.translate(x, y);
				ctx.rotate(deg * Math.PI / 180);
				ctx.fillText(txt, 0, 0);
				//恢复坐标原点和旋转角度
				ctx.rotate(-deg * Math.PI / 180);
				ctx.translate(-x, -y);
			}

			/**绘制干扰线**/
			for (var i = 0; i < 3; i++) {
				ctx.strokeStyle = randomColor(40, 180);
				ctx.beginPath();
				ctx.moveTo(randomNum(0, width / 2), randomNum(0, height / 2));
				ctx.lineTo(randomNum(0, width / 2), randomNum(0, height));
				ctx.stroke();
			}
			/**绘制干扰点**/
			for (var i = 0; i < 50; i++) {
				ctx.fillStyle = randomColor(255);
				ctx.beginPath();
				ctx.arc(randomNum(0, width), randomNum(0, height), 1, 0, 2 * Math.PI);
				ctx.fill();
			}
		}
		return code;
	}
	//初始化验证码
	var verCode = drawPic();
	var canvas = document.getElementById("canvas");
	if (canvas != null) {
		document.getElementById("canvas").onclick = function(e) {
			e.preventDefault();
			verCode = drawPic();
		}
	}

	$("#authCode").click(function() {
		$("#authCode").focus();
		$("#authCode").blur(function() {
			var authCode = $("#authCode").val();
			if (authCode == verCode) {
				$("#acode1").removeClass("has-error");
				$("#acode1").addClass("has-success");
				$("#acode2").text("验证码输入正确!");
				$("#acode2").css("color", "#A2A2A2");
			} else {
				$("#acode1").addClass("has-error");
				$("#acode2").text("验证码输入错误!");
				$("#acode2").css("color", "red");
			}
		});
	});

});
