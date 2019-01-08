import Vue from "vue";
import Router from "vue-router";
import Login from "@/pages/Login";
import Redirect from "@/pages/Redirect";
import Main from "@/pages/Main";
import AssetsManager from "@/pages/AssetsManager";
import NoAuthority from "@/pages/NoAuthority";

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: "/",
            name: "default",
            component: Login
        },
        {
            path: "/login",
            name: "login",
            component: Login
        },
        {
            path: "/main",
            name: "main",
            component: Main
        },
        {
            path: "/Redirect",
            name: "Redirect",
            component: Redirect
        },
        {
            path: "/NoAuthority",
            name: "NoAuthority",
            component: NoAuthority
        },
        {
            path: "/assets",
            name: "assets",
            component: AssetsManager
        }
    ]
});
