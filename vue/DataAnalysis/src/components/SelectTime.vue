<template>
	<div id="charts">
		<h3 align="center">{{title}} 数据重演图</h3>
		<div id="selectTime"  class="el-container"></div>
		<div class="footer">
		操作面板
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
			<el-col :span="15" >
					<span class="demonstration">选择时间段</span>
					  <el-time-picker
					    is-range
					    v-model="time"
						:picker-options="{
						      selectableRange: '08:00:00 - 20:30:00'
						    }"
					    range-separator="-"
					    start-placeholder="开始时间"
					    end-placeholder="结束时间"
					    placeholder="选择时间范围">
					  </el-time-picker>
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
		  value1: 5,
		  time: '08:00:00',
		  data: [],
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
      }
   },
    mounted(){
	  this.created(); 
	},
	methods:{
		created(){
			this.random_Data(100000);
		},
		 formatTooltip(val) {
		     
		},
		random_Data(now) { //把代码封装到一个方法里
		      function randomData() {
		        now = now + 100;
		        value = value + Math.random() * 21 - 10;
		        return {
		          name: now.toString(),
		          value: [
		            now,
		            Math.round(value)
		          ]
		        };
		      }
		      var data = [];
		      var value = Math.random() * 1000;
		      for (var i = 0; i < 1000; i++) {
		        data.push(randomData());
		      }
			  var myChart = echarts.init(document.getElementById('selectTime'));
		      let option = { //加个let
		        title: {
		          text: " 仿真数据重演",
				  subtext: ' 默认开始时间为08:00:00',
		        },
		        tooltip: {
		          trigger: "axis",
		          formatter: function(params) {
		            params = params[0];
		            var date = new Date(params.name);
		            return (
		              date.getDate()
		            );
		          },
		          axisPointer: {
		            animation: false
		          }
		        },
		        xAxis: {
		          type: "time",
		          splitLine: {
		            show: false
		          }
		        },
		        yAxis: {
		          type: "value",
		          boundaryGap: [0, "100%"],
		          splitLine: {
		            show: false
		          }
		        },
		        series: [
		          {
		            name: "模拟数据",
		            type: "line",
		            showSymbol: false,
		            hoverAnimation: false,
		            data: data
		          }
		        ]
		      };
			   myChart.setOption(option);
		    }
			  
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
