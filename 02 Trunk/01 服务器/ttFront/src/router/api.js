import Vue from "vue";
import axios from "axios";


const HOST = "ws://39.104.81.103:8561";

axios.defaults.baseURL = HOST;  // 设置请求地址，后面的接口都将基于这个基本请求地址

// 设置请求头
axios.defaults.headers.post["Content-Type"] = "application/json";

Vue.prototype.$axios = (data) => {

};