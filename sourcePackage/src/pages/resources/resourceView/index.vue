<template>
  <div class="resourcelist-wrap">
    <div class="search-box">
      <div class="search-input-box">
        <input class="search-input" type="text" v-model="searchText" maxlength="36" placeholder="请输入关键字"></input>
        <div class="search-btn" @click="searchResourceBtn()">搜索</div>
      </div>
      <div class="add-search-conditions" @click="addSearchConditions()">增加搜索条件</div>
    </div>
    <template v-for="(item, index) in rsList">
      <div class="resourcelist-item-wrap content-item" :key='index'>
        <template v-for="(ss, inx) in item">
          <div :key='inx'>
            <div v-if="ss.attrType == 'picture'" class="resource-image">
              
            </div>
            <div v-else-if="ss.attrType == 'video'" class="resource-video">
              
            </div>
            <div v-else class="content-item inline-block">
              <span class="item-title align-right inline-block">{{ss.attrName}}</span>：{{ss.attrValue}}
            </div>
          </div>
        </template>
        <div  class="resource-image">
          <image  class="resource-image" src="../../../assets/images/resource_init.png" alt=""></image>
        </div>
        <div class="line-fill inline-block">
            <span class="audit-btn for-ok" @click="addShareBagBtn(index)">加入分享包
              <span class="sh-count">{{shCount}}</span>
            </span>
            <span class="space"></span></span><span class="space"></span></span><span class="space"></span></span><span class="space"></span>
            <span class="audit-btn for-refuse" @click="clickGotoShareBtn()">去分享</span>
        </div>
      </div>
    </template>
    <div  class="line-block align-center inline-block">
      <span class="audit-btn for-ok" @click="getrsTypevalue(typeKey)">获取更多</span>
    </div>
  </div>
</template>

<script>

export default {

  data () {
    return {
      typeKey: '',
      typeName: '',
      searchText: '',
      page: 1,
      rowNumbers: 2,
      rsList:[], //待审核资源列表
      shCount: 0, //分享包里已选分享资源的数量
      shRsList: [] //待分享资源列表
    }
  },
  onShow(){
    this.shCount = this.$store.state.myShareBag.length;
  },
  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.typeKey = paramsObj.typeKey;
    this.typeName = paramsObj.typeName;
    wx.setNavigationBarTitle({title: this.typeName + "资源浏览"});
    this.rsList = [];
    this.page = 1;
    this.getrsTypevalue(this.typeKey);
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
      for(var i=0; i<this.$store.state.myShareBag.length; i++){
        var flag = 0;
        for(var j=0; j<this.$store.state.myShareBag[i].length; j++){
          if(this.rsList[index].length < ( j+1 ) )break;
          if(this.$store.state.myShareBag[i][j] == this.rsList[index][j] ) flag++;
        }
        if(flag == this.$store.state.myShareBag[i].length){
          add = false;
          break;
        }
      }
      if(add){
        this.$store.state.myShareBag.push(this.rsList[index]);
        this.shCount = this.$store.state.myShareBag.length;
      }
      else{
        wx.showToast({title: '重复选择'});
      }
    },
    clickGotoShareBtn(){
      if(this.$store.state.myShareBag.length < 1){
        wx.showModal({title: '提示', content: '选些资源才能打开分享包', showCancel: false});
        return;
      }
       wx.navigateTo({url: "../../share/main"});
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
  .search-box{
    width:100%;
    background-color: #dddddd;
    margin-bottom: 16px;
  }
  .search-input-box{
    display: flex;
    align-items: center;
    margin: 4px 16px;
    height: 36px;
  }
  .search-input{
    width: 288px;
    font-size: 16px;
    height: 30px;
    line-height: 30px;
    border-radius: 8px;
    background-color: #fff;
  }
  .search-btn{
    font-size: 16px;
    padding-left: 16px; 
  }
  .add-search-conditions{
    width:100%;
    height: 48px;
    text-align: center;
    font-size: 16px;
    color: #fff;
    line-height: 48px;
    background-color: #f6923c;
  }
  .audit-btn{
    display: inline-block;
    vertical-align: middle;
    border-radius: 4px;
    font-size: 15px;
    height: 30px;
    padding: 0 15px;
    line-height: 30px;
    &.for-ok {
      color: white;
      background-color: #67c23a;
    }
    &.for-modify {
      color: white;
      background-color: #f6923c;
    }
    &.for-refuse {
      color: white;
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
    padding: 8px 0px;
    font-size: 15px;
  }
  .resourcelist-wrap {
    margin-bottom: 12px;
  }
  .resourcelist-item-wrap{
    padding-bottom: 12px;
    margin: 0 16px;
    border-bottom: 1px solid #f56c6c;
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
