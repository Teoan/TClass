<template>
  <div>
    <el-form
      ref="loginForm"
      v-loading="loading"
      element-loading-text="正在登录..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
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
        />
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          size="normal"
          type="password"
          auto-complete="off"
          placeholder="请输入密码"
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
        <img :src="vcUrl" alt="" style="cursor: pointer" @click="updateVerifyCode">
      </el-form-item>
      <el-checkbox v-model="loginForm.remember" class="loginRemember">记住我</el-checkbox>
      <el-button
        size="normal"
        type="dark"
        style="width: 100%;"
        round
        @click="submitLogin"
      >登录</el-button>
    </el-form>
  </div>
</template>

<script>

export default {
  name: 'Login',
  data() {
    return {
      loading: false,
      vcUrl: '/verifyCode.jpg?comut=' + Math.random(),
      loginForm: {
        username: '1724111400',
        password: '123456',
        code: '',
        remember: true
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    updateVerifyCode() {
      this.vcUrl = '/verifyCode.jpg?count=' + Math.random()
    },
    submitLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true
          this.postRequest('/login', this.loginForm).then(resp => {
            this.loading = false
            if (resp.code === 0) {
              this.$store.commit('INIT_CURRENTUSER', resp.data)
              // window.sessionStorage.setItem('user', JSON.stringify(resp.obj))
              // const path = this.$route.query.redirect
              // this.$router.replace((path === '/' || path === undefined) ? '/home' : path)
              this.$router.replace('/home')
            } else {
              this.vcUrl = '/verifyCode.jpg?count=' + Math.random()
            }
          }).catch(error => {
            this.loading = false
            console.log(error)
          })
        } else {
          return false
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
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
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
