import Vue from 'vue'
import App from './index'

const app = new Vue(App)
app.$mount()

export default {
    // 这个字段走 页面配置.json
    config: {
        window: {
            navigationBarBackgroundColor: '#40586d',
            navigationBarTextStyle: 'white',
            navigationBarTitleText: '我的',
            backgroundColor: '#f8f9fb',
            backgroundTextStyle: 'dark',
            enablePullDownRefresh: false
        }
    }
}