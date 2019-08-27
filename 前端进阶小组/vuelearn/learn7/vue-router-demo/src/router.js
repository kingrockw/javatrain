import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Cookies from 'js-cookie';

Vue.use(Router);

export const router = new Router({
    // mode:'history',
  routes: [
      {
        path:'/*',
          redirect:{
            name:'home'
          }
      },
    {
      path: "/home",
      name: "home",
      component: Home,
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    },
      {
          path: "/login",
          name: "login",
          component: () =>
              import(/* webpackChunkName: "about" */ "./views/Login.vue")
      },
      {
          path: "/pam/:id",
          name: "pam",
          component: () =>
              import(/* webpackChunkName: "about" */ "./views/Pam.vue")
      },
      {
          path: "/pam2",
          name: "pam2",
          component: () =>
              import(/* webpackChunkName: "about" */ "./views/Pam2.vue")
      },
      {
          path: "/query1",
          name: "query1",
          component: () =>
              import(/* webpackChunkName: "about" */ "./views/Query1.vue")
      }
  ]
});

router.beforeEach((to,from,next)=>{
  if (!Cookies.get("user") && to.name != 'login'){
      //没有登录切，不是去的登录页面
      alert("请登录")
      next({
          name:'login'
      })
  }else {
      next()
  }
})

router.afterEach((to) =>{
    console.log('路由切换完成')
})