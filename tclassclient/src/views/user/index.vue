<template>
  <span class="userMana-div">
    <el-card
      class="box-card-userdata"
      shadow="hover"
      :body-style="{height:'100%'}"
    >
      <div class="select-div">
        <el-input v-model="selectUserData.name" prefix-icon="el-icon-search" placeholder="根据学生姓名搜索" style="min-width: 200px;width: 20%;margin-right:20px" @keydown.enter.native="selectStudent" />
        <el-button type="primary" style="margin-right:20px" icon="el-icon-search" @click="selectStudent">搜索</el-button>
        <el-dialog title="数据管理" :visible.sync="dialogDataVisible">
          <div class="student-date-but-div">
            <div>
              <el-upload
                class="upload"
                action="/user/admin/import"
                :on-success="onSeccessUpload"
                :on-error="onErrorUpload"
                :before-upload="beforeUpload"
                :disabled="uploadDisabled"
                :multiple="false"
                accept=".xlsx"
                :show-file-list="false"
              >
                <el-button
                  v-loading="uploadLoading"
                  type="primary"
                  icon="el-icon-upload2"
                  :disabled="uploadDisabled"
                >导入学生数据</el-button>
              </el-upload>
              <el-button type="primary" style="margin-left:20px" icon="el-icon-download" @click="exportAllStudentData">导出学生数据</el-button>
              <el-tooltip effect="dark" content="模板内容请参考导出的学生数据！" placement="top">
                <el-link style="margin-left:20px" :underline="false" icon="el-icon-download" type="success" @click="getTemplate">下载导入模板</el-link>
              </el-tooltip>
            </div>
          </div>
        </el-dialog>
        <el-button type="primary" style="margin-right:20px" icon="el-icon-s-grid" @click="dialogDataVisible = true">学生数据管理</el-button>
        <el-dialog title="高级搜索" :visible.sync="dialogSelectVisible">
          <el-form ref="selectUserData" :model="selectUserData" label-width="auto" :inline="true" :status-icon="true">
            <el-form-item label="籍贯">
              <el-cascader
                v-model="selectedNativePlaceOptions"
                :options="regionData"
              />
            </el-form-item>
            <el-form-item label="民族">
              <el-select v-model="selectUserData.nationId" placeholder="请选择民族" filterable>
                <el-option
                  v-for="item in nations"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="政治面貌">
              <el-select v-model="selectUserData.politicId" placeholder="请选择政治面貌" filterable>
                <el-option
                  v-for="item in politicsstatuses"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="班级职务">
              <el-select v-model="selectUserData.posId" placeholder="请选择班级职务" filterable>
                <el-option
                  v-for="item in positions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="性别">
              <el-select v-model="selectUserData.gender" placeholder="请选择性别">
                <el-option label="男" value="男" />
                <el-option label="女" value="女" />
              </el-select>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="selectStudent">查询</el-button>
          </span>
        </el-dialog>
        <el-button type="primary" style="margin-right:20px" icon="el-icon-info" @click="dialogSelectVisible = true">高级搜索</el-button>
        <el-dialog title="批量管理" :visible.sync="dialogBatchVisible">
          <div class="student-date-but-div">
            <template>
              <el-popconfirm
                title="确定批量删除所选学生吗？"
                @onConfirm="deleteStudents"
              >
                <el-button slot="reference" type="danger" style="margin-right:20px">批量删除</el-button>
              </el-popconfirm>
            </template>
            <el-button type="primary" style="margin-right:20px" @click="exportStudentData">批量导出</el-button>
          </div>
        </el-dialog>
        <el-button v-if="multipleSelection.length>0" type="primary" style="margin-right:20px" icon="el-icon-s-data" @click="dialogBatchVisible = true">批量管理</el-button>
      </div>
      <div class="table-div">
        <el-tooltip content="选中可进行批量删除" placement="top-start">
          <el-table
            v-loading="tableLoading"
            :data="userDataList"
            style="width: 100%"
            height="100%"
            border
            @selection-change="handleSelectionChange"
          >
            <el-table-column
              type="selection"
              width="55"
            />
            <el-table-column
              prop="name"
              label="学生姓名"
              min-width="100"
            />
            <el-table-column
              prop="id"
              label="学号"
              min-width="110"
            />
            <el-table-column
              prop="gender"
              label="性别"
              min-width="50"
            />
            <el-table-column
              prop="nation.name"
              label="民族"
              min-width="80"
            />
            <el-table-column
              prop="nativePlace"
              label="籍贯"
              :formatter="formatNativePlace"
              min-width="100"
            />
            <el-table-column
              prop="position.name"
              label="班级职务"
              min-width="80"
            />
            <el-table-column
              prop="politicsstatus.name"
              label="政治面貌"
              min-width="100"
            />
            <el-table-column
              prop="email"
              label="邮箱"
              min-width="100"
            />
            <el-table-column
              prop="phone"
              label="手机号码"
              min-width="100"
            />
            <el-table-column
              prop="address"
              label="住址"
              min-width="100"
            />
            <el-table-column
              prop="loginTime"
              label="最近登录时间"
              min-width="120"
            />
            <el-table-column
              fixed="right"
              label="操作"
              width="180"
            >
              <template slot-scope="scope">
                <div class="cell-contain">
                  <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
                  <el-button size="small" type="warning" @click="handleResetPass(scope.row)">重置密码</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-tooltip>
      </div>
      <div class="page-div">
        <el-pagination
          background
          :total="pageInfo.total"
          :page-count="pageInfo.pages"
          :current-page="pageInfo.current"
          hide-on-single-page
          @current-change="currentChange"
        />
      </div>
    </el-card>
  </span>
</template>

<script>
import { regionData, CodeToText } from 'element-china-area-data'

export default {
  data() {
    return {
      userDataList: [],
      pageInfo: '',
      selectInput: '',
      selectUserData: {
        id: null,
        name: null,
        gender: null,
        posId: null,
        nationId: null,
        politicId: null,
        nativePlace: null
      },
      tableLoading: false,
      multipleSelection: [],
      dialogDataVisible: false,
      dialogBatchVisible: false,
      dialogSelectVisible: false,
      regionData: regionData,
      selectedNativePlaceOptions: [],
      nations: [],
      departments: [],
      politicsstatuses: [],
      positions: [],
      uploadDisabled: false,
      uploadLoading: false
    }
  },
  created() {
    this.getData(1, 10)
    this.getNations()
    this.getDepartments()
    this.getPoliticsstatuses()
    this.getPositions()
  },
  methods: {
    getData(current, size) {
      this.tableLoading = true
      this.getRequest('/user/admin/', { current: current, size: size }).then(resp => {
        if (resp.code === 200) {
          this.pageInfo = resp.data
          this.userDataList = this.pageInfo.records
          this.tableLoading = false
        }
      }).catch(error => {
        this.tableLoading = false
        console.log(error)
      })
    },
    handleDelete(row) {
      this.deleteStudentByidList([row.id])
    },
    handleEdit(row) {
      this.$router.push({
        path: '/useredit',
        query: { id: row.id }
      })
    },
    // 重置密码
    handleResetPass(row) {
      this.tableLoading = true
      this.putRequest('/admin/reSetPassword/' + row.id).then(resp => {
        if (resp.code === 200 && resp.data) {
          this.$message.success('密码重置成功！')
          this.tableLoading = false
        }
      }).catch(error => {
        this.tableLoading = false
        console.log(error)
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    formatNativePlace(row, column) {
      return row.nativePlace.replace(/[,]/g, '')
    },
    currentChange(current) {
      this.getData(current, 10)
    },
    selectStudent() {
      this.tableLoading = true
      this.dialogSelectVisible = false
      this.nativePlaceCodeToText()
      this.getRequest('/user/admin/', this.selectUserData).then(resp => {
        if (resp.code === 200) {
          this.pageInfo = resp.data
          this.userDataList = this.pageInfo.records
          this.tableLoading = false
          this.initSelectUserData()
        }
      }).catch(error => {
        this.tableLoading = false
        console.log(error)
      })
    },
    initSelectUserData() {
      this.selectUserData = {
        id: null,
        name: null,
        gender: null,
        posId: null,
        nationId: null,
        politicId: null,
        nativePlace: null
      }
      this.selectedNativePlaceOptions = []
    },
    deleteStudentByidList(idList) {
      this.tableLoading = true
      this.deleteRequest('/user/admin/', idList).then(resp => {
        if (resp.code === 200 && resp.data) {
          this.$message.success('删除成功')
          this.getData(this.pageInfo.current, 10)
          this.tableLoading = false
          this.dialogBatchVisible = false
        }
      }).catch(error => {
        this.tableLoading = false
        console.log(error)
      })
    },
    deleteStudents() {
      var idList = []
      if (this.multipleSelection.length > 0) {
        for (var i = 0; i < this.multipleSelection.length; i++) {
          idList[i] = this.multipleSelection[i].id
        }
        this.deleteStudentByidList(idList)
      }
    },
    exportStudentData() {
      var idList = []
      for (var i = 0; i < this.multipleSelection.length; i++) {
        idList[i] = this.multipleSelection[i].id
      }
      window.open('/user/admin/export?idList=' + idList, '_parent')
    },
    exportAllStudentData() {
      window.open('/user/admin/export?idList=', '_parent')
    },
    // 获取列表数据
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
    getPoliticsstatuses() {
      this.getRequest('/user/political').then(resp => {
        if (resp.code === 200) {
          this.politicsstatuses = resp.data
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
    nativePlaceCodeToText() {
      if (this.selectedNativePlaceOptions.length > 0) {
        var text = ''
        for (var i in this.selectedNativePlaceOptions) {
          text = text + CodeToText[this.selectedNativePlaceOptions[i]] + ','
        }
        this.selectUserData.nativePlace = text.substring(0, text.lastIndexOf(','))
      }
    },
    onSeccessUpload(response, file, fileList) {
      if (response.code === -1) {
        this.$message.error(response.msg)
      } else {
        this.$message.success(response.msg)
      }
      this.uploadDisabled = false
      this.uploadLoading = false
      this.getData(1, 10)
    },
    onErrorUpload(err, file, fileList) {
      this.uploadDisabled = false
      this.uploadLoading = false
      err = JSON.parse(err.message)
      this.$message.error(err.message)
    },
    beforeUpload(file) {
      this.uploadDisabled = true
      this.uploadLoading = true
    },
    getTemplate() {
      window.open('/user/admin/template', '_parent')
    }
  }
}
</script>

<style scoped>
  .box-card-userdata {
    width: 80%;
    height: 100%;
  }
  .box-card-userdata /deep/ .el-card__body {
    display: flex;
    flex-direction: column;
  }
  .userMana-div {
    display: flex;
    justify-content:center;
    align-items:center;
    height: 100%;
  }
  .table-div {
    margin: 20px 0;
    height: 80%;
  }
  .select-div {
    display: flex;
    flex-direction: row;
  }
  .cell-contain {
    display: flex;
    justify-content: space-around;
  }
  .cell-contain /deep/ .el-button {
    margin: 0;
  }
  .student-date-but-div {
    display: flex;
    justify-content:center;
  }
  .upload {
    display: inline-flex;
  }
  .progerss {
    width: 80%;
    margin-top: 10px;
  }
  .page-div {
    min-height: 10%;
  }
</style>
