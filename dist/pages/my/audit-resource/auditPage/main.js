require("../../../../common/manifest.js");
require("../../../../common/vendor.js");
global.webpackJsonp([16],{

/***/ 125:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(126);



var app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 126:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(128);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_556a8e32_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(129);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(127)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_lib_template_compiler_index_id_data_v_556a8e32_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src\\pages\\my\\audit-resource\\auditPage\\index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-556a8e32", Component.options)
  } else {
    hotAPI.reload("data-v-556a8e32", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 127:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 128:
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


/* harmony default export */ __webpack_exports__["a"] = ({
  data: function data() {
    return {
      operType: '',
      page: 1,
      rowNumbers: 5,
      rsTypeList: [], //资源类型列表
      rsList: [], //待审核资源列表
      canMore: 'false', //开关，显示获取更多待审核资源按钮
      requestCount: 0 //开关，解决异步调用的问题
    };
  },
  mounted: function mounted() {
    var paramsObj = this.$tool.getOptions();
    this.operType = paramsObj.operType;
    if (this.operType == "ApprovalAdd") wx.setNavigationBarTitle({ title: "新增资源审核" });else if (this.operType == "ApprovalUpdate") wx.setNavigationBarTitle({ title: "修改资源审核" });else if (this.operType == "ApprovalDel") wx.setNavigationBarTitle({ title: "删除资源审核" });
    this.rsTypeList = [], this.rsList = [], this.getAttrDefinedList();
  },

  methods: {
    /**
     * 展开用户信息，只有当用户状态是待审核时候才能审核
     */
    expandUserMessage: function expandUserMessage(index) {
      this.userList[index].showContent = !this.userList[index].showContent;
    },
    clickOpenAuthBtn: function clickOpenAuthBtn(index) {
      this.register.reason = this.userList[index].reason;
    },

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
     *过滤需要审核的资源
     */
    attrStateFilter: function attrStateFilter(auditType, saList) {
      var auditList = [];
      for (var i = 0; i < saList.length; i++) {
        if (saList[i][0].attrState == auditType) auditList.push(saList[i]);
      }
      return auditList;
    },


    /*
    *取资源类别定义
    */
    getAttrDefinedList: function getAttrDefinedList() {
      var _this = this;

      this.$http({
        url: '/rs/def/getall.do',
        method: 'get',
        success: function success(res) {
          _this.rsTypeList = res.data;
          wx.hideLoading();
          _this.getMoreListBtn();
          console.log(_this.rsList);
        }
      });
    },


    /*
     *获取某资源类别下的待审批的资源
     */
    getrsTypevalue: function getrsTypevalue(typeKey, typeName) {
      var _this2 = this;

      this.$http({
        url: '/rs/detail/getallpending.do',
        method: 'get',
        data: {
          //opertype: this.operType,
          typekey: typeKey,
          start: (this.page - 1) * this.rowNumbers,
          len: this.rowNumbers },
        success: function success(res) {
          var saList = _this2.changTheSourceArray(res.data);
          var auditList = _this2.attrStateFilter(_this2.operType, saList);
          wx.hideLoading();
          for (var j = 0; j < auditList.length; j++) {
            var vdata = [];
            for (var i = 1; i < auditList[j].length; i++) {
              vdata.push(auditList[j][i]);
            }
            _this2.rsList.push({
              typeName: typeName,
              theFirstAttr: {
                attrName: auditList[j][0].attrName,
                attrValue: auditList[j][0].attrValue },
              data: vdata,
              auditText: ''
            });
          }
          _this2.requestCount++;
          if (_this2.requestCount >= _this2.rsTypeList.length) {
            if (_this2.rsList.length < 1) {
              wx.showToast({ title: '后面没有了' });
              _this2.canMore = 'true';
            } else _this2.canMore = 'false';
          }
        }
      });
    },


    /**
     * 审核按钮通过按钮点击
     */
    clickAuthOKBtn: function clickAuthOKBtn(index) {
      var _this3 = this;

      if (this.rsList[index].auditText.length < 1) {
        wx.showModal({ title: '提示', content: '你还没有输入审批意见', showCancel: false });
        return;
      }
      var url = "";
      if (this.operType == "ApprovalAdd") url = "/rs/detail/approvaladd.do";else if (this.operType == "ApprovalUpdate") url = "/rs/detail/approvalupdate.do";else if (this.operType == "ApprovalDel") url = "/rs/detail/approvaldel.do";
      this.$http({
        url: url,
        method: 'post',
        data: {
          resourcekey: this.rsList[index].data[0].resourceKey,
          approvalmess: this.rsList[index].auditText
        },
        success: function success(res) {
          _this3.rsList.splice(index, 1);
          wx.hideLoading();
          wx.showToast({ title: '操作成功' });
          if (_this3.rsList.length < 1) _this3.canMore = 'true';
        }
      });
    },

    /**
     * 不同意操作
     */
    clickAuditRefuseBtn: function clickAuditRefuseBtn(index) {
      var _this4 = this;

      if (this.rsList[index].auditText.length < 1) {
        wx.showModal({ title: '提示', content: '你还没有输入审批意见', showCancel: false });
        return;
      }
      this.$http({
        url: '/rs/detail/reject.do',
        method: 'post',
        data: {
          resourcekey: this.rsList[index].data[0].resourceKey,
          approvalmess: this.rsList[index].auditText
        },
        success: function success(res) {
          _this4.rsList.splice(index, 1);
          wx.hideLoading();
          wx.showToast({ title: '操作成功' });
          if (_this4.rsList.length < 1) _this4.canMore = 'true';
        }
      });
    },

    /**
     * 获取跟多待审批的条目
     */
    getMoreListBtn: function getMoreListBtn() {
      this.requestCount = 0;
      for (var i = 0; i < this.rsTypeList.length; i++) {
        this.getrsTypevalue(this.rsTypeList[i].typeKey, this.rsTypeList[i].name);
      }
    }
  }
});

/***/ }),

/***/ 129:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "resourcelist-wrap"
  }, [_vm._l((_vm.rsList), function(item, index) {
    return [_c('div', {
      key: index,
      staticClass: "resourcelist-item-wrap content-item"
    }, [_c('div', {
      staticClass: "attr-type "
    }, [_c('span', {
      staticClass: "align-right inline-block"
    }, [_vm._v("资源类型")]), _vm._v("：" + _vm._s(item.typeName) + "\n      ")]), _vm._v(" "), _c('div', {
      staticClass: "resource-name"
    }, [_vm._v(_vm._s(item.theFirstAttr.attrValue))]), _vm._v(" "), _vm._l((item.data), function(ss, inx) {
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
      staticClass: "line-block"
    }, [_c('div', {
      staticClass: "line-fill inline-block"
    }, [_c('span', {
      staticClass: "audit-input-title inline-block"
    }, [_vm._v("审核意见：")]), _vm._v(" "), _c('textarea', {
      directives: [{
        name: "model",
        rawName: "v-model",
        value: (item.auditText),
        expression: "item.auditText"
      }],
      staticClass: "audit-input inline-block",
      attrs: {
        "maxlength": "128",
        "placeholder": "如：同意，请核实单价，请完善备注说明，这个不发了......",
        "type": "text",
        "eventid": '0-' + index
      },
      domProps: {
        "value": (item.auditText)
      },
      on: {
        "input": function($event) {
          if ($event.target.composing) { return; }
          item.auditText = $event.target.value
        }
      }
    })]), _vm._v(" "), (_vm.operType != 'ApprovalDel') ? _c('div', {
      staticClass: "line-fill inline-block"
    }, [_c('span', {
      staticClass: "audit-btn for-ok",
      attrs: {
        "eventid": '1-' + index
      },
      on: {
        "click": function($event) {
          _vm.clickAuthOKBtn(index)
        }
      }
    }, [_vm._v("审核通过并发布")]), _vm._v(" "), _c('span', {
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
        "eventid": '2-' + index
      },
      on: {
        "click": function($event) {
          _vm.clickAuditRefuseBtn(index)
        }
      }
    }, [_vm._v("不通过")])]) : _c('div', {
      staticClass: "line-fill inline-block"
    }, [_c('span', {
      staticClass: "audit-btn for-ok",
      attrs: {
        "eventid": '3-' + index
      },
      on: {
        "click": function($event) {
          _vm.clickAuthOKBtn(index)
        }
      }
    }, [_vm._v("同意并立即删除")]), _vm._v(" "), _c('span', {
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
          _vm.clickAuditRefuseBtn(index)
        }
      }
    }, [_vm._v("不同意")])])])], 2)]
  }), _vm._v(" "), (_vm.canMore == 'true') ? _c('div', {
    staticClass: "line-block align-center inline-block"
  }, [_c('span', {
    staticClass: "audit-btn for-ok",
    attrs: {
      "eventid": '5'
    },
    on: {
      "click": function($event) {
        _vm.getMoreListBtn()
      }
    }
  }, [_vm._v("获取更多")])]) : _vm._e()], 2)
}
var staticRenderFns = [function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "resource-image"
  }, [_c('image', {
    staticClass: "resource-image",
    attrs: {
      "src": "../../../../assets/images/resource_init.png",
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
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-556a8e32", esExports)
  }
}

/***/ })

},[125]);
//# sourceMappingURL=main.js.map