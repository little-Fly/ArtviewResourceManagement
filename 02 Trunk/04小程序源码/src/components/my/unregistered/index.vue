<template>
  <div class="full-width">
    <!-- message -->
    <div class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigateToMessage">
        <div class="zan-cell__icon zan-icon zan-icon-chat" style="color:#666;"></div>
        <div class="zan-cell__bd">我的消息</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
    <!-- team -->
    <div class="zan-panel">
      <div class="zan-cell zan-cell--access" @click="navigateToMyTeam">
        <div class="zan-cell__icon zan-icon zan-icon-setting" style="color:#666;"></div>
        <div class="zan-cell__bd">注册申请</div>
        <div class="zan-cell__ft"></div>
      </div>
    </div>
  </div>
</template>