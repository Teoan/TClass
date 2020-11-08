<template>
  <span class="noticeMana-div">
    <el-card
      class="box-card-noticedata"
      shadow="hover"
      :body-style="{height:'100%'}"
    >
      <el-input v-model="selectNoticeTitle" placeholder="请输入通知标题搜索" style="width: 500px;" @keydown.enter.native="selectNoticeByName" />
      <el-date-picker
        v-model="selectCreateTime"
        align="right"
        type="date"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择日期"
        :picker-options="pickerOptions"
        style="margin-left: 20px;"
      />
      <el-button type="primary" style="margin-left:20px" icon="el-icon-search" @click="selectNoticeByName">搜索</el-button>
      <el-button type="primary" style="margin-left:20px" icon="el-icon-edit" @click="addNotice">发布通知</el-button>
      <template v-if="multipleSelection.length > 0">
        <el-popconfirm
          title="确定批量删除所选通知吗？"
          @onConfirm="deleteNotices"
        >
          <el-button slot="reference" type="danger" style="margin-left:20px" icon="el-icon-delete">批量删除</el-button>
        </el-popconfirm>
      </template>
      <div class="table-div">
        <el-table
          v-loading="tableLoading"
          :data="noticeDataList"
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
            prop="title"
            label="标题"
            min-width="100"
          />
          <el-table-column
            prop="sname"
            label="发布人"
            min-width="110"
          />
          <el-table-column
            prop="editSName"
            label="最近编辑"
            min-width="110"
          />
          <el-table-column
            prop="createTime"
            label="创建时间"
            min-width="120"
          />
          <el-table-column
            prop="updateTime"
            label="更新时间"
            min-width="120"
          />
          <el-table-column>
            <template slot-scope="scope">
              <div class="cell-contain">
                <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
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
      noticeDataList: [],
      pageInfo: null,
      tableLoading: false,
      multipleSelection: [],
      selectNoticeTitle: null,
      selectCreateTime: null,
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
  created() {
    this.refreshTableData(1, 12)
  },
  methods: {
    currentChange(current) {
      this.refreshTableData(current, 12)
    },
    getNoticesData(current, size) {
      if (this.selectNoticeTitle === '') {
        this.selectNoticeTitle = null
      }
      this.getRequest('/notice/', { current: current, size: size, title: this.selectNoticeTitle, createTime: this.selectCreateTime }).then(resp => {
        if (resp.code === 0) {
          this.pageInfo = resp.data
          this.noticeDataList = this.pageInfo.records
          this.tableLoading = false
        }
      }).catch(error => {
        this.tableLoading = false
        console.log(error)
      })
    },
    selectNoticeByName() {
      this.refreshTableData(1, 12)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    handleDelete(row) {
      this.deleteNoticesidList(row.id)
    },
    deleteNotices() {
      var idList = []
      if (this.multipleSelection.length > 0) {
        for (var i = 0; i < this.multipleSelection.length; i++) {
          idList[i] = this.multipleSelection[i].id
        }
        this.deleteNoticesidList(idList)
      }
    },
    handleEdit(row) {
      this.$router.push({
        path: '/noticeedit',
        query: { noticeId: row.id }
      })
    },
    deleteNoticesidList(idList) {
      this.tableLoading = true
      this.deleteRequest('/admin/notice/', { idList: idList + '' }).then(resp => {
        if (resp.code === 0 && resp.data) {
          this.$message.success('删除成功')
          this.getNoticesData(this.pageInfo.current, 12)
          this.tableLoading = false
        }
      }).catch(error => {
        this.tableLoading = false
        console.log(error)
      })
    },
    addNotice() {
      this.$router.push({
        path: '/noticeedit'
      })
    },
    refreshTableData(current, size) {
      this.tableLoading = true
      this.getNoticesData(current, size)
    }
  }
}
</script>

<style>
.box-card-noticedata {
    width: 80%;
    height: 100%;
}
.noticeMana-div {
    display: flex;
    justify-content:center;
    align-items:center;
    height: 100%;
}
.table-div {
    margin: 20px 0;
    height: 80%;
}

</style>
