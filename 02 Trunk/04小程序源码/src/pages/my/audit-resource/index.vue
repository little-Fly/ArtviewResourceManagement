<template>
  <div>
    <div class="zan-panel zan-cell--access">
      <div class="zan-cell">
        <div class="zan-cell__icon zan-icon zan-icon-description" style="color:#4b0;"></div>
        <div class="zan-cell__bd">新增资源审核</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <div class="zan-panel zan-cell--access">
      <div class="zan-cell">
        <div class="zan-cell__icon zan-icon zan-icon-description" style="color:#f85;"></div>
        <div class="zan-cell__bd">修改资源审核</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <div class="zan-panel zan-cell--access">
      <div class="zan-cell">
        <div class="zan-cell__icon zan-icon zan-icon-description" style="color:#f24544;"></div>
        <div class="zan-cell__bd">删除资源审核</div>
        <div class="zan-cell__ft"></div>
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
