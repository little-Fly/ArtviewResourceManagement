<template>
  <div>
    <ul class="container log-list">
      <li v-for="(log, index) in logs" :key="index" class="log-item">
        {{log.remark}}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data () {
    return {
      logs: []
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
          pageSize: 30,
          pageNumber: 1
        },
        success: res => {
          console.log(res);
          this.logs = res.data;
        },
        fail:() => {
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
</style>
