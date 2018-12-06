<template>
  <div>
    <div class="zan-panel">
      <div class="zan-cell">
        <div class="zan-cell__bd">姓名</div>
        <div class="zan-cell__ft">Manfray</div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__bd">权限</div>
        <div class="zan-cell__ft">超级管理员</div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__bd">性别</div>
        <div class="zan-cell__ft">男</div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__bd">电话</div>
        <div class="zan-cell__ft">18126450043</div>
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
  
</style>
