<template>
  <div>
    <ul class="container log-list">
      <li v-for="(log, index) in logs" :key="index" class="log-item">
        {{log.remark}}
      </li>
    </ul>
    <div class="more-logs">
      <span class="get-more-logs" @click="getLogList()">查看更多日志</span>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      logs: [],
      pageSize: 30,
      pageNumber: 1
    }
  },
  mounted () {
    this.getLogList();
  },
  methods: {
    /**
     * 获取日志列表
     */
    getLogList () {
      this.$http({
        url: '/wx/log/query.do',
        method: 'get',
        data: {
          pageSize: this.pageSize,
          pageNumber: this.pageNumber
        },
        success: res => {
          //console.log(res);
          if(res.data.length > 0){
            this.pageNumber += 1;
            for(var i=0; i<res.data.length; i++){
              this.logs.push(res.data[i]);
            }
            //this.onLoad();
          }
          else {
            wx.showToast({
              title: "后面没有日志了！",
              mask: true
            });
          }
        }
      });
    }
  }
}
</script>

<style>
page {
  background-color: #fff;
}
.log-list {
  display: flex;
  flex-direction: column;
  padding: 40rpx;
}
.log-item {
  width: 335px;
  margin-top: 10px;
  font-size: 15px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.more-logs {
  height: 32px;
  width: 100%;
  font-size: 18px;
  text-align: center;
  line-height: 32px;
}
.get-more-logs {
  padding: 4px 7px;
  color: white;
  background-color: #E64340;
  border-radius: 4px;
}
</style>
