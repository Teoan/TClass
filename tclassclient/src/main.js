import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import fontawesome from '@fortawesome/fontawesome'
import solid from '@fortawesome/fontawesome-free-solid'
import regular from '@fortawesome/fontawesome-free-regular'
import brands from '@fortawesome/fontawesome-free-brands'

fontawesome.library.add(solid)
fontawesome.library.add(regular)
fontawesome.library.add(brands)

import {
  Button,
  Input,
  Table,
  TableColumn,
  Dialog,
  Card,
  Container,
  Icon,
  Select,
  Form,
  Tag,
  Tree,
  Pagination,
  Badge,
  Loading,
  MessageBox,
  Menu,
  Tabs,
  TabPane,
  Breadcrumb,
  BreadcrumbItem,
  Dropdown,
  Steps,
  Step,
  Tooltip,
  Popover,
  Collapse,
  FormItem,
  Checkbox,
  Header,
  DropdownMenu,
  DropdownItem,
  Aside,
  Main,
  MenuItem,
  Submenu,
  Option,
  Col,
  Row,
  Upload,
  Radio,
  DatePicker,
  RadioGroup,
  CollapseItem,
  Switch
} from 'element-ui'
Vue.use(Switch)
Vue.use(CollapseItem)
Vue.use(Radio)
Vue.use(RadioGroup)
Vue.use(DatePicker)
Vue.use(Upload)
Vue.use(Row)
Vue.use(Col)
Vue.use(Option)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(Header)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Checkbox)
Vue.use(FormItem)
Vue.use(Collapse)
Vue.use(Popover)
Vue.use(Menu)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Dropdown)
Vue.use(Steps)
Vue.use(Step)
Vue.use(Tooltip)
Vue.use(Tree)
Vue.use(Pagination)
Vue.use(Badge)
Vue.use(Loading)
Vue.use(Button)
Vue.use(Input)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Dialog)
Vue.use(Card)
Vue.use(Container)
Vue.use(Icon)
Vue.use(Select)
Vue.use(Form)
Vue.use(Tag)

Vue.prototype.$alert = MessageBox.alert
Vue.prototype.$confirm = MessageBox.confirm

import { postRequest } from './utils/api'
import { postKeyValueRequest } from './utils/api'
import { putRequest } from './utils/api'
import { deleteRequest } from './utils/api'
import { getRequest } from './utils/api'
import 'element-theme-ink'
// import 'element-theme-dark'

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
