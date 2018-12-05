<template>
  <div>
    <div class="invite-btn">
      <button class="zan-btn zan-btn--primary">邀请新成员</button>
    </div>
    <div class="zan-panel">
      <div class="zan-cell">
        <div class="zan-cell__bd">
          薛利
          <capsule v-bind="{leftText:'', rightText:'管理员', type:'danger'}" ></capsule>
          <capsule v-bind="{leftText:'', rightText:'资源录入文员', color:'#f85'}" ></capsule>
        </div>
        <div class="zan-cell__ft">更改权限</div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__bd">
          代强
          <capsule v-bind="{leftText:'', rightText:'资源录入文员', color:'#f85'}" ></capsule>
        </div>
        <div class="zan-cell__ft">更改权限</div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__bd">
          王玲
          <capsule v-bind="{leftText:'', rightText:'管理员', type:'danger'}" ></capsule>
        </div>
        <div class="zan-cell__ft">更改权限</div>
      </div>
    </div>
    
  </div>
</template>

<script>
import { formatTime } from '@/utils/index'
import card from '@/components/card'
import capsule from '@/components/zan/capsule'

export default {
  components: {
    card,
    capsule
  },

  data () {
    return {
      data: {
          leftText: '1折',
          rightText: '限购一份',
          color: '#38f',
          onclick: null
        }
    }
  },

  created () {
    const logs = (wx.getStorageSync('logs') || [])
    this.logs = logs.map(log => formatTime(new Date(log)))
  }
}
</script>

<style lang=scss>
  page{
    background-color: #fff;
  }
  .invite-btn{
    margin: 15px 8px;
  }
</style>
