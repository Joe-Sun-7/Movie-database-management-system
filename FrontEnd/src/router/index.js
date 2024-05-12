import {createRouter, createWebHashHistory} from "vue-router";
import Home from "../views/Home.vue";

const routes = [
    {
        path: '/',
        redirect: '/movielist'
    }, {
        path: "/",
        name: "Home",
        component: Home,
        children: [
            {
                path: '/user',
                name: 'user',
                meta: {
                    title: '个人中心'
                },
                component: () => import (/* webpackChunkName: "user" */ '../views/User.vue')
            }, {
                path: '/movielist',
                name: 'movielist',
                meta: {
                    title: '电影列表'
                },
                component: () => import (/* webpackChunkName: "editor" */ '../views/MovieList.vue')
            }, {
                path: '/seniorsearch',
                name: 'seniorsearch',
                meta: {
                    title: '组合查询'
                },
                component: () => import (/* webpackChunkName: "editor" */ '../views/SeniorSearch.vue')
            }
        ]
    }, {
        path: "/login",
        name: "Login",
        meta: {
            title: '登录'
        },
        component: () => import ( /* webpackChunkName: "login" */ "../views/Login.vue")
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title} | vue-manage-system`;
    const role = localStorage.getItem('ms_username');
    if (!role && to.path !== '/login') {
        next('/login');
    } else if (to.meta.permission) {
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin'
            ? next()
            : next('/403');
    } else {
        next();
    }
});

export default router;