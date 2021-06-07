const loginInfoKey = 'loginInfo'
const oauth2InfoKey = 'oauth2Info'
const loginInfoEXP = 7 // day
const oauth2InfoEXP = 30 // min 这里的存在时间与后端相同
export function setLoginInfo(data) {
  localStorageSetItem(loginInfoKey, data)
}

// EXP * 24 * 60 * 60 * 1 * 1000
export function getLoginInfo() {
  const loginInfo = localStorageGetItem(loginInfoKey, loginInfoEXP * 24 * 60 * 60 * 1 * 1000)
  if (JSON.stringify(loginInfo) === '{}') {
    console.log('用户信息已过期')
    return null
  }
  return loginInfo
}

export function setOauth2Info(data) {
  localStorageSetItem(oauth2InfoKey, data)
}

export function getOauth2Info() {
  const oauth2Info = localStorageGetItem(oauth2InfoKey, oauth2InfoEXP * 60 * 1000)
  if (JSON.stringify(oauth2Info) === '{}') {
    console.log('认证信息已过期')
    return null
  }
  return oauth2Info
}
// localStorage的Set方法封装:记录时间
export function localStorageSetItem(key, value) {
  const curTime = new Date().getTime()
  localStorage.setItem(key, JSON.stringify({ data: value, time: curTime }))
}
// localStorage的Get方法封装:根据过期时间获取
export function localStorageGetItem(key, exp) {
  const data = localStorage.getItem(key)
  const dataObj = JSON.parse(data)
  if (new Date().getTime() - dataObj.time > exp) {
    return null
  } else {
    return dataObj.data
  }
}
