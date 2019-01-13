require("../../common/manifest.js");
require("../../common/vendor.js");
global.webpackJsonp([7],{

/***/ 201:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(202);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 202:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(204);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_61dc874e_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(205);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(203)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_61dc874e_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\resources\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-61dc874e", Component.options)
  } else {
    hotAPI.reload("data-v-61dc874e", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 203:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 204:
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


/* harmony default export */ __webpack_exports__["a"] = ({
  data: function data() {
    return {
      isVisitor: true,
      resList: []
    };
  },

  computed: {
    userRoleListState: function userRoleListState() {
      return this.$store.state.userRoleList;
    }
  },
  mounted: function mounted() {
    // 请求资源列表
    this.getResourseListData();
    this.isRoleVisitor();
  },

  methods: {
    //判断访问用户是不是游客, 黑名单方式
    isRoleVisitor: function isRoleVisitor() {
      if (this.$store.state.userRoleList.length < 1) return;
      var vst = 0; //
      for (var i = 0; i < this.$store.state.userRoleList.length; i++) {
        if (this.$store.state.userRoleList[i].roleName == "visitor") vst = 1;
      }
      if (vst == 0) this.isVisitor = false;
    },
    getResourseListData: function getResourseListData() {
      var _this = this;

      this.$http({
        url: '/rs/def/getall.do',
        method: 'get',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        success: function success(res) {
          console.log(res);
          _this.resList = res.data;
        }
      });
    },
    clickResourceTap: function clickResourceTap(typeKey, name) {
      wx.navigateTo({ url: "resourceView/main?typeKey=" + typeKey + "&typeName=" + name });
    },
    clickVisitorTap: function clickVisitorTap() {
      var _this2 = this;

      wx.showModal({
        title: '提示',
        content: '你还没有进行实名注册，不能查看资源内容！/n现在注册？',
        success: function success(res) {
          if (res.confirm) {
            //有两个注册入口，这里是其中之一
            // 由于微信用户要与PC用户互通，这里注册需要得到用户授权获取高级信息
            _this2.$tool.checkUserInfoAuth().then(function () {
              // 用户未授权，进到授权页
              console.log('已授权');
              wx.navigateTo({ url: "../my/register/main" });
            }, function () {
              _this2.$tool.goToAuthPage();
            });
          }
        }
      });
    }
  }
});

/***/ }),

/***/ 205:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "container"
  }, [_c('div', {
    staticClass: "resource-warp clearfix"
  }, [_vm._l((_vm.resList), function(item, index) {
    return [_c('div', {
      key: 'res_items_' + index,
      staticClass: "res-item fl"
    }, [(_vm.isVisitor) ? _c('image', {
      staticClass: "res-item__headimg",
      attrs: {
        "src": "../../assets/images/resourcebk.png",
        "alt": "",
        "eventid": '1-' + index
      },
      on: {
        "click": function($event) {
          _vm.clickVisitorTap()
        }
      }
    }) : (item.logoUrl) ? _c('image', {
      staticClass: "res-item__headimg",
      attrs: {
        "src": "../../assets/images/resource_init.png",
        "alt": "",
        "eventid": '0-' + index
      },
      on: {
        "click": function($event) {
          _vm.clickResourceTap(item.typeKey, item.name)
        }
      }
    }) : _c('image', {
      staticClass: "res-item__headimg",
      attrs: {
        "src": "../../assets/images/resourcebk.png",
        "alt": ""
      }
    }), _vm._v(" "), _c('div', {
      staticClass: "res-name"
    }, [_vm._v(_vm._s(item.name))])])]
  })], 2)])
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-61dc874e", esExports)
  }
}

/***/ })

},[201]);
//# sourceMappingURL=main.js.map