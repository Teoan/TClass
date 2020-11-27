<template>
  <div class="div-card">
    <div class="select-div">
      <el-input v-model="selectWorkName" placeholder="请输入作业名称搜索" style="width: 500px;" @keydown.enter.native="selectWorkByName" />
      <el-date-picker
        v-model="selectCreateTime"
        align="right"
        type="date"
        value-format="yyyy-MM-dd HH:mm:ss"
        placeholder="选择日期"
        :picker-options="pickerOptions"
        style="margin-left: 20px;"
      />
      <el-button type="primary" icon="el-icon-search" style="margin-left: 20px;" @click="selectWorkByName">搜索</el-button>
    </div>
    <el-row class="el-row-div">
      <el-col v-loading="workDataLoading" :span="24">
        <div class="work-list-div">
          <workInfo v-for="work in workList" :key="work.id" :work="work" />
        </div>
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

<script>
import Work from '@/components/Work/'
export default {
  components: {
    workInfo: Work
  },
  data() {
    return {
      workList: [],
      pageInfo: '',
      selectWorkName: null,
      selectCreateTime: null,
      workDataLoading: false,
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
    this.getWorksData(1, 6)
  },
  mounted() {
  },
  methods: {
    currentChange(current) {
      this.getWorksData(current, 6)
    },
    getWorksData(current, size) {
      this.workDataLoading = true
      this.getRequest('/work/', { current: current, size: size, name: this.selectWorkName, createTime: this.selectCreateTime }).then(resp => {
        if (resp.code === 0) {
          this.pageInfo = resp.data
          this.workList = this.pageInfo.records
          this.workDataLoading = false
        }
      }).catch(error => {
        console.log(error)
        this.workDataLoading = false
      })
    },
    selectWorkByName() {
      this.getWorksData(1, 6)
    }
  }
}
</script>

<style scoped>
  .div-card {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100%;
  }
  .el-row-div {
    margin-bottom: 20px;
    width: 70%;
    height: 80%;
    display: flex;
  }
  .el-col-div {
    width: 70%;
  }
  .select-div {
    display: flex;
    flex-direction: row;
    margin-bottom: 20px;
  }
  .work-list-div {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    height: 100%;
  }
</style>
