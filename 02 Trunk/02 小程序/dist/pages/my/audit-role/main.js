require("../../../common/manifest.js");
require("../../../common/vendor.js");
global.webpackJsonp([10],{

/***/ 40:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(41);



const app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 41:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(43);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_0aac9893_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(44);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(42)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_0aac9893_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src/pages/my/audit-role/index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-0aac9893", Component.options)
  } else {
    hotAPI.reload("data-v-0aac9893", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 42:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 43:
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


/* harmony default export */ __webpack_exports__["a"] = ({
  data() {
    return {
      roleKey: '',
      roleName: ''
    };
  },
  mounted() {
    let paramsObj = this.$tool.getOptions();
    this.roleKey = paramsObj.rolekey;
    this.roleName = this.$tool.getRoleNameByRoleKey(this.$config.$DATA.ROLE_KEY_MAP, this.roleKey);
    wx.setNavigationBarTitle({
      title: `${this.roleName}`
    });
  },
  methods: {
    /**
     * 去具体的授权页
     */
    goToAuthList(status) {
      wx.navigateTo({
        url: `../audit-role-list/main?rolekey=${this.roleKey}&operatekey=${status}`
      });
    }
  }
});

/***/ }),

/***/ 44:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "zan-panel zan-cell--access"
  }, [_c('div', {
    staticClass: "zan-cell",
    attrs: {
      "eventid": '0'
    },
    on: {
      "click": function($event) {
        _vm.goToAuthList("add")
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-description",
    staticStyle: {
      "color": "#4b0"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("授权新" + _vm._s(_vm.roleName))]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  })])]), _vm._v(" "), _c('div', {
    staticClass: "zan-panel zan-cell--access",
    attrs: {
      "eventid": '1'
    },
    on: {
      "click": function($event) {
        _vm.goToAuthList("delete")
      }
    }
  }, [_c('div', {
    staticClass: "zan-cell"
  }, [_c('div', {
    staticClass: "zan-cell__icon zan-icon zan-icon-description",
    staticStyle: {
      "color": "#f85"
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("取消" + _vm._s(_vm.roleName) + "授权")]), _vm._v(" "), _c('div', {
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
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-0aac9893", esExports)
  }
}

/***/ })

},[40]);
//# sourceMappingURL=main.js.map