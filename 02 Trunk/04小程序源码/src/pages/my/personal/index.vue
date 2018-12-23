<template>
  <div>
    <div class="zan-panel">
      <div class="zan-cell">
        <div class="zan-cell__bd">姓名</div>
        <div class="zan-cell__ft">{{userInfoState.nickName}}</div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__bd">权限</div>
        <div class="zan-cell__ft">{{userRoleNames}}</div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__bd">性别</div>
        <div class="zan-cell__ft">{{userInfoState.gender == 0 ? '女' : '男'}}</div>
      </div>
      <div class="zan-cell">
        <div class="zan-cell__bd">电话</div>
        <div class="zan-cell__ft">{{userInfoState.phone}}</div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      userRoleNameList: [],
      userRoleNames: ''
    }
  },

  mounted () {
    this.getUserRoleList();
    this.userRoleNames = this.userRoleNameList.join(',');
  },
  methods: {
    /**
     * 根据全局的用户角色key获得用户的角色名称
     */
    getUserRoleList () {
      console.log(this.userRoleListState);
      for (let i = 0, len = this.userRoleListState.length; i < len; i ++) {
        this.userRoleNameList.push(this.$tool.getRoleNameByRoleKey(this.$config.$DATA.ROLE_KEY_MAP, this.userRoleListState[i]));
      }
    }
  },
  computed: {
    userRoleListState () {
      return this.$store.state.userRoleList;
    },
    userInfoState () {
      return this.$store.state.userInfo;
    }
  }
}
</script>

<style lang=scss>
  
</style>
