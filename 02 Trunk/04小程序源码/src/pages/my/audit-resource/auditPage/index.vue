<template>
  <div class="resourcelist-wrap">
    <template v-for="(item, index) in rsList">
      <div class="resourcelist-item-wrap content-item" :key='index'>
        <div class="attr-type ">
          <span class="align-right inline-block">资源类型</span>：{{item.typeName}}
        </div>
        <div class="resource-name">{{item.theFirstAttr.attrValue}}</div>
        
        <template v-for="(ss, inx) in item.data">
          <div :key='inx'>
            <div v-if="ss.attrType != 'image'" class="content-item inline-block">
              <span class="item-title align-right inline-block">{{ss.attrName}}</span>：{{ss.attrValue}}
            </div>
            <div v-if="ss.attrType == 'image'" class="resource-image">
              
            </div>
          </div>
        </template>
        <div v-if="item.command != 'delete'" class="line-fill align-center inline-block">
           <span class="audit-btn for-ok" @click="clickAuthOKBtn(index)">审核通过并发布</span>
           <span class="space"></span>
           <span class="audit-btn for-modify"  @click="clickAuditReturnBtn(index)">返回修改</span>
           <span class="space"></span>
           <span class="audit-btn for-refuse" @click="clickAuditRefuseBtn(index)">不通过</span>
        </div>
        <div v-else class="line-fill align-center inline-block">
           <span class="audit-btn for-ok" @click="clickAuthOKBtn(index)">同意并立即删除</span>
           <span class="space"></span>
           <span class="audit-btn for-refuse" @click="clickAuditRefuseBtn(index)">不同意</span>
        </div>
      </div>
    </template>
    <div  class="line-fill align-center inline-block">
      <span class="audit-btn for-ok" @click="clickAuditBtn()">获取更多</span>
    </div>
  </div>
</template>

<script>

export default {
  components: {
    //card,
    //capsule
  },

  data () {
    return {
      operType:'',
      page: 1,
      rowNumbers: 2,
      rsTypeList:[],
      rsList:[],
      userList: []
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
          for(var i=0; i<this.rsTypeList.length; i++){
            this.getrsTypevalue(this.rsTypeList[i].typeKey, this.rsTypeList[i].name);
          }
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
          var saList = this.changTheSourceArray(res.data);
          var auditList = this.attrStateFilter(this.operType, saList);
          wx.hideLoading();
          for(var j=0; j<auditList.length; j++){
             var vdata = [];
             for(var i=1; i<auditList[j].length; i++){
               vdata.push(auditList[j][i]);
             }
             this.rsList.push({
                typeName: typeName, 
                theFirstAttr: {
                   attrName: auditList[j][0].attrName, 
                   attrValue:auditList[j][0].attrValue},
                data: vdata
              });
            }
        }
      });
    },

    /**
     * 审核按钮通过按钮点击
     */
    clickAuthOKBtn (index) {
      var url = "";
      if(this.operType == "ApprovalAdd")url = "/rs/detail/approvaladd.do";
      else if(this.operType == "ApprovalUpdate")url = "/rs/detail/approvalupdate.do";
      else if(this.operType == "ApprovalDel")url = "/rs/detail/approvaldel.do";
      this.$http({
        url: url,
        method: 'post',
        data: {resourcekey: this.rsList[index].data[0].resourceKey},
        success: res => {
          this.userList.splice(index,1);
          wx.hideLoading();
          wx.showToast({title: '操作成功'});
        }
      });
    },
    /**
     * 返回修改，针对新增、修改资源
     */
    clickAuditReturnBtn(index){
      this.$http({
        url: '',
        method: 'post',
        data: {resourcekey: this.rsList[index].data[0].resourceKey},
        success: res => {
          this.userList.splice(index,1);
          wx.hideLoading();
          wx.showToast({title: '操作成功'});
        }
      });
    },
    /**
     * 不同意操作
     */
    clickAuditRefuseBtn(index){
      this.$http({
        url: '/rs/detail/reject.do',
        method: 'post',
        data: {resourcekey: this.rsList[index].data[0].resourceKey},
        success: res => {
          this.userList.splice(index,1);
          wx.hideLoading();
          wx.showToast({title: '操作成功'});
        }
      });
    },
    /**
     * 获取跟多待审批的条目
     */
    getMoreListBtn(){
      getrsTypevalue(typeKey, typeName);
    }
  }
}
</script>

<style  lang="scss" rel="stylesheet/scss" scope>
  
  page{
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
    }
  }
  

  .line-fill{
    width: 100%;
    margin: 16px 0;
  }
  .resourcelist-wrap {
    margin-bottom: 12px;
    border-bottom: 1px solid #eee;
  }
  .resourcelist-item-wrap{
    margin-bottom: 12px;
    border-bottom: 1px solid #aaaaaa;
  }
  .content-item{
    padding: 8px 12px;
    font-size: 15px;
  }
  .attr-type{
    padding: 8px 12px;
    font-size: 16px;
    color: #E64340;
  }
  .resource-name{
    padding: 8px 12px;
    font-size: 20px;
  }
  .collapse-body {
    font-size: 15px;
    .content-item{
      margin-top: 5px;
      &.btn-wrap {
        margin-top: 20px;
        margin-bottom: 20px;
      }
      .item-title {
        width: 70px;
      }
    }
  }
  .refuse-input{
    vertical-align: middle;
    border-radius: 4px;
    font-size: 13px;
    height: 18px;
    width: 256px;
    padding: 0 5px;
    line-height: 18px;
    border: 1px solid #999999;
  }
  .space{
    margin: 0px 8px;
  }
</style>
