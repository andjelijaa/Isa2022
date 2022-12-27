import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import router from './router'
import Vuex from 'vuex'

const store = new Vuex.Store({
  state: {
    token: null,
  },
  mutations: {
    setToken (state, value) {
      state.token = value
    }
  },
  actions: {
    setToken ({ commit }, value) {
      commit('setToken', value)
    }
  }
})

createApp(App).use(router).use(store).mount('#app')
