$(document).ready(function() {

	var url = "http://localhost:8080/messageBoard/province/showAllProvince";

	$.ajax({
		url: url,
		type: "post",
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			for (var i in data) {
				$("#province").append('<option>' + data[i].name + '</option>');
			}
			$("#province").focus();
			$("#province").blur(function() {
				// console.log($("#province").text());
				// 用val获得表单提交的值.
				$("#city").empty();
				var name = $("#province").val();
				var url = "http://localhost:8080/messageBoard/province/queryCode";

				$.ajax({
					url: url,
					type: "post",
					dataType: "json",
					data: {
						"name": name
					},
					success: function(data) {
						var url = "http://localhost:8080/messageBoard/city/queryCity";
						var provinceCode = data;

						$.ajax({
							url: url,
							type: "post",
							dataType: "json",
							data: {
								"provinceCode": provinceCode
							},
							success: function(data) {
								for (var i in data) {
									$("#city").append('<option>' + data[i].name + '</option>');
								}
								$("#city").focus();
								$("#city").blur(function() {
									$("#area").empty();
									var name = $("#city").val();
									var url = "http://localhost:8080/messageBoard/city/queryCode";

									$.ajax({
										url: url,
										type: "post",
										dataType: "json",
										data: {
											"name": name
										},
										success: function(data) {
											var url = "http://localhost:8080/messageBoard/area/queryArea";
											var cityCode = data;

											$.ajax({
												url: url,
												type: "post",
												dataType: "json",
												data: {
													"cityCode": cityCode
												},
												success: function(data) {
													for (var i in data) {
														$("#area").append('<option>' + data[i].name + '</option>');
													}
													// $("#city").focus();
													// $("#city").blur(function() {

													//  })
												},
												error: function() {
													console.log("error");
												}
											})

										},
										error: function() {
											console.log("error");
										}
									})
								});
							},
							error: function() {
								console.log("error");
							}
						})
					},
					error: function() {
						console.log("error");
					}
				})
			});
		},
		error: function() {
			console.log("error");
		},
	})
})
