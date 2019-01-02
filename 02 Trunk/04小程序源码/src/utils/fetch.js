import Vue from 'vue'
import { BASE_URL } from '@/config/api';
/**
 * 小程序请求方法封装
 * @param {*} queryObj 请求对象
 */
function $http (queryObj) {
	// queryObj.url.indexOf('rs') > -1 && console.log(queryObj.url);
	wx.showLoading(this.$config.LOADING_PARAM_OBJ);
	// 拿自定义登录态
	let customSession = this.$store.state.third_session;
	let customSessionObj = customSession ? {third_session: customSession} : {};
	wx.request({
		url: BASE_URL + queryObj.url,//必传参数
		data: queryObj.data || "",//默认为空
		header: Object.assign({}, {
			'content-type': 'application/x-www-form-urlencoded'
		}, customSessionObj, queryObj.header),
		method: queryObj.method || "GET",// 默认为get
		// success: queryObj.success || function () {},// 默认为空函数
		success (res) {
			if (queryObj.url.indexOf('rs') > -1) {
				res = res.data[0]; // 数据结构返回内容为data下
				res.data = JSON.parse(res.data);
				if (res.state === 'successful') { // 请求正常
					queryObj.success && queryObj.success(res);// 默认为空函数
					wx.hideLoading();
				} else { // 415 405 401
					console.error(`后台接口返回state不为successful，错误信息${res.msg}`);
					wx.hideLoading();
				}
			} else {
				res = res.data; // 数据结构返回内容为data下
				if (res.code === 1) { // 请求正常
					queryObj.success && queryObj.success(res);// 默认为空函数
					wx.hideLoading();
				} else { // 415 405 401
					console.error(`后台接口返回code不为1，错误信息${res.msg}`);
					wx.hideLoading();
				}
			}
			
		},
		fail () {
			wx.hideLoading();
			// 请求失败，貌似一直无法触发
			if (res.statusCode !== 200) {
				// 说明请求失败
				console.error(res);
				// 如果有自定义错误操作，就在此执行
				queryObj.fail && queryObj.fail();
			}
		},
		complete (res) {
			console.log(res);
			if (res.statusCode !== 200) {
				// 说明请求失败
				console.error(res);
				// 如果有自定义错误操作，就在此执行
				queryObj.fail && queryObj.fail();
			}
		}
	})
}
export { $http }