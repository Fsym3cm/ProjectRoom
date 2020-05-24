<template>
	<div id="charts">
		<h3 align="center">{{title}} 数据图</h3>
		<div id="chartsId"  class="el-container"></div>
		<div class="footer">
		统计信息{{value.label}}
		 <el-row>
			<el-col :span="8" :offset="1">
				<template>选择属性
				  <el-select v-model="attr" placeholder="请选择">
					<el-option
					  v-for="item in options"
					  :key="item.attr"
					  :label="item.label"
					  :value="item.attr">
					</el-option>
				  </el-select>
				</template>
		    </el-col>
			<el-col :span="10" >
				  <span class="demonstration">选择分析方法与成员</span>
				    <el-cascader
				      v-model="value"
				      :options="methods"
				      @change="handleChange"></el-cascader>
			</el-col>
		  </el-row>
		 <el-row>
			<el-col :span="2" :offset="1">平均数</el-col> 
			<el-col :span="4">
			   <div class="numArea">1000</div>
			</el-col> 
			<el-col :span="2">方差</el-col>
			<el-col :span="4">
			   <div class="numArea">28</div>
			</el-col> 
			<el-col :span="2">标准差</el-col>
			<el-col :span="4">
			   <div class="numArea">5.29</div>
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
		  step: [10, 20, 30, 40, 50, 60, 70],
		  outputValue: [820, 932, 901, 934, 1290, 1330, 1320],
          options: [{
            attr: 'one',
            label: '属性1'
          }, {
            attr: 'two',
            label: '属性2'
          }, {
            attr: 'three',
            label: '属性3'
          }],
          attr: 'one',
		  methods: [{
		    value: '0',
			label: '分类分析',
			children: [{
			  value: '0',
			  label: '成员1'
			}, {
			  value: '1',
			  label: '成员2'
			}],
			},{
		    value: '1',
		    label: '仿真时域',
			children: [{
			  value: '0',
			  label: '成员1'
			}, {
			  value: '1',
			  label: '成员2'
			}],
		  }, {
		    value: '2',
		    label: '线性回归',
			children: [{
			  value: '0',
			  label: '成员1'
			}, {
			  value: '1',
			  label: '成员2'
			}],
		  }, {
		    value: '3',
		    label: '关联分析',
			children: [{
			  value: '0',
			  label: '成员1'
			}, {
			  value: '1',
			  label: '成员2'
			}],
		  }],
		  value: 'error',
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
		  handleChange(value){
			  console.log(value);
			  if(value[0]  == 0){
				  alert(1)
			  } else if (value[0] == 1){
				  alert(2)
			  } else if (value[0] == 2){
				  //线性回归
				  var memberId2 = parseInt(value[1]) + 1;
				  var dataId = this.$route.query.dataId;
				  this.$router.push({path:'/linearRegression',query:{memberId1:this.$route.query.memberId, memberId2:memberId2.toString(), dataId: dataId}});
			  } else{
				  var memberId2 = parseInt(value[1]) + 1;
				  var dataId = this.$route.query.dataId;
				  this.$router.push({path:'/correlation',query:{memberId1:this.$route.query.memberId, memberId2:memberId2.toString(), dataId: dataId}});
			  }
		   },
		  drawLine(id) {
			  var myChart = echarts.init(document.getElementById(id));
			  	myChart.showLoading();
			    // 指定图表的配置项和数据
			    var option = {
			  	   legend: {
			  	          data: [this.value]
			  	      },
			  		xAxis: {
			  			type: 'category',
			  			data: this.step
			  		},
			  		yAxis: {
			  			type: 'value'
			  		},
			  		series: [{
			  			name: this.value,
			  			data: this.outputValue,
			  			type: 'line'
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
