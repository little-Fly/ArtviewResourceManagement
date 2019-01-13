require("../../common/manifest.js");
require("../../common/vendor.js");
global.webpackJsonp([5],{

/***/ 211:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(212);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 212:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(214);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_3a47e0f0_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(215);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(213)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_3a47e0f0_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\share\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-3a47e0f0", Component.options)
  } else {
    hotAPI.reload("data-v-3a47e0f0", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 213:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 214:
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

/* harmony default export */ __webpack_exports__["a"] = ({
  data: function data() {
    return {
      shRsList: [] //待分享资源列表
    };
  },
  mounted: function mounted() {
    var paramsObj = this.$tool.getOptions();
    this.shRsList = JSON.parse(paramsObj.shRsList);
  },

  methods: {
    searchResourceBtn: function searchResourceBtn() {},
    addSearchConditions: function addSearchConditions() {},

    /*
    *从接口拿到的资源数据是一维平铺，这里根据sourceKey进行二维处理
    */
    changTheSourceArray: function changTheSourceArray(data) {
      var saList = [];
      var dList = [];
      for (;;) {
        if (data.length < 1) break;
        var key = data[0].resourceKey;
        dList.push(data[0]);
        data.splice(0, 1);
        var i = 0;
        for (;;) {
          if (key == data[i].resourceKey) {
            dList.push(data[i]);
            data.splice(i, 1);
          } else i++;
          if (data.length == 0) break;
          if (i >= data.length) break;
        }
        saList.push(dList);
        dList = [];
      }
      return saList;
    },


    /*
     *获取某资源类别下的资源
     */
    getrsTypevalue: function getrsTypevalue(typeKey) {
      var _this = this;

      this.$http({
        url: '/rs/detail/getallbyuser.do',
        method: 'get',
        data: {
          typekey: typeKey,
          start: (this.page - 1) * this.rowNumbers,
          len: this.rowNumbers },
        success: function success(res) {
          var rData = _this.changTheSourceArray(res.data);
          for (var i = 0; i < rData.length; i++) {
            _this.rsList.push(rData[i]);
          }
          if (rData.length > 0) _this.page++;
          wx.hideLoading();
        }
      });
    },


    /**
     * 将当前资源加入分享包
     */
    addShareBagBtn: function addShareBagBtn(index) {
      var add = true;
      for (var i = 0; i < this.shRsList.length; i++) {
        var flag = 0;
        for (var j = 0; j < this.shRsList[i].length; j++) {
          if (this.rsList[index].length < j + 1) break;
          if (this.shRsList[i][j] == this.rsList[index][j]) flag++;
        }
        if (flag == this.shRsList[i].length) {
          add = false;
          break;
        }
      }
      if (add) {
        this.shRsList.push(this.rsList[index]);
        this.shCount = this.shRsList.length;
      } else {
        wx.showToast({ title: '重复选择' });
      }
    },

    /**
     * 获取更多待资源条目
     */
    getMoreListBtn: function getMoreListBtn() {
      this.requestCount = 0;
      for (var i = 0; i < this.rsTypeList.length; i++) {
        this.getrsTypevalue(this.typeKey);
      }
    }
  }
});

/***/ }),

/***/ 215:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "resourcelist-wrap"
  }, [_c('div', {
    staticClass: "function-btn-box"
  }, [_c('span', {
    staticClass: "function-btn for-select-template",
    attrs: {
      "eventid": '0'
    },
    on: {
      "click": function($event) {
        _vm.selectTemplate()
      }
    }
  }, [_vm._v("选取模板")]), _vm._v(" "), _c('span', {
    staticClass: "space"
  }), _c('span', {
    staticClass: "space"
  }), _vm._v(" "), _c('span', {
    staticClass: "function-btn for-create-page",
    attrs: {
      "eventid": '1'
    },
    on: {
      "click": function($event) {
        _vm.createSharePage()
      }
    }
  }, [_vm._v("生成分享页面")])]), _vm._v(" "), _vm._l((_vm.shRsList), function(item, index) {
    return [_c('div', {
      key: index,
      staticClass: "resourcelist-item-wrap"
    }, [_vm._m(0, true), _vm._v(" "), _c('div', {
      staticClass: "inline-block"
    }, [_vm._l((item), function(ss, inx) {
      return [_c('div', {
        key: inx
      }, [(ss.attrType == 'picture') ? _c('div', {
        staticClass: "resource-image  content-item"
      }) : (ss.attrType == 'video') ? _c('div', {
        staticClass: "resource-video  content-item"
      }) : _c('div', {
        staticClass: "content-item inline-block"
      }, [_c('span', {
        staticClass: "item-title align-right inline-block"
      }, [_vm._v(_vm._s(ss.attrName))]), _vm._v("：" + _vm._s(ss.attrValue) + "\n            ")])])]
    }), _vm._v(" "), _vm._m(1, true)], 2)])]
  })], 2)
}
var staticRenderFns = [function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "icon-group-box"
  }, [_c('image', {
    staticClass: "rs-icon",
    attrs: {
      "src": "../../assets/images/delete.png",
      "alt": ""
    }
  }), _vm._v(" "), _c('span', {
    staticClass: "space"
  }), _vm._v(" "), _c('image', {
    staticClass: "rs-icon",
    attrs: {
      "src": "../../assets/images/up.png",
      "alt": ""
    }
  }), _vm._v(" "), _c('image', {
    staticClass: "rs-icon",
    attrs: {
      "src": "../../assets/images/down.png",
      "alt": ""
    }
  })])
},function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "resource-image  content-item"
  }, [_c('image', {
    staticClass: "resource-image",
    attrs: {
      "src": "../../assets/images/resource_init.png",
      "alt": ""
    }
  })])
}]
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-3a47e0f0", esExports)
  }
}

/***/ })

},[211]);
//# sourceMappingURL=main.js.map