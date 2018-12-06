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
          sexName: '男'
        },
        {
          sex: 1,
          sexName: '女'
        }
      ],
      selectSexIndex: 0,
      loadingStatus: false
    }
  },
  created () {
  },
  methods: {
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
      this.formObj[componentId] = target.value;
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
    submitRegisterMessage () {
      this.loadingStatus = true;
      this.$http({
        url: '/wx/user/add.do',
        method: 'post',
        data: Object.assign({}, {
          nickName: this.formObj.name,
          sex: this.formObj.sex,
          phone: this.formObj.phone,
          answer: this.formObj.reason,
        }, {userID: this.$store.state.userInfo.uid}),
        success: res => {
          console.log(res);
          this.loadingStatus = false;
        },
        fail:() => {
          this.loadingStatus = false;
        }
      });
    }
  }
}
</script>

<style lang=scss>
  
</style>
