<template>
  <div class="userinfo-div">
    <el-card class="box-card-info" shadow="hover">
      <div slot="header" class="clearfix">
        <span v-if="isEditOtherUserDate">关于ta</span>
        <span v-else>关于我</span>
      </div>
      <div>
        <el-avatar :size="150" :src="getUserAvatarUrl" />
      </div>
      <div class="user-name">
        {{ userInfo.name }}
      </div>
      <div class="user-role-name border-bottom">
        {{ userInfo.role.zhName }}
      </div>
      <div class="user-orther-info">
        <div class="item border-bottom">
          <span>
            <i class="fas fa-map-marker-alt" />
          </span>
          {{ userInfo.nativePlace.replace(/[,]/g, '') }}
        </div>
        <div class="item border-bottom">
          <span>
            <i class="fas fa-envelope" />
          </span>
          {{ userInfo.email }}
        </div>
        <div class="item border-bottom">
          <span>
            <i class="fas fa-phone" />
          </span>
          {{ userInfo.phone }}
        </div>
        <div class="login-time">
          <div>上一次登录时间</div>
          <div>{{ userInfo.loginTime }}</div>
        </div>
      </div>

    </el-card>
    <el-card class="box-card-data" shadow="hover">
      <el-tooltip v-if="!isEditOtherUserDate" effect="dark" content="点击更换头像" placement="top">
        <el-upload
          class="avatar-uploader"
          action="/user/avatar"
          :headers="avatarUploaderHeaders"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :on-error="handleAvatarError"
        >
          <img v-if="userInfo.avatarUrl" :src="getUserAvatarUrl()" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-tooltip>
      <div>
        <el-form ref="userData" v-loading="userDataLoading" :model="userData" label-width="auto" :rules="rules" :inline="true" :status-icon="true">
          <el-form-item v-if="isEditOtherUserDate" label="名字">
            <el-input v-model="userData.name" class="input-width" />
          </el-form-item>
          <el-form-item v-if="isEditOtherUserDate" label="权限">
            <el-select v-model="userData.roleId" placeholder="请选择权限" filterable>
              <el-option
                v-for="item in roles"
                :key="item.id"
                :label="item.zhName"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="userData.phone" class="input-width" />
          </el-form-item>
          <el-form-item label="籍贯">
            <el-cascader
              v-model="selectedNativePlaceOptions"
              :options="options"
              class="input-width"
            />
          </el-form-item>
          <el-form-item label="邮箱地址" prop="email">
            <el-input v-model="userData.email" class="input-width" />
          </el-form-item>
          <el-form-item label="民族">
            <el-select v-model="userData.nationId" placeholder="请选择民族" filterable>
              <el-option
                v-for="item in nations"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="政治面貌">
            <el-select v-model="userData.politicId" placeholder="请选择政治面貌" filterable>
              <el-option
                v-for="item in politicsStatusList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="userData.gender" placeholder="请选择性别">
              <el-option label="男" value="男" />
              <el-option label="女" value="女" />
            </el-select>
          </el-form-item>
          <el-form-item v-if="isEditOtherUserDate" label="校内职务">
            <el-select v-model="userData.posId" placeholder="校内职务">
              <el-option
                v-for="item in positions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="校内部门">
            <el-select v-model="userData.departmentIdList" multiple placeholder="请选择" style="width:600px">
              <el-option
                v-for="item in departments"
                :key="item.value"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <div>
            <el-form-item label="家庭住址">
              <el-input v-model="userData.address" type="textarea" :autosize="{ minRows: 2, maxRows: 4}" class="address-textarea" />
            </el-form-item>
          </div>
          <div class="submit-btn">
            <el-button type="primary" @click="submitForm('userData')">修改资料</el-button>
          </div>
        </el-form>
        <el-divider v-if="!isEditOtherUserDate" />
        <el-form v-if="!isEditOtherUserDate" ref="passData" v-loading="passDataLoading" label-width="auto" :model="pass" :rules="rules">
          <el-form-item label="新密码" prop="password">
            <el-input v-model="pass.password" type="password" autocomplete="off" class="input-width" />
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPassword">
            <el-input v-model="pass.checkPassword" type="password" autocomplete="off" class="input-width" />
          </el-form-item>
          <el-form-item class="submit-btn">
            <el-button type="primary" @click="submitForm('passData')">修改密码</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { regionData, CodeToText, TextToCode } from 'element-china-area-data'
import { getOauth2Info } from '@/utils/auth'

export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.pass.checkPassword !== '') {
          this.$refs.passData.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.pass.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      // 需要更新的用户字段
      userData: {
        id: null,
        name: null,
        gender: null,
        nationId: null,
        politicId: null,
        email: null,
        phone: null,
        address: null,
        nativePlace: null,
        posId: null,
        roleId: null,
        departmentIdList: []
      },
      // 显示的用户字段
      userInfo: {
        name: null,
        email: null,
        phone: null,
        nativePlace: null,
        loginTime: null,
        role: { zhName: null },
        avatarUrl: null
      },
      // 判断是否修改其他用户数据
      isEditOtherUserDate: this.$route.query.id !== undefined,
      rules: {
        email: [{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }],
        phone: [{ pattern: /^1[3|4|5|7|8][0-9]\d{8}$/, message: '请输入正确的电话号码', trigger: ['blur', 'change'] }],
        password: [
          { validator: validatePass, trigger: 'blur' }, { min: 6, message: '密码最少为6位', trigger: 'blur' }
        ],
        checkPassword: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      userDataLoading: false,
      passDataLoading: false,
      userDepartmentList: [],
      nations: [],
      departments: [],
      politicsStatusList: [],
      positions: [],
      roles: [],
      pass: {
        password: '',
        checkPassword: ''
      },
      options: regionData,
      selectedNativePlaceOptions: [],
      // 配置上传请求头
      avatarUploaderHeaders: {
        Authorization: 'Bearer ' + getOauth2Info().access_token
      }
    }
  },
  computed: {
    currentUser() {
      if (this.$store.state.currentUser.role !== undefined) {
        return this.$store.state.currentUser
      } else {
        return JSON.parse(localStorage.getItem('INIT_CURRENTUSER'))
      }
    }
  },
  created() {
    this.initUserData()
    this.getNations()
    this.getDepartments()
    this.getpoliticsStatusList()
    this.getPositions()
    this.getRoles()
  },
  methods: {
    getNations() {
      this.getRequest('/user/nations').then(resp => {
        if (resp.code === 200) {
          this.nations = resp.data
        }
      })
    },
    getDepartments() {
      this.getRequest('/user/departments').then(resp => {
        if (resp.code === 200) {
          this.departments = resp.data
        }
      })
    },
    getpoliticsStatusList() {
      this.getRequest('/user/political').then(resp => {
        if (resp.code === 200) {
          this.politicsStatusList = resp.data
        }
      })
    },
    getPositions() {
      this.getRequest('/user/positions').then(resp => {
        if (resp.code === 200) {
          this.positions = resp.data
        }
      })
    },
    getRoles() {
      this.getRequest('/user/roles').then(resp => {
        if (resp.code === 200) {
          this.roles = resp.data
        }
      })
    },
    nativePlaceCodeToText() {
      var text = ''
      for (var i in this.selectedNativePlaceOptions) {
        text = text + CodeToText[this.selectedNativePlaceOptions[i]] + ','
      }
      this.userData.nativePlace = text.substring(0, text.lastIndexOf(','))
    },
    nativePlaceTextToCode() {
      var code = []
      var nativeText = this.userData.nativePlace.split(',')
      code[0] = TextToCode[nativeText[0]].code
      code[1] = TextToCode[nativeText[0]][nativeText[1]].code
      code[2] = TextToCode[nativeText[0]][nativeText[1]][nativeText[2]].code
      this.selectedNativePlaceOptions = code
    },
    initUserData() {
      var userId = this.currentUser.id
      if (this.isEditOtherUserDate) {
        userId = this.$route.query.id
      }
      this.getRequest('/user/userInfo/' + userId).then(resp => {
        if (resp.code === 200) {
          // 初始化需要显示的数据
          this.userInfo.name = resp.data.name
          this.userInfo.email = resp.data.email
          this.userInfo.phone = resp.data.phone
          this.userInfo.nativePlace = resp.data.nativePlace
          this.userInfo.loginTime = resp.data.loginTime
          this.userInfo.role = resp.data.role
          this.userInfo.avatarUrl = resp.data.avatarUrl
          // 初始化需要修改的数据
          this.userData.id = resp.data.id
          this.userData.name = resp.data.name
          this.userData.roleId = resp.data.roleId
          this.userData.gender = resp.data.gender
          this.userData.nationId = resp.data.nationId
          this.userData.politicId = resp.data.politicId
          this.userData.posId = resp.data.posId
          this.userData.email = resp.data.email
          this.userData.phone = resp.data.phone
          this.userData.address = resp.data.address
          this.userData.nativePlace = resp.data.nativePlace
          this.userData.departmentIdList = resp.data.departmentIdList
          this.nativePlaceTextToCode()
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 管理员修改用户信息
          if (formName === 'userData') {
            this.userDataLoading = true
            this.nativePlaceCodeToText()
            if (this.isEditOtherUserDate) {
              this.putRequest('/user/admin/', this.userData).then(resp => {
                if (resp.code === 200) {
                  this.$message.success('资料修改成功！')
                  this.userDataLoading = false
                }
              }).catch(error => {
                this.userDataLoading = false
                console.log(error)
              })
              // 用户修改自己的信息
            } else {
              this.putRequest('/user/', this.userData).then(resp => {
                if (resp.code === 200) {
                  this.$message.success('资料修改成功！')
                  this.userDataLoading = false
                  this.$store.commit('INIT_CURRENTUSER', resp.data)
                  this.userInfo = resp.data
                }
              }).catch(error => {
                this.userDataLoading = false
                console.log(error)
              })
            }
          } else if (formName === 'passData') {
            this.passDataLoading = true
            this.putRequest('/user/admin/reSetPassword/' + this.userData.id).then(resp => {
              if (resp.code === 200 && resp.data) {
                this.$message.success('密码修改成功！')
                this.passDataLoading = false
                this.pass.password = ''
                this.pass.checkPassword = ''
              }
            }).catch(error => {
              this.passDataLoading = false
              console.log(error)
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleAvatarSuccess(res, file) {
      this.getRequest('/user/' + this.currentUser.id).then(resp => {
        if (resp.code === 200) {
          // 刷新当前用户数据
          this.$store.commit('INIT_CURRENTUSER', resp.data)
          localStorage.setItem('INIT_CURRENTUSER', JSON.stringify(resp.data))
          this.userInfo.avatarUrl = resp.data.avatarUrl + '?count=' + Math.random()
          this.$message.success('头像修改成功！')
        }
      }).catch(error => {
        console.log(error)
      })
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    handleAvatarError(err, file, fileList) {
      err = JSON.parse(err.message)
      this.$message.error(err.message)
    },
    getUserAvatarUrl() {
      return '/user/avatar/get?photoPath=' + this.userInfo.avatarUrl
    }
  }
}
</script>

<style scoped>
.userinfo-div {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items:center;
  height: 100%;
}
.box-card-info {
  text-align: center;
  width: 15%;
  min-width: 230px;
  height: 100%;
}
.user-name {
  font-weight: 700;
}
.user-role-name {
  padding-top: 10px;
  padding-bottom: 10px;
  font-weight: 400;
  font-size: 14px;
  color: #777777;
}
.item {
  padding: 15px 0;
  font-weight: 500;
}
.item span {
  margin-right: 10px;
}
.user-orther-info {
  text-align:left;
  color: #777777;
}
.login-time {
  text-align:center;
  padding: 10px 0;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  height: 100px;
}
.login-time div {
  margin-bottom: 10px;
}
.box-card-data {
  width: 80%;
  min-width: 500px;
  height: 100%;
  overflow: auto;
}
.submit-btn {
  display: flex;
  justify-content: flex-end
}
.input-width {
  width: 221px;
}
.border-bottom {
  border-bottom: 1px solid #EBEEF5;
  border-bottom-width: 1px;
  border-bottom-style: solid;
  border-bottom-color: rgb(235, 238, 245);
}
.address-textarea /deep/ .el-textarea__inner {
  width: 600px;
}
.avatar-uploader {
  width: 178px;
  height: 178px;
  margin-bottom: 20px;
  margin-left: 30px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
