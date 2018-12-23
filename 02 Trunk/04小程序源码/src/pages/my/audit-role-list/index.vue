<template>
  <div>
    <div class="zan-panel">
      <button @click="ensureOperate" :type="operateKey == 'add' ? 'primary' : 'warn'">{{operateKey === 'add' ? '授权' : '取消授权'}}</button>
    </div>
    <div class="zan-panel">
      <checkbox-group @change="selecteUserChange">
        <div v-for="(item, index) of userListForSelect" :key="index" class="zan-cell">
          <div class="zan-cell__icon zan-icon zan-icon-contact" style="color:#666;"></div>
          <div class="zan-cell__bd">{{item.nickName}}</div>
          <div class="zan-cell__ft">
            <checkbox  :value="item.uid"/>
          </div>
        </div>
      </checkbox-group>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      // 当前操作的权限
      roleKey: '',
      roleName: '',
      // 增加还是删除
      operateKey: '',
      // 待操作人员
      userListForSelect: '',
      // 选中的人员
      selectedUsers: []
    }
  },
  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.roleKey = paramsObj.rolekey;
    this.operateKey = paramsObj.operatekey;
    this.roleName = this.$tool.getRoleNameByRoleKey(this.$config.$DATA.ROLE_KEY_MAP, this.roleKey);
    this.updateTitle();
    this.getMulList();
  },
  methods: {
    /**
     * 更新页面导航文案
     */
    updateTitle () {
      let navTitle = this.operateKey === 'add' ? `授权${this.roleName}` : `取消${this.roleName}授权`
      wx.setNavigationBarTitle({
        title: navTitle
      })
    },
    /**
     * 获取当前页面的可多选的人员列表
     */
    getMulList () {
      let API = this.operateKey === 'add' ? '/wx/user/getUnUsersByRole.do' : '/wx/user/getUsersByRole.do'
      this.$http({
        url: API,
        method: 'get',
        data: {
          roleKey: this.roleKey
        },
        success: res => {
          this.userListForSelect = res.data;
        }
      });
    },
    /**
     * 多选框选中事件
     */
    selecteUserChange (e) {
      this.selectedUsers = e.target.value;
    },
    /**
     * 点击确认授权或者确认取消授权按钮
     */
    ensureOperate () {
      console.log(this.selectedUsers);
      let API = this.operateKey === 'add' ? '/wx/role/batchAddUserRole.do' : '/wx/role/batchdelUserRole.do';
      let sendData = {
        roleKey: this.roleKey,
        uids: this.selectedUsers
      };
      this.$http({
        url: API,
        method: 'post',
        data: sendData,
        success: res => {
          this.getMulList();
          wx.showToast({
            title: '操作成功！',
            duration: 2000
          });
        }
      });
    }
  }
}
</script>

<style lang=scss>
  
</style>
