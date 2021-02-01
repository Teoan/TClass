const loginInfoKey = 'loginInfo'
const EXP = 7 // day
export function setLoginInfo(data) {
  localStorageSetItem(loginInfoKey, data)
}

// EXP * 24 * 60 * 60 * 1 * 1000
export function getLoginInfo() {
  return localStorageGetItem(loginInfoKey, EXP * 24 * 60 * 60 * 1 * 1000)
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
    console.log('用户信息已过期')
    return null
  } else {
    return dataObj.data
  }
}
