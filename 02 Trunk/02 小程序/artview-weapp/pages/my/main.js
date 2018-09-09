require("../../common/manifest.js");
require("../../common/vendor.js");
global.webpackJsonp([4],{

/***/ 48:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(49);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
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

/***/ 49:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(51);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_41a4ffe4_hasScoped_true_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(52);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(50)
}
var normalizeComponent = __webpack_require__(0)
/* script */

/* template */

/* styles */
var __vue_styles__ = injectStyle
/* scopeId */
var __vue_scopeId__ = "data-v-41a4ffe4"
/* moduleIdentifier (server only) */
var __vue_module_identifier__ = null
var Component = normalizeComponent(
  __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_index_vue__["a" /* default */],
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_41a4ffe4_hasScoped_true_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
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
    hotAPI.createRecord("data-v-41a4ffe4", Component.options)
  } else {
    hotAPI.reload("data-v-41a4ffe4", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 50:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 51:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__components_card__ = __webpack_require__(2);
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



/* harmony default export */ __webpack_exports__["a"] = ({
  data: function data() {
    return {
      motto: 'Hello World',
      userInfo: {}
    };
  },


  components: {
    card: __WEBPACK_IMPORTED_MODULE_0__components_card__["a" /* default */]
  },

  methods: {
    navigateToAuditResource: function navigateToAuditResource() {
      wx.navigateTo({
        url: "../audit-resource/main"
      });
    },
    navigateToMessage: function navigateToMessage() {
      wx.navigateTo({
        url: "../my-message/main"
      });
    },
    navigateToAbout: function navigateToAbout() {
      wx.navigateTo({
        url: "../about/main"
      });
    },
    navigateToPersonal: function navigateToPersonal() {
      wx.navigateTo({
        url: "../personal/main"
      });
    },
    navigateToAuditRegist: function navigateToAuditRegist() {
      wx.navigateTo({
        url: "../auditRegistration/main"
      });
    },
    navigateToMyTeam: function navigateToMyTeam() {
      console.log(1111);
      wx.navigateTo({
        url: "../my-team/main"
      });
    },
    navigateToLog: function navigateToLog() {
      var url = '../logs/main';
      wx.navigateTo({ url: url });
    },
    getUserInfo: function getUserInfo() {
      var _this = this;

      // 调用登录接口
      wx.login({
        success: function success() {
          wx.getUserInfo({
            success: function success(res) {
              _this.userInfo = res.userInfo;
            }
          });
        }
      });
    },
    clickHandle: function clickHandle(msg, ev) {
      console.log('clickHandle:', msg, ev);
    }
  },

  created: function created() {
    // 调用应用实例的方法获取全局数据
    this.getUserInfo();
  }
});

/***/ }),

/***/ 52:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "container",
    attrs: {
      "eventid": '7'
    },
    on: {
      "click": function($event) {
        _vm.clickHandle('test click', $event)
      }
    }
  }, [_c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '0'
    },
    on: {
      "click": _vm.navigateToMessage
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
  })]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '1'
    },
    on: {
      "click": _vm.navigateToAuditResource
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-edit",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("资源审核")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })])]), _vm._v(" "), _c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '2'
    },
    on: {
      "click": _vm.navigateToMyTeam
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-shop",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("我的团队")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '3'
    },
    on: {
      "click": _vm.navigateToLog
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
  })]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '4'
    },
    on: {
      "click": _vm.navigateToAuditRegist
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-add-o",
    staticStyle: {
      "color": "#666"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("新成员注册审核")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })])]), _vm._v(" "), _c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '5'
    },
    on: {
      "click": _vm.navigateToPersonal
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
  })])]), _vm._v(" "), _c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell zan-cell--access",
    attrs: {
      "eventid": '6'
    },
    on: {
      "click": _vm.navigateToAbout
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
  })])])])
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-41a4ffe4", esExports)
  }
}

/***/ })

},[48]);
//# sourceMappingURL=main.js.map