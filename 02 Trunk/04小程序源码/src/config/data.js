import {CITY} from './city.js';
export const CAMPER_RELATIVE_LIMIT = [
  {
    id: 1020,
    name: '全部权限'
  },
  {
    id: 1023,
    name: '全部权限' // 主账户全部权限
  },
  {
    id: 4,
    name: '仅为学员选课 '
  }
];

export const CAMPER_ALL_LIST = [
  {
    name: '为学员选课购课',
    status: true
  },
  {
    name: '查看学员班级动态',
    status: true
  },
  {
    name: '查看学员户外营期动态',
    status: true
  },
  {
    name: '查看学员相册',
    status: true
  },
  {
    name: '查看学员教学反馈',
    status: true
  },
  {
    name: '查看学员班级详情',
    status: true
  },
  {
    name: '查看学员考勤情况',
    status: true
  },
  {
    name: '接收学员教学相关通知',
    status: true
  },
  {
    name: '编辑学员资料',
    status: false
  },
  {
    name: '管理学员亲属',
    status: false
  }
];

export const SEX_ARR = [
  {
    name: '男',
    id: 1
  },
  {
    name: '女',
    id: 2
  }
];

export const ACCOUNT_RELATIVE_ARR = [
  {
    name: '主账号',
    id: 1
  },
  {
    name: '亲属关联',
    id: 2
  }
];

export const CAMPER_RELATIVE_ARR = [
  '爸爸', '妈妈', '爷爷', '奶奶', '外公', '外婆', '本人', '其他'
];

export const CAMPER_CARD = [
  {
    id: 0,
    name: '身份证'
  },
  {
    id: 2,
    name: '护照'
  },
  {
    id: 7,
    name: '港澳通行证'
  },
  {
    id: 8,
    name: '台湾通行证'
  }
];

export const CAMPER_HEIGHT = () => {
  let HeightArr = [];
  for (let i = 79; i <= 201; i++) {
    let obj = {
      id: i,
      name: i + 'CM'
    };
    if (i === 79) {
      obj.name = '80CM以下';
    };
    if (i === 201) {
      obj.name = '200CM以上';
    };
    HeightArr.push(obj);
  };
  return HeightArr;
};

export const CAMPER_WEIGHT = () => {
  let WeightArr = [];
  for (let i = 9; i <= 100; i++) {
    let obj = {
      id: i,
      name: i + 'KG'
    };
    if (i === 9) {
      obj.name = '10KG以下';
    };
    if (i === 100) {
      obj.name = '99KG以上';
    };
    WeightArr.push(obj);
  };
  return WeightArr;
};

export const CAMPER_SHOOES = () => {
  let ShooesArr = [];
  for (let i = 155; i <= 480; i += 5) {
    let obj = {
      id: i,
      name: i / 10 + '码'
    };
    ShooesArr.push(obj);
  };
  return ShooesArr;
};

// 支付主体状态
export const PAY_STATUS = [
  {
    id: -1,
    name: '已禁用'
  },
  {
    id: 0,
    name: '未开通'
  },
  {
    id: 1,
    name: '开通中'
  },
  {
    id: 2,
    name: '已开通'
  }
];

// 合作状态
export const PARTNER_STATUS = [
  {
    id: 1,
    name: '禁用'
  },
  {
    id: 0,
    name: '启用'
  }
];

// 继承来源
export const PARTNER_SOURCE = [
  {
    id: 1,
    name: '继承父团队'
  },
  {
    id: 0,
    name: '自行建立'
  }
];

// 合作权限
export const PARTNER_LIMIT = [
  {
    id: 1,
    name: '课程'
  },
  {
    id: 2,
    name: '校区'
  }/* ,
  {
    id: 3,
    name: '渠道'
  } */
];

// 课程团队业务类型
export const COURSE_TEAM_TYPE = [
  {
    id: 1,
    name: '户外'
  },
  {
    id: 2,
    name: '社区'
  },
  {
    id: 3,
    name: '全日制'
  }
];

// 订单状态
export const ORDER_STATUS = [
  {
    id: 0,
    name: '待付款'
  },
  {
    id: 20,
    name: '已付款'
  },
  {
    id: 60,
    name: '退款申请中'
  },
  {
    id: 30,
    name: '退款中'
  },
  {
    id: 40,
    name: '部分退款'
  },
  {
    id: 50,
    name: '已退款'
  },
  {
    id: 10,
    name: '已关闭'
  }
];

export const SPECS_UNIT = [
  {
    id: 1,
    name: '小时'
  },
  {
    id: 2,
    name: '天'
  },
  {
    id: 3,
    name: '月'
  }
];

export const COURSE_TYPE = [
  {
    id: 1,
    name: '全日制课程'
  },
  {
    id: 2,
    name: '课程包'
  },
  {
    id: 3,
    name: '常规兴趣班'
  },
  {
    id: 4,
    name: '单次课程'
  }
];

export const CAMPER_TYPE = [
  {
    name: '小孩',
    id: 1
  },
  {
    name: '成人',
    id: 2
  }
];

export const COST_TYPE = [
  {
    name: '学费',
    id: 1
  },
  {
    name: '体验课',
    id: 2
  },
  {
    name: '杂费',
    id: 3
  },
  {
    name: '餐费',
    id: 4
  },
  {
    name: '交通费',
    id: 5
  },
  {
    name: '一次性杂费',
    id: 6
  }
];

export const COUPON_TYPE = [
  {
    name: '代金券',
    id: 1
  },
  {
    name: '折扣券',
    id: 2
  }
];

export const validateTypeArr = [
  {
    name: '固定期限',
    id: 2
  },
  {
    name: '自领取起生效',
    id: 1
  }
];

export const COUPON_STATUS = [{
  name: '未使用',
  id: '0'
},
{
  name: '已使用',
  id: '1'
},
{
  name: '已过期',
  id: '2'
}];

export const ACTIVITY_STATUS = [{
  name: '未开始',
  id: '1'
},
{
  name: '进行中',
  id: '2'
},
{
  name: '已结束',
  id: '3'
}];

/*
  101 : 全日制营地-幼儿园
  102 : 全日制营地-430
  103 : 全日制营地-课后辅导
  104 : 全日制学校-小学
  105 : 全日制学校-初中
  106 : 全日制学校-高中
  201 : 课程包
  301 : 常规兴趣班
  401 : 单次课程
 */
export const $DATA = {
  ACCOUNT_RELATIVE_ARR,
  CAMPER_CARD,
  CAMPER_TYPE,
  SEX_ARR,
  CAMPER_RELATIVE_LIMIT,
  CAMPER_ALL_LIST,
  CAMPER_RELATIVE_ARR,
  CAMPER_HEIGHT: CAMPER_HEIGHT(),
  CAMPER_WEIGHT: CAMPER_WEIGHT(),
  CAMPER_SHOOES: CAMPER_SHOOES(),
  CITY,
  PAY_STATUS,
  PARTNER_STATUS,
  PARTNER_SOURCE,
  PARTNER_LIMIT,
  COURSE_TEAM_TYPE,
  ORDER_STATUS,
  SPECS_UNIT,
  COURSE_TYPE,
  COST_TYPE,
  COUPON_TYPE,
  COUPON_STATUS,
  ACTIVITY_STATUS,
  validateTypeArr
};
