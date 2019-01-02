<template>
  <div>
    <div class="zan-panel zan-cell--access">
      <div class="zan-cell" @click='goToAuthList("add")'>
        <div class="zan-cell__icon zan-icon zan-icon-description" style="color:#4b0;"></div>
        <div class="zan-cell__bd">授权新{{roleName}}</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <div class="zan-panel zan-cell--access" @click='goToAuthList("delete")'>
      <div class="zan-cell">
        <div class="zan-cell__icon zan-icon zan-icon-description" style="color:#f85;"></div>
        <div class="zan-cell__bd">取消{{roleName}}授权</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <div class="zan-panel">
      <div v-for="(item, index) of userListForSelect" :key="index" class="zan-cell">
        <div class="zan-cell__icon zan-icon zan-icon-contact" style="color:#666;"></div>
        <div class="zan-cell__bd">{{item.nickName}}</div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      roleKey: '',
      roleName: '',
      userListForSelect: []
    }
  },
  onShow () {
    this.getMulList();
  },
  mounted () {
    let paramsObj = this.$tool.getOptions();
    this.roleKey = paramsObj.rolekey;
    this.roleName = this.$tool.getRoleNameByRoleKey(this.$config.$DATA.ROLE_KEY_MAP, this.roleKey);
    this.getMulList();
    wx.setNavigationBarTitle({
      title: `${this.roleName}`
    })
  },
  methods: {
    /**
     * 去具体的授权页
     */
    goToAuthList (status) {
      wx.navigateTo({
        url: `../audit-role-list/main?rolekey=${this.roleKey}&operatekey=${status}`
      })
    },
    /**
     * 获取当前页面的可多选的人员列表
     */
    getMulList () {
      let API = '/wx/user/getUnUsersByRole.do';
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
    }
  }
}
</script>

<style lang=scss>
  
</style>
