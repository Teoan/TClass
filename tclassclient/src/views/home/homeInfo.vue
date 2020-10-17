<template>
  <div class="homeinfo-div">
    <el-row class="el-row-div">
      <el-col :span="24" style="margin-bottom: 8%;">
        <noticeInfo :notice="notice" />
      </el-col>
      <el-col :span="24" style="margin-bottom: 8%;">
        <div class="work-list-div">
          <workInfo v-for="work in workList" :key="work.id" :work="work" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Notice from '@/components/Notice/'
import Work from '@/components/Work/'
export default {
  components: {
    noticeInfo: Notice,
    workInfo: Work
  },
  data() {
    return {
      workList: '',
      notice: ''
    }
  },
  created() {
    this.getCurrentNoticeData()
    this.getWorkPageData(1, 3)
  },
  methods: {
    getCurrentNoticeData() {
      this.getRequest('/notice/current').then(resp => {
        if (resp.code === 0) {
          this.notice = resp.data
        }
      })
    },
    getWorkPageData(current, size) {
      this.getRequest('/work/', { current: current, size: size }).then(resp => {
        if (resp.code === 0) {
          this.workList = resp.data.records
        }
      })
    },
    readMoreNotice() {
      this.$router.push({
        path: '/noticeinfo',
        query: { id: this.notice.id }
      })
    }
  }
}
</script>

<style scoped>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .clearfix {
    font-size:15px;
  }
  .el-row-div {
    display: flex;
    justify-content:center;
    align-items: center;
    flex-direction: column;
    width: 70%;
    height: 100%;
  }
  .homeinfo-div {
    display: flex;
    justify-content:center;
    align-items: center;
    height: 100%;
  }
  .work-list-div {
    display: flex;
    justify-content:space-between;
  }
</style>
