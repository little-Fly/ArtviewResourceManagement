<template>
  <div>
    <ul class="container msg-list">
      <li v-for="(msg, index) in msgs" :key="index" class="msg-item">
        {{msg.report}}<span class="space"></span>{{msg.date}}
      </li>
    </ul>
    <div class="more-msgs">
      <span class="get-more-msgs" @click="getMsgList()">查看更多</span>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      msgs: [],
      pageSize: 20,
      pageNumber: 1
    }
  },
  mounted () {
    this.getMsgList();
  },

  //wx/log/queryReport.do
  methods: {
    /**
     * 获取日志列表
     */
    getMsgList () {
      this.$http({
        url: '/wx/log/queryReport.do',
        method: 'get',
        data: {
          uid: this.$store.state.userInfo.uid,
          pageSize: this.pageSize,
          pageNumber: this.pageNumber
        },
        success: res => {
          //console.log(res);
          if(res.data.length > 0){
            this.pageNumber += 1;
            for(var i=0; i<res.data.length; i++){
              this.msgs.push(res.data[i]);
            }
          }
          else {
            wx.showToast({
              title: "后面没有了！",
              mask: true
            });
          }
        }
      });
    }
  }
}
</script>

<style lang=scss>
page {
  background-color: #fff;
}
.msg-list {
  display: flex;
  flex-direction: column;
  padding: 40rpx;
}
.msg-item {
  width: 335px;
  margin-top: 10px;
  font-size: 15px;
  border-bottom: 1px solid #eee;
}
.more-msgs {
  height: 32px;
  width: 100%;
  font-size: 18px;
  text-align: center;
  line-height: 32px;
}
.get-more-msgs {
  padding: 4px 7px;
  color: white;
  background-color: #E64340;
  border-radius: 4px;
}
.space{
  padding: 8px 8px;
}
</style>
