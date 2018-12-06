<template>
  <div class="counter-warp">
    

    <!-- <a href="/pages/index/main" class="home">去往首页</a> -->
  </div>
</template>

<script>

export default {
  computed: {
    count () {
      return this.$store.state.count
    }
  },
  methods: {
    increment () {
      this.$store.commit('increment')
    },
    decrement () {
      this.$store.commit('decrement')
    }
  },
  created () {
    // 请求资源列表
    this.getResourseListData();
  },
  methods: {
    getResourseListData () {
      /* this.$http({
        url: '/wx/login.do',
        method: 'post',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
          code: res.code
        },
        success: res => {
          console.log(res);
          //本地存储识别码
          app.globalData.userId = res.data.third_session
          //拿到识别码后，获取用户信息，返回用户审核状态值
          getUsersInfo();
        }
      }); */
    }
  }
}
</script>

<style>
.counter-warp {
  text-align: center;
  margin-top: 100px;
}
.home {
  display: inline-block;
  margin: 100px auto;
  padding: 5px 10px;
  color: blue;
  border: 1px solid blue;
}
</style>
