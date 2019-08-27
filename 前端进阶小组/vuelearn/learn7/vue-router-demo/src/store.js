import Vue from "vue";
import Vuex from "vuex";
import {router} from './router'
import Sync from 'vuex-router-sync'

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {},
  mutations: {},
  actions: {}
});

Sync.sync(store, router, {
    moduleName: 'RouteModule'
})