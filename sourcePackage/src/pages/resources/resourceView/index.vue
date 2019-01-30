<template>
  <div class="resourcelist-wrap">
    <div class="search-box">
      <div class="search-input-box">
        <input class="search-input" type="text" v-model="searchText" maxlength="36" placeholder="如：姓名：张三, 性别：女" @confirm="searchResourceBtn()">
        <div class="search-btn" @click="searchResourceBtn()">搜索</div>
      </div>
      <div class="add-search-conditions" @click="addSearchConditions()">增加搜索条件</div>
    </div>
    <template v-for="(item, index) in rsList">
      <div class="resourcelist-item-wrap" :key='index'>
        <template v-for="(ss, inx) in item">
          <div :key='inx'>
            <div v-if="ss.attrType == 'picture'" class="content-item">
              <image  class="resource-image" :src="ss.attrValue" alt=""></image>
            </div>
            <div v-else-if="ss.attrType == 'video'" class="content-item">
              <video :src="ss.attrValue" controls="controls"  class="resource-vedio"></video>
            </div>
            <div v-else class="content-item">
              <span class="item-title">{{ss.attrName}}：</span>
              <span class="item-value">{{ss.attrValue}}</span>
            </div>
          </div>
        </template>
        <div class="line-fill inline-block">
            <span class="audit-btn for-ok" @click="addShareBagBtn(index)">加入分享包
              <span class="sh-count">{{shCount}}</span>
            </span>
            <span class="space"></span><span class="space"></span><span class="space"></span><span class="space"></span>
            <span class="audit-btn for-refuse" @click="clickGotoShareBtn()">去分享</span>
        </div>
      </div>
    </template>
    <div  class="line-block align-center inline-block">
      <span class="audit-btn for-ok" @click="getMoreListBtn()">获取更多</span>
    </div>
  </div>
</template>

<script>
import { $resourcer } from "@/utils/resourcer.js"
export default {

  data () {
    return {
      typeKey: '',
      typeName: '',
      searchText: '',
      isForAdd: false,
      isSearchGet: false,
      isMoreSearchGet: false,
      page: 1,
      rowNumbers: 5,
      rsList:[], //待审核资源列表
      shCount: 0, //分享包里已选分享资源的数量
      shRsList: [], //待分享资源列表
      attrList: ''  //表头名列表
    }
  },
  onShow(){
    this.shCount = this.$store.state.myShareBag.rsData.length;
    if(this.isForAdd)this.searchText=this.$store.state.rsResearchWord;
  },
  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.typeKey = paramsObj.typeKey;
    this.$store.state.rsResearchWord = ' ';
    this.typeName = paramsObj.typeName;
    wx.setNavigationBarTitle({title: this.typeName + "资源浏览"});
    this.rsList = [];
    this.page = 1;
    this.getrsTypevalue(this.typeKey);
  },
  
  methods: {
    /*
     *把搜索字符串存入全局变量
     */
    saveSerchWord(){
      this.searchText = this.searchText.replace(/ /g, '');
      this.$store.state.rsResearchWord = this.searchText;
    },
    /*
     *把全局存放的搜索字符串结构化
     */
    cutTheResearchWord(){
      var words = this.$store.state.rsResearchWord;
      words = words.replace(/ /g, '');
      words = words.replace(/,/g, '，');
      words = words.replace(/:/g, '：');
      var wList = words.split('，');
      var searchWordList = [];
      for(var i=0; i<wList.length; i++){
        var atList = wList[i].split('：');
        if(atList.length == 2){
          searchWordList.push({
            name: atList[0],//表头名
            key: this.getAttrKeyByName(atList[0]),
            word: atList[1] //匹配关键字
          });
        }
      }
      return searchWordList;
    },
    getAttrKeyByName(name){
      for(var i=0; i<this.attrList.names.length; i++){
        if(this.attrList.names[i] == name)return this.attrList.keys[i];
      }
      return '';
    },
    getAttrList(){
      var nameList = [];
      var keyList = [];
      for(var i=0; i<this. rsList[0].length; i++){
        if(this.rsList[0][i].attrType != "picture" && this.rsList[0][i].attrType != "vedio")
          nameList.push(this.rsList[0][i].attrName);
          keyList.push(this.rsList[0][i].attrKey);
      }
      this.attrList = {names: nameList, keys: keyList};
    },
    searchResourceBtn(){
      this.saveSerchWord();
      this.isMoreSearchGet = false;
      this.isSearchGet = true;
      this.page = 1;
      this.isMoreSearchGet = false;
      this.searchResource();
    },
    searchResource(){
      if(!this.$store.state.rsResearchWord){
        this.isSearchGet = false;
        this.page = 1;
        this.rsList = [];
        this.getrsTypevalue(this.typeKey);
        return;
      }
      if(!this.isMoreSearchGet){
          this.rsList = [];
      }
      var searchList = this.cutTheResearchWord();
    /*
     *送往后台的搜索条件字符串
     *    searchkey: {"示例表头1":"add", "示例表头2":"2"}, //JSON - 属性字表头名 : 在此列中将要匹配的内容
     */
      var sendData = "{";
      for(var i=0; i<searchList.length; i++){
        sendData +='\"' + searchList[i].key + '\"' + ":" + '\"' + searchList[i].word + '\"';
        if((i+1) < searchList.length)sendData += ",";
      }
      sendData += "}";
      sendData = JSON.parse(sendData);
      this.$http({
        url: '/rs/search/searchbyuser.do',
        method: 'get',
        data: {
          typekey: this.typeKey,
          start: (this.page-1)*this.rowNumbers,
          len: this.rowNumbers,
          searchkey: sendData
        },
        success: res => {
          wx.hideLoading();
          var rData = $resourcer(res.data);
          for(var i=0; i<rData.length; i++){
             this.rsList.push(rData[i]);
          }
          if(rData.length > 0){
            this.page++;
            this.getAttrList();
          }
          else{
            wx.showToast({title: '后面没有了', icon: "none"});
          }
          if(this.rsList.length<1)wx.showToast({title: '没找到内容', icon: "none"});
        }
      });
    },
    addSearchConditions(){
      this.saveSerchWord();
      var alist = JSON.stringify({names: this.attrList.names});//直接传数组貌似不行
      wx.navigateTo({url: "../searchView/main?attrNames=" + alist});
      this.isForAdd = true;
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
          var rData = $resourcer(res.data);
          for(var i=0; i<rData.length; i++){
             this.rsList.push(rData[i]);//当获取更多的时候，直接添加到列表的后面
          }
          if(rData.length > 0){
            this.page++;
            this.getAttrList();
          }
          wx.hideLoading();
        }
      });
    },

    /**
     * 将当前资源加入分享包
     */
    addShareBagBtn(index) {
      var add = true;
      for(var i=0; i<this.$store.state.myShareBag.rsData.length; i++){
        var flag = 0;
        for(var j=0; j<this.$store.state.myShareBag.rsData[i].length; j++){
          if(this.rsList[index].length < ( j+1 ) )break;
          if(this.$store.state.myShareBag.rsData[i][j] == this.rsList[index][j] ) flag++;
        }
        if(flag == this.$store.state.myShareBag.rsData[i].length){
          add = false;
          break;
        }
      }
      if(add){
        this.$store.commit('addRSShareData', this.rsList[index]);
        this.$store.commit('updateRSShareIsModify', true);
        this.shCount = this.$store.state.myShareBag.rsData.length;
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
       wx.navigateTo({url: "../../share/main?rsTypeName=" + this.typeName + "&typeKey=" + this.typeKey});
    },
    /**
     * 获取更多资源条目
     */
    getMoreListBtn(){
      if(this.isSearchGet){
        this.isMoreSearchGet = true;
        this.searchResource();
      } 
      else this.getrsTypevalue(this.typeKey);
    }
  }
}
</script>

<style  lang="scss" rel="stylesheet/scss" scope>
  
  page{
    width:100%;
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
    padding: 0 2px;
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
  .line-block{
    width: 100%;
    margin: 16px 0;
  }
  .line-fill{
    width: 100%;
    padding: 8px 0px;
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
    width:90%;
    padding-bottom: 12px;
    margin: 16px;
    border-bottom: 1px solid #f56c6c;
  }
  .resource-image{
    width: 100%;
    height: 180px;
  }
  .resource-vedio{
    width: 100%;
    height: 200px;
   }
  .sh-count{
    color: #ff0000;
  }
</style>
