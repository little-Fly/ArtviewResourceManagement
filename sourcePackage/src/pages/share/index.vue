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
          <image  class="rs-icon" src="../../assets/images/delete.png" alt="" @click="delResource(index)"></image>
          <span class="space"></span>
          <image  class="rs-icon" src="../../assets/images/up.png" alt="" @click="upResource(index)"></image>
          <image  class="rs-icon" src="../../assets/images/down.png" alt="" @click="downResource(index)"></image>
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
   this.shRsList = this.$store.state.myShareBag;
  },
  methods: {

    searchResourceBtn(){
      
    },
    addSearchConditions(){
      
    },
    /*
    *从资源包里删除一条资源
    */
    delResource(index){
      this.shRsList.splice(index, 1);
      this.$store.state.myShareBag = this.shRsList;
    },

    /*
     *把资源包里的这条资源向前移动一个位置
     */
    upResource(index){
       if(index == 0){
         wx.showToast({title: '到顶了'});
         return;
       }
       this.shRsList.splice(index + 1, 0, this.shRsList[index - 1]);
       this.shRsList.splice(index - 1, 1);
      this.$store.state.myShareBag = this.shRsList;
    },
    downResource(index){
       if((index + 1) >= this.shRsList.length){
         wx.showToast({title: '到底了'});
         return;
       }
       this.shRsList.splice(index + 2, 0, this.shRsList[index]);
       this.shRsList.splice(index, 1);
      this.$store.state.myShareBag = this.shRsList;
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
