<template>
  <div>
    <div class="clearfix pt-10 pb-10 pr-pl-15">
      <div class="fl">选择模版：</div>
      <div class="fr">
        <picker @change="tPickerChange" :value="tIndex" :range="tArray" range-key="name">
          <view class="picker">
            <span class="" v-if="tIndex === -1">请选择</span>
            <span v-else>{{tArray[tIndex].name}}</span>
          </view>
        </picker>
      </div>
    </div>
    <span class="function-btn for-select-template" @click="creatShareBitmap()">生成二维码</span>
    <span class="function-btn for-create-page" @click="PreviewSharePage()">预览分享页面</span>
  </div>
</template>

<script>

export default {
  name: 'resTemplate',
  data () {
    return {
      tIndex: -1,
      tArray: [
        {
          tId: 'io394riw3brw8h837rhw3briwu1',
          name: '模版一'
        },
        {
          tId: 'io394riw3brw8h837rhw3briwu2',
          name: '模版二'
        },
        {
          tId: 'io394riw3brw8h837rhw3briwu3',
          name: '模版三'
        },
        {
          tId: 'io394riw3brw8h837rhw3briwu4',
          name: '模版四'
        }
      ]
    }
  },
  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.rsTypeName = paramsObj.rsTypeName;
    this.shRsList = this.$store.state.myShareBag;
  },
  methods: {
    tPickerChange (e) {
      this.tIndex = e.target.value;
    },
    creatShareBitmap(){
      var tUrl = "https://www.hwyst.net/ttzy/pages/share/template/template0.jsp";
      wx.navigateTo({url: "/pages/share/erweima/main?shareUrl=" + tUrl + "&rsTypeName=" + this.rsTypeName}) ;
    },
    PreviewSharePage () {
      wx.navigateTo({
        url: "/pages/share/sharePage/main"
      });
    },
  }
}
</script>

<style lang=scss>
  
</style>
