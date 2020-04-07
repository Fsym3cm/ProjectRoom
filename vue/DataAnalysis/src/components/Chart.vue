<template>
	<div id="charts" class="el-container"></div>
</template>

<script>
import echarts from 'echarts';
export default {
  mounted(){
		  var myChart = echarts.init(document.getElementById('charts'));
		  // 指定图表的配置项和数据
		  var option = {
			  title: {
				  text: 'ECharts 入门示例'
			  },
			  tooltip: {},
			  legend: {
				  data:['销量']
			  },
			  xAxis: {
				  data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
			  },
			  yAxis: {},
			  series: [{
				  name: '销量',
				  type: 'bar',
				  data: [5, 20, 36, 10, 10, 20]
			  }]
		  };
		  // 使用刚指定的配置项和数据显示图表。
		  myChart.setOption(option);
	}
}
</script>

<style scoped="less">
.el-container{
	width: 500px;
	height: 300px;
}
</style>
