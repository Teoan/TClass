<template>
  <span class="userMana-div">
    <el-card class="box-card-userdata" shadow="hover">
      <div class="select-div">
        <el-input v-model="selectUserData.name" prefix-icon="el-icon-search" placeholder="根据学生姓名搜索" style="width: 400px;margin-right:20px" />
        <el-button type="primary" style="margin-right:20px" @click="selectStudent">搜索</el-button>
      </div>
      <div class="table-div">
        <el-table
          v-loading="tableLoading"
          :data="userDataList"
          style="width: 100%"
          height="400"
          border
        >
          <el-table-column
            prop="id"
            label="学号"
            width="110"
          />
          <el-table-column
            prop="name"
            label="学生姓名"
            width="100"
          />
          <el-table-column
            prop="gender"
            label="性别"
            width="50"
          />
          <el-table-column
            prop="nation.name"
            label="民族"
            width="80"
          />
          <el-table-column
            prop="nativePlace"
            label="籍贯"
            :formatter="formatNativePlace"
            width="100"
          />
          <el-table-column
            prop="position.name"
            label="班级职务"
            width="80"
          />
          <el-table-column
            prop="politicsstatus.name"
            label="政治面貌"
            width="100"
          />
          <el-table-column
            prop="email"
            label="邮箱"
            width="100"
          />
          <el-table-column
            prop="phone"
            label="手机号码"
            width="100"
          />
          <el-table-column
            prop="address"
            label="住址"
            width="100"
          />
          <el-table-column
            prop="loginTime"
            label="最近登录时间"
            width="120"
          />
          <el-table-column
            fixed="right"
            label="操作"
            width="234"
          >
            <template slot-scope="scope">
              <template>
                <el-popconfirm
                  title="确定删除该用户吗？"
                >
                  <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </el-popconfirm></template>
              <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button size="small" type="warning" @click="handleResetPass(scope.row)">重置密码</el-button>
            </template></el-table-column>
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
      tableLoading: false
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
      })
    },
    handleDelete(row) {
      console.log(row)
    },
    handleEdit(row) {
      console.log(row)
    },
    handleResetPass(row) {

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
    }
  }
}
</script>

<style scoped>
  .box-card-userdata {
    width: 1400px;
    height: 600px;
    display: flex;
    flex-direction: column;
  }
  .userMana-div {
    display: flex;
    justify-content:center;
    align-items:center;
  }
  .table-div {
    margin: 20px 0;
  }
  .select-div {
    display: flex;
    flex-direction: row;
  }
</style>
