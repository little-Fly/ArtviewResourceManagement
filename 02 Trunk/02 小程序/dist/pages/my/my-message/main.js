require("../../../common/manifest.js");
require("../../../common/vendor.js");
global.webpackJsonp([6],{

/***/ 60:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(61);



const app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 61:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(63);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_2eea50d1_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(64);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(62)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_2eea50d1_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src/pages/my/my-message/index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-2eea50d1", Component.options)
  } else {
    hotAPI.reload("data-v-2eea50d1", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 62:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 63:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__utils_index__ = __webpack_require__(2);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__components_card__ = __webpack_require__(3);
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

  data() {
    return {
      logs: []
    };
  },

  created() {
    const logs = wx.getStorageSync('logs') || [];
    this.logs = logs.map(log => Object(__WEBPACK_IMPORTED_MODULE_0__utils_index__["c" /* formatTime */])(new Date(log)));
  }
});

/***/ }),

/***/ 64:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _vm._m(0)
}
var staticRenderFns = [function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell"
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-pending-evaluate",
    staticStyle: {
      "color": "#f85"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_c('div', {
    staticClass: "zan-cell__text"
  }, [_vm._v("系统通知")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__desc"
  }, [_vm._v("您的注册申请被超级审核员通过，欢迎加入天天意境！")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__desc"
  }, [_vm._v("2018/09/09")])])]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell"
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-pending-evaluate",
    staticStyle: {
      "color": "#f85"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_c('div', {
    staticClass: "zan-cell__text"
  }, [_vm._v("系统通知")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__desc"
  }, [_vm._v("您的注册申请被超级审核员拒绝！")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__desc"
  }, [_vm._v("拒绝理由：还没有想好")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__desc"
  }, [_vm._v("2018/09/09")])])])])])
}]
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-2eea50d1", esExports)
  }
}

/***/ })

},[60]);
//# sourceMappingURL=main.js.map