<template>
  <div class="login-div">
    <el-form
      ref="loginForm"
      v-loading="loading"
      element-loading-text="正在登录..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(235, 238, 245, 0.8)"
      :rules="rules"
      :model="loginForm"
      class="loginContainer"
    >
      <h3 class="loginTitle">登录</h3>
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          size="normal"
          type="text"
          auto-complete="off"
          placeholder="请输入用户名"
          suffix-icon="el-icon-user"
        />
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          size="normal"
          type="password"
          auto-complete="off"
          placeholder="请输入密码"
          show-password
        />
      </el-form-item>
      <el-form-item prop="code">
        <el-input
          v-model="loginForm.code"
          size="normal"
          type="text"
          auto-complete="off"
          placeholder="点击图片更换验证码"
          style="width: 200px"
          @keydown.enter.native="submitLogin"
        />
        <el-tooltip content="点击可刷新验证码" placement="top">
          <img :src="vcUrl" alt="" style="cursor: pointer" @click="updateVerifyCode">
        </el-tooltip>
      </el-form-item>
      <el-checkbox v-model="loginForm.remember" class="loginRemember">记住我</el-checkbox>
      <el-button
        size="normal"
        type="primary"
        style="width: 100%;"
        round
        @click="submitLogin"
      >登录</el-button>
    </el-form>
  </div>
</template>

<script>
import { setLoginInfo } from '@/utils/auth'
import { getLoginInfo } from '@/utils/auth'
import { setOauth2Info } from '@/utils/auth'

export default {
  name: 'Login',
  data() {
    return {
      loading: false,
      vcUrl: '/oauth/verifyCode.jpg?comut=' + Math.random(),
      loginForm: {
        username: '',
        password: '',
        code: '',
        remember: true,
        grant_type: 'password'
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }, { min: 6, message: '密码最少为6位', trigger: 'blur' }],
        code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
      }
    }
  },
  created() {
    if (getLoginInfo() !== null) {
      this.loginForm = getLoginInfo()
    }
    this.loginForm.code = ''
  },
  methods: {
    updateVerifyCode() {
      this.vcUrl = '/oauth/verifyCode.jpg?count=' + Math.random()
    },
    submitLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true
          this.loginPostRequest('/oauth/login', this.loginForm).then(resp => {
            this.loading = false
            if (resp.code === 200) {
              if (this.loginForm.remember) {
                setLoginInfo(this.loginForm)
              } else {
                setLoginInfo(null)
              }
              // 保存认证token信息
              this.$store.commit('OAUTH2', resp.data)
              setOauth2Info(resp.data)
              this.getAndSaveCurrentUser()
              this.$message.success(resp.msg)
              this.$router.replace('/home')
            } else {
              this.vcUrl = '/oauth/verifyCode.jpg?count=' + Math.random()
            }
          }).catch(error => {
            this.loading = false
            console.log(error)
          })
        } else {
          return false
        }
      })
    },
    getAndSaveCurrentUser() {
      this.getRequest('/oauth/getCurrentUser').then(resp => {
        if (resp.code === 200) {
        // 将当前用户信息保存到本地和store
          this.$store.commit('INIT_CURRENTUSER', resp.data)
          localStorage.setItem('INIT_CURRENTUSER', JSON.stringify(resp.data))
        }
      })
    }
  }
}
</script>

<style>
  .loginContainer {
      border-radius: 15px;
      background-clip: padding-box;
      width: 350px;
      padding: 15px 35px 15px 35px;
      background: #fff;
      border: 1px solid #eaeaea;
      box-shadow: 0 0 25px #cac6c6;
  }
  .login-div{
    height: 100%;
    width: 100%;
    display:flex;
    align-items:center;
    justify-content: center;
  }
  .loginTitle {
      margin: 15px auto 20px auto;
      text-align: center;
      color: #505458;
  }
  .loginRemember {
      text-align: right;
      margin: 0px 0px 15px 0px;
  }
  .el-form-item__content{
      display: flex;
      align-items: center;
  }
</style>
