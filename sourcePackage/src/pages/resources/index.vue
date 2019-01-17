<template>
  <div class="container">
    <div class="resource-warp clearfix">
      <template v-for="(item, index) in resList">
        <div class="res-item fl" :key="'res_items_' + index">
          <image v-if="isVisitor" class="res-item__headimg" src="../../assets/images/resourcebk.png" alt=""" @click="clickVisitorTap()"></image>
          <image v-else-if="item.logoUrl" class="res-item__headimg" src="../../assets/images/resource_init.png" alt="" @click="clickResourceTap(item.typeKey, item.name)"></image>
          <image v-else class="res-item__headimg" src="../../assets/images/resourcebk.png" alt=""></image>
          <div class="res-name">{{item.name}}</div>
        </div>
      </template>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      isVisitor: true,
      resList: []
    }
  },
  computed: {

  },
  mounted () {
    // 请求资源列表
    this.getResourseListData();
    this.isRoleVisitor();
  },
  methods: {  

    //判断访问用户是不是游客
    isRoleVisitor() {
      if(this.$store.state.userAuditStatus == 4){
        this.isVisitor = false;
      }
    },
    getResourseListData () {
      this.$http({
        url: '/rs/def/getall.do',
        method: 'get',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: res => {
          console.log(res);
          this.resList = res.data;
        }
      });
    },
    clickResourceTap(typeKey, name){
      wx.navigateTo({url: "resourceView/main?typeKey=" + typeKey+"&typeName="+name})
    },
    clickVisitorTap(){
      if(this.$store.state.userAuditStatus == 1 || this.$store.state.userAuditStatus == 3){
        var ctent = '你还没有进行实名注册，不能查看资源内容！现在注册？';
        if(this.$store.state.userAuditStatus == 3)ctent = '你的注册被管理员拒绝，不能查看资源内容！可以到我的消息查看原因，也可以重新注册，现在注册？';
        wx.showModal({
          title: '提示', 
          content: ctent,
          success: res =>{
            if(res.confirm){//有两个注册入口，这里是其中之一
              // 由于微信用户要与PC用户互通，这里注册需要得到用户授权获取高级信息
              this.$tool.checkUserInfoAuth().then(() => {
              // 用户未授权，进到授权页
                console.log('已授权');
                wx.navigateTo({url: "../my/register/main"});
              }, () => {
                this.$tool.goToAuthPage();
              });
            }
          }
        });
      }
      else if(this.$store.state.userAuditStatus == 2){
        wx.showModal({title: '提示', content: '你的注册申请管理员还没有审批，不能查看资源！', showCancel: false});
      }
    }
  }
}
</script>

<style lang="scss">
page {
  background-color: #fff;
}
.counter-warp {
  text-align: center;
  margin-top: 100px;
}
.home {
  display: inline-block;
  margin: 100px auto;
  padding: 5px 10px;
  color: blue;
  border: 1px solid blue;
}
.container {
  padding: 15px;
}
.resource-warp {
  margin-left: -7.5px;
  margin-right: -7.5px;
}
.res-item {
  width: 33.3333333%;
  padding: 0 7.5px 10px;
  text-align: center;
  .res-item__headimg {
    width: 105px;
    height: 105px;
    vertical-align: bottom;
  }
  .res-name {
    font-size: 15px;
  }
}
</style>
