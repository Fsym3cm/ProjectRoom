import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import VueRouter from 'vue-router'//引入vue-router
import Home from './components/Home.vue'
import Chart from './components/Chart.vue'
import Scheme from './components/Scheme.vue'
import AddScheme from './components/AddScheme.vue'
import UpdateScheme from './components/UpdateScheme.vue'
import LinearRegression from './components/LinearRegression.vue'
import DataReplay from './components/DataReplay.vue'
import SelectTime from './components/SelectTime.vue'
import Correlation from './components/Correlation.vue'

Vue.use(ElementUI)
Vue.use(VueRouter)//使用vue-router

window.router=router
const  router = new VueRouter({//定义一个常量来配置路由
    routes:[
        {path:"/home", component:Home},//配置路径与对应组件名
        {path:"/chart", component:Chart},//配置路径与对应组件名
		{path:"/scheme", component:Scheme},
		{path:"/addScheme", component:AddScheme},
		{path:"/updateScheme", component:UpdateScheme},
		{path:"/linearRegression", component:LinearRegression},
		{path:"/dataReplay", component:DataReplay},
		{path:"/selectTime", component:SelectTime},
		{path:"/correlation", component:Correlation},
    ],
    mode:"history"//建议设置该属性，否则地址栏将出现“#”号，会影响后续编码
})


new Vue({
  router,//实例化路由
  el: '#app',
  render: h => h(App)
})
