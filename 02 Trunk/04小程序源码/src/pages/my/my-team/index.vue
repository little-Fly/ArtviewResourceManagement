<template>
  <div class="userlist-wrap">
    <template v-for="(item, index) in userList">
      <div class="userlist-item-wrap" :key='index'>
        <div class="userlist-item clearfix" @click="expandUserMessage(index)">
          {{item.name+"("+item.nickName+")"}}<span class="space"></span>{{item.gender}}<span class="space"></span>{{item.phone}}
          <div v-if="item.userStatus != '待审核'" class="inline-block">
            <template v-for="(citem, cindex) in item.roles">
              <span class="user_status_icon" :class="citem.roleKey" :key='cindex'>{{citem.roleName}}</span>
            </template>
            <span class="user_status_icon for_clear_auth_button" @click="clickClearAuthBtn(index)">清空权限</span>
          </div>
          <div v-else class="fr">
            <span class="user_status_icon for_audit" @click="clickOpenAuthBtn(index)">待审核</span>
          </div>
        </div>
        <div v-show="item.userStatus === '待审核' && item.showContent" class="collapse-body">
          <div class="content-item register-reason">
            <span class="item-title align-right inline-block">注册理由</span>：{{register.reason}}
          </div>
          <div class="content-item btn-wrap align-center">
            <button class="lr-mg-10" type='primary' size='mini' @click="clickAuthBtn(index, 'pass')">审核通过</button>
          </div>
          <div class="content-item  align-left">
            <span class="item-title align-right inline-block">不过原因</span>：
            <input class="refuse-input inline-block"  placeholder="如：非公司员工，请用实名，信息不完整......" type="text"  maxlength="24" v-model="register.auditRefuseText">
          </div>
          <div class="content-item btn-wrap align-center">
            <button class="lr-mg-10" type='warn' size='mini' @click="clickAuthBtn(index, 'refuse')">不能通过</button>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
import { formatTime } from '@/utils/index'
import card from '@/components/card'
import capsule from '@/components/zan/capsule'

export default {
  components: {
    card,
    capsule
  },

  data () {
    return {
      userList: [],
      data: {
        leftText: '1折',
        rightText: '限购一份',
        color: '#38f',
        onclick: null
      },
      register:{
         reason:'',
         auditRefuseText:''
      }
    }
  },

  mounted () {
    function formatUserList (userList) {
      // 为每一个用户添加信息展示状态
      for (let i = 0, len = userList.length; i < len; i++) {
        userList[i].showContent = false;
      }
      return userList;
    }
    let res1 = [
      {
        "uid": 10,
        "nickName": "龚明辉",
        "name": "龚明辉",
        "roles": [{
          "remark": "",
          "roleKey": "admin",
          "roleName": "管理员",
          "roleType": 0
        }, {
          "remark": "",
          "roleKey": "writer",
          "roleName": "录入人员",
          "roleType": 0
        }],
        "createTime": "20180927 23:34:29",
        "userStatus": "待审核"
      }, {
        "uid": 11,
        "nickName": "薛丽飞",
        "name": "薛利飞",
        "roles": [{
          "remark": "",
          "roleKey": "writer",
          "roleName": "录入人员",
          "roleType": 0
        }],
        "createTime": "20180927 23:34:29",
        "userStatus": "被拒绝"
      }
    ]
    // mockdata
    this.userList = formatUserList(res1);
    this.$http({
      url: '/wx/user/queryUsers.do',
      method: 'get',
      success: res => {
        this.userList = formatUserList(res.data);
        console.log(this.userList);
      }
    });
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
    /**
     * 审核按钮通过或者不通过按钮点击
     */
    clickAuthBtn (index, res) {
      console.log(index, this.userList);
      let sendData = {
        uid: this.userList[index].uid,
        name: this.userList[index].name,
        nickName: this.userList[index].nickName
      };
      if (res === 'pass') {
        sendData.status = 1;
        sendData.refuse = ''
      } else {
        if(this.register.auditRefuseText.length<1){
          wx.showToast({
            title: '请输入不通过原因！',
            mask: true
          });
          return;
        }
        sendData.status = 2;
        sendData.refuse = this.register.auditRefuseText;
      }
      this.$http({
        url: '/wx/user/changeUserStatus.do',
        method: 'get',
        data: sendData,
        success: res => {
          this.userList[index].userStatus = '已审核';
          wx.hideLoading();
          wx.showToast({
            title: '操作成功'
          });
        }
      });
    },
    /**
     * 清除注册用户的全部权限，将其变为游客
     */
    clickClearAuthBtn (index) {
      let sendData = {
        name: this.userList[index].name,
        uid: this.userList[index].uid
      };
      this.$http({
        url: '/wx/user/clear.do',
        method: 'put',
        data: sendData,
        success: res => {
          wx.hideLoading();
          if(res.code == 1){
            this.userList[index].userStatus = '被拒绝';
            //this.onLoad();
            wx.showToast({
              title: '操作成功'
            });
          }
          else{
            wx.showToast({
              title: '操作失败，请重试。'
            });
          }
        }
      });
    }
  }
}
</script>

<style  lang="scss" rel="stylesheet/scss" scope>
  @import '../../../assets/scss/_mixin.scss';
  page{
    background-color: #fff;
  }
  .invite-btn{
    margin: 15px 8px;
  }
  .userlist-wrap {
    margin-top: 10px;
    border-bottom: 1px solid #eee;
    .userlist-item-wrap {
      border-top: 1px solid #eee;
      .userlist-item {
        padding: 12px 15px;
        font-size: 16px;
      }
    }
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
  .zan-cell {
    display: block;
  }
  .user_status_icon{
    display: inline-block;
    color: green;
    vertical-align: middle;
    border-radius: 4px;
    font-size: 13px;
    height: 15px;
    padding: 0 5px;
    line-height: 15px;
    &.for_audit {
      padding: 2px 7px;
      margin: 0 7px;
      color: white;
      background-color: #E64340;
    }
    &.for_clear_auth_button{
      padding: 2px 7px;
      margin: 4px 7px;
      color: white;
      background-color: #E64340;
    }
    &.admin{
      color: purple;
    }
    &.checker{
      color: orange;
    }
    &.writer{
      color: gold;
    }
    &.common{
      color: pink;
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
  .register-reason{
    color: #999999;
  }
</style>
