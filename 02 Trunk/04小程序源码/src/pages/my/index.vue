<template>
  <div class="container" @click="clickHandle('test click', $event)">
    <!-- <Unregistered></Unregistered> -->
    <!-- myMessage and Register -->
    <div class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigeteClick('myMessage')">
        <div class="zan-cell__icon zan-icon zan-icon-chat" style="color:#666;"></div>
        <div class="zan-cell__bd">我的消息</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <!-- 不同角色进来不同功能 -->
    <div class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigeteClick('register')">
        <div class="zan-cell__icon zan-icon zan-icon-setting" style="color:#666;"></div>
        <div class="zan-cell__bd">注册申请</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div class="zan-cell zan-cell--access" @click="navigeteClick('auditResourse')">
        <div class="zan-cell__icon zan-icon zan-icon-edit" style="color:#666;"></div>
        <div class="zan-cell__bd">资源审核</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div class="zan-cell zan-cell--access" @click="navigateToMyTeam">
        <div class="zan-cell__icon zan-icon zan-icon-shop" style="color:#666;"></div>
        <div class="zan-cell__bd">我的团队</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div class="zan-cell zan-cell--access" @click="navigateToLog">
        <div class="zan-cell__icon zan-icon zan-icon-records" style="color:#666;"></div>
        <div class="zan-cell__bd">查看日志</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div class="zan-cell zan-cell--access" @click="navigateToAuditRegist">
        <div class="zan-cell__icon zan-icon zan-icon-add-o" style="color:#666;"></div>
        <div class="zan-cell__bd">新成员注册审核</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <!-- personalPage -->
    <div class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigateToPersonal">
        <div class="zan-cell__icon zan-icon zan-icon-contact" style="color:#666;"></div>
        <div class="zan-cell__bd">个人信息</div>
        <div class="zan-cell__ft"></div>
      </div>
      <div class="zan-cell zan-cell--access" @click="navigateToRegister">
        <div class="zan-cell__icon zan-icon zan-icon-contact" style="color:#666;"></div>
        <div class="zan-cell__bd">注册申请</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <!-- about -->
    <div class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigateToAbout">
        <div class="zan-cell__icon zan-icon zan-icon-setting" style="color:#666;"></div>
        <div class="zan-cell__bd">关于天天资源</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <zanDialog @ensureBtnClick="ensureBtnClick" :zanDialogObj="zanDialogObj"/>
  </div>
</template>

<script>
// import card from '@/components/card'
// import Unregistered from '@/components/my/unregistered'
// 不能命名为dialog会与微信的产生冲突
import zanDialog from '@/components/zan/dialog'
export default {
  data () {
    return {
      motto: 'Hello World',
      userInfo: {},
      zanDialogObj: {
        'name': '',
        'show': true,
        'title': '',
        'content': '您的信息正在审核中，请耐心等待！',
        'buttons': [{
          text: '确定',
          color: 'green',
          type: 'confirm',
          fnName: 'ensureBtnClick'
        }]
      }
    }
  },

  components: {
    // Unregistered,
    zanDialog
  },

  methods: {
    navigeteClick (pageKey) {
      switch (pageKey) {
        case 'myMessage':
          wx.navigateTo({
            url: "../my-message/main"
          })
          break;
        case 'register':
          // 由于微信用户要与PC用户互通，这里注册需要得到用户授权获取高级信息
          debugger;
          if (!this.$tool.checkUserInfoAuth()) { // 用户未授权，进到授权页
            this.$tool.goToAuthPage();
          } else {
            wx.navigateTo({
              url: "../register/main"
            })
          }
          break;
        case 'auditResourse':
          wx.navigateTo({
            url: "../audit-resource/main"
          })
          break;
      }
    },
    navigateToMessage () {
      wx.navigateTo({
        url: "../my-message/main"
      })
    },
    navigateToAbout () {
      wx.navigateTo({
        url: "../about/main"
      })
    },
    navigateToPersonal () {
      wx.navigateTo({
        url: "../personal/main"
      })
    },
    navigateToRegister () {
      wx.navigateTo({
        url: "../personal/main"
      })
    },
    navigateToAuditRegist () {
      wx.navigateTo({
        url: "../auditRegistration/main"
      })
    },
    navigateToMyTeam () {
      console.log(1111)
      wx.navigateTo({
        url: "../my-team/main"
      })
    },
    navigateToLog () {
      const url = '../logs/main'
      wx.navigateTo({ url })
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

  created () {
    // 调用应用实例的方法获取全局数据
    // this.getUserInfo()
  },
  computed: {
    // 根据用户角色状态分流
    userRoleState () {
      return this.$store.state.userRole;
    }
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
