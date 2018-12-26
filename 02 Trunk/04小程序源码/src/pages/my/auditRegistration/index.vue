<template>
  <div>
    <div class="zan-panel">
      <div class="zan-row">
        <div class="zan-col zan-col-7 title align-right">
          <strong>Manfray：</strong>
        </div>
        <div class="zan-col zan-col-17 title">
          <strong>申请加入天天意境</strong>
        </div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">联系方式：</div>
        <div class="zan-col zan-col-17">18126470043</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">性别：</div>
        <div class="zan-col zan-col-17">男</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">注册理由：</div>
        <div class="zan-col zan-col-17">就啊收快递放假啦是肯德基就啊了空间发空间刷空间卢卡斯简单科技会计法上课</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">微信号：</div>
        <div class="zan-col zan-col-17">Mistergong</div>
      </div>
      <div class="align-center btn-box">
        <button class="zan-btn zan-btn--small zan-btn--primary">审核通过</button>
        <button class="zan-btn zan-btn--small zan-btn--danger">不能通过</button>
      </div>
    </div>
    <div class="zan-panel">
      <div class="zan-row">
        <div class="zan-col zan-col-7 title align-right">
          <strong>Manfray：</strong>
        </div>
        <div class="zan-col zan-col-17 title">
          <strong>申请加入天天意境</strong>
        </div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">联系方式：</div>
        <div class="zan-col zan-col-17">18126470043</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">性别：</div>
        <div class="zan-col zan-col-17">男</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">注册理由：</div>
        <div class="zan-col zan-col-17">就啊收快递放假啦是肯德基就啊了空间发空间刷空间卢卡斯简单科技会计法上课</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">微信号：</div>
        <div class="zan-col zan-col-17">Mistergong</div>
      </div>
      <div class="align-center btn-box">
        <button class="zan-btn zan-btn--small zan-btn--primary">审核通过</button>
        <button class="zan-btn zan-btn--small zan-btn--danger">不能通过</button>
      </div>
    </div>
    <div class="zan-panel">
      <div class="zan-row">
        <div class="zan-col zan-col-7 title align-right">
          <strong>Manfray：</strong>
        </div>
        <div class="zan-col zan-col-17 title">
          <strong>申请加入天天意境</strong>
        </div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">联系方式：</div>
        <div class="zan-col zan-col-17">18126470043</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">性别：</div>
        <div class="zan-col zan-col-17">男</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">注册理由：</div>
        <div class="zan-col zan-col-17">就啊收快递放假啦是肯德基就啊了空间发空间刷空间卢卡斯简单科技会计法上课</div>
      </div>
      <div class="zan-row">
        <div class="zan-col zan-col-7 align-right">微信号：</div>
        <div class="zan-col zan-col-17">Mistergong</div>
      </div>
      <div class="align-center btn-box">
        <button class="zan-btn zan-btn--small zan-btn--primary">审核通过</button>
        <button class="zan-btn zan-btn--small zan-btn--danger">不能通过</button>
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
  .zan-panel{
    padding: 20px 15px;
  }
  .zan-col {
    line-height: 20px;
    font-size: 12px;
  }
  .btn-box{
    margin-top: 20px;
    .zan-btn{
      margin: 0 15px;
    }
  }
  .title{
    font-size: 30rpx;
    font-weight: bold;
  }
</style>
