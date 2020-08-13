<template>
  <div class="userinfo-div">
    <el-card class="box-card-info" shadow="hover">
      <div slot="header" class="clearfix">
        <span>关于我</span>
      </div>
      <div>
        <el-avatar :src="currentUser.avatarUrl" :size="150" />
      </div>
      <div class="user-name">
        {{ currentUser.name }}
      </div>
      <div class="user-role-name border-bottom">
        {{ currentUser.role.zhName }}
      </div>
      <div class="user-orther-info">
        <div class="item border-bottom">
          <span>
            <i class="fas fa-map-marker-alt" />
          </span>
          {{ currentUser.nativePlace.replace(/[,]/g,'') }}
        </div>
        <div class="item border-bottom">
          <span>
            <i class="fas fa-envelope" />
          </span>
          {{ currentUser.email }}
        </div>
        <div class="item border-bottom">
          <span>
            <i class="fas fa-phone" />
          </span>
          {{ currentUser.phone }}
        </div>
        <div class="login-time">
          <div>最近登录时间</div>
          <div>{{ currentUser.loginTime }}</div>
        </div>
      </div>

    </el-card>
    <el-card class="box-card-data" shadow="hover">
      <div>
        <el-form ref="userData" v-loading="userDataLoading" :model="userData" label-width="auto" :rules="rules" :inline="true" :status-icon="true">
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
            <el-select v-model="userData.nationId" placeholder="请选择您的民族" filterable>
              <el-option
                v-for="item in nations"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="政治面貌">
            <el-select v-model="userData.politicId" placeholder="请选择您的政治面貌" filterable>
              <el-option
                v-for="item in politicsstatuses"
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
          <el-form-item label="校内部门">
            <el-select v-model="userData.departmentIdList" multiple placeholder="请选择" style="width:400px">
              <el-option
                v-for="item in departments"
                :key="item.value"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="家庭住址">
            <el-input v-model="userData.address" type="textarea" class="address-textarea" />
          </el-form-item>
          <div class="submit-btn">
            <el-button type="primary" @click="submitForm('userData')">修改资料</el-button>
          </div>
        </el-form>
        <el-divider />
        <el-form ref="passData" v-loading="passDataLoading" label-width="auto" :model="pass" :rules="rules">
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
      userData: {
        id: '',
        gender: '',
        nationId: '',
        politicId: '',
        email: '',
        phone: '',
        address: '',
        nativePlace: '',
        departmentIdList: []
      },
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
      politicsstatuses: [],
      pass: {
        password: '',
        checkPassword: ''
      },
      options: regionData,
      selectedNativePlaceOptions: []
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.currentUser
    }
  },
  created() {
    this.initUserData()
    this.getNations()
    this.getDepartments()
    this.getPoliticsstatuses()
  },
  methods: {
    getNations() {
      this.getRequest('/student/nations').then(resp => {
        if (resp.code === 0) {
          this.nations = resp.data
        }
      })
    },
    getDepartments() {
      this.getRequest('/student/departments').then(resp => {
        if (resp.code === 0) {
          this.departments = resp.data
        }
      })
    },
    getPoliticsstatuses() {
      this.getRequest('/student/politicsstatuses').then(resp => {
        if (resp.code === 0) {
          this.politicsstatuses = resp.data
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
      this.userData.id = this.currentUser.id
      this.userData.gender = this.currentUser.gender
      this.userData.nationId = this.currentUser.nationId
      this.userData.politicId = this.currentUser.politicId
      this.userData.email = this.currentUser.email
      this.userData.phone = this.currentUser.phone
      this.userData.address = this.currentUser.address
      this.userData.nativePlace = this.currentUser.nativePlace
      this.userData.departmentIdList = this.currentUser.departmentIdList
      this.nativePlaceTextToCode()
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (formName === 'userData') {
            this.userDataLoading = true
            this.nativePlaceCodeToText()
            this.putRequest('/student/', this.userData).then(resp => {
              if (resp.code === 0) {
                this.$message.success('资料修改成功！')
                this.userDataLoading = false
                this.$store.commit('INIT_CURRENTUSER', resp.data)
              }
            }).catch(error => {
              this.userDataLoading = false
              console.log(error)
            })
          } else if (formName === 'passData') {
            this.passDataLoading = true
            this.putRequest('/student/', { id: this.userData.id, password: this.pass.password }).then(resp => {
              if (resp.code === 0 && resp.data) {
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
    }
  }
}
</script>

<style>
.userinfo-div {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
}
.box-card-info {
  text-align: center;
  width: 250px;
  height: 600px;
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
  width: 1200px;
  height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
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
.address-textarea .el-textarea__inner {
  width: 820px;
}
</style>
