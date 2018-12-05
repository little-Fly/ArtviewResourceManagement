<template>
  <div>
    <!-- <img src="" alt=""> -->
    <div>天天资源</div>
    <div>企业内部资源管理工具软件</div>
    <div>九天云工作室@制作</div>
    <!-- 需要使用 button 来授权登录 -->
    <button v-if="canIUse" :type="authStatus == 'refused' ? 'warn' : 'primary'" open-type="getUserInfo" @getuserinfo="getUserInfo">{{authStatus == 'refused' ? '点击重新授权' : '授权登录'}}</button>
  </div>
</template>

<script>

export default {
  components: {
  },
  data () {
    return {
      canIUse: wx.canIUse('button.open-type.getUserInfo'),
      // 用户授权状态 refused
      authStatus: ''
    }
  },
  created () {
  },
  methods: {
    getUserInfo (e) {
      // 根据返回是否有用户敏感信息判断用户是否拒绝授权
      console.log(e);
      if(e.target.encryptedData) { // 用户已授权
        // 返回进入到授权页的前一页
        wx.navigateBack();
      } else {
        this.authStatus = 'refused'
      }
    }
  }
}
</script>

<style lang=scss>
  
</style>
