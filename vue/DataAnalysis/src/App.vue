<template>
  <div id="app">
	<el-row>
	  <el-col :span="4" :offset="11"><div class="grid-content bg-purple-dark">仿真数据分析系统</div></el-col>
	</el-row>
	<hr />
	<router-view></router-view>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.el-row {
    margin-bottom: 10px;
    &:last-child {
      margin-bottom: 0;
    }
  }
</style>
