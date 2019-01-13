/**
 * 检查是否授权userinfo
 */
function checkUserInfoAuth () {
  return new Promise((resolve, reject) => {
    // let auditUserinfoStatus;
    wx.getSetting({
      success: res => {
        console.log(`用户授权信息${JSON.stringify(res)}`);
        if (!!res.authSetting['scope.userInfo']) {// 授权
          resolve();
        } else {
          reject();
        }
      }
    })
  });
}
/**
 * 导航到授权页
 */
function goToAuthPage () {
  wx.navigateTo({
    url: "../authorized/main"
  })
}
/**
 * 根据roleKey获取roleName
 */
function getRoleNameByRoleKey (dataMap, key)  {
  let res;
  console.log(dataMap, key);
  for (let i = 0, len = dataMap.length; i< len; i++) {
    if (dataMap[i].roleKey === key) {
      res = dataMap[i].roleName;
    }
  }
  return res;
}
/*获取当前页url*/
function getCurrentPageUrl(){
  var pages = getCurrentPages()    //获取加载的页面
  var currentPage = pages[pages.length-1]    //获取当前页面的对象
  var url = currentPage.route    //当前页面url
  return url
}

/*获取当前页带参数的url*/
function getCurrentPageUrlWithArgs(){
  var pages = getCurrentPages()    //获取加载的页面
  var currentPage = pages[pages.length-1]    //获取当前页面的对象
  var url = currentPage.route    //当前页面url
  var options = currentPage.options    //如果要获取url中所带的参数可以查看options
  
  //拼接url的参数
  var urlWithArgs = url + '?'
  for(var key in options){
      var value = options[key]
      urlWithArgs += key + '=' + value + '&'
  }
  urlWithArgs = urlWithArgs.substring(0, urlWithArgs.length-1)
  
  return urlWithArgs
}
/*获取当前页带参数的url*/
function getOptions(){
  var pages = getCurrentPages()    //获取加载的页面
  var currentPage = pages[pages.length-1]    //获取当前页面的对象
  var options = currentPage.options    //如果要获取url中所带的参数可以查看options
  
  return options
}
const $tool = {
  checkUserInfoAuth,
  goToAuthPage,
  getRoleNameByRoleKey,
  getCurrentPageUrl,
  getCurrentPageUrlWithArgs,
  getOptions
}
export {$tool}