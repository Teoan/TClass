<template>
  <div v-loading.fullscreen.lock="noticeDataLoading" class="div-card">
    <div class="select-div">
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
      <el-button type="primary" icon="el-icon-search" style="margin-left: 20px;" @click="selectNoticeByName">搜索</el-button>
    </div>
    <el-row v-for="item in noticeList" :key="item.id" class="el-row-div">
      <el-col :span="24" class="el-col-div">
        <noticeInfo :notice="item" />
      </el-col>
    </el-row>
    <el-pagination
      background
      :page-count="pageInfo.pages"
      :current-page="pageInfo.current"
      hide-on-single-page
      @current-change="currentChange"
    />
  </div>
</template>

<script scoped>
import noticeInfo from '@/components/Notice/'
export default {
  components: {
    noticeInfo: noticeInfo
  },
  data() {
    return {
      noticeList: [],
      pageInfo: '',
      selectNoticeTitle: '',
      selectCreateTime: null,
      noticeDataLoading: false,
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
    this.getNationsData(1, 3)
  },
  mounted() {
  },
  methods: {
    currentChange(current) {
      this.getNationsData(current, 3)
    },
    getNationsData(current, size) {
      this.noticeDataLoading = true
      this.getRequest('/notice/', { current: current, size: size, title: this.selectNoticeTitle, createTime: this.selectCreateTime }).then(resp => {
        if (resp.code === 0) {
          this.pageInfo = resp.data
          this.noticeList = this.pageInfo.records
        }
        this.noticeDataLoading = false
      }).catch(error => {
        console.log(error)
        this.noticeDataLoading = false
      })
    },
    selectNoticeByName() {
      this.getNationsData(1, 3)
    }
  }
}
</script>

<style>
  .div-card {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
  }
  .el-row-div {
    margin-bottom: 20px;
    width: 100%;
    display: flex;
    justify-content:space-around;
  }
  .el-col-div {
    width: 70%;
  }
  .select-div {
    display: flex;
    flex-direction: row;
    margin-bottom: 20px;
  }
</style>
