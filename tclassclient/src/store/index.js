import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex)
const vuexLocal = new VuexPersistence({
  storage: window.sessionStorage
})

export default new Vuex.Store({
  state: {
    currentUser: {}
  },
  mutations: {
    INIT_CURRENTUSER(state, user) {
      state.currentUser = user
    }
  },
  actions: {},
  modules: {},
  plugins: [vuexLocal.plugin] // vuex 持久化
})
