// https://vuex.vuejs.org/zh-cn/intro.html
// make sure to call Vue.use(Vuex) if using a module system
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    // count: 0,
    third_session: '', // 后台返回自定义登录态
    userInfo: {}, // 保存后台返回的userInfo
    ifAutherized: false, // 是否授权：方便随时调起授权页
    userAuditStatus: 1, // 用户审核状态 1:未注册 2：审核中 3:被拒绝 4: 已通过
    roleList: [], // 用户角色列表 eg: ['super', 'admin']
    accessItem: { // 五种角色状态，在不同的页面拥有不同的权限
      unregister: {
        my: ['myMessages', 'personalPage', 'about', 'register']
      },
      super: {
        my: ['myMessages', 'personalPage', 'about', 'userManagement', 'enpowerAdmins']
      },
      admin: {
        my: ['myMessages', 'personalPage', 'about', 'userManagement', 'logPage', 'powerForInputRes', 'powerForAuditRes']
      },
      auditor: {
        my: ['myMessages', 'personalPage', 'about', 'auditResourse']
      },
      inputer: {
        my: ['myMessages', 'personalPage', 'about', 'inputResourse']
      }
    },
    accessTree: {
      my:{
        myMessages: false, // common
        register: false, // unregister
        userManagement: false, // super admin 
        enpowerAdmins: false, // 管理员授权 super
        logPage: false, // 查看日志 admin
        powerForInputRes: false, // 资源录入授权 admin
        powerForAuditRes: false, // 资源审核授权 admin
        auditResourse: false, // 资源审核 资源审核组成员
        inputResourse: false, // 资源录入 资源录入组成员
        personalPage: false, // common
        about: false, // common
      }
    }
  },
  mutations: {
    // increment: (state) => {
    //   const obj = state
    //   obj.count += 1
    // },
    // decrement: (state) => {
    //   const obj = state
    //   obj.count -= 1
    // },
    updateCustomLoginStatus (state, val) {
      state.third_session = val;
    },
    updateUserAuditStatus (state, val) {
      state.userAuditStatus = val;
    },
    updateRoleList (state, val) {
      state.roleList = val;
    },
    updateUserInfo (state, val) {
      state.userInfo = val
    }
  }
})

export default store
