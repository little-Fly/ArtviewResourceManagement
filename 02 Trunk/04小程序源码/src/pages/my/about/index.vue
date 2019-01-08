<template>
  <div>
    <div class="zan-panel">
      <div class="zan-cell">
        <div class="zan-cell__bd">天天资源版本号</div>
        <div class="zan-cell__ft">1.0.0</div>
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
