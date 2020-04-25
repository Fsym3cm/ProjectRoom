<template>
  <div id="app">
	<el-row>
	  <el-col :span="4" ><div class="grid-content bg-purple-dark">仿真数据分析系统</div></el-col>
	</el-row>
	<el-container style="height: 665px; border: 1px solid #eee">
	  <el-aside width="250px" style="background-color: rgb(238, 241, 246)">
	    <el-menu :default-openeds="['1']">
	      <el-submenu index="1">
	        <template slot="title"><i class="el-icon-message"></i>数据图</template>
	        <el-menu-item-group>
	          <template slot="title">操作</template>
	          <el-menu-item index="1-1" @click="showChart">查看</el-menu-item>
	          <!-- <el-menu-item index="1-2">新增</el-menu-item> -->
	        </el-menu-item-group>
	        </el-submenu>
	      <el-submenu index="2">
	        <template slot="title"><i class="el-icon-menu"></i>方案</template>
	        <el-menu-item-group>
	          <template slot="title">操作</template>
	          <el-menu-item index="2-1" @click="showScheme">查看</el-menu-item>
	          <el-menu-item index="2-2" @click="addScheme">新增</el-menu-item>
	          <el-menu-item index="2-3" @click="updateScheme">修改</el-menu-item>
			  <el-menu-item index="2-4" @click="showScheme">删除</el-menu-item>
	        </el-menu-item-group>
	      </el-submenu>
	    </el-menu><br>
		<el-row>方案信息</el-row>
		<el-tree
		  :data="data"
		  :props="defaultProps"
		  @node-click="schemeMassege">
		</el-tree>
		<br>
		<el-row>历史查询方案</el-row>
		<el-tree
		  :data="data"
		  :props="defaultProps"
		  accordion
		  @node-click="historicalQuery">
		</el-tree>
	  </el-aside>
	  
	  <el-container>
		  <el-header type="card">    
		        <span>data</span>
		  </el-header>
		      
		  <el-main>
			  	<router-view></router-view>
		  </el-main>
	  </el-container>
	</el-container>

  </div>
</template>

<script>
import axios from 'axios';
export default {
	 data() {
	      return {
			activeName: 'first',
	        data: [{
	          label: '方案名称：1',
	          children: [{
	            label: '成员ID：1',
	            children: [{
	              label: '属性：1'
	            }]
				},{
				label: '成员ID：2',
				children: [{
				  label: '属性：2',
				 }]
	          }]
	        }, {
	          label: '方案名称：2',
	          children: [{
	            label: '成员ID：1',
	            children: [{
	              label: '属性：1',
				  }]
	            },{
				  label: '成员ID：2',
				  children: [{
				    label: '属性：2'
				  }]
	          }, {
	            label: '成员ID：3',
	            children: [{
	              label: '属性：3'
	            }]
	          }]
	        }, {
	          label: '方案名称：3',
	          children: [{
	            label: '成员ID：1',
	            children: [{
	              label: '属性：1',
	              }]
	            },{
				  label: '成员ID：2',
				  children: [{
					label: '属性：2'
				  }]
	          }, {
	            label: '成员ID：3',
	            children: [{
	              label: '属性：3'
	            }]
	          }]
	        }],
	        defaultProps: {
	          children: 'children',
	          label: 'label'
	        }
	      };
	    },
		created(){
		   const _this = this
		   axios.get('http://localhost:8080/DataCollect/dateTree').then(res => {
		   	console.log(res);   //查询成功返回的值
		   	_this.data = res.data;
		   	}).catch(error => { console.log(error) })   //查询失败返回的值
		},
	methods:{
		showChart(){
			this.$router.push('/chart');
		},
		showScheme(){
			this.$router.push('/scheme');
		},
		addScheme(){
			this.$router.push('/addScheme');
		},
		updateScheme(){
			this.$router.push('/updateScheme');
		},
		schemeMassege(data) {
		    console.log(data);
		},
		historicalQuery(data) {
		    console.log(data);
		},
	}
}
</script>

<style>
.el-row {
    margin-bottom: 10px;
    &:last-child {
      margin-bottom: 0;
    }
  }
.el-header {
 background-color: #B3C0D1;
 color: #333;
 line-height: 60px;
}
.el-aside {
 color: #333;
}
</style>
