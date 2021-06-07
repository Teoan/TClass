import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex)
const vuexLocal = new VuexPersistence({
  storage: window.sessionStorage
})

export default new Vuex.Store({
  state: {
    currentUser: {},
    oauth2: {}
  },
  mutations: {
    INIT_CURRENTUSER(state, user) {
      state.currentUser = user
    },
    OAUTH2(state, oauth2) {
      state.oauth2 = oauth2
    }
  },
  actions: {},
  modules: {},
  plugins: [vuexLocal.plugin] // vuex 持久化
})
