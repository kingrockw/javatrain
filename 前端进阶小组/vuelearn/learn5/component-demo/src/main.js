import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import mydialog from './components/mydialog/pluginIndex'
Vue.config.productionTip = false;


/**
 * 异步组件
 */
//
// Vue.component('async-example', function (resolve, reject) {
//     setTimeout(function () {
//         // 向 `resolve` 回调传递组件定义
//         resolve({
//             template: '<div>I am async!</div>'
//         })
//     }, 1000)
// })

Vue.use(mydialog)

new Vue({
  router,
  store,
  render: h => h(App),
  el:'#app'
})

//.$mount("#app");
