<template>
	<div id="charts">
		<h3 align="center">{{title}} 数据图</h3>
		<div id="chartsId"  class="el-container"></div>
		<div class="footer">
		统计信息
		 <el-row>
			<el-col :span="8" :offset="1">
				<template>选择属性
				  <el-select v-model="value" placeholder="请选择">
					<el-option
					  v-for="item in options"
					  :key="item.value"
					  :label="item.label"
					  :value="item.value">
					</el-option>
				  </el-select>
				</template>
		    </el-col>
		  </el-row>
		 <el-row>
			<el-col :span="2" :offset="1">平均数</el-col> 
			<el-col :span="4">
			   <div class="numArea">123</div>
			</el-col> 
			<el-col :span="2">方差</el-col>
			<el-col :span="4">
			   <div class="numArea">123</div>
			</el-col> 
			<el-col :span="2">标准差</el-col>
			<el-col :span="4">
			   <div class="numArea">123</div>
			</el-col> 
		 </el-row>
		</div>
	</div>
</template>

<script>
import echarts from 'echarts';
import axios from 'axios';
export default {
  data() {
        return {
		  title: 'echarts',
          options: [{
            value: 'one',
            label: '成员1'
          }, {
            value: 'two',
            label: '成员2'
          }, {
            value: 'three',
            label: '成员3'
          }],
          value: 'one'
        }
      },
    watch: {
		'$route' (to, from) {
		  // 路由发生变化页面刷新
			 // this.$router.go(0);
			  this.title = this.$route.query.memberId;
			 const _this = this;
			 axios.post('http://localhost:8080/DataCollect/chartDate/'+this.$route.query.memberId +'/'+ this.$route.query.dataId).then(res => {
				console.log(res);   //查询成功返回的值
				// _this.title = res.data.memberId;
				}).catch(error => { console.log(error) })   //查询失败返回的值
			}
	  },
	/* created(){
		const _this = this;
		axios.get('http://localhost:8080/DataCollect/chartDate/'+this.$route.query.federationId).then(res => {
			console.log(res);   //查询成功返回的值
			_this.title = res.data.memberId;
			}).catch(error => { console.log(error) })   //查询失败返回的值
	  }, */
    mounted(){
	  var myChart = echarts.init(document.getElementById('chartsId'));
	  // 指定图表的配置项和数据
	  var option = {
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
	  this.title = this.$route.query.memberId;
	},
	methods:{
		handleClick(tab, event) {
				console.log(tab, event);
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
