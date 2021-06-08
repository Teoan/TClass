<template>
  <span class="workMana-div">
    <el-card
      class="box-card-workdata"
      shadow="hover"
      :body-style="{height:'100%'}"
    >
      <el-input v-model="selectWorkName" placeholder="请输入作业名字搜索" style="width: 30%;min-width: 180px;margin-top:20px" @keydown.enter.native="selectWorkByName" />
      <el-date-picker
        v-model="selectCreateTime"
        align="right"
        type="date"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择发布日期"
        :picker-options="pickerOptions"
        style="margin-left:20px;margin-top:20px"
      />
      <el-button type="primary" style="margin-left:20px;margin-top:20px" icon="el-icon-search" @click="selectWorkByName">搜索</el-button>
      <el-button type="primary" style="margin-left:20px;margin-top:20px" icon="el-icon-edit" @click="showDialog('发布')">发布作业</el-button>
      <el-dialog :title="dialogTitle" :visible.sync="dialogSelectVisible" width="30%" @close="initData">
        <el-form ref="workDataForm" v-loading="workFormLoading" :rules="rules" :model="workData" label-width="auto" :status-icon="true" label-position="left">
          <el-form-item label="作业名称" prop="name">
            <el-input v-model="workData.name" />
          </el-form-item>
          <el-form-item label="文件命名格式" prop="fileNameFormatEnum">
            <el-tooltip content="若选择无，则不强制修改用户提交文件的文件名" placement="right">
              <el-select
                v-model="workData.fileNameFormatEnum"
                placeholder="请选择文件命名格式"
              >
                <el-option
                  v-for="item in fileNameFormats"
                  :key="item.enum"
                  :label="item.format"
                  :value="item.enum"
                />
              </el-select>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="文件扩展名" prop="extensionId">
            <el-tooltip content="将对用户上传的文件进行扩展名验证" placement="right">
              <el-select
                v-model="workData.extensionId"
                placeholder="请选择文件扩展名"
              >
                <el-option
                  v-for="item in extensions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="截止提交时间" prop="lastTime">
            <el-tooltip content="超过截止提交时间，则用户无法提交文件" placement="right">
              <el-date-picker v-model="workData.lastTime" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" placeholder="选择日期时间" :picker-options="WorkLastTimePickerOptions" />
            </el-tooltip>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="workData.remarks" type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" />
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button v-if="isEditWork" type="primary" @click="doEditWork">完成编辑</el-button>
          <el-button v-else type="primary" @click="doAddWork">发布</el-button>
        </span>
      </el-dialog>
      <el-button type="primary" style="margin-left:20px;margin-top:20px" icon="el-icon-edit" @click="dialogExtensionVisible = true">编辑扩展名列表</el-button>
      <el-dialog title="编辑扩展名列表" :visible.sync="dialogExtensionVisible" width="400px" @close="whenDialogExtensionClose('extensionValidateForm')">
        <el-form ref="extensionValidateForm" label-width="auto" :model="extensionValidateForm" :inline="true" :status-icon="true">
          <el-form-item
            prop="extension"
            label="扩展名"
            :rules="[
              { required: true, message: '请输入扩展名', trigger: 'blur' },
              { pattern: /^\.[0-9a-zA-Z]+$/, message: '请输入正确的扩展名', trigger: ['blur', 'change'] }
            ]"
          >
            <el-input v-model="extensionValidateForm.extension" size="small" style="" />
          </el-form-item>
          <el-form-item>
            <el-button size="small" type="primary" @click="addExtension">添加</el-button>
          </el-form-item>
        </el-form>
        <el-table
          v-loading="extensionTableLoading"
          :data="extensions"
          style="width: 100%;"
          max-height="200px"
          :row-class-name="tableRowClassName"
        >
          <el-table-column
            prop="name"
            label="扩展名"
            width="200"
          />
          <el-table-column label="操作" min-width="80" fixed="right">
            <template slot-scope="scope">
              <div class="cell-contain">
                <el-button size="small" type="danger" :disabled="scope.row.id===1" @click="deleteExtension(scope.row)">删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="dialogExtensionVisible = false">完成编辑</el-button>
        </span>
      </el-dialog>
      <template v-if="multipleSelection.length > 0">
        <el-popconfirm
          title="删除作业会将已提交的文件一并删除"
          @onConfirm="deleteWorks"
        >
          <el-button slot="reference" type="danger" style="margin-left:20px" icon="el-icon-delete">批量删除</el-button>
        </el-popconfirm>
      </template>
      <div class="table-div">
        <el-tooltip content="选中可进行批量删除" placement="top-start">
          <el-table
            v-loading="tableLoading"
            :data="workDataList"
            style="width: 100%"
            height="100%"
            border
            @selection-change="handleSelectionChange"
          >
            <el-tooltip content="选中可进行批量删除" placement="top">
              <el-table-column
                type="selection"
                width="55"
              />
            </el-tooltip>
            <el-table-column
              fixed
              prop="name"
              label="作业名称"
              min-width="100"
            />
            <el-table-column
              prop="fileNameFormatEnum"
              :formatter="fileNameFormat"
              label="文件名规则"
              min-width="80"
            />
            <el-table-column
              prop="extensionName"
              label="文件扩展名"
              min-width="60"
            />
            <el-table-column
              prop="lastTime"
              label="截止提交时间"
              min-width="100"
            />
            <el-table-column
              prop="remarks"
              label="备注"
              min-width="110"
            />
            <el-table-column
              prop="editorName"
              label="最近编辑"
              min-width="50"
            />
            <el-table-column
              prop="createTime"
              label="发布时间"
              min-width="100"
            />
            <el-table-column
              prop="updateTime"
              label="更新时间"
              min-width="100"
            />
            <el-table-column label="操作" fixed="right" min-width="200">
              <template slot-scope="scope">
                <div class="cell-contain">
                  <template class="handle-div">
                    <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
                  </template>
                  <el-badge :value="scope.row.uploadFileCount" class="handle-div">
                    <el-button size="small" type="info" :disabled="scope.row.uploadFileCount===0" @click="handleUpload(scope.row)">查看提交情况</el-button>
                  </el-badge>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-tooltip>
      </div>
      <el-pagination
        background
        :total="pageInfo.total"
        :page-count="pageInfo.pages"
        :current-page="pageInfo.current"
        hide-on-single-page
        @current-change="currentChange"
      />
    </el-card>
  </span>
</template>

<script>
export default {
  data() {
    return {
      workDataList: [],
      pageInfo: null,
      tableLoading: false,
      multipleSelection: [],
      selectWorkName: null,
      selectCreateTime: null,
      dialogSelectVisible: false,
      dialogExtensionVisible: false,
      dialogTitle: '',
      workFormLoading: false,
      extensionTableLoading: false,
      isExtensionsChange: false,
      extensionValidateForm: {
        extension: ''
      },
      extensions: [],
      isEditWork: false,
      fileNameFormats: [{ enum: 1, format: '无' },
        { enum: 2, format: '学号-姓名-作业名' },
        { enum: 3, format: '姓名-学号-作业名' },
        { enum: 4, format: '姓名-作业名' },
        { enum: 5, format: '学号-作业名' }],
      rules: {
        name: [{ required: true, message: '请输入作业名称', trigger: 'blur' }],
        lastTime: [{ required: true, message: '请输入截止提交时间', trigger: 'blur' }],
        extensionId: [{ required: true, message: '选择文件拓展名', trigger: 'blur' }],
        fileNameFormatEnum: [{ required: true, message: '请输入文件命名格式', trigger: 'blur' }]
      },
      WorkLastTimePickerOptions: {
        disabledDate(time) {
          return time.getTime() <= Date.now()
        }
      },
      workData: {
        id: null,
        sid: null,
        fileNameFormatEnum: null,
        extensionId: null,
        name: null,
        lastTime: null,
        remarks: null,
        editSId: null
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now()
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date())
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24)
            picker.$emit('pick', date)
          }
        }]
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
    this.getWorksData(1, 10)
    this.getExtension()
  },
  methods: {
    currentChange(current) {
      this.getWorksData(current, 10)
    },
    getWorksData(current, size) {
      if (this.selectWorkName === '') {
        this.selectWorkName = null
      }
      this.getRequest('/work/', { current: current, size: size, name: this.selectWorkName, createTime: this.selectCreateTime }).then(resp => {
        if (resp.code === 200) {
          this.pageInfo = resp.data
          this.workDataList = this.pageInfo.records
          this.tableLoading = false
        }
      }).catch(error => {
        this.tableLoading = false
        console.log(error)
      })
    },
    selectWorkByName() {
      this.refreshTableData(1, 10)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    deleteWorks() {
      var idList = []
      if (this.multipleSelection.length > 0) {
        for (var i = 0; i < this.multipleSelection.length; i++) {
          idList[i] = this.multipleSelection[i].id
        }
        this.deleteWorksidList(idList)
      }
    },
    handleEdit(row) {
      this.showDialog('编辑')
      this.isEditWork = true
      this.workFormLoading = true
      this.getRequest('/work/admin/' + row.id).then(resp => {
        if (resp.code === 200) {
          this.workData.id = resp.data.id
          this.workData.fileNameFormatEnum = resp.data.fileNameFormatEnum
          this.workData.name = resp.data.name
          this.workData.lastTime = resp.data.lastTime
          this.workData.remarks = resp.data.remarks
          this.workData.extensionId = resp.data.extensionId
          this.workFormLoading = false
        }
      }).catch(error => {
        console.log(error)
        this.workFormLoading = false
      })
    },
    deleteWorksidList(idList) {
      this.tableLoading = true
      this.deleteRequest('/work/admin/', idList).then(resp => {
        if (resp.code === 200 && resp.data) {
          this.$message.success('删除成功!')
          this.refreshTableData(this.pageInfo.current, 10)
        }
      }).catch(error => {
        this.tableLoading = false
        this.$message.error(error.msg)
        console.log(error)
      })
    },
    doAddWork() {
      this.$refs['workDataForm'].validate((valid) => {
        if (valid) {
          this.workFormLoading = true
          this.workData.sid = this.currentUser.id
          this.postRequest('/work/admin/', this.workData).then(resp => {
            if (resp.code === 200) {
              this.$message.success('发布成功!')
              this.workFormLoading = false
              this.dialogSelectVisible = false
              this.refreshTableData(1, 10)
            }
          }).catch(error => {
            this.$message.success('发布失败!')
            this.workFormLoading = false
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    refreshTableData(current, size) {
      this.tableLoading = true
      this.getWorksData(current, size)
    },
    initData() {
      this.workData.id = null
      this.workData.sid = null
      this.workData.fileNameFormatEnum = null
      this.workData.name = null
      this.workData.lastTime = null
      this.workData.remarks = null
      this.workData.editSId = null
      this.workData.extensionId = null
      this.isEditWork = false
      this.$refs['workDataForm'].resetFields()
    },
    showDialog(title) {
      this.dialogTitle = title
      this.dialogSelectVisible = true
    },
    doEditWork() {
      this.$refs['workDataForm'].validate((valid) => {
        if (valid) {
          this.workData.editSId = this.currentUser.id
          this.putRequest('/work/admin/', this.workData).then(resp => {
            if (resp.code === 200) {
              this.$message.success('编辑成功!')
              this.dialogSelectVisible = false
              this.refreshTableData(this.pageInfo.current, 10)
            }
          }).catch(error => {
            this.$message.error('编辑失败!')
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    getExtension() {
      this.getRequest('/work/extensions').then(resp => {
        if (resp.code === 200) {
          this.extensions = resp.data
        }
      })
    },
    fileNameFormat(row, column) {
      return this.fileNameFormats[row.fileNameFormatEnum - 1].format
    },
    handleUpload(row) {
      this.$router.push({
        path: '/workuploadinfo'
      })
      // 将work对象保存到本地
      localStorage.setItem('work', JSON.stringify(row))
    },
    deleteExtension(extension) {
      this.extensionTableLoading = true
      this.deleteRequest('/work/admin/extensions', { id: extension.id }).then(resp => {
        if (resp.code === 200 && resp.data === true) {
          this.$message.success('删除成功！')
          this.getExtension()
          this.isExtensionsChange = true
        } else {
          this.$message.error('删除失败！')
        }
        this.extensionTableLoading = false
      }).catch(error => {
        this.$message.error('删除失败！')
        this.extensionTableLoading = false
        console.log(error)
      })
    },
    addExtension() {
      this.$refs['extensionValidateForm'].validate((valid) => {
        if (valid) {
          this.extensionTableLoading = true
          this.postRequest('/work/admin/extensions', { name: this.extensionValidateForm.extension }).then(resp => {
            if (resp.code === 200 && resp.data === true) {
              this.$message.success('添加成功！')
              this.getExtension()
              this.isExtensionsChange = true
            } else {
              this.$message.error('添加失败！')
            }
            this.extensionTableLoading = false
          }).catch(error => {
            this.extensionTableLoading = false
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    whenDialogExtensionClose(formName) {
      this.$refs[formName].resetFields()
      if (this.isExtensionsChange) {
        this.refreshTableData(1, 10)
        this.isExtensionsChange = false
      }
    },
    tableRowClassName(row, index) {
      if (row.id === 1) {
        return 'hidden-row'
      }
      return ''
    }
  }

}
</script>

<style>
.box-card-workdata {
    width: 80%;
    height: 100%;
}
.workMana-div {
    display: flex;
    justify-content:center;
    align-items:center;
    height: 100%;
}
.table-div {
    margin: 20px 0;
    height: 70%;
}
.handle-div {
  margin-top: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
}
</style>
