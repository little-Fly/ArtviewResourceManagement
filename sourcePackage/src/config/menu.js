export const GET_MENU_CONFIG = (power, companyType) => {
  // 课程菜单对象
  let courseMenuObj = {
    title: '课程管理',
    icon: 'course-manageme',
    index: 'course',
    children: [
    ]
  };
  // 课程菜单子数组
  let courseChildMenuArr = [
    {
      title: '课程模板',
      index: '/course/template/list',
      limit: 2
    },
    {
      title: '销售课程',
      index: '/course/sale/list',
      limit: 32
    }/*,
      {
        title: '查看时段',
        index: '/course/timeIntervalList'
      },
      {
        title: '查看学员',
        index: ''
      },
      {
        title: '产品系列',
        index: '/course/courseProductList'
      } */
  ];
  //  订单菜单对象
  let orderMenuObj = {
    title: '订单管理',
    icon: 'dingdan',
    index: 'order',
    children: [
    ]
  };
  // 订单菜单子数组
  let orderChildMenuArr = [
    {
      title: '全部订单列表',
      index: '/order/allOrderList',
      limit: 2,
      type: ''
    },
    {
      title: '课程团队订单',
      index: '/order/courseTeamOrder',
      limit: 2,
      type: '' // 标志团队 companyType
    },
    {
      title: '校区团队订单',
      index: '/order/campusTeamOrder',
      limit: 2,
      type: '' // companyType
    },
    {
      title: '渠道团队订单',
      index: '/order/channelTeamOrder',
      limit: 2,
      type: '' // companyType
    },
    {
      title: '创建订单',
      index: '/order/CreateOrder',
      limit: 4,
      type: ''
    }/* ,
      {
        title: '非主营业务订单',
        index: '/order/NonBusinessList'
      } */
  ];

  // 优惠券菜单对象
  let couponMenuObj = {
    title: '优惠券管理',
    icon: 'dingdan',
    index: 'coupon',
    children: [
    ]
  };

  let couponChildMenuArr = [
    {
      title: '优惠券',
      index: '/coupon/couponCouponList',
      limit: 2
    },
    {
      title: '发券活动',
      index: '/coupon/activityActivityList',
      limit: 2
    }
  ];

  // 退款菜单对象
  let refundMenuObj = {
    title: '退款管理',
    icon: 'tuikuan',
    index: 'refund',
    children: [
    ]
  };
  let refundChildMenuArr = [
    {
      title: '退款列表',
      index: '/refund/list',
      limit: 2
    }
  ];
  // 账单菜单对象
  let billMenuObj = {
    title: '账单管理',
    icon: 'zhangdan',
    index: 'bill',
    children: [
    ]
  };
  let billChildMenuArr = [
    {
      title: '账单',
      index: '/bill/list',
      limit: 2
    }
  ];
  // 协议菜单对象
  let contractMenuObj = {
    title: '合同管理',
    icon: 'hetong',
    index: 'contract',
    children: []
  };
  let contractChildMenuArr = [{
    title: '课程协议列表',
    index: '/contract/list',
    limit: 2
  }];
  let totalMenu = []; // 整体菜单数组
  // 权限控制
  if (power) {
    // 课程权限处理
    let course = power.course;
    if ((course & 1) > 0) {
      // 课程子菜单栏权限控制
      courseChildMenuArr.forEach(item => {
        let limit = item.limit;
        if ((course & limit) > 0) {
          courseMenuObj.children.push(item);
        }
      });
      totalMenu.push(courseMenuObj);
    };
    // 订单权限处理
    let order = power.order;
    if ((order & 1) > 0) {
      // 订单子菜单权限控制
      orderChildMenuArr.forEach(item => {
        let type = item.type;
        let limit = item.limit;
        if (type === '') {
          if ((order & limit) > 0) {
            orderMenuObj.children.push(item);
          }
        } else {
          if (companyType && ((order & 2) > 0) && ((companyType & limit) > 0)) {
            orderMenuObj.children.push(item);
          }
        }
      });
      totalMenu.push(orderMenuObj);
    };

    // 优惠券权限
    let coupon = power.coupon;
    if ((coupon & 2) > 0 || (coupon & 4) > 0) {
      couponChildMenuArr.forEach(item => {
        let limit = item.limit;
        if ((coupon & limit) > 0 || (coupon & 4) > 0) {
          couponMenuObj.children.push(item);
        }
      });
      totalMenu.push(couponMenuObj);
    }

    // 退款权限
    let refund = power.refund;
    if ((refund & 1) > 0) {
      refundChildMenuArr.forEach(item => {
        let limit = item.limit;
        if ((refund & limit) > 0) {
          refundMenuObj.children.push(item);
        }
      });
      totalMenu.push(refundMenuObj);
    }

    // 账单权限
    let bill = power.bill;
    if ((bill & 1) > 0) {
      billChildMenuArr.forEach(item => {
        let limit = item.limit;
        if ((bill & limit) > 0) {
          billMenuObj.children.push(item);
        }
      });
      totalMenu.push(billMenuObj);
    }
    // 课程协议
    let contract = power.contract;
    contractChildMenuArr.forEach(item => {
      let limit = item.limit;
      if ((contract & limit) > 0) {
        contractMenuObj.children.push(item);
      }
    });
    totalMenu.push(contractMenuObj);
  }
  return totalMenu;
};

export const $menu = { GET_MENU_CONFIG };
