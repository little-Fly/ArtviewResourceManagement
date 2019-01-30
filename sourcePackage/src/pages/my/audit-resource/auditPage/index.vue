<template>
  <div class="resourcelist-wrap">
    <template v-for="(item, index) in rsList">
      <div class="resourcelist-item-wrap" :key='index'>
        <div class="attr-type ">
          <span class="align-right inline-block">资源类型</span>：{{item.typeName}}
        </div>
        <template v-for="(ss, inx) in item.data">
          <div :key='inx'>
            <div v-if="ss.attrType == 'picture'" class="content-item">
              <image  class="resource-image" :src="ss.attrValue" @error="errImg" alt=""></image>
              <span class="item-level">{{ss.attrLevel}}</span>
            </div>
            <div v-else-if="ss.attrType == 'video'" class="content-item">
              <video :src="ss.attrValue" controls="controls"  class="resource-vedio"></video>
              <span class="item-level">{{ss.attrLevel}}</span>
            </div>
            <div v-else class="content-item">
              <span class="item-title">{{ss.attrName}}：</span>
              <span class="item-value">{{ss.attrValue}}</span>
              <span class="item-level">{{ss.attrLevel}}</span>
            </div>
          </div>
        </template>

        <div class="line-block">
          <div class="content-item">
            <span class="audit-input-title inline-block">审核意见：</span>
            <textarea class="audit-input inline-block" maxlength="128"  placeholder="如：同意，请核实单价，请完善备注说明，这个不发了......" type="text" v-model="item.auditText"></textarea>
          </div>
          <div v-if="operType != 'ApprovalDel'" class="line-fill inline-block">
            <span class="audit-btn for-ok" @click="clickAuthOKBtn(index)">审核通过并发布</span>
            <span class="space"></span></span><span class="space"></span></span><span class="space"></span></span><span class="space"></span>
            <span class="audit-btn for-refuse" @click="clickAuditRefuseBtn(index)">不通过</span>
          </div>
          <div v-else class= "line-fill inline-block">
            <span class="audit-btn for-ok" @click="clickAuthOKBtn(index)">同意并立即删除</span>
            <span class="space"></span></span><span class="space"></span></span><span class="space"></span></span><span class="space"></span>
            <span class="audit-btn for-refuse" @click="clickAuditRefuseBtn(index)">不同意</span>
          </div>
        </div>
      </div>
    </template>
    <div v-if="canMore == 'true'"  class="line-block align-center inline-block">
      <span class="audit-btn for-ok" @click="getMoreListBtn()">获取更多</span>
    </div>
  </div>
</template>

<script>
import { $resourcer } from "@/utils/resourcer.js"
export default {

  data () {
    return {
      sampleImg: "../../../../assets/images/resource_init.png",
      operType:'',
      page: 1,
      rowNumbers: 5,
      rsTypeList:[], //资源类型列表
      rsList:[], //待审核资源列表
      canMore:'false', //开关，显示获取更多待审核资源按钮
      requestCount:0 //开关，解决异步调用的问题
    }
  },

  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.operType = paramsObj.operType;
    if(this.operType == "ApprovalAdd")wx.setNavigationBarTitle({title: "新增资源审核"});
    else if(this.operType == "ApprovalUpdate")wx.setNavigationBarTitle({title: "修改资源审核"});
    else if(this.operType == "ApprovalDel")wx.setNavigationBarTitle({title: "删除资源审核"});
    this.rsTypeList = [],
    this.rsList = [],
    this.getAttrDefinedList();
  },
  methods: {
    /**
     * 展开用户信息，只有当用户状态是待审核时候才能审核
     */
    expandUserMessage (index) {
      this.userList[index].showContent = !this.userList[index].showContent;
    },
    clickOpenAuthBtn(index){
      this.register.reason = this.userList[index].reason;
    },
    errImg(e){
      var arr=e.target.dataset.eventid.split('-');
      var row = parseInt(arr[1]);
      var col = parseInt(arr[2]);
      this.rsList[row][col].attrValue = "../../../../assets/images/resourcebk.png";
    },
   /*
    *过滤需要审核的资源
    */
    attrStateFilter(auditType, saList){
      var auditList = [];
      for(var i=0; i<saList.length; i++){
        if(saList[i][0].attrState == auditType)auditList.push(saList[i]);
      }
      return auditList;
    },

    /*
    *取资源类别定义
    */
    getAttrDefinedList (){
       this.$http({
        url: '/rs/def/getall.do',
        method: 'get',
        success: res => {
          this.rsTypeList = res.data;
          wx.hideLoading();
          this.getMoreListBtn();
          console.log(this.rsList);
        }
      });
    },
    
    /*
     *获取某资源类别下的待审批的资源
     */
    getrsTypevalue(typeKey, typeName){
       this.$http({
        url: '/rs/detail/getallpending.do',
        method: 'get',
        data: {
          //opertype: this.operType,
          typekey: typeKey,
          start: (this.page-1)*this.rowNumbers,
          len: this.rowNumbers},
        success: res => {
          var saList = $resourcer(res.data);
          var auditList = this.attrStateFilter(this.operType, saList);
          wx.hideLoading();
          for(var j=0; j<auditList.length; j++){
             this.rsList.push({
                typeName: typeName, 
                data: auditList[j],
                auditText: ''
             });
           }
           this.requestCount++;
           if(this.requestCount >= this.rsTypeList.length){
             if(this.rsList.length < 1){
               wx.showToast({title: '后面没有了'});
               this.canMore = 'true';
             }
             else this.canMore = 'false';
           }
        }
      });
    },
    /**
     * 审核按钮通过按钮点击
     */
    clickAuthOKBtn (index) {
      if(this.rsList[index].auditText.length < 1){
        wx.showModal({title: '提示', content: '你还没有输入审批意见', showCancel: false});
        return;
      }
      var url = "";
      if(this.operType == "ApprovalAdd")url = "/rs/detail/approvaladd.do";
      else if(this.operType == "ApprovalUpdate")url = "/rs/detail/approvalupdate.do";
      else if(this.operType == "ApprovalDel")url = "/rs/detail/approvaldel.do";
      this.$http({
        url: url,
        method: 'post',
        data: {
          resourcekey: this.rsList[index].data[0].resourceKey,
          approvalmess: this.rsList[index].auditText
        },
        success: res => {
          this.rsList.splice(index,1);
          wx.hideLoading();
          wx.showToast({title: '操作成功'});
          if(this.rsList.length < 1)this.canMore = 'true';
        }
      });
    },
    /**
     * 不同意操作
     */
    clickAuditRefuseBtn(index){
       if(this.rsList[index].auditText.length < 1){
        wx.showModal({title: '提示', content: '你还没有输入审批意见', showCancel: false});
        return;
      }
      this.$http({
        url: '/rs/detail/reject.do',
        method: 'post',
        data: {
          resourcekey: this.rsList[index].data[0].resourceKey,
          approvalmess: this.rsList[index].auditText
        },
        success: res => {
          this.rsList.splice(index,1);
          wx.hideLoading();
          wx.showToast({title: '操作成功'});
          if(this.rsList.length < 1)this.canMore = 'true';
        }
      });
    },
    /**
     * 获取跟多待审批的条目
     */
    getMoreListBtn(){
      this.requestCount = 0;
      for(var i=0; i<this.rsTypeList.length; i++){
         this.getrsTypevalue(this.rsTypeList[i].typeKey, this.rsTypeList[i].name);
      }
    }
  }
}
</script>

<style  lang="scss" rel="stylesheet/scss" scope>
  
  page{
    width:100%;
    background-color: #fff;
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
  .audit-input{
    font-size: 13px;
    height: 48px;
    width: 254px;
    padding: 0 5px;
    line-height: 18px;
    border: 1px solid #999999;
    maxlength: 32px;
    fixed: true;
    color: #000;
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
    width: 70%;
    text-align: left;
    word-break: break-all;
    margin: 0 8px;
  }
  .item-level{
    display: inline-block;
    width: 15%;
    text-align: right;
  }
  .resourcelist-wrap {
    width: 100%;
    margin-bottom: 12px;
  }
  .resourcelist-item-wrap{
    width:90%;
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
    width: 85%;
    height: 180px;
  }
  .resource-vedio{
    width: 85%;
    height: 180px;
  }
</style>
