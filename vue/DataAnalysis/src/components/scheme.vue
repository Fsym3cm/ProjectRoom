<template>
	<div id="scheme">
		<el-table
		    :data="tableData"
		    style="width: 100%">
		    <el-table-column type="expand">
		      <template slot-scope="props">
		        <el-form label-position="left" inline class="demo-table-expand">
		          <el-form-item label="预案编号">
		            <span>{{ props.row.schemeId }}</span>
		          </el-form-item>
		          <el-form-item label="预案名称">
		            <span>{{ props.row.schemeName }}</span>
		          </el-form-item>
		          <el-form-item label="开始时间">
		            <span>{{ props.row.beginTime }}</span>
		          </el-form-item>
		          <el-form-item label="结束时间">
		            <span>{{ props.row.endTime }}</span>
		          </el-form-item>
		          <el-form-item label="生成日期">
		            <span>{{ props.row.generateDate }}</span>
		          </el-form-item>
		          <el-form-item label="方案作者">
		            <span>{{ props.row.schemeAuthor }}</span>
		          </el-form-item>
		          <el-form-item label="仿真说明">
		            <span>{{ props.row.schemeDesc }}</span>
		          </el-form-item>
				  <el-form-item label="仿真文件">
				    <span>{{ props.row.schemeFile }}</span>
				  </el-form-item>
				  <el-form-item label="是否发布">
				    <span>{{ props.row.isPublish }}</span>
				  </el-form-item>
		        </el-form>
		      </template>
		    </el-table-column>
		    <el-table-column
		      label="预案编号"
		      prop="schemeId">
		    </el-table-column>
		    <el-table-column
		      label="预案名称"
		      prop="schemeName">
		    </el-table-column>
		    <el-table-column
		      label="方案作者"
		      prop="schemeAuthor">
		    </el-table-column>
			<el-table-column
			      align="right">
			      <template slot="header" slot-scope="scope">
			        <el-input
			          v-model="search"
			          size="mini"
			          placeholder="输入关键字搜索"/>
			      </template>
			      <template slot-scope="scope">
			        <el-button
			          size="mini"
			          @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
			        <el-button
			          size="mini"
			          type="danger"
			          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
			      </template>
			    </el-table-column>
		  </el-table>
	</div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
      return {
		search: '',
		
        tableData: [{
          schemeId: '666666',
          schemeName: '反复横跳',
          beginTime: '2020-04-01 19:23:41',
          endTime: '2020-04-08 19:23:41',
          generateDate: '2020-04-08',
          schemeAuthor: '坂本',
          schemeDesc: '帅就完事了',
		  schemeFile: '',
		  isPublish: ''
        }]
      }
    },
	created(){
		const _this = this
		axios.get('http://localhost:8080/SimuScheme/show').then(res => {
			console.log(res);   //查询成功返回的值
			_this.tableData = res.data;
			}).catch(error => { console.log(error) })   //查询失败返回的值
	},
	methods: {
		handleEdit(index, row) {
			this.$router.push({
				path:'/updateScheme',
				query:{
					schemeId: row.schemeId
				}
			});
		},
		handleDelete(index, row) {
			axios.get('http://localhost:8080/SimuScheme/removeById/'+row.schemeId).then(res => {
				console.log(res);   //查询成功返回的值
				location.reload();
				}).catch(error => { console.log(error) })   //查询失败返回的值
		}
	}
  }
</script>

<style scoped="less">
 .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
