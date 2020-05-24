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
		  step: ['10', '20', '30', '40', '50', '60', '70', '80', '90', '100', '110', '120'],
		  outputValue1: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
		  outputValue2: [3.9, 5.9, 11.1, 18.7, 48.3, 69.2, 231.6, 46.6, 55.4, 18.4, 10.3, 0.7],
        }
      },
    watch: {
		'$route' (to, from) {
			  this.created(); 
			}
	  },
    mounted(){
	  this.created(); 
	},
	methods:{
		created(){
			const _this = this;
			axios.get('http://localhost:8080/DataCollect/chartDate/'+this.$route.query.memberId +'/'+ this.$route.query.dataId).then(res => {
				console.log(res);   //查询成功返回的值
				var step = [], outputValue = [];
				for (let i in res.data) {
				    /* let s = {}, o = {};
					s[i] = res.data[i].step; 保留原来的数据格式
					o[i] = res.data[i].outputValue; */
					step.push(res.data[i].step)
					outputValue.push(Number(res.data[i].outputValue))
				}
				_this.step = step;
				_this.outputValue = outputValue;
				this.drawLine('chartsId');
				}).catch(error => { 
					console.log(error);
					this.drawLine('chartsId'); 
				})   //查询失败返回的值
				axios.get('http://localhost:8080/Compute/methodsTree/' + this.$route.query.dataId).then(res => {
					console.log(res);   //查询成功返回的值
					_this.methods = res.data;
					}).catch(error => { 
						console.log(error);
					})   //查询失败返回的值
		  },
		  drawLine(id) {
			    var myChart = echarts.init(document.getElementById(id));
			  	myChart.showLoading();
			    // 指定图表的配置项和数据
				var colors = ['#5793f3', '#d14a61', '#675bba'];

			    var option = {
			  	   color: colors,
			  	   title: {
			  	           text: ' Correlation Analysis',
			  	       },
			  	       tooltip: {
			  	           trigger: 'none',
			  	           axisPointer: {
			  	               type: 'cross'
			  	           }
			  	       },
			  	       legend: {
			  	           data:['成员一', '成员二']
			  	       },
			  	       grid: {
			  	           top: 70,
			  	           bottom: 50
			  	       },
			  	       xAxis: [
			  	           {
			  	               type: 'category',
			  	               axisTick: {
			  	                   alignWithLabel: true
			  	               },
			  	               axisLine: {
			  	                   onZero: false,
			  	                   lineStyle: {
			  	                       color: colors[1]
			  	                   }
			  	               },
			  	               axisPointer: {
			  	                   label: {
			  	                       formatter: function (params) {
			  	                           return '成员  ' + params.value
			  	                               + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
			  	                       }
			  	                   }
			  	               },
			  	               data: this.step
			  	           },
			  	           {
			  	               type: 'category',
			  	               axisTick: {
			  	                   alignWithLabel: true
			  	               },
			  	               axisLine: {
			  	                   onZero: false,
			  	                   lineStyle: {
			  	                       color: colors[0]
			  	                   }
			  	               },
			  	               axisPointer: {
			  	                   label: {
			  	                       formatter: function (params) {
			  	                           return '成员  ' + params.value
			  	                               + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
			  	                       }
			  	                   }
			  	               },
			  	               data:this.step
			  	           }
			  	       ],
			  	       yAxis: [
			  	           {
			  	               type: 'value'
			  	           }
			  	       ],
			  	       series: [
			  	           {
			  	               name: '成员一',
			  	               type: 'line',
			  	               xAxisIndex: 1,
			  	               smooth: true,
			  	               data: this.outputValue1
			  	           },
			  	           {
			  	               name: '成员二',
			  	               type: 'line',
			  	               smooth: true,
			  	               data: this.outputValue2
			  	           }
			  	       ]
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
