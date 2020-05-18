<template>
	<div id="charts">
		<h3 align="center">{{title}} 数据图</h3>
		<div id="chartsId"  class="el-container"></div>
	</div>
</template>


<script>
import echarts from 'echarts';
import axios from 'axios';
export default {
  data() {
        return {
		  title: 'echarts',
		  data: [
		      [10.0, 8.04],
		      [8.0, 6.95],
		      [13.0, 7.58],
		      [9.0, 8.81],
		      [11.0, 8.33],
		      [14.0, 9.96],
		      [6.0, 7.24],
		      [4.0, 4.26],
		      [12.0, 10.84],
		      [7.0, 4.82],
		      [5.0, 5.68]
		  ],		 
      }
   },
    mounted(){
	  this.created(); 
	},
	methods:{
		created(){
			const _this = this;
			axios.get('http://localhost:8080/Compute/linearRegression/' + this.$route.query.memberId1 +'/'+ this.$route.query.memberId2 +'/'+ this.$route.query.dataId).then(res => {
				console.log(res);   //查询成功返回的值
				_this.data = res.data;
				this.drawLine('chartsId');
				}).catch(error => { 
					console.log(error);
					this.drawLine('chartsId'); 
				})   //查询失败返回的值		
		  },
		  drawLine(id) {
			    var myChart = echarts.init(document.getElementById(id));
			  	
				// See https://github.com/ecomfe/echarts-stat
				var myRegression = ecStat.regression('linear', this.data);
				
				myRegression.points.sort(function(a, b) {
				    return a[0] - b[0];
				});

			    // 指定图表的配置项和数据
			    var option = {
			  	   title: {
			  	           text: 'Linear Regression',
			  	           // subtext: 'By cm',
			  	           sublink: 'https://github.com/ecomfe/echarts-stat',
			  	           left: 'center'
			  	       },
			  	       tooltip: {
			  	           trigger: 'axis',
			  	           axisPointer: {
			  	               type: 'cross'
			  	           }
			  	       },
			  	       xAxis: {
			  	           type: 'value',
			  	           splitLine: {
			  	               lineStyle: {
			  	                   type: 'dashed'
			  	               }
			  	           },
			  	       },
			  	       yAxis: {
			  	           type: 'value',
			  	           min: 1.5,
			  	           splitLine: {
			  	               lineStyle: {
			  	                   type: 'dashed'
			  	               }
			  	           },
			  	       },
			  	       series: [{
			  	           name: 'scatter',
			  	           type: 'scatter',
			  	           emphasis: {
			  	               label: {
			  	                   show: true,
			  	                   position: 'left',
			  	                   color: 'blue',
			  	                   fontSize: 16
			  	               }
			  	           },
			  	           data: this.data
			  	       }, {
			  	           name: 'line',
			  	           type: 'line',
			  	           showSymbol: false,
			  	           data: myRegression.points,
			  	           markPoint: {
			  	               itemStyle: {
			  	                   color: 'transparent'
			  	               },
			  	               label: {
			  	                   show: true,
			  	                   position: 'left',
			  	                   formatter: myRegression.expression,
			  	                   color: '#333',
			  	                   fontSize: 14
			  	               },
			  	               data: [{
			  	                   coord: myRegression.points[myRegression.points.length - 1]
			  	               }]
			  	           }
			  	       }]
			  	};
			    myChart.hideLoading();
			    myChart.setOption(option);
			    this.title = this.$route.query.memberId;
			  },
	}	
}
</script>

<style scoped="less">
.el-container{
	margin-top: 50px;
	width: 1200px;
	height: 450px;
}
.footer{
	text-indent:1em;
	height: 150px;
	box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
	color: #333;
	border-radius: 4px
}
.numArea{
	border-top: 1px solid #eee;
	border-left: 1px solid #eee;
	border-bottom: 1px solid #969696;
	border-right: 1px solid #eee;
}
</style>
