import Vue from "vue";
import axios from "axios";
import qs from "qs";

const PRODUCT = "https://www.hwyst.net/";
const TEST = "https://www.debug.group";
const LOCAL = "url/";
const HOST = PRODUCT;

// 设置请求头
axios.defaults.headers.post["Content-Type"] = "application/json";

const API = {
    API_LOGIN: "login/pcLogin.do",
    API_LOGOUT: "logOut",

    ADD_ATTR: "rs/attr/add.do",
    DEL_ATTR: "rs/attr/del.do",
    UPDATE_ATTR: "rs/attr/update.do",
    GET_ATTR: "rs/attr/get.do",
    GET_ATTR_ALL: "rs/attr/getall.do",

    ADD_DEF: "rs/def/add.do",
    DEL_DEF: "rs/def/del.do",
    UPDATE_DEF: "rs/def/update.do",
    GET_DEF: "rs/def/get.do",
    GET_DEF_ALL: "rs/def/getall.do",

    ADD_DETAIL: "rs/detail/add.do",
    DEL_DETAIL: "rs/detail/del.do",
    UPDATE_DETAIL: "rs/detail/update.do",
    GET_DETAIL: "rs/detail/get.do",
    GET_DETAIL_ALL: "rs/detail/getall.do",

    SEARCH: "/rs/search/search.do",

    GET_FILE: "/rs/file/get.do"
};
// https://www.hwyst.net/rs/file/add.do
// https://www.hwyst.net/pages/testUpload.jsp

axios.defaults.baseURL = HOST;

axios.defaults.withCredentials = true;

axios.interceptors.request.use(function (config) {
    if (config.ContentType !== "application/json" && config.method !== "get" && config.method !== "put") {
        config.data = qs.stringify(config.data);
    }
    return config;
});


axios.interceptors.response.use(function (response) {
    if (typeof response.data !== "object") {
        sessionStorage.clear();
        location.href = location.origin;
    }
    return response;
});
Vue.prototype.$ajax = {
    //登录请求
    autoService: {
        login(params) {
            return axios({
                method: "GET",
                url: API.API_LOGIN,
                cache: true,
                params: params
            });
        },
        logout(params) {
            // return axios({
            //     method: "POST",
            //     url: API_LOGOUT,
            //     data: params,
            //     ContentType: "application/x-www-form-urlencoded"
            // });
        }
    },
    attr: {
        addAttr(params) {
            return axios({
                method: "GET",
                url: API.ADD_ATTR,
                params: params
            });
        },
        delAttr(params) {
            return axios({
                method: "GET",
                url: API.DEL_ATTR,
                params: params
            });
        },
        updateAttr(params) {
            return axios({
                method: "GET",
                url: API.UPDATE_ATTR,
                params: params
            });
        },
        getAttr(params) {
            return axios({
                method: "GET",
                url: API.GET_ATTR,
                params: params
            });
        },
        getAttrAll(params) {
            return axios({
                method: "GET",
                url: API.GET_ATTR_ALL,
                params: params
            });
        },
    },
    def: {
        addDef(params) {
            return axios({
                method: "GET",
                url: API.ADD_DEF,
                params: params
            });
        },
        delDef(params) {
            return axios({
                method: "GET",
                url: API.DEL_DEF,
                params: params
            });
        },
        updateDef(params) {
            return axios({
                method: "GET",
                url: API.UPDATE_DEF,
                params: params
            });
        },
        getDef(params) {
            return axios({
                method: "GET",
                url: API.GET_DEF,
                params: params
            });
        },
        getDefAll(params) {
            return axios({
                method: "GET",
                url: API.GET_DEF_ALL,
                params: params
            });
        }
    },
    detail: {
        addDetail(params) {
            return axios({
                method: "GET",
                url: API.ADD_DETAIL,
                params: params
            });
        },
        delDetail(params) {
            return axios({
                method: "GET",
                url: API.DEL_DETAIL,
                params: params
            });
        },
        updateDetail(params) {
            return axios({
                method: "GET",
                url: API.UPDATE_DETAIL,
                params: params
            });
        },
        getDetail(params) {
            return axios({
                method: "GET",
                url: API.GET_DETAIL,
                params: params
            });
        },
        getDetailAll(params) {
            return axios({
                method: "GET",
                url: API.GET_DETAIL_ALL,
                params: params
            });
        }
    },
    search: {
        searchAll(params) {
            return axios({
                method: "GET",
                url: API.SEARCH,
                params: params
            });
        }
    },
    file: {
        getFiles(params) {
            return axios({
                method: "GET",
                url: API.GET_FILE,
                params: params
            });
        }
    }
};

Vue.prototype.$chargeAuthority = () => {
    // let ada = [
    //     {"remark": "", "roleKey": "admin", "roleName": "游客", "roleType": 0},
    //     {"remark": "", "roleKey": "writer", "roleName": "录入人员", "roleType": 0},
    //     {"remark": "", "roleKey": "checker", "roleName": "录入人员", "roleType": 0},
    //     {"remark": "", "roleKey": "root", "roleName": "录入人员", "roleType": 0},
    // ];
    // sessionStorage.setItem("myRoles", JSON.stringify(ada));
    return new Promise((resolve, reject) => {
        let role = sessionStorage.getItem("myRoles");
        if (!role || role === "") {
            //没权限
            reject(false);
            return;
        }
        let myRoles = JSON.parse(role);
        let canIn = false;
        let canWrite = false;
        for (let i = 0; i < myRoles.length; i++) {
            switch (myRoles[i].roleKey) {
                case "writer":
                    canWrite = true;
                    break;
                case "admin":
                case "checker":
                case "common":
                    canIn = true;
                    break;
                default:
                    canIn = false;
            }
        }
        if (canWrite) {
            resolve("writer");
            return;
        }
        resolve(canIn);
    });
};