import {loginPostRequest} from '@/utils/api'

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
    if (!loginInfo) {
        console.log('用户信息已过期')
        return null
    }
    return loginInfo
}

export function setOauth2Info(data) {
    localStorageSetItem(oauth2InfoKey, data)
}

export function getOauth2Info() {
    var oauth2Info = localStorageGetItem(oauth2InfoKey, oauth2InfoEXP * 60 * 1000)
    if (!oauth2Info) {
        console.log('认证信息已过期')

        var oauth2Data = localStorage.getItem(oauth2InfoKey)
        oauth2Info = JSON.parse(oauth2Data).data
        // console.log('过期的认证信息' + oauth2Info)
        loginPostRequest('/oauth/login', {
            grant_type: 'refresh_token',
            refresh_token: oauth2Info.refresh_token
        }).then(resp => {
            if (resp.code === 200) {
                setOauth2Info(resp.data)
                oauth2Info = resp.data
                // console.log('刷新后的认证信息' + oauth2Info)
            }
        })
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
