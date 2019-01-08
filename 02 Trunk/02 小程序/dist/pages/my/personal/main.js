require("../../../common/manifest.js");
require("../../../common/vendor.js");
global.webpackJsonp([6],{

/***/ 185:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(186);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 186:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(188);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_006bbb6c_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(189);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(187)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_006bbb6c_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\my\\personal\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-006bbb6c", Component.options)
  } else {
    hotAPI.reload("data-v-006bbb6c", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 187:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 188:
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


/* harmony default export */ __webpack_exports__["a"] = ({
  data: function data() {
    return {
      userRoleNameList: [],
      userRoleNames: ''
    };
  },
  mounted: function mounted() {
    this.getUserRoleList();
    this.userRoleNames = this.userRoleNameList.join(',');
  },

  methods: {
    /**
     * 根据全局的用户角色key获得用户的角色名称
     */
    getUserRoleList: function getUserRoleList() {
      console.log(this.userRoleListState);
      this.userRoleNameList = [];
      for (var i = 0, len = this.userRoleListState.length; i < len; i++) {
        this.userRoleNameList.push(this.$tool.getRoleNameByRoleKey(this.$config.$DATA.ROLE_KEY_MAP, this.userRoleListState[i]));
      }
    }
  },
  computed: {
    userRoleListState: function userRoleListState() {
      return this.$store.state.userRoleList;
    },
    userInfoState: function userInfoState() {
      return this.$store.state.userInfo;
    }
  }
});

/***/ }),

/***/ 189:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "zan-panel"
  }, [_c('div', {
    staticClass: "zan-cell"
  }, [_c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("姓名")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  }, [_vm._v(_vm._s(_vm.userInfoState.nickName))])]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell"
  }, [_c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("权限")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  }, [_vm._v(_vm._s(_vm.userRoleNames))])]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell"
  }, [_c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("性别")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  }, [_vm._v(_vm._s(_vm.userInfoState.gender == 0 ? '女' : '男'))])]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell"
  }, [_c('div', {
    staticClass: "zan-cell__bd"
  }, [_vm._v("电话")]), _vm._v(" "), _c('div', {
    staticClass: "zan-cell__ft"
  }, [_vm._v(_vm._s(_vm.userInfoState.phone))])])])])
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-hot-reload-api").rerender("data-v-006bbb6c", esExports)
  }
}

/***/ })

},[185]);
//# sourceMappingURL=main.js.map