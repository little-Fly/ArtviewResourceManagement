<template>
  <div>
    <div class="zan-panel">
      <zan-field v-bind="Object.assign({}, {rightTitle: true, maxLength: 20}, handleFunctions, registerObj.name)" :value="formObj.name"/>
      <div class="zan-cell zan-field">
        <div class="zan-field__title align-right">性别</div>
        <picker class="zan-field__input zan-field__bd" @change="bindPickerChange" range-key='sexName' :value="selectSexIndex" :range="objectArray">
          {{objectArray[selectSexIndex].sexName}}
        </picker>
      </div>
      <zan-field v-bind="Object.assign({}, {rightTitle: true, maxLength: 11}, handleFunctions, registerObj.phone)" :value="formObj.phone"/>
      <zan-field v-bind="Object.assign({}, {rightTitle: true, maxLength: 200}, handleFunctions, registerObj.reason)" :value="formObj.reason"/>
    </div>
    <div class="zan-panel">
      <button class="zan-btn zan-btn--primary" :disabled="loadingStatus" :loading="loadingStatus" @click="submitRegisterMessage">提交审核</button>
    </div>
  </div>
</template>

<script>
// import { formatTime } from '@/utils/index'
// import card from '@/components/card'
import ZanField from '@/components/zan/field'
export default {
  components: {
    ZanField
  },

  data () {
    return {
      nickName:'',
      // 页面的注册配置对象
      registerObj: {
        name: {
          // focus: true,
          title: '姓名',
          placeholder: '请输入您的名字',
          componentId: 'name'
        },
        phone: {
          error: true,
          title: '联系电话',
          inputType: 'number',
          placeholder: '请输入手机号',
          componentId: 'phone'
        },
        reason: {
          title: '注册理由',
          type: 'textarea',
          placeholder: '请输入申请注册的原因',
          componentId: 'reason'
        }
      },
      // 表单处理事件
      handleFunctions: {
        handleZanFieldChange: this.handleZanFieldChange,
        handleZanFieldFocus: this.handleZanFieldFocus,
        handleZanFieldBlur: this.handleZanFieldBlur
      },
      // 用户输入的姓名电话注册理由
      formObj: {
        name: '',
        sex: 0,
        phone: '',
        reason: ''
      },
      // 性别piker
      objectArray: [
        {
          sex: 0,
          sexName: '女'
        },
        {
          sex: 1,
          sexName: '男'
        }
      ],
      selectSexIndex: 0,
      loadingStatus: false
    }
  },
  mounted () {
    if(this.$store.state.userAuditStatus == 1){//用户第一次注册提交
      this.getWxUserInfo(); // 不能放在created中，页面一加载就会执行，还没有调用户授权接口的话就会报错
    } else if(this.$store.state.userAuditStatus == 3){ //用户注册被拒绝了，他又进了注册页面
      this.formObj.name = this.$store.state.userInfo.name;
      this.formObj.phone = this.$store.state.userInfo.phone;
      this.formObj.reason = this.$store.state.userInfo.reason;
      this.selectSexIndex = this.$store.state.userInfo.gender;
    }
  },
  methods: {
    // 获取用户的授权后的敏感数据, 同步到全局
    getWxUserInfo () {
      wx.showLoading({ title: '加载中', mask: true }); // 全局的loading配置对象储存到$data中
      wx.getUserInfo({
        success: (res) => { // 里面函数有this,所以用箭头函数把上下文的this绑定到一起
          wx.hideLoading();
          // 存储用户敏感信息之加密码
          this.$store.commit('updateEncryptedObj', {
            iv: res.iv,
            encryptedData: res.encryptedData
          });
          // 存储用户敏感信息之加密码
          this.$store.commit('updateWxUserInfo', res.userInfo);
          this.nickName = res.userInfo.nickName;
        },
        fail (res) {
          console.error(res);
          wx.hideLoading();
        }
      })
    },
    // 选择性别事件
    bindPickerChange: function(e) {
      let val = e.target.value;
      // 同步页面性别
      this.selectSexIndex = val;
      // this.$set({
      //   'formObj.sex': this.objectArray[Number(val)].sex
      // });
      console.log(this.formObj);
      this.formObj.sex = this.objectArray[Number(val)].sex;
      console.log(this.formObj);
    },
    handleZanFieldChange (e) {
      const { componentId, target, detail } = e;
      console.log('修改后新的值为');
      console.log(e.target.value);
      this.formObj[componentId] = target.value;
      console.log(this.formObj[componentId]);
    },
    handleZanFieldFocus (e) {
      const { componentId, target, detail } = e
      // console.log(this.formObj);
      // this.formObj[componentId] = target.value;
      // console.log(this.formObj);
      // console.log('[zan:field:focus]', componentId, target, detail)
    },
    handleZanFieldBlur (e) {
      const { componentId, target, detail } = e
      // this.formObj[componentId] = target.value;
      // console.log('[zan:field:blur]', componentId, target, detail)
    },
    inputCheck(){
      this.formObj.name = this.formObj.name.replace(/ /g,"");
      this.formObj.phone = this.formObj.phone.replace(/ /g,"");
      this.formObj.reason = this.formObj.reason.replace(/ /g,"");
      if(this.formObj.name.length < 1){
        wx.showModal({title: '提示', content: '姓名不能为空！', showCancel: false});
        return false;
      }
      if(this.formObj.phone.length < 1){
        wx.showModal({title: '提示', content: '电话不能为空！', showCancel: false});
        return false;
      }
      if(this.formObj.reason.length < 1){
        wx.showModal({title: '提示', content: '注册原因不能为空！', showCancel: false});
        return false;
      }
      return true;
    },
    // 点击注册按钮
    submitRegisterMessage () {
      if(this.inputCheck() == false)return;
      var sendUrl = '/wx/user/add.do';
      var sendData = {
        nickName: this.nickName,
        name: this.formObj.name,
        gender: this.formObj.sex,
        phone: this.formObj.phone,
        reason: this.formObj.reason,
        iv: this.$store.state.encryptedObj.iv,
        encryptedData: this.$store.state.encryptedObj.encryptedData
      };
      this.$store.commit('updateUserInfo', sendData);      // 本地储存用户基础信息
      if(this.$store.state.userAuditStatus == 3){//用户注册被拒绝了，这里他再次提交
        sendUrl = '/wx/user/update.do';
        sendData = {
          name: this.formObj.name,
          gender: this.formObj.sex,
          phone: this.formObj.phone,
          reason: this.formObj.reason
        };
      }
      console.log(sendData);
      this.$http({
        url: sendUrl,
        method: 'post',
        data: sendData,
        success: res => {
          wx.hideLoading();
          wx.showModal({
            title: '审核',
            content: '您的信息已提交到管理员审核，请耐心等候！',
            showCancel: false,
            success: (res) => {
              console.log(res);
              if (res.confirm) {
                // 全局记录用户审核状态
                this.$store.commit('updateUserAuditStatus', 2);
                // 返回进入到授权页的前一页
                wx.navigateBack();
              }
            }
          });
        }
      });
    }
  }
}
</script>

<style lang=scss>
  
</style>
