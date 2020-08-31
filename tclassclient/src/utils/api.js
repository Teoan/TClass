import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router'
import qs from 'qs'

axios.interceptors.response.use(success => {
  if (success.status && success.status === 200 && success.data.status === 500) {
    Message.error({ message: success.data.msg })
    return
  }
  if (success.data.code === -1) {
    Message.error({ message: success.data.msg })
  }
  return success.data
}, error => {
  if (error.response.status === 504 || error.response.status === 404) {
    Message.error({ message: '找不到服务器' })
  } else if (error.response.status === 403) {
    Message.error({ message: '权限不足，请联系管理员' })
  } else if (error.response.status === 401) {
    Message.error({ message: error.response.data.msg ? error.response.data.msg : '尚未登录，请登录' })
    router.replace('/')
  } else {
    if (error.response.data.msg) {
      Message.error({ message: error.response.data.msg })
    } else {
      Message.error({ message: '未知错误!' })
    }
  }
  return
})

const base = ''

export const postKeyValueRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function(data) {
      let ret = ''
      for (const i in data) {
        ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: qs.stringify(params)
  })
}
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params
  })
}
export const getRequest = (url, params) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
    params: params
  })
}
export const deleteRequest = (url, params) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    params: params
  })
}
export const getDataRequest = (url, params) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
    params: params,
    responseType: 'blob'
  })
}

