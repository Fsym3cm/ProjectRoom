<template>
    <div id="updateSchema">
	   <el-form ref="form" :model="form" label-width="80px">
		  <el-form-item label="预案名称">
		    <el-input v-model="form.schemeName"></el-input>
		  </el-form-item>
		  <el-form-item label="起始时间">
		    <el-col :span="11">
		      <el-date-picker type="date" placeholder="选择日期" v-model="form.beginTime" style="width: 100%;"></el-date-picker>
		    </el-col>
		    <el-col class="line" :span="1" :offset="1">-</el-col>
		    <el-col :span="11">
		      <el-time-picker placeholder="选择时间" v-model="form.beginTime" style="width: 100%;"></el-time-picker>
		    </el-col>
		  </el-form-item>
		  <el-form-item label="结束时间">
		    <el-col :span="11">
		      <el-date-picker type="date" placeholder="选择日期" v-model="form.endTime" style="width: 100%;"></el-date-picker>
		    </el-col>
		    <el-col class="line" :span="1" :offset="1">-</el-col>
		    <el-col :span="11">
		      <el-time-picker placeholder="选择时间" v-model="form.endTime" style="width: 100%;"></el-time-picker>
		    </el-col>
		  </el-form-item>
		  <el-form-item label="生成日期">
		    <el-col :span="11">
		      <el-date-picker type="date" placeholder="选择日期" v-model="form.generateDate" style="width: 100%;"></el-date-picker>
		    </el-col>
		  </el-form-item>
		  <el-form-item label="方案作者">
		      <el-input v-model="form.schemeAuthor"></el-input>
		    </el-form-item>
		  <el-form-item label="仿真说明">
		    <el-input type="textarea" v-model="form.schemeDesc"></el-input>
		  </el-form-item>
		  <el-form-item label="仿真文件">
			 <el-upload :span="11"
			   class="upload-demo"
			   action=""
			   :on-change="handleChange"
			   :file-list="fileList">
			   <el-button size="small" type="primary">点击上传</el-button>
			   <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
			 </el-upload>
		   </el-form-item>
		  <el-form-item label="是否发布">
			 <el-upload
			   class="upload-demo"
			   action=""
			   :on-change="handleChange"
			   :file-list="fileList">
			   <el-button size="small" type="primary">点击上传</el-button>
			 </el-upload>
		  </el-form-item>
		  <el-form-item>
		    <el-button type="primary" @click="onSubmit">修改</el-button>
		   <el-button @click="cancel">取消</el-button>
		  </el-form-item>
		</el-form>
	</div>
</template>

<script>
import axios from 'axios';
export default {
	data() {
	  return {
		form: {
		 schemeName: '反复横跳',
		 beginTime: '2020-04-09 21:44:30',
		 beginTime1: '2020-04-09 21:44:30',
		 endTime: '2020-04-09 21:44:30',
		 endTime1: '2020-04-09 21:44:30',
		 generateDate: '2020-04-09 21:44:30',
		 schemeAuthor: '坂本',
		 schemeDesc: '帅就完事了',
		 schemeFile: '',
		 isPublish: ''
		},
		fileList: [{
		  name: 'food.jpeg',
		  url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
		}] 
	  }
	},
	created(){
	   const _this = this
	   axios.get('http://localhost:8080/SimuScheme/getById/'+this.$route.query.schemeId).then(res => {
	   	console.log(res);   //查询成功返回的值
	   	_this.form = res.data;
	   	}).catch(error => { console.log(error) })   //查询失败返回的值
	},
	methods: {
	  onSubmit() {
		 axios.post('http://localhost:8080/SimuScheme/update',this.form).then(res => {
			console.log(res);
			alert('修改成功');
		   }).catch(error => { console.log(error) })    //查询失败返回的值 		
	  },
	  handleChange(file, fileList) {
		  this.fileList = fileList.slice(-3);
	  },
	  cancel(){
		  location.reload();
	  }
	}
  }
</script>

<style>
</style>