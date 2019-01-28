import Vue from 'vue'
import { HostUrl } from '@/config/api';
/**
 * 小程序请求方法封装
 * @param {*} queryObj 请求对象
 */
function $http (queryObj) {
	wx.showLoading(this.$config.LOADING_PARAM_OBJ);
	// 拿自定义登录态
	let customSession = this.$store.state.third_session;
	let customSessionObj = customSession ? {third_session: customSession} : {};
	wx.request({
		url: HostUrl + queryObj.url,//必传参数
		data: queryObj.data || "",//默认为空
		header: Object.assign({}, {
			'content-type': 'application/x-www-form-urlencoded'
		}, customSessionObj, queryObj.header),
		method: queryObj.method || "GET",// 默认为get
		// success: queryObj.success || function () {},// 默认为空函数
		success (res) {
                                  var resData = '';
                                  if(res.data.length > 0)resData = res.data[0];
                                  else resData = res.data;
                                  if(typeof(resData.code) != "undefined"){
                                    if (resData.code === 1) { // 请求正常
                                      queryObj.success && queryObj.success(resData);// 默认为空函数
                                    } else { // 415 405 401
                                      console.error(`后台接口返回code不为1，错误信息${resData.msg}`);
                                      wx.showModal({title: '错误', content: '${resData.msg}', showCancel: false});
                                    }
                                  }
                                  else if(typeof(resData.state) != "undefined"){
                                    if(typeof(resData.data) != "undefined")resData.data = JSON.parse(resData.data);
                                    if (resData.state === 'successful') { // 请求正常
                                      queryObj.success && queryObj.success(resData);// 默认为空函数
                                    } else { // 415 405 401
                                      console.error(`后台接口返回state不为successful，错误信息${resData.msg}`);
                                      wx.showModal({title: '错误', content: '${resData.msg}', showCancel: false});
                                    }
                                  }
                                 else{
                                   console.error(`后台接口返回数据格式错误`);
                                   wx.showModal({title: '错误', content: '服务器返回数据格式错误', showCancel: false});
                                 }
                                 wx.hideLoading();
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