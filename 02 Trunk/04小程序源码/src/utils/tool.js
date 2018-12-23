function checkUserInfoAuth () {
  let auditUserinfoStatus;
  new Promise().then()
  wx.getSetting({
    success: res => {
      debugger;
      if (!!res.authSetting['scope.userInfo']) {// 未授权
        auditUserinfoStatus = false;
      } else {
        auditUserinfoStatus = true;
      }
    }
  })
  return auditUserinfoStatus;
}
function goToAuthPage () {
  wx.navigateTo({
    url: "../authorized/main"
  })
}
const $tool = {
  checkUserInfoAuth,
  goToAuthPage
}
export {$tool}