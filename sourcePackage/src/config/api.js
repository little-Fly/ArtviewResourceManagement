// const ORIGIN = window.location.origin;

// 开发环境的API前缀
let API_BASE_URL = 'https://www.hwyst.net/ttzy';

// API 设置，请使用大写+下划线的的写法，例如：NAMES_LIKE_THIS
export const API = {
  PUBLIC: {
    GET_LOG_LIST: `${API_BASE_URL}/api/admin/log/get-list`,
  },
  // 订单相关接口
  ORDER: {
    GET_ORDER_LIST: `${API_BASE_URL}/api/admin/sale/order/get_list`,  // 订单列表
  },
  COURSE: {
    // 枚举
    GET_ENUMS: '/api/admin/sale/course_template/get_enums', // 枚举接口
  },
  CAMPER: {
    GET_LIST: `${API_BASE_URL}/api/admin/student/get-list` // 获取学员列表
  },
  CLIENT: {
    GET_OR_CREATE: `${API_BASE_URL}/api/admin/user/get_or_create` // 创建或获取用户
  },
  BILL: {
    BILL_ENUMS: `${API_BASE_URL}/api/admin/sale/bill/get_enums`
  },
  COUPON: {
    GET_COUPON_ENUMS: `${API_BASE_URL}/api/admin/sale/coupon/get_enums`
  },
  CONTRACT: {
    GET_CONTRACT_LIST: `${API_BASE_URL}/api/admin/sale/contract/course/get_list`
  }
};

export const BASE_URL = API_BASE_URL;
