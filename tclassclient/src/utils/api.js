import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router'
import qs from 'qs'
import { getOauth2Info } from '@/utils/auth'
// 设置响应拦截器
axios.interceptors.response.use(success => {
  if (success.status && success.status === 200 && success.data.code === 500) {
    Message.error({ message: success.data.msg })
    return
  }
  if (success.data.code === 200) {
    return success.data
  }
  if (success.data.code === 504 || success.data.code === 404) {
    Message.error({ message: '找不到服务器' })
  } else if (success.data.code === 403) {
    Message.error({ message: '权限不足，请联系管理员' })
  } else if (success.data.code === 401) {
    Message.error({ message: success.data.msg ? success.data.msg : '尚未登录，请登录' })
    router.replace('/')
  } else {
    if (success.data.msg) {
      Message.error({ message: success.data.msg })
    } else {
      Message.error({ message: '未知错误!' })
    }
  }
  return success.data
}, error => {
  console.log(error)
  if (error.response.status === 504 || error.response.status === 404) {
    Message.error({ message: '找不到服务器' })
  } else if (error.response.status === 403) {
    Message.error({ message: '权限不足，请联系管理员' })
  } else if (error.response.status === 401) {
    Message.error({ message: error.response.data.msg ? error.response.data.msg : '尚未登录，请登录' })
    router.replace('/')
  } else {
    if (error.response.data.message) {
      Message.error({ message: error.response.data.message })
    } else {
      Message.error({ message: '未知错误!' })
    }
  }
  return
})

// 设置请求拦截器 添加token认证
axios.interceptors.request.use(config => {
  if (config.url !== '/oauth/login') { // 登录请求不携带token
    const OAUTH2 = getOauth2Info()
    // console.log(OAUTH2)
    if (OAUTH2) {
      config.headers.common['Authorization'] = 'Bearer ' + OAUTH2.access_token
    }
  }
  return config
}, error => {
  return Promise.reject(error)
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
export const loginPostRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: qs.stringify(params)
  })
}
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params
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
    data: params
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

export const downLoadFileRequest = (url,params) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
    params: params,
    responseType: 'blob'
  }).then(resp => {
    const { data, headers } = resp
    const fileName = headers['content-disposition'].replace(/\w+;filename=(.*)/, '$1')
    // 此处当返回json文件时需要先对data进行JSON.stringify处理，其他类型文件不用做处理
    const blob = new Blob([data], {type: headers['content-type']})
    let dom = document.createElement('a')
    let url = window.URL.createObjectURL(blob)
    dom.href = url
    dom.download = decodeURI(fileName)
    dom.style.display = 'none'
    document.body.appendChild(dom)
    dom.click()
    dom.parentNode.removeChild(dom)
    window.URL.revokeObjectURL(url)
  })
}

