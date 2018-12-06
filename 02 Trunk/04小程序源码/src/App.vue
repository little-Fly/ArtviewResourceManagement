<script>
/*
  主页启动逻辑：
  1. 拿code换取：
    1. 自定义登录态，储存到公共状态管理，后面接口调用都要带上；
    2. 用户注册信息：
      1. 已注册用户
        1. 审核中/被拒绝：页面一进来就toast提示
        2. 审核已通过：拿到用户的角色信息，配置全局的权限对象
      2. 用户未注册：
  2. 
*/
export default {
  data () {
    return {
      // 用户审核状态
      userAuditStatus: 1
    }
  },
  created () {
    // 登录
    wx.login({
      success: res => {
        this.$http({
          url: '/wx/login.do',
          method: 'post',
          data: {
            code: res.code
          },
          success: res => {
            //公共状态管理自定义登录态
            this.$store.commit('updateCustomLoginStatus', res.third_session);
            //拿到识别码后，获取用户审核状态值 1:未注册 2：审核中 3:被拒绝 4: 已通过
            this.updateUserRole(res.userStatus);
            // 如果已注册
            if (this.userAuditStatus === 4) {
              // 本地储存用户基础信息
              this.$store.commit('updateUserInfo', res.userInfo);
              // 拿用户的用户角色列表
              // this.updateRoleList(res.userInfo.roles);
            } else {
              this.$store.commit('updateRoleList', ['unregister']);
            }
          }
        });
      }
    })
    //APP启动第一步：判断app是否授权,如果授权，再根据（未注册，审核中，被拒绝）和审核通过进入不同的页面
    /* wx.getSetting({
      success: res => {
        console.log(res);
        if (!!res.authSetting['scope.userInfo']) {// 未授权
          wx.redirectTo({
            url: '../authorization/authorization',//跳转到授权页
          })
        } else {//已经授权，根据用户状态，判断进入哪个页面
          
          // 获取用户信息, 特别是用户状态
          let getUsersInfo = () => {
            wx.getUserInfo({
              success: res => {
                // 可以将 res 发送给后台解码出 unionId
                app.globalData.userInfo = res.userInfo
                //向后台查询用户开发者系统信息，以及此时状态，返回审核中，被拒绝 或 审核通过
                wx.request({
                  url: 'https://www.grosup.com/practice/user/info.do',
                  // method: 'post',
                  header: {
                    'content-type': 'application/x-www-form-urlencoded',
                    'third_session': app.globalData.userId
                  },
                  success: res => {
                    if (res.data.msg == "当前用户未注册") {
                      //未通过，显示注册页(未注册，审核中，被拒绝)
                      wx.redirectTo({
                        url: '../register/register'
                      })
                    }else{
                      let saveFilterdData = (data) => {
                        // this.globalData.userInfoInOurSystem.personInfo = res.data.data;
                        //根据用户状态进行页面的不同显示，只有当用户通过审核时候才会显示个人信息页，否则显示注册页
                        let userStatus//找到用户状态
                        let userType//格式化用户角色
                        let userRole//储存用户角色，用于审核通过状态展示对应页面
                        let selectRole //中文格式角色，用于审核时同步当前角色
                        userStatus = data.status
                        userType = data.userType
                        switch (userType) {
                          case 0:
                            userRole = "student",
                              selectRole = "学生"
                            break;
                          case 1:
                            userRole = "teacher",
                              selectRole = "老师"
                            break;
                          default:
                            userRole = "admin",
                              selectRole = "管理员"
                        }
                        data.formatUserRole = userRole
                        data.formatSelectRole = selectRole

                        return data
                      }
                      //用户个人信息格式化后储存
                      app.globalData.userInfoInOurSystem.personInfo = saveFilterdData(res.data.data)
                      if (app.globalData.userInfoInOurSystem.personInfo.status == 1) {//通过审核
                        //导航到main页
                        // wx.redirectTo({
                        //   url: '../main/main',
                        // })
                        //本页面为启动页，因此当小程序启动，判断是否展示授权或注册页时候，此页已经显示，故需要把页面先隐藏，当用户审核通过时候再展示
                        let userInfo = app.globalData.userInfoInOurSystem.personInfo
                        if (userInfo.status == 1) {
                          let navigatorArray2Value = 'navigatorArray[1]'
                          this.setData({
                            ifshow: true,
                            [navigatorArray2Value]: userInfo.formatUserRole
                            // navigatorArray: ["my", userInfo.formatUserRole]
                          })
                        }
                      } else {
                        //未通过，显示注册页(未注册，审核中，被拒绝)
                        wx.redirectTo({
                          url: '../register/register'
                        })
                      }
                    }
                    
                  },
                  fail: () => {
                    wx.showToast({
                      title: '服务器请求异常，请检查网络或联系管理员！',
                      icon: 'none'
                    })
                  }
                })
              }
            })
          }
        }
      }
    }) */
  },
  methods: {
    updateUserRole (userAuditStatus) {
      // 存储格式化为前端自定义用户审核状态
      switch (userAuditStatus) {
        case 'checked':
          userAuditStatus = 4;
          break;
        case 'unchecked':
          userAuditStatus = 3;
          break;
        case 'unchecked':
          userAuditStatus = 2;
          break;
        case 'unchecked':
          userAuditStatus = 1;
          break;
      }
      // 同步公共状态管理
      this.$store.commit('updateUserAuditStatus', userAuditStatus);
      this.userAuditStatus = userAuditStatus;
    },
    updateRoleList (roles) {
      // 遍历用户角色列表，本地储存
      let roleList = [];
      let roleListData = roles;
      for (let i = 0, len = roleListData.length; i < len; i ++) {
        // 过滤转化后台返回角色信息
        switch (roleList[i].roleType) {
          case 0:
            roleList.push('super');
            break;
          case 1:
            roleList.push('admin');
            break;
          case 2:
            roleList.push('auditor');
            break;
          case 3:
            roleList.push('inputer');
            break;
        }
      }
      // 同步到公共状态管理
      this.$store.commit('updateRoleList', roleList);
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {},
}
</script>

<style lang=scss>
@import './assets/zanui-css/zanui.wxss';
page{
  min-height: 100%;
  background-color: #eee;
}
.align-right{
  text-align: right;
}
.align-center{
  text-align: center;
}
.full-width {
  width: 100%
}
/* .container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: 200rpx 0;
  box-sizing: border-box;
} */
</style>
