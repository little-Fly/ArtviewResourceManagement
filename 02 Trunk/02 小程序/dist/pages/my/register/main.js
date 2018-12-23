require("../../../common/manifest.js");
require("../../../common/vendor.js");
global.webpackJsonp([1],{

/***/ 79:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__(1);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__index__ = __webpack_require__(80);



const app = new __WEBPACK_IMPORTED_MODULE_0_vue___default.a(__WEBPACK_IMPORTED_MODULE_1__index__["a" /* default */]);
app.$mount();

/***/ }),

/***/ 80:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_index_vue__ = __webpack_require__(82);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_4148864e_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__ = __webpack_require__(88);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(81)
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
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_4148864e_hasScoped_false_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_index_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src/pages/my/register/index.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] index.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-4148864e", Component.options)
  } else {
    hotAPI.reload("data-v-4148864e", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 81:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 82:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__components_zan_field__ = __webpack_require__(83);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

// import { formatTime } from '@/utils/index'
// import card from '@/components/card'

/* harmony default export */ __webpack_exports__["a"] = ({
  components: {
    ZanField: __WEBPACK_IMPORTED_MODULE_0__components_zan_field__["a" /* default */]
  },

  data() {
    return {
      // 页面的注册配置对象
      registerObj: {
        name: {
          // focus: true,
          title: '姓名',
          placeholder: '请输入您的名字',
          componentId: 'name'
        },
        phone: {
          error: true,
          title: '联系电话',
          inputType: 'number',
          placeholder: '请输入手机号',
          componentId: 'phone'
        },
        reason: {
          title: '注册理由',
          type: 'textarea',
          placeholder: '请输入申请注册的原因',
          componentId: 'reason'
        }
      },
      // 表单处理事件
      handleFunctions: {
        handleZanFieldChange: this.handleZanFieldChange,
        handleZanFieldFocus: this.handleZanFieldFocus,
        handleZanFieldBlur: this.handleZanFieldBlur
      },
      // 用户输入的姓名电话注册理由
      formObj: {
        name: '',
        sex: 0,
        phone: '',
        reason: ''
      },
      // 性别piker
      objectArray: [{
        sex: 0,
        sexName: '女'
      }, {
        sex: 1,
        sexName: '男'
      }],
      selectSexIndex: 0,
      loadingStatus: false
    };
  },
  mounted() {
    this.getWxUserInfo(); // 不能放在created中，页面一加载就会执行，还没有调用户授权接口的话就会报错
  },
  methods: {
    // 获取用户的授权后的敏感数据, 同步到全局
    getWxUserInfo() {
      wx.showLoading({ title: '加载中', mask: true }); // 全局的loading配置对象储存到$data中
      wx.getUserInfo({
        success: res => {
          // 里面函数有this,所以用箭头函数把上下文的this绑定到一起
          wx.hideLoading();
          // 存储用户敏感信息之加密码
          this.$store.commit('updateEncryptedObj', {
            iv: res.iv,
            encryptedData: res.encryptedData
          });
          // 存储用户敏感信息之加密码
          this.$store.commit('updateWxUserInfo', res.userInfo);
        },
        fail(res) {
          console.error(res);
          wx.hideLoading();
        }
      });
    },
    // 选择性别事件
    bindPickerChange: function (e) {
      let val = e.target.value;
      // 同步页面性别
      this.selectSexIndex = val;
      // this.$set({
      //   'formObj.sex': this.objectArray[Number(val)].sex
      // });
      console.log(this.formObj);
      this.formObj.sex = this.objectArray[Number(val)].sex;
      console.log(this.formObj);
    },
    handleZanFieldChange(e) {
      const { componentId, target, detail } = e;
      console.log('修改后新的值为');
      console.log(e.target.value);
      this.formObj[componentId] = target.value;
      console.log(this.formObj[componentId]);
    },
    handleZanFieldFocus(e) {
      const { componentId, target, detail } = e;
      // console.log(this.formObj);
      // this.formObj[componentId] = target.value;
      // console.log(this.formObj);
      // console.log('[zan:field:focus]', componentId, target, detail)
    },
    handleZanFieldBlur(e) {
      const { componentId, target, detail } = e;
      // this.formObj[componentId] = target.value;
      // console.log('[zan:field:blur]', componentId, target, detail)
    },
    // 点击注册按钮
    submitRegisterMessage() {
      let sendData = {
        nickName: this.formObj.name,
        gender: this.formObj.sex,
        phone: this.formObj.phone,
        reason: this.formObj.reason,
        iv: this.$store.state.encryptedObj.iv,
        encryptedData: this.$store.state.encryptedObj.encryptedData
      };
      // 本地储存用户基础信息
      this.$store.commit('updateUserInfo', sendData);
      console.log(sendData);
      this.$http({
        url: '/wx/user/add.do',
        method: 'post',
        data: sendData,
        success: res => {
          wx.showModal({
            title: '审核',
            content: '您的信息已提交到管理员审核，请耐心等候！',
            showCancel: false,
            success: res => {
              console.log(res);
              if (res.confirm) {
                // 全局记录用户审核状态
                this.$store.commit('updateUserAuditStatus', 2);
                // 返回进入到授权页的前一页
                wx.navigateBack();
              }
            }
          });
        },
        fail: () => {}
      });
    }
  }
});

/***/ }),

/***/ 83:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_field_vue__ = __webpack_require__(85);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_60aadd4e_hasScoped_true_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_field_vue__ = __webpack_require__(87);
var disposed = false
function injectStyle (ssrContext) {
  if (disposed) return
  __webpack_require__(84)
}
var normalizeComponent = __webpack_require__(0)
/* script */

/* template */

/* styles */
var __vue_styles__ = injectStyle
/* scopeId */
var __vue_scopeId__ = "data-v-60aadd4e"
/* moduleIdentifier (server only) */
var __vue_module_identifier__ = null
var Component = normalizeComponent(
  __WEBPACK_IMPORTED_MODULE_0__babel_loader_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_script_index_0_field_vue__["a" /* default */],
  __WEBPACK_IMPORTED_MODULE_1__node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_template_compiler_index_id_data_v_60aadd4e_hasScoped_true_transformToRequire_video_src_source_src_img_src_image_xlink_href_node_modules_mpvue_loader_1_1_4_mpvue_loader_lib_selector_type_template_index_0_field_vue__["a" /* default */],
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src/components/zan/field.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key.substr(0, 2) !== "__"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] field.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-loader/node_modules/vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-60aadd4e", Component.options)
  } else {
    hotAPI.reload("data-v-60aadd4e", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ 84:
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ 85:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__utils_helper__ = __webpack_require__(86);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

/**
 * 不能用input事件，会导致页面频闪
 * 新增title靠右对齐
 * 新增最大长度
 */

/* harmony default export */ __webpack_exports__["a"] = ({
  props: {
    error: Boolean,
    mode: String,
    title: String,
    type: String,
    focus: Boolean,
    name: String,
    componentId: String,
    value: String,
    inputType: String,
    rightTitle: Boolean,
    right: Boolean,
    placeholder: String,
    maxLength: Number,
    handleZanFieldChange: Function,
    handleZanFieldFocus: Function,
    handleZanFieldBlur: Function
  },
  methods: {
    _handleZanFieldChange(event) {
      const componentId = Object(__WEBPACK_IMPORTED_MODULE_0__utils_helper__["a" /* extractComponentId */])(event);
      event.componentId = componentId;

      // console.info('[zan:field:change]', event)

      if (this.handleZanFieldChange) {
        return this.handleZanFieldChange(event);
      }

      console.warn('页面缺少 handleZanFieldChange 回调函数');
    },

    _handleZanFieldFocus(event) {
      const componentId = Object(__WEBPACK_IMPORTED_MODULE_0__utils_helper__["a" /* extractComponentId */])(event);
      event.componentId = componentId;

      console.info('[zan:field:focus]', event);

      if (this.handleZanFieldFocus) {
        return this.handleZanFieldFocus(event);
      }

      console.warn('页面缺少 handleZanFieldFocus 回调函数');
    },

    _handleZanFieldBlur(event) {
      const componentId = Object(__WEBPACK_IMPORTED_MODULE_0__utils_helper__["a" /* extractComponentId */])(event);
      event.componentId = componentId;

      // console.info('[zan:field:blur]', event)

      if (this.handleZanFieldBlur) {
        return this.handleZanFieldBlur(event);
      }

      console.warn('页面缺少 handleZanFieldBlur 回调函数');
    }
  }
});

/***/ }),

/***/ 86:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
// 从事件对象中解析得到 componentId
// 需要在元素上声明 data-component-id
const getComponentByTag = (parent, tag) => {
  for (let c of parent.$children) {
    if (c.$options._componentTag === tag) {
      return c;
    }
  }
};
/* unused harmony export getComponentByTag */


const extractComponentId = (event = {}) => {
  const { dataset: { componentId } } = event.currentTarget || {};
  return componentId;
};
/* harmony export (immutable) */ __webpack_exports__["a"] = extractComponentId;


/*
  注：默认合并所有生命周期函数
  配置合并指定的生命周期 or 忽略指定字段
  const extend = extendCreator({
    life: ['onLoad', 'onPullDownRefresh'],
    exclude: ['binder']
  });
  Page(extend({}, {
    onLoad() {},
    ...
  }));
*/

const LIFE_CYCLE = ['onLoad', 'onReady', 'onShow', 'onHide', 'onUnload', 'onPullDownRefresh', 'onReachBottom', 'onShareAppMessage', 'onPageScroll'];

const extendCreator = (config = {}) => {
  const {
    life = LIFE_CYCLE,
    exclude = []
  } = config;

  const excludeList = exclude.concat(LIFE_CYCLE.map(getFuncArrayName));

  if (!Array.isArray(life) || !Array.isArray(exclude)) throw new Error('Invalid Extend Config');
  let lifeCycleList = life.filter(item => LIFE_CYCLE.indexOf(item) >= 0);
  return function extend(target, ...objList) {
    objList.forEach(source => {
      if (source) {
        let keys = Object.keys(source);
        keys.forEach(key => {
          let value = source[key];
          if (excludeList.indexOf(key) >= 0) return;
          if (lifeCycleList.indexOf(key) >= 0 && typeof value === 'function') {
            let funcArrayName = getFuncArrayName(key);
            if (!target[funcArrayName]) {
              target[funcArrayName] = [];
              if (target[key]) {
                target[funcArrayName].push(target[key]);
              }
              target[key] = function (...rest) {
                target[funcArrayName].forEach(func => func.apply(this, rest));
              };
            }

            if (source[funcArrayName]) {
              // 经过生命周期合并的组件直接整合函数列表
              target[funcArrayName].push(...source[funcArrayName]);
            } else {
              // 添加生命周期函数进入函数列表
              target[funcArrayName].push(value);
            }
          } else {
            target[key] = value;
          }
        });
      }
    });
    return target;
  };
};

const getFuncArrayName = name => `__$${name}`;

/* unused harmony default export */ var _unused_webpack_default_export = ({
  extractComponentId,
  extend: Object.assign,
  extendCreator
});

/***/ }),

/***/ 87:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "zan-cell zan-field",
    class: {
      'zan-field--error': _vm.error, 'zan-field--wrapped': _vm.mode === 'wrapped'
    }
  }, [(_vm.title) ? _c('div', {
    staticClass: "zan-cell__hd zan-field__title",
    class: {
      'align-right': _vm.rightTitle
    }
  }, [_vm._v(_vm._s(_vm.title))]) : _vm._e(), _vm._v(" "), (_vm.type === 'textarea') ? _c('textarea', {
    key: 'textarea-' + _vm.componentId,
    staticClass: "zan-field__input zan-cell__bd",
    class: {
      'zan-field__input--right': _vm.right
    },
    attrs: {
      "auto-height": "",
      "name": _vm.name || _vm.componentId || '',
      "value": _vm.value,
      "focus": _vm.focus,
      "maxlength": _vm.maxLength,
      "placeholder": _vm.placeholder,
      "placeholder-class": "zan-field__placeholder",
      "data-component-id": _vm.componentId || '',
      "eventid": '1'
    },
    on: {
      "change": _vm._handleZanFieldChange,
      "focus": _vm._handleZanFieldFocus,
      "blur": _vm._handleZanFieldBlur
    }
  }) : _c('input', {
    key: 'input-' + _vm.componentId,
    staticClass: "zan-field__input zan-cell__bd",
    class: {
      'zan-field__input--right': _vm.right
    },
    attrs: {
      "type": _vm.inputType || 'text',
      "name": _vm.name || _vm.componentId || '',
      "value": _vm.value,
      "focus": _vm.focus,
      "maxlength": _vm.maxLength,
      "placeholder": _vm.placeholder,
      "placeholder-class": "zan-field__placeholder",
      "data-component-id": _vm.componentId || '',
      "eventid": '0'
    },
    on: {
      "change": _vm._handleZanFieldChange,
      "focus": _vm._handleZanFieldFocus,
      "blur": _vm._handleZanFieldBlur
    }
  })])
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-60aadd4e", esExports)
  }
}

/***/ }),

/***/ 88:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
var render = function () {var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "zan-panel"
  }, [_c('zan-field', _vm._b({
    attrs: {
      "value": _vm.formObj.name,
      "mpcomid": '0'
    }
  }, 'zan-field', Object.assign({}, {
    rightTitle: true,
    maxLength: 20
  }, _vm.handleFunctions, _vm.registerObj.name), false)), _vm._v(" "), _c('div', {
    staticClass: "zan-cell zan-field"
  }, [_c('div', {
    staticClass: "zan-field__title align-right"
  }, [_vm._v("性别")]), _vm._v(" "), _c('picker', {
    staticClass: "zan-field__input zan-field__bd",
    attrs: {
      "range-key": "sexName",
      "value": _vm.selectSexIndex,
      "range": _vm.objectArray,
      "eventid": '0'
    },
    on: {
      "change": _vm.bindPickerChange
    }
  }, [_vm._v("\n        " + _vm._s(_vm.objectArray[_vm.selectSexIndex].sexName) + "\n      ")])], 1), _vm._v(" "), _c('zan-field', _vm._b({
    attrs: {
      "value": _vm.formObj.phone,
      "mpcomid": '1'
    }
  }, 'zan-field', Object.assign({}, {
    rightTitle: true,
    maxLength: 11
  }, _vm.handleFunctions, _vm.registerObj.phone), false)), _vm._v(" "), _c('zan-field', _vm._b({
    attrs: {
      "value": _vm.formObj.reason,
      "mpcomid": '2'
    }
  }, 'zan-field', Object.assign({}, {
    rightTitle: true,
    maxLength: 200
  }, _vm.handleFunctions, _vm.registerObj.reason), false))], 1), _vm._v(" "), _c('div', {
    staticClass: "zan-panel"
  }, [_c('button', {
    staticClass: "zan-btn zan-btn--primary",
    attrs: {
      "disabled": _vm.loadingStatus,
      "loading": _vm.loadingStatus,
      "eventid": '1'
    },
    on: {
      "click": _vm.submitRegisterMessage
    }
  }, [_vm._v("提交审核")])], 1)])
}
var staticRenderFns = []
render._withStripped = true
var esExports = { render: render, staticRenderFns: staticRenderFns }
/* harmony default export */ __webpack_exports__["a"] = (esExports);
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-loader/node_modules/vue-hot-reload-api").rerender("data-v-4148864e", esExports)
  }
}

/***/ })

},[79]);
//# sourceMappingURL=main.js.map