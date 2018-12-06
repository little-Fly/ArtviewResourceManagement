<template>
  <div>
    <div class="zan-panel">
      <div class="zan-cell">
        <div class="zan-cell__icon zan-icon zan-icon-pending-evaluate" style="color:#f85;"></div>
        <div class="zan-cell__bd">
          <div class="zan-cell__text">系统通知</div>
          <div class="zan-cell__desc">您的注册申请被超级审核员通过，欢迎加入天天意境！</div>
          <div class="zan-cell__desc">2018/09/09</div>
        </div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__icon zan-icon zan-icon-pending-evaluate" style="color:#f85;"></div>
        <div class="zan-cell__bd">
          <div class="zan-cell__text">系统通知</div>
          <div class="zan-cell__desc">您的注册申请被超级审核员拒绝！</div>
          <div class="zan-cell__desc">拒绝理由：还没有想好</div>
          <div class="zan-cell__desc">2018/09/09</div>
        </div>
      </div>
      
    </div>
  </div>
</template>

<script>
import { formatTime } from '@/utils/index'
import card from '@/components/card'

export default {
  components: {
    card
  },

  data () {
    return {
      logs: []
    }
  },

  created () {
    const logs = (wx.getStorageSync('logs') || [])
    this.logs = logs.map(log => formatTime(new Date(log)))
  }
}
</script>

<style lang=scss>
  .zan-icon {
    margin-right:15px;
  }

</style>
