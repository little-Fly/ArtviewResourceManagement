require("../../../common/manifest.js");
require("../../../common/vendor.js");
global.webpackJsonp([6],{

/***/ 206:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(207);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 207:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(209);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_734ce4ca_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(210);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(208)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_734ce4ca_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\resources\\resourceView\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-734ce4ca", Component.options)
  } else {
    hotAPI.reload("data-v-734ce4ca", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 208:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 209:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_json_stringify__ = __webpack_require__(39);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_json_stringify___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_json_stringify__);

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
      typeKey: '',
      typeName: '',
      searchText: '',
      page: 1,
      rowNumbers: 2,
      rsList: [], //待审核资源列表
      shCount: 0, //分享包里已选分享资源的数量
      shRsList: [] //待分享资源列表
    };
  },
  mounted: function mounted() {
    var paramsObj = this.$tool.getOptions();
    this.typeKey = paramsObj.typeKey;
    this.typeName = paramsObj.typeName;
    wx.setNavigationBarTitle({ title: this.typeName + "资源浏览" });
    this.rsList = [];
    this.page = 1;
    this.getrsTypevalue(this.typeKey);
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
    clickGotoShareBtn: function clickGotoShareBtn() {
      if (this.shRsList.length < 1) {
        wx.showModal({ title: '提示', content: '选些资源才能打开分享包', showCancel: false });
        return;
      }
      wx.navigateTo({ url: "../../share/main?shRsList=" + __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_json_stringify___default()(this.shRsList) });
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

/***/ 210:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "resourcelist-wrap"
  }, [_c('div', {
    staticClass: "search-box"
  }, [_c('div', {
    staticClass: "search-input-box"
  }, [_c('input', {
    directives: [{
      name: "model",
      rawName: "v-model",
      value: (_vm.searchText),
      expression: "searchText"
    }],
    staticClass: "search-input",
    attrs: {
      "type": "text",
      "maxlength": "36",
      "placeholder": "请输入关键字",
      "eventid": '0'
    },
    domProps: {
      "value": (_vm.searchText)
    },
    on: {
      "input": function($event) {
        if ($event.target.composing) { return; }
        _vm.searchText = $event.target.value
      }
    }
  }), _vm._v(" "), _c('div', {
    staticClass: "search-btn",
    attrs: {
      "eventid": '1'
    },
    on: {
      "click": function($event) {
        _vm.searchResourceBtn()
      }
    }
  }, [_vm._v("搜索")])]), _vm._v(" "), _c('div', {
    staticClass: "add-search-conditions",
    attrs: {
      "eventid": '2'
    },
    on: {
      "click": function($event) {
        _vm.addSearchConditions()
      }
    }
  }, [_vm._v("增加搜索条件")])]), _vm._v(" "), _vm._l((_vm.rsList), function(item, index) {
    return [_c('div', {
      key: index,
      staticClass: "resourcelist-item-wrap content-item"
    }, [_vm._l((item), function(ss, inx) {
      return [_c('div', {
        key: inx
      }, [(ss.attrType == 'picture') ? _c('div', {
        staticClass: "resource-image"
      }) : (ss.attrType == 'video') ? _c('div', {
        staticClass: "resource-video"
      }) : _c('div', {
        staticClass: "content-item inline-block"
      }, [_c('span', {
        staticClass: "item-title align-right inline-block"
      }, [_vm._v(_vm._s(ss.attrName))]), _vm._v("：" + _vm._s(ss.attrValue) + "\n          ")])])]
    }), _vm._v(" "), _vm._m(0, true), _vm._v(" "), _c('div', {
      staticClass: "line-fill inline-block"
    }, [_c('span', {
      staticClass: "audit-btn for-ok",
      attrs: {
        "eventid": '3-' + index
      },
      on: {
        "click": function($event) {
          _vm.addShareBagBtn(index)
        }
      }
    }, [_vm._v("加入分享包\n            "), _c('span', {
      staticClass: "sh-count"
    }, [_vm._v(_vm._s(_vm.shCount))])]), _vm._v(" "), _c('span', {
      staticClass: "space"
    }), _c('span', {
      staticClass: "space"
    }), _c('span', {
      staticClass: "space"
    }), _c('span', {
      staticClass: "space"
    }), _vm._v(" "), _c('span', {
      staticClass: "audit-btn for-refuse",
      attrs: {
        "eventid": '4-' + index
      },
      on: {
        "click": function($event) {
          _vm.clickGotoShareBtn()
        }
      }
    }, [_vm._v("去分享")])])], 2)]
  }), _vm._v(" "), _c('div', {
    staticClass: "line-block align-center inline-block"
  }, [_c('span', {
    staticClass: "audit-btn for-ok",
    attrs: {
      "eventid": '5'
    },
    on: {
      "click": function($event) {
        _vm.getrsTypevalue(_vm.typeKey)
      }
    }
  }, [_vm._v("获取更多")])])], 2)
}
var staticRenderFns = [function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "resource-image"
  }, [_c('image', {
    staticClass: "resource-image",
    attrs: {
      "src": "../../../assets/images/resource_init.png",
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
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-734ce4ca", esExports)
  }
}

/***/ })

},[206]);
//# sourceMappingURL=main.js.map