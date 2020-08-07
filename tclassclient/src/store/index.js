import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

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
  modules: {}
})
