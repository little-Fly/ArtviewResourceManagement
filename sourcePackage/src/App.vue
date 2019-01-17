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
      userAuditStatus: 1,
      // 用户角色列表
      userRoleList: []
    }
  },
  created () {
    wx.showLoading(this.$config.LOADING_PARAM_OBJ);
    // 登录
    wx.login({
      success: res => {
        wx.hideLoading();
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
            this.updateUserRole(res.status);
            // 如果已注册
            if (this.userAuditStatus === 4) {
              // 本地储存用户基础信息
              this.$store.commit('updateUserInfo', res.userInfo);
              // 拿用户的用户角色列表
              this.updateRoleList(res.userInfo.roles);
              // 更新用户权限树
              this.$store.commit('updateRoleAuthTree', this.userRoleList);
              this.$store.commit('updateUserRoleList', this.userRoleList);
              console.log(this.userRoleList, this.$store);
            } else { // 其他三种状态的页面结构一样
              // 同步公共状态管理
              this.$store.commit('updateUserAuditStatus', this.userAuditStatus);
              this.$store.commit('updateRoleAuthTree', ['unregister']); // 游客 == 未注册
            }
          },
          fail: () => {
            wx.showToast({title: '用户登录失败！', icon: 'none'});
          }
        });
      },
      fail: res => {
        wx.hideLoading();
        console.log(res);
        wx.showModal({title: '错误', content: res.errMsg, showCancel: false});
      }
    });
  },
  methods: {
    updateUserRole (status) {
      let userAuditStatus;
      // 存储格式化为前端自定义用户审核状态 1:未注册 2：审核中 3:被拒绝 4: 已通过
      switch (status) {
        case -1:
          userAuditStatus = 1;
          break;
        case 0:
          userAuditStatus = 2;
          break;
        case 1:
          userAuditStatus = 4;
          break;
        case 2:
          userAuditStatus = 3;
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
        roleList.push(roleListData[i].roleKey);
      }
      this.userRoleList = roleList;
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
page {
  min-height: 100%;
  background-color: #eee;
}
view {
  box-sizing: border-box;
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
.inline-block {
  display: inline-block;
}
.lr-mg-5 {
  margin-left: 5px;
  margin-right: 5px;
}
.lr-mg-10 {
  margin-left: 10px;
  margin-right: 10px;
}
.fr {
  float: right;
}
.fl {
  float: left;
}
.clearfix {
  &::before,&::after {
    content: '';
    display: block;
    clear: both;
  }
}
/* 行内元素两边对其 */
.keith {
  text-align: justify;
  &::after {
    display: inline-block;
    width: 100%;
    content: '';
  }
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
