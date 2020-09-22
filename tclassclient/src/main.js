import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import fontawesome from '@fortawesome/fontawesome'
import solid from '@fortawesome/fontawesome-free-solid'
import regular from '@fortawesome/fontawesome-free-regular'
import brands from '@fortawesome/fontawesome-free-brands'

fontawesome.library.add(solid)
fontawesome.library.add(regular)
fontawesome.library.add(brands)

Vue.use(ElementUI)

Vue.prototype.$alert = ElementUI.MessageBox.alert
Vue.prototype.$message = ElementUI.Message
Vue.prototype.$confirm = ElementUI.MessageBox.confirm
Vue.prototype.$notify = ElementUI.Notification

import { postRequest } from './utils/api'
import { postKeyValueRequest } from './utils/api'
import { putRequest } from './utils/api'
import { deleteRequest } from './utils/api'
import { getRequest } from './utils/api'
import '@/style/element-variables.scss'

Vue.prototype.postRequest = postRequest
Vue.prototype.postKeyValueRequest = postKeyValueRequest
Vue.prototype.putRequest = putRequest
Vue.prototype.deleteRequest = deleteRequest
Vue.prototype.getRequest = getRequest

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
