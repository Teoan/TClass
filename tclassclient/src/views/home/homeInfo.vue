<template>
  <span class="div-card">
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <div class="title">通知：{{ notice.title }}</div>
        <span class="homeinfo-icon">
          <i class="fas fa-clock" />
        </span>
        {{ notice.createTime }}
        <span class="homeinfo-icon snamemargin">
          <i class="fas fa-user" />
        </span>
        {{ notice.sname }}
      </div>
      <div class="card-body">
        <div v-if="notice.content" class="box-text">
          {{ getSubstr(notice.content,0,120)+'...' }}
        </div>
        <div class="read-more-btn">
          <el-button
            size="normal"
            type="primary"
            round
            @click="readMoreNotice"
          >阅读更多...</el-button>
        </div>
      </div>
    </el-card>
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <div class="title">作业：{{ work.name }}</div>
        <span class="homeinfo-icon">
          <i class="fas fa-clock" />
        </span>
        {{ work.createTime }}
        <span class="homeinfo-icon snamemargin">
          <i class="fas fa-user" />
        </span>
        {{ work.sname }}
      </div>
      <div class="card-body">
        <div class="box-text">
          <div>
            <i class="fas fa-italic" />
            上传文件格式:{{ work.fileNameFormat }}
          </div>
          <div>
            <i class="fas fa-hourglass-half" />
            最晚提交时间:{{ work.lastTime }}
          </div>
        </div>
        <div class="read-more-btn">
          <el-button
            size="normal"
            type="primary"
            round
            @click="uploadFile"
          >上传作业<i class="el-icon-upload el-icon--right" /></el-button>
        </div>
      </div>
    </el-card>
  </span>
</template>

<script>
export default {
  data() {
    return {
      work: '',
      notice: ''
    }
  },
  created() {
    this.getCurrentNoticeData()
    this.getCurrentWorkData()
  },
  methods: {
    getCurrentNoticeData() {
      this.getRequest('/notice/current').then(resp => {
        if (resp.code === 0) {
          this.notice = resp.data
        }
      })
    },
    getCurrentWorkData() {
      this.getRequest('/work/current').then(resp => {
        if (resp.code === 0) {
          this.work = resp.data
        }
      })
    },
    readMoreNotice() {
      this.$message.info('readMoreNotice')
    },
    uploadFile() {
      this.$message.info('uploadFile')
    },
    getSubstr(str, start, end) {
      return str.substring(start, end)
    }
  }
}
</script>

<style scoped>
  .read-more-btn {
   display: flex;
   justify-content: flex-end;
   align-items:content;
   margin-bottom: 10px;
   margin-top: 10px;
  }
  .box-text {
    font-size: 100%;
    height: auto;
  }
  .snamemargin {
    margin-left: 10px;
  }
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

  .box-card {
    width: 70%;
    height: 40%;
  }

  .div-card {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    height: 100%;
  }
  .title {
    font-size: 30px;
    font-weight: 700;
    margin-bottom: 10px;
  }
  .homeinfo-icon {
    font-size: 15px;
    color: #909399;
  }
  .card-body {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100%;
  }
</style>
