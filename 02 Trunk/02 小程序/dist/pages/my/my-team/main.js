require("../../../common/manifest.js");
require("../../../common/vendor.js");
global.webpackJsonp([2],{

/***/ 176:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(177);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 177:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(179);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_4206b170_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(184);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(178)
}
var normalizeComponent = __webpack_require__(1)
/* script */

/* template */

/* styles */
var __vue_styles__ = injectStyle
/* scopeId */
var __vue_scopeId__ = null
/* moduleIdentifier (server only) */
var __vue_module_identifier__ = null
var Component = normalizeComponent(
  __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__["a" /* default */],
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_4206b170_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\my\\my-team\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-4206b170", Component.options)
  } else {
    hotAPI.reload("data-v-4206b170", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 178:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 179:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__utils_index__ = __webpack_require__(9);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__components_card__ = __webpack_require__(13);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__components_zan_capsule__ = __webpack_require__(180);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//





/* harmony default export */ __webpack_exports__["a"] = ({
  components: {
    card: __WEBPACK_IMPORTED_MODULE_1__components_card__["a" /* default */],
    capsule: __WEBPACK_IMPORTED_MODULE_2__components_zan_capsule__["a" /* default */]
  },

  data: function data() {
    return {
      userList: [],
      data: {
        leftText: '1折',
        rightText: '限购一份',
        color: '#38f',
        onclick: null
      }
    };
  },
  mounted: function mounted() {
    var _this = this;

    function formatUserList(userList) {
      // 为每一个用户添加信息展示状态
      for (var i = 0, len = userList.length; i < len; i++) {
        userList[i].showContent = false;
      }
      return userList;
    }
    var res1 = [{
      "uid": 10,
      "nickName": "龚明辉",
      "roles": [{
        "remark": "",
        "roleKey": "admin",
        "roleName": "管理员",
        "roleType": 0
      }, {
        "remark": "",
        "roleKey": "writer",
        "roleName": "录入人员",
        "roleType": 0
      }],
      "createTime": "20180927 23:34:29",
      "userStatus": "待审核"
    }, {
      "uid": 11,
      "nickName": "薛丽飞",
      "roles": [{
        "remark": "",
        "roleKey": "writer",
        "roleName": "录入人员",
        "roleType": 0
      }],
      "createTime": "20180927 23:34:29",
      "userStatus": "被拒绝"
    }];
    // mockdata
    this.userList = formatUserList(res1);
    this.$http({
      url: '/wx/user/queryUsers.do',
      method: 'get',
      success: function success(res) {
        _this.userList = formatUserList(res.data);
        console.log(_this.userList);
      }
    });
  },

  methods: {
    /**
     * 展开用户信息，只有当用户状态是待审核时候才能审核
     */
    expandUserMessage: function expandUserMessage(index) {
      this.userList[index].showContent = !this.userList[index].showContent;
    },

    /**
     * 审核按钮通过或者不通过按钮点击
     */
    clickAuthBtn: function clickAuthBtn(index, res) {
      var _this2 = this;

      console.log(index, this.userList);
      var sendData = {
        uid: this.userList[index].uid,
        nickName: this.userList[index].nickName
      };
      if (res === 'pass') {
        sendData.status = 1;
        sendData.refuse = '';
      } else {
        sendData.status = 2;
        sendData.refuse = '';
      }
      this.$http({
        url: '/wx/user/changeUserStatus.do',
        method: 'get',
        data: sendData,
        success: function success(res) {
          _this2.userList[index].userStatus = '已审核';
          wx.hideLoading();
          wx.showToast({
            title: '操作成功'
          });
        }
      });
    }
  }
});

/***/ }),

/***/ 180:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_capsule_vue__ = __webpack_require__(182);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_5f62416b_hasScoped_true_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_capsule_vue__ = __webpack_require__(183);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(181)
}
var normalizeComponent = __webpack_require__(1)
/* script */

/* template */

/* styles */
var __vue_styles__ = injectStyle
/* scopeId */
var __vue_scopeId__ = "data-v-5f62416b"
/* moduleIdentifier (server only) */
var __vue_module_identifier__ = null
var Component = normalizeComponent(
  __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_capsule_vue__["a" /* default */],
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_5f62416b_hasScoped_true_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_capsule_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\components\\zan\\capsule.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] capsule.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-5f62416b", Component.options)
  } else {
    hotAPI.reload("data-v-5f62416b", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 181:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 182:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
//
//
//
//
//
//
//
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["a"] = ({
  props: {
    type: String,
    color: String,
    leftText: String,
    rightText: String,
    onclick: Function
  },
  computed: {
    computedClassStr: function computedClassStr() {
      return 'background: ' + this.color + '; border-color: ' + this.color;
    },
    getObject: function getObject() {
      return 'color: ' + this.color + '; border-color: ' + this.color;
    }
  },
  methods: {
    doClick: function doClick() {
      var a = this.onclick || function () {};
      a();
    }
  }
});

/***/ }),

/***/ 183:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-capsule",
    class: 'zan-capsule--' + _vm.type,
    attrs: {
      "eventid": '0'
    },
    on: {
      "click": _vm.doClick
    }
  }, [(_vm.color) ? _c('block', [_c('div', {
    staticClass: "zan-capsule__left",
    style: (_vm.computedClassStr)
  }, [_vm._v(_vm._s(_vm.leftText))]), _vm._v(" "), _c('div', {
    staticClass: "zan-capsule__right",
    style: (_vm.getObject)
  }, [_vm._v(_vm._s(_vm.rightText))])]) : _c('block', [_c('div', {
    staticClass: "zan-capsule__left"
  }, [_vm._v(_vm._s(_vm.leftText))]), _vm._v(" "), _c('div', {
    staticClass: "zan-capsule__right"
  }, [_vm._v(_vm._s(_vm.rightText))])])], 1)
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-hot-reload-api").rerender("data-v-5f62416b", esExports)
  }
}

/***/ }),

/***/ 184:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "userlist-wrap"
  }, [_vm._l((_vm.userList), function(item, index) {
    return [_c('div', {
      key: index,
      staticClass: "userlist-item-wrap"
    }, [_c('div', {
      staticClass: "userlist-item clearfix",
      attrs: {
        "eventid": '0-' + index
      },
      on: {
        "click": function($event) {
          _vm.expandUserMessage(index)
        }
      }
    }, [_vm._v("\n        " + _vm._s(item.nickName) + "\n        "), (item.userStatus != '待审核') ? _c('div', {
      staticClass: "inline-block"
    }, [_vm._l((item.roles), function(citem, cindex) {
      return [_c('span', {
        key: cindex,
        staticClass: "user_status_icon",
        class: citem.roleKey
      }, [_vm._v(_vm._s(citem.roleName))])]
    })], 2) : _c('div', {
      staticClass: "fr"
    }, [_c('span', {
      staticClass: "user_status_icon for_audit"
    }, [_vm._v("待审核")])])]), _vm._v(" "), _c('div', {
      directives: [{
        name: "show",
        rawName: "v-show",
        value: (item.userStatus === '待审核' && item.showContent),
        expression: "item.userStatus === '待审核' && item.showContent"
      }],
      staticClass: "collapse-body"
    }, [_c('div', {
      staticClass: "content-item"
    }, [_c('span', {
      staticClass: "item-title align-right inline-block"
    }, [_vm._v("性别")]), _vm._v("：" + _vm._s('男') + "\n        ")]), _vm._v(" "), _c('div', {
      staticClass: "content-item"
    }, [_c('span', {
      staticClass: "item-title align-right inline-block"
    }, [_vm._v("电话")]), _vm._v("：" + _vm._s('18126450043') + "\n        ")]), _vm._v(" "), _c('div', {
      staticClass: "content-item"
    }, [_c('span', {
      staticClass: "item-title align-right inline-block"
    }, [_vm._v("注册理由")]), _vm._v("：" + _vm._s('巴拉巴拉一大堆巴拉巴拉一大堆巴拉巴拉一大堆巴拉巴拉一大堆巴拉巴拉一大堆') + "\n        ")]), _vm._v(" "), _c('div', {
      staticClass: "content-item btn-wrap align-center"
    }, [_c('button', {
      staticClass: "lr-mg-10",
      attrs: {
        "type": "primary",
        "size": "mini",
        "eventid": '1-' + index
      },
      on: {
        "click": function($event) {
          _vm.clickAuthBtn(index, 'pass')
        }
      }
    }, [_vm._v("审核通过")]), _vm._v(" "), _c('button', {
      staticClass: "lr-mg-10",
      attrs: {
        "type": "warn",
        "size": "mini",
        "eventid": '2-' + index
      },
      on: {
        "click": function($event) {
          _vm.clickAuthBtn(index, 'refuse')
        }
      }
    }, [_vm._v("不能通过")])], 1)])])]
  })], 2)
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-hot-reload-api").rerender("data-v-4206b170", esExports)
  }
}

/***/ })

},[176]);
//# sourceMappingURL=main.js.map