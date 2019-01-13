<template>
  <div class="resourcelist-wrap">
    <div class="function-btn-box">
      <span class="function-btn for-select-template" @click="selectTemplate()">选取模板</span>
      <span class="space"></span><span class="space"></span>
      <span class="function-btn for-create-page" @click="createSharePage()">生成分享页面</span>
    </div>
    <template v-for="(item, index) in shRsList">
      <div class="resourcelist-item-wrap" :key='index'>
        <div class="icon-group-box">
          <image  class="rs-icon" src="../../assets/images/delete.png" alt=""></image>
          <span class="space"></span>
          <image  class="rs-icon" src="../../assets/images/up.png" alt=""></image>
          <image  class="rs-icon" src="../../assets/images/down.png" alt=""></image>
        </div>
        <div class="inline-block">
          <template v-for="(ss, inx) in item">
            <div :key='inx'>
              <div v-if="ss.attrType == 'picture'" class="resource-image  content-item">

              </div>
              <div v-else-if="ss.attrType == 'video'" class="resource-video  content-item">
              
              </div>
              <div v-else class="content-item inline-block">
                <span class="item-title align-right inline-block">{{ss.attrName}}</span>：{{ss.attrValue}}
              </div>
            </div>
          </template>
        
          <div  class="resource-image  content-item">
            <image  class="resource-image" src="../../assets/images/resource_init.png" alt=""></image>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
export default {
  data () {
    return {
      shRsList: [] //待分享资源列表
    }
  },

  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.shRsList = JSON.parse(paramsObj.shRsList);
  },
  methods: {

    searchResourceBtn(){
      
    },
    addSearchConditions(){
      
    },
    /*
    *从接口拿到的资源数据是一维平铺，这里根据sourceKey进行二维处理
    */
    changTheSourceArray(data){
      var saList = [];
      var dList = [];
      for(;;){
        if(data.length < 1)break;
        var key = data[0].resourceKey;
        dList.push(data[0]);
        data.splice(0,1);
        var i=0;
        for(;;){
          if(key == data[i].resourceKey){
            dList.push(data[i]);
            data.splice(i,1);
          }
          else i++;
          if(data.length == 0)break;
          if(i >= data.length)break;
        }
        saList.push(dList);
        dList = [];
      }
      return saList;
    },

    /*
     *获取某资源类别下的资源
     */
    getrsTypevalue(typeKey){
       this.$http({
        url: '/rs/detail/getallbyuser.do',
        method: 'get',
        data: {
          typekey: typeKey,
          start: (this.page-1)*this.rowNumbers,
          len: this.rowNumbers},
        success: res => {
          var rData = this.changTheSourceArray(res.data);
          for(var i=0; i<rData.length; i++){
             this.rsList.push(rData[i]);
          }
          if(rData.length > 0)this.page++;
          wx.hideLoading();
        }
      });
    },

    /**
     * 将当前资源加入分享包
     */
    addShareBagBtn(index) {
      var add = true;
      for(var i=0; i<this.shRsList.length; i++){
        var flag = 0;
        for(var j=0; j<this.shRsList[i].length; j++){
          if(this.rsList[index].length < ( j+1 ) )break;
          if(this.shRsList[i][j] == this.rsList[index][j] ) flag++;
        }
        if(flag == this.shRsList[i].length){
          add = false;
          break;
        }
      }
      if(add){
        this.shRsList.push(this.rsList[index]);
        this.shCount = this.shRsList.length;
      }
      else{
        wx.showToast({title: '重复选择'});
      }
    },
    /**
     * 获取更多待资源条目
     */
    getMoreListBtn(){
      this.requestCount = 0;
      for(var i=0; i<this.rsTypeList.length; i++){
         this.getrsTypevalue(this.typeKey);
      }
    }
  }
}
</script>

<style  lang="scss" rel="stylesheet/scss" scope>
  
  page{
    background-color: #fff;
  }
  .function-btn-box{
    display: inline-block;
    width: 100%;
    padding: 8px 16px;
    vertical-align: middle;
    text-align: right;
  }
  .function-btn{
    display: inline-block;
    vertical-align: middle;
    border-radius: 4px;
    font-size: 15px;
    height: 30px;
    padding: 0 15px;
    line-height: 30px;
    color: white;
    &.for-select-template {
      background-color: #67c23a;
    }
    &.for-create-page {
      background-color: #f56c6c;
      margin-right: 0px;
    }
  }
  .space{
    margin: 0px 8px;
  }
  .line-block{
    width: 100%;
    margin: 16px 0;
  }
  .line-fill{
    width: 100%;
    padding: 8px 0px;
  }
  .content-item{
    margin-left: 16px;
    padding: 8px 0px;
    font-size: 15px;
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
  .resourcelist-wrap {
    margin: 0 16px;
    margin-bottom: 12px;
  }
  .resourcelist-item-wrap{
    display: flex;
    width: 100%;
    padding-bottom: 12px;
    border-top: 1px solid #f56c6c;
  }
  .attr-type{
    padding: 8px 0px;
    font-size: 16px;
    color: #E64340;
  }
  .resource-name{
    padding: 8px 0px;
    font-size: 20px;
  }
  .resource-image{
    display: inline-block;
    padding: 8px 0px;
    width: 100px;
    height: 100px;
  }
  .sh-count{
    color: #ff0000;
  }
</style>
