/**
 * 统一账户系统地址获取
 * @returns {string}
 */
export const getSystemAddress = () => {
  let accountUrl = '';
  let origin = window.location.origin;
  if (/192.168|localhost|test/.test(origin)) {
    accountUrl = 'https://test-admin.meishakeji.com/';
  } else if (/pre/.test(origin)) {
    accountUrl = 'https://pre-admin.meishakeji.com/';
  } else {
    accountUrl = 'https://admin.meishakeji.com/';
  };
  return accountUrl;
};

/**
 * 响应式户外管理后台地址获取
 * @returns {string}
 */
export const getResCampusSystemAddress = () => {
  let accountUrl = '';
  let origin = window.location.origin;
  if (/192.168|localhost|test/.test(origin)) {
    accountUrl = '//test-res-campsadmin.meishakeji.com/';
  } else if (/pre/.test(origin)) {
    accountUrl = '//pre-res-campsadmin.meishakeji.com/';
  } else {
    accountUrl = '//res-campsadmin.meishakeji.com/';
  };
  return accountUrl;
};

/**
 * 测评报告地址获取
 * @returns {string}
 */
export const getEvaSystemAddress = () => {
  let accountUrl = '';
  let origin = window.location.origin;
  if (/192.168|localhost|test/.test(origin)) {
    accountUrl = '//test-evaluate.meishakeji.com/';
  } else if (/pre/.test(origin)) {
    accountUrl = '//pre-evaluate.meishakeji.com/';
  } else {
    accountUrl = '//evaluate.meishakeji.com/';
  };
  return accountUrl;
};

/**
 *课程详情地址获取
 * @returns {string}
 */
export const getEvaAdminSystemAddress = () => {
  let accountUrl = '';
  let origin = window.location.origin;
  if (/192.168|localhost|test/.test(origin)) {
    accountUrl = '//test-evaluateadmin.meishakeji.com/';
  } else if (/pre/.test(origin)) {
    accountUrl = '//pre-evaluateadmin.meishakeji.com/';
  } else {
    accountUrl = '//evaluateadmin.meishakeji.com/';
  };
  return accountUrl;
};

/**
 * 教务管理后台地址获取
 * @returns {string}
 */
export const getEduAdminSystemAddress = () => {
  let accountUrl = '';
  let origin = window.location.origin;
  if (/192.168|localhost|test/.test(origin)) {
    accountUrl = '//test-educationadmin.meishakeji.com/';
  } else if (/pre/.test(origin)) {
    accountUrl = '//pre-educationadmin.meishakeji.com/';
  } else {
    accountUrl = '//educationadmin.meishakeji.com/';
  };
  return accountUrl;
};

export const $SYSTEM = {
  getSystemAddress,
  getResCampusSystemAddress,
  getEvaSystemAddress,
  getEvaAdminSystemAddress,
  getEduAdminSystemAddress
};
