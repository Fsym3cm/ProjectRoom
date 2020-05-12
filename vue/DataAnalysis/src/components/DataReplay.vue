<template>
	<div id="charts">
		<h3 align="center">{{title}} 数据重演图</h3>
		<div id="replay"  class="el-container"></div>
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
			<el-col :span="8" >
				 <div class="block">
					<span class="demonstration">调整速度</span>
					<el-slider v-model="value1" :format-tooltip="formatTooltip"></el-slider>
				  </div>
			</el-col>
			<el-col :span="4" :offset="1">
				<span class="demonstration">暂停键</span>
				<el-button  class="el-icon-video-play" size="mini" type="info" round></el-button>
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
			this.random_Data();
		},
		 formatTooltip(val) {
		     
		},
		random_Data() { //把代码封装到一个方法里
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
		      var now = 0;
		      var value = Math.random() * 1000;
		      for (var i = 0; i < 1000; i++) {
		        data.push(randomData());
		      }
			  var myChart = echarts.init(document.getElementById('replay'));
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
			  
		      setInterval(function() {
		        for (var i = 0; i < 5; i++) {
		          data.shift();
		          data.push(randomData());
		        }
		        //这个换成下方的一句代码，不然会报错（series.type should be specified.）
		        // myChart.setOption({
		        //   series: [
		        //     {
		        //       data: data
		        //     }
		        //   ]
		        // });
		        myChart.setOption(option);
		      }, 1000);
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
