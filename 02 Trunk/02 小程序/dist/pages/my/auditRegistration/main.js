require("../../../common/manifest.js");
require("../../../common/vendor.js");
global.webpackJsonp([10],{

/***/ 156:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(157);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 157:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(159);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_c3ee3944_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(160);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(158)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_c3ee3944_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\my\\auditRegistration\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-c3ee3944", Component.options)
  } else {
    hotAPI.reload("data-v-c3ee3944", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 158:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 159:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__utils_index__ = __webpack_require__(9);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__components_card__ = __webpack_require__(13);
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
    card: __WEBPACK_IMPORTED_MODULE_1__components_card__["a" /* default */]
  },

  data: function data() {
    return {
      logs: []
    };
  },
  created: function created() {
    var logs = wx.getStorageSync('logs') || [];
    this.logs = logs.map(function (log) {
      return Object(__WEBPACK_IMPORTED_MODULE_0__utils_index__["c" /* formatTime */])(new Date(log));
    });
  }
});

/***/ }),

/***/ 160:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 title align-right"
  }, [_c('strong', [_vm._v("Manfray：")])], 1), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17 title"
  }, [_c('strong', [_vm._v("申请加入天天意境")])], 1)]), _vm._v(" "), _vm._m(0), _vm._v(" "), _vm._m(1), _vm._v(" "), _vm._m(2), _vm._v(" "), _vm._m(3), _vm._v(" "), _c('div', {
    staticClass: "align-center btn-box"
  }, [_c('button', {
    staticClass: "zan-btn zan-btn--small zan-btn--primary"
  }, [_vm._v("审核通过")]), _vm._v(" "), _c('button', {
    staticClass: "zan-btn zan-btn--small zan-btn--danger"
  }, [_vm._v("不能通过")])], 1)]), _vm._v(" "), _c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 title align-right"
  }, [_c('strong', [_vm._v("Manfray：")])], 1), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17 title"
  }, [_c('strong', [_vm._v("申请加入天天意境")])], 1)]), _vm._v(" "), _vm._m(4), _vm._v(" "), _vm._m(5), _vm._v(" "), _vm._m(6), _vm._v(" "), _vm._m(7), _vm._v(" "), _c('div', {
    staticClass: "align-center btn-box"
  }, [_c('button', {
    staticClass: "zan-btn zan-btn--small zan-btn--primary"
  }, [_vm._v("审核通过")]), _vm._v(" "), _c('button', {
    staticClass: "zan-btn zan-btn--small zan-btn--danger"
  }, [_vm._v("不能通过")])], 1)]), _vm._v(" "), _c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 title align-right"
  }, [_c('strong', [_vm._v("Manfray：")])], 1), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17 title"
  }, [_c('strong', [_vm._v("申请加入天天意境")])], 1)]), _vm._v(" "), _vm._m(8), _vm._v(" "), _vm._m(9), _vm._v(" "), _vm._m(10), _vm._v(" "), _vm._m(11), _vm._v(" "), _c('div', {
    staticClass: "align-center btn-box"
  }, [_c('button', {
    staticClass: "zan-btn zan-btn--small zan-btn--primary"
  }, [_vm._v("审核通过")]), _vm._v(" "), _c('button', {
    staticClass: "zan-btn zan-btn--small zan-btn--danger"
  }, [_vm._v("不能通过")])], 1)])])
}
var staticRenderFns = [function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("联系方式：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("18126470043")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("性别：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("男")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("注册理由：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("请输入申请注册用户的原因，如公司员工，工作需要")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("微信号：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("Mistergong")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("联系方式：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("18126470043")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("性别：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("男")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("注册理由：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("请输入申请注册用户的原因，如公司员工，工作需要")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("微信号：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("Mistergong")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("联系方式：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("18126470043")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("性别：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("男")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("注册理由：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("请输入申请注册用户的原因，如公司员工，工作需要")])])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-row"
  }, [_c('div', {
    staticClass: "zan-col zan-col-7 align-right"
  }, [_vm._v("微信号：")]), _vm._v(" "), _c('div', {
    staticClass: "zan-col zan-col-17"
  }, [_vm._v("Mistergong")])])
}]
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-hot-reload-api").rerender("data-v-c3ee3944", esExports)
  }
}

/***/ })

},[156]);
//# sourceMappingURL=main.js.map