<template>
  <div class="container" @click="clickHandle('test click', $event)">
    <!-- <Unregistered></Unregistered> -->
    <!-- myMessage and Register -->
    <div v-if="myMessages" class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigeteClick('myMessage')">
        <div class="zan-cell__icon zan-icon zan-icon-chat" style="color:#666;"></div>
        <div class="zan-cell__bd">我的消息</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <!-- 不同角色进来不同功能 -->
    <div class="zan-panel">
      <div v-if="register" class="zan-cell zan-cell--access" @click="navigeteClick('register')">
        <div class="zan-cell__icon zan-icon zan-icon-setting" style="color:#666;"></div>
        <div class="zan-cell__bd">注册申请</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div v-if="userManagement" class="zan-cell zan-cell--access" @click="navigeteClick('myTeam')">
        <div class="zan-cell__icon zan-icon zan-icon-shop" style="color:#666;"></div>
        <div class="zan-cell__bd">用户管理</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div v-if="enpowerAdmins" class="zan-cell zan-cell--access" @click="navigeteClick('auditAdmin')">
        <div class="zan-cell__icon zan-icon zan-icon-add-o" style="color:#666;"></div>
        <div class="zan-cell__bd">管理员授权</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div v-if="powerForInputRes" class="zan-cell zan-cell--access" @click="navigeteClick('auditWriter')">
        <div class="zan-cell__icon zan-icon zan-icon-add-o" style="color:#666;"></div>
        <div class="zan-cell__bd">权限管理（资源录入）</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div v-if="powerForAuditRes" class="zan-cell zan-cell--access" @click="navigeteClick('auditChecker')">
        <div class="zan-cell__icon zan-icon zan-icon-add-o" style="color:#666;"></div>
        <div class="zan-cell__bd">权限管理（资源审核）</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div v-if="auditResourse" class="zan-cell zan-cell--access" @click="navigeteClick('auditResourse')">
        <div class="zan-cell__icon zan-icon zan-icon-edit" style="color:#666;"></div>
        <div class="zan-cell__bd">审核资源</div>
        <div class="zan-cell__ft"></div>
      </div>
      <!-- <div v-if="inputResourse" class="zan-cell zan-cell--access" @click="navigeteClick('auditResourse')">
        <div class="zan-cell__icon zan-icon zan-icon-edit" style="color:#666;"></div>
        <div class="zan-cell__bd">录入资源</div>
        <div class="zan-cell__ft"></div>
      </div> -->
      <div v-if="logPage" class="zan-cell zan-cell--access" @click="navigeteClick('log')">
        <div class="zan-cell__icon zan-icon zan-icon-records" style="color:#666;"></div>
        <div class="zan-cell__bd">查看日志</div>
        <div class="zan-cell__ft"></div>
      </div>
      <!-- <div v-if="logPage" class="zan-cell zan-cell--access" @click="navigateToAuditRegist">
        <div class="zan-cell__icon zan-icon zan-icon-add-o" style="color:#666;"></div>
        <div class="zan-cell__bd">新成员注册审核</div>
        <div class="zan-cell__ft"></div>
      </div> -->
    </div>
    <!-- personalPage -->
    <div v-if="personalPage" class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigeteClick('personal')">
        <div class="zan-cell__icon zan-icon zan-icon-contact" style="color:#666;"></div>
        <div class="zan-cell__bd">个人信息</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <!-- about -->
    <div v-if="about" class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigeteClick('about')">
        <div class="zan-cell__icon zan-icon zan-icon-setting" style="color:#666;"></div>
        <div class="zan-cell__bd">关于天天资源</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
  </div>
</template>

<script>
// import card from '@/components/card'
// import Unregistered from '@/components/my/unregistered'
export default {
  data () {
    return {
      motto: 'Hello World',
      userInfo: {}
    }
  },
  methods: {
    navigeteClick (pageKey) {
      // 判断用户审核状态
      if (this.userAuditStatusState === 2) { // 审核中
        // 点击我的消息，个人信息注册申请时弹出个人信息正在审核中
        if (['myMessage', 'register'].indexOf(pageKey) > -1) {
          wx.showModal({
            content: '等待管理员审核中...',
            showCancel: false
          });
          return;
        }
      }
      // 根据用户点击项，导航到不同页面
      switch (pageKey) {
        case 'myMessage':
          wx.navigateTo({
            url: "./my-message/main"
          })
          break;
        case 'register':
          // 由于微信用户要与PC用户互通，这里注册需要得到用户授权获取高级信息
          this.$tool.checkUserInfoAuth().then(() => {
            // 用户未授权，进到授权页
            console.log('已授权');
            wx.navigateTo({
              url: "./register/main"
            })
          }, () => {
            console.log('未授权');
            this.$tool.goToAuthPage();
          });
          break;
        case 'auditAdmin':
          wx.navigateTo({
            url: "./audit-role/main?rolekey=admin"
          })
          break;
        case 'auditChecker':
          wx.navigateTo({
            url: "./audit-role/main?rolekey=checker"
          })
          break;
        case 'auditWriter':
          wx.navigateTo({
            url: "./audit-role/main?rolekey=writer"
          })
          break;
        case 'auditResourse':
          wx.navigateTo({
            url: "./audit-resource/main"
          })
          break;
        case 'myTeam':
          wx.navigateTo({
            url: "./my-team/main"
          })
          break;
        case 'log':
          wx.navigateTo({
            url: "./logs/main"
          })
          break;
        case 'personal':
          wx.navigateTo({
            url: "./personal/main"
          })
          break;
        case 'about':
          wx.navigateTo({
            url: "./about/main"
          })
          break;
      }
    },
    navigateToAuditRegist () {
      wx.navigateTo({
        url: "./auditRegistration/main"
      })
    },
    getUserInfo () {
      // // 调用登录接口
      // wx.login({
      //   success: () => {
      //     wx.getUserInfo({
      //       success: (res) => {
      //         this.userInfo = res.userInfo
      //       }
      //     })
      //   }
      // })
    },
    clickHandle (msg, ev) {
      console.log('clickHandle:', msg, ev)
    },
    ensureBtnClick () {
      console.log(1111);
    }
  },

  mounted () {
    console.log(this.$store.state);
    // 调用应用实例的方法获取全局数据
    // this.getUserInfo()
  },
  computed: {
    userAuditStatusState () {
      return this.$store.state.userAuditStatus;
    },
    /**
     * 计算得到的用户权限树，每个权限的计算值
     */
    myMessages () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.myMessages;
    },
    register () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.register;
    },
    userManagement () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.userManagement;
    },
    enpowerAdmins () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.enpowerAdmins;
    },
    logPage () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.logPage;
    },
    powerForInputRes () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.powerForInputRes;
    },
    powerForAuditRes () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.powerForAuditRes;
    },
    auditResourse () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.auditResourse;
    },
    /* inputResourse () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.inputResourse;
    }, */
    personalPage () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.personalPage;
    },
    about () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.about;
    },
  }
}
</script>

<style lang=scss>
  .container{
    padding: 0;
    background-color: #eee;
    .zan-panel{
      width: 100%;
    }
  }
</style>
