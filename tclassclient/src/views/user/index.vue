<template>
  <span class="userMana-div">
    <el-card
      class="box-card-userdata"
      shadow="hover"
      :body-style="{height:'100%'}"
    >
      <div class="select-div">
        <el-input v-model="selectUserData.name" prefix-icon="el-icon-search" placeholder="根据学生姓名搜索" style="width: 400px;margin-right:20px" />
        <el-button type="primary" style="margin-right:20px" icon="el-icon-search" @click="selectStudent">搜索</el-button>
        <el-dialog title="数据管理" :visible.sync="dialogDataVisible">
          <div class="student-date-but-div">
            <el-button type="primary" style="margin-right:20px" icon="el-icon-upload2">导入学生数据</el-button>
            <el-button type="primary" style="margin-right:20px" icon="el-icon-download" @click="exportAllStudentData">导出学生数据</el-button>
          </div>
        </el-dialog>
        <el-button type="primary" style="margin-right:20px" icon="el-icon-s-data" @click="dialogDataVisible = true">学生数据管理</el-button>
        <template v-if="multipleSelection.length>0">
          <el-popconfirm
            title="确定批量删除所选学生吗？"
            @onConfirm="deleteStudents"
          >
            <el-button slot="reference" type="danger" style="margin-right:20px">批量删除</el-button>
          </el-popconfirm>
        </template>
        <el-button v-if="multipleSelection.length>0" type="primary" style="margin-right:20px" @click="exportStudentData">批量导出</el-button>
      </div>
      <div class="table-div">
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
            prop="id"
            label="学号"
            min-width="110"
          />
          <el-table-column
            prop="name"
            label="学生姓名"
            min-width="100"
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
      userDataList: '',
      pageInfo: '',
      selectInput: '',
      selectUserData: {
        id: null,
        name: null,
        gender: null,
        posId: null,
        nationId: null,
        politicId: null,
        phone: null
      },
      tableLoading: false,
      multipleSelection: [],
      dialogDataVisible: false
    }
  },
  created() {
    this.getData(1, 10)
  },
  methods: {
    getData(current, size) {
      this.tableLoading = true
      this.getRequest('/student/', { current: current, size: size }).then(resp => {
        if (resp.code === 0) {
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
      console.log(row)
    },
    handleResetPass(row) {
      console.log(row)
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
      this.getRequest('/student/', this.selectUserData).then(resp => {
        if (resp.code === 0) {
          this.pageInfo = resp.data
          this.userDataList = this.pageInfo.records
          this.tableLoading = false
          this.initSelectUserData()
        }
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
        phone: null
      }
    },
    deleteStudentByidList(idList) {
      this.tableLoading = true
      this.deleteRequest('/admin/student/', { idList: idList + '' }).then(resp => {
        if (resp.code === 0 && resp.data) {
          this.$message.success('删除成功')
          this.getData(this.pageInfo.current, 10)
          this.tableLoading = false
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
      window.open('/admin/student/export?idList=' + idList, '_parent')
    },
    exportAllStudentData() {
      window.open('/admin/student/export?idList=', '_parent')
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
    justify-content: center;
  }
  .userMana-div {
    display: flex;
    justify-content:center;
    align-items:center;
    height: 100%;
  }
  .table-div {
    margin: 20px 0;
    height: 70%;
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
    text-align:center;
  }
</style>
