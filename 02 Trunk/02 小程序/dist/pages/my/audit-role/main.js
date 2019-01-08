require("../../../common/manifest.js");
require("../../../common/vendor.js");
global.webpackJsonp([11],{

/***/ 151:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(152);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 152:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(154);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_f7aeebd8_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(155);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(153)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_f7aeebd8_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\my\\audit-role\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-f7aeebd8", Component.options)
  } else {
    hotAPI.reload("data-v-f7aeebd8", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 153:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 154:
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


/* harmony default export */ __webpack_exports__["a"] = ({
  data: function data() {
    return {
      roleKey: '',
      roleName: '',
      userListForSelect: []
    };
  },
  onShow: function onShow() {
    if (this.roleKey != "") this.getMulList();
  },
  mounted: function mounted() {
    var paramsObj = this.$tool.getOptions();
    this.roleKey = paramsObj.rolekey;
    this.roleName = this.$tool.getRoleNameByRoleKey(this.$config.$DATA.ROLE_KEY_MAP, this.roleKey);
    //this.getMulList();
    wx.setNavigationBarTitle({
      title: '' + this.roleName
    });
    this.getMulList();
  },

  methods: {
    /**
     * 去具体的授权页
     */
    goToAuthList: function goToAuthList(status) {
      wx.navigateTo({
        url: '../audit-role-list/main?rolekey=' + this.roleKey + '&operatekey=' + status
      });
    },

    /**
     * 获取当前权限人员列表
     */
    getMulList: function getMulList() {
      var _this = this;

      //let API = '/wx/user/getUnUsersByRole.do';
      var API = '/wx/user/getUsersByRole.do';
      this.$http({
        url: API,
        method: 'get',
        data: {
          roleKey: this.roleKey
        },
        success: function success(res) {
          _this.userListForSelect = res.data;
        }
      });
    }
  }
});

/***/ }),

/***/ 155:
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
  })])]), _vm._v(" "), _c('div', {
    staticClass: "zan-panel"
  }, _vm._l((_vm.userListForSelect), function(item, index) {
    return _c('div', {
      key: index,
      staticClass: "zan-cell"
    }, [_c('div', {
      staticClass: "zan-cell__icon zan-icon zan-icon-contact",
      staticStyle: {
        "color": "#666"
      }
    }), _vm._v(" "), _c('div', {
      staticClass: "zan-cell__bd"
    }, [_vm._v(_vm._s(item.nickName))])])
  }))])
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-hot-reload-api").rerender("data-v-f7aeebd8", esExports)
  }
}

/***/ })

},[151]);
//# sourceMappingURL=main.js.map