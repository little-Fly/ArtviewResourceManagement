<template>
  <div class="father">
    <div class="operation-box">
      <span class="share-btn" @click="canvasToTempImage()">微信分享</span><span class="remark">(点击按钮，长按弹出图片，分享给微信好友)</span>
    </div>
    <div>
      <canvas canvas-id="sharecanvas" class="share-bitmap"></canvas>
    </div>
  </div>
</template>


<script>
import QRCode from '../../../utils/qrcode.js'
export default {
  data () {
    return {
      imagePath: '',
      maskHidden: false,
      canvasId: 'sharecanvas',
      shareUrl: '',
      rsTypeName:"",
      size:{
        ewmW: 216,
        ewmH: 216,
        ewmTop: 48,
        ewmLeft: 0,
      },
    }
  },

  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.shareUrl = paramsObj.shareUrl;
    this.rsTypeName = paramsObj.rsTypeName;
    this.createImg();
  },

  methods: {

    //获取临时缓存照片路径，存入data中
    canvasToTempImage(){
      var that = this;
      wx.canvasToTempFilePath({
        canvasId: this.canvasId,
        success: res => {
          var tempFilePath = res.tempFilePath;
          console.log(tempFilePath);
          this.imagePath = tempFilePath;
          wx.previewImage({
            current: this.imagePath, // 当前显示图片的http链接  
            urls: [this.imagePath] // 需要预览的图片http链接列表
          });
        },
        fail: res =>{
          console.log(res);
        }
      });
    },
    createImg(e) {
      //绘制二维码
      var text = [];
      text.push({
        text: "天天艺境文化传播有限公司",
        font: "14px 微软雅黑", 
        color: "#000", 
        top: 20, 
        left: 10, 
        fontSize: 14,
        align: 'center'
      });
      text.push({
        text: this.rsTypeName + "介绍",
        font: "14px 微软雅黑", 
        color: "#e56c6c", 
        top: 38, 
        left: 10, 
        fontSize: 14,
        align: 'center'
      });
      text.push({
        text: "手机微信扫一扫",
        font: "14px 微软雅黑", 
        color: "#000", 
        top: this.size.ewmTop+this.size.ewmH, 
        left: 56, 
        fontSize: 14,
        align: 'center'
      });
      QRCode.api.draw(this.shareUrl, this.canvasId, 216, 300, this.size.ewmW, this.size.ewmH, this.size.ewmLeft, this.size.ewmTop,text);
    },
    
  }
}
</script>

<style  lang="scss" rel="stylesheet/scss" scope>
  
  page{
   width:100%;
    background-color: #fff;
  }
  .father{
    width:100%;
    text-align: center;
  }
  .operation-box{
     margin-top: 24px;
     display: inline-block;
  }
  .share-btn{
    display: inline-block;
    vertical-align: middle;
    border-radius: 4px;
    font-size: 15px;
    height: 30px;
    padding: 0 64px;
    line-height: 30px;
    color: white;
    background-color: #f56c6c;
    margin-right: 0px;
  }
  .remark{
    display: inline-block;
    font-size: 12px;
  }
  .share-bitmap{
    width: 216px;
    height: 300px;
    border: 1px solid #000;
    background: #fff;
    margin: 32px auto;
  }
  
  .line-block{
    width: 100%;
    margin: 16px 0;
  }
  .line-fill{
    width: 100%;
    padding: 8px 0px;
  }
  .icon-group-box{
    padding-top: 8px;
    width: 20px;
    display: inline-block;
  }
  .rs-icon{
    width: 20px;
    height: 20px;
  }
  .content-item{
    width: 100%;
    display: flex;
    padding: 8px 0px;
    font-size: 15px;
  }
  .item-title{
    display: inline-block;
    width: 15%;
    text-align: right;
    word-break: break-all;
  }
  .item-value{
    display: inline-block;
    width: 85%;
    text-align: left;
    word-break: break-all;
    margin: 0 8px;
  }
  .resourcelist-wrap {
    width: 100%;
    margin-bottom: 12px;
  }
  .resourcelist-item-wrap{
    display: flex;
    width:90%;
    padding-top: 16px;
    border-top: 1px solid #f56c6c;
    margin-left: 20px;
  }
  .item-box{
    width:100%;
    padding-left: 8px;
  }
  .attr-type{
    padding: 8px 0px;
    font-size: 16px;
    color: #E64340;
  }
  .resource-image{
    width: 100%;
    height: 180px;
  }
  .sh-count{
    color: #ff0000;
  }
</style>
