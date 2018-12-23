import Vue from 'vue'
import App from './App'
import { $http, $tool } from "@/utils"
// import Vant from 'vant'
// import 'vant/lib/vant-css/index.css'

// //引用vantUI库
// Vue.use(Vant)


//引入 store
import store from './store/index'
//引入 config
import * as config from './config/index'
// 把 store 挂载到全局
Vue.prototype.$store = store;

// 把 config 挂载到全局
Vue.prototype.$config = config;

Vue.prototype.$http = $http;
Vue.prototype.$tool = $tool;

Vue.config.productionTip = false
App.mpType = 'app'

const app = new Vue(App)
app.$mount()

