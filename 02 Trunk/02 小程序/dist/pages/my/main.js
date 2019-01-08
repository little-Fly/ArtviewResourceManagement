require("../../common/manifest.js");
require("../../common/vendor.js");
global.webpackJsonp([7],{

/***/ 55:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(56);



const app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/* harmony default export */ __webpack_exports__["default"] = ({
    // 这个字段走 页面配置.json
    config: {
        window: {
            navigationBarBackgroundColor: '#40586d',
            navigationBarTextStyle: 'white',
            navigationBarTitleText: '我的',
            backgroundColor: '#f8f9fb',
            backgroundTextStyle: 'dark',
            enablePullDownRefresh: false
        }
    }
});

/***/ }),

/***/ 56:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(58);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_65e1681a_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(59);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(57)
}
var normalizeComponent = __webpack_require__(0)
/* script */

/* template */

/* styles */
var __vue_styles__ = injectStyle
/* scopeId */
var __vue_scopeId__ = null
/* moduleIdentifier (server only) */
var __vue_module_identifier__ = null
var Component = normalizeComponent(
  __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_index_vue__["a" /* default */],
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_65e1681a_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src/pages/my/index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-65e1681a", Component.options)
  } else {
    hotAPI.reload("data-v-65e1681a", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 57:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 58:
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

// import card from '@/components/card'
// import Unregistered from '@/components/my/unregistered'
/* harmony default export */ __webpack_exports__["a"] = ({
  data() {
    return {
      motto: 'Hello World',
      userInfo: {}
    };
  },
  methods: {
    navigeteClick(pageKey) {
      // 判断用户审核状态
      if (this.userAuditStatusState === 2) {
        // 审核中
        // 点击我的消息，个人信息注册申请时弹出个人信息正在审核中
        if (['myMessage', 'register'].indexOf(pageKey) > -1) {
          wx.showModal({
            content: '等待管理员审核中...',
            showCancel: false
          });
          return;
        }
      }
      // 根据用户点击项，导航到不同页面
      switch (pageKey) {
        case 'myMessage':
          wx.navigateTo({
            url: "./my-message/main"
          });
          break;
        case 'register':
          // 由于微信用户要与PC用户互通，这里注册需要得到用户授权获取高级信息
          this.$tool.checkUserInfoAuth().then(() => {
            // 用户未授权，进到授权页
            console.log('已授权');
            wx.navigateTo({
              url: "./register/main"
            });
          }, () => {
            console.log('未授权');
            this.$tool.goToAuthPage();
          });
          break;
        case 'auditAdmin':
          wx.navigateTo({
            url: "./audit-role/main?rolekey=admin"
          });
          break;
        case 'auditChecker':
          wx.navigateTo({
            url: "./audit-role/main?rolekey=checker"
          });
          break;
        case 'auditWriter':
          wx.navigateTo({
            url: "./audit-role/main?rolekey=writer"
          });
          break;
        case 'auditResourse':
          wx.navigateTo({
            url: "./audit-resource/main"
          });
          break;
        case 'myTeam':
          wx.navigateTo({
            url: "./my-team/main"
          });
          break;
        case 'log':
          wx.navigateTo({
            url: "./logs/main"
          });
          break;
        case 'personal':
          wx.navigateTo({
            url: "./personal/main"
          });
          break;
        case 'about':
          wx.navigateTo({
            url: "./about/main"
          });
          break;
      }
    },
    navigateToAuditRegist() {
      wx.navigateTo({
        url: "./auditRegistration/main"
      });
    },
    getUserInfo() {
      // // 调用登录接口
      // wx.login({
      //   success: () => {
      //     wx.getUserInfo({
      //       success: (res) => {
      //         this.userInfo = res.userInfo
      //       }
      //     })
      //   }
      // })
    },
    clickHandle(msg, ev) {
      console.log('clickHandle:', msg, ev);
    },
    ensureBtnClick() {
      console.log(1111);
    }
  },

  mounted() {
    console.log(this.$store.state);
    // 调用应用实例的方法获取全局数据
    // this.getUserInfo()
  },
  computed: {
    userAuditStatusState() {
      return this.$store.state.userAuditStatus;
    },
    /**
     * 计算得到的用户权限树，每个权限的计算值
     */
    myMessages() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.myMessages;
    },
    register() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.register;
    },
    userManagement() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.userManagement;
    },
    enpowerAdmins() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.enpowerAdmins;
    },
    logPage() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.logPage;
    },
    powerForInputRes() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.powerForInputRes;
    },
    powerForAuditRes() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.powerForAuditRes;
    },
    auditResourse() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.auditResourse;
    },
    /* inputResourse () {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.inputResourse;
    }, */
    personalPage() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.personalPage;
    },
    about() {
      return this.$store.state.accessTree.my && this.$store.state.accessTree.my.about;
    }
  }
});

/***/ }),

/***/ 59:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "container",
    attrs: {
      "eventid": '10'
    },
    on: {
      "click": function($event) {
        _vm.clickHandle('test click', $event)
      }
    }
  }, [(_vm.myMessages) ? _c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '0'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('myMessage')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-chat",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("我的消息")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })])]) : _vm._e(), _vm._v(" "), _c('div', {
    staticClass: "zan-panel"
  }, [(_vm.register) ? _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '1'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('register')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-setting",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("注册申请")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })]) : _vm._e(), _vm._v(" "), (_vm.userManagement) ? _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '2'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('myTeam')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-shop",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("用户管理")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })]) : _vm._e(), _vm._v(" "), (_vm.enpowerAdmins) ? _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '3'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('auditAdmin')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-add-o",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("管理员授权")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })]) : _vm._e(), _vm._v(" "), (_vm.powerForInputRes) ? _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '4'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('auditWriter')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-add-o",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("权限管理（资源录入）")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })]) : _vm._e(), _vm._v(" "), (_vm.powerForAuditRes) ? _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '5'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('auditChecker')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-add-o",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("权限管理（资源审核）")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })]) : _vm._e(), _vm._v(" "), (_vm.auditResourse) ? _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '6'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('auditResourse')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-edit",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("审核资源")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })]) : _vm._e(), _vm._v(" "), (_vm.logPage) ? _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '7'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('log')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-records",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("查看日志")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })]) : _vm._e()]), _vm._v(" "), (_vm.personalPage) ? _c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '8'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('personal')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-contact",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("个人信息")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })])]) : _vm._e(), _vm._v(" "), (_vm.about) ? _c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '9'
    },
    on: {
      "click": function($event) {
        _vm.navigeteClick('about')
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-setting",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("关于天天资源")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })])]) : _vm._e()])
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-65e1681a", esExports)
  }
}

/***/ })

},[55]);
//# sourceMappingURL=main.js.map