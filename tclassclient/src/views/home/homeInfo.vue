<template>
  <div class="homeinfo-div">
    <el-row style="width:100%">
      <el-col :span="24" class="el-row-div">
        <el-card class="box-card" shadow="hover" :body-style="{ padding: '20px', height:'100%'}">
          <div slot="header" class="clearfix">
            <div class="title">{{ notice.title }}</div>
            <span class="homeinfo-icon">
              <i class="fas fa-clock" />
            </span>
            {{ notice.createTime }}
            <span class="homeinfo-icon snamemargin">
              <i class="fas fa-user" />
            </span>
            {{ notice.sname }}
            <span v-if="notice.editSName!==null">
              <span class="homeinfo-icon snamemargin">
                <i class="fas fa-user-edit" />
              </span>
              {{ notice.editSName }}
            </span>
          </div>
          <div class="card-body">
            <viewer :initial-value="getSubstr(notice.content,0,120)+'...'" class="viewer" />
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
      </el-col>
      <el-col :span="24" class="el-row-div" :body-style="{ padding: '20px', height:'100%'}">
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
              >提交作业<i class="el-icon-upload el-icon--right" /></el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import '@toast-ui/editor/dist/toastui-editor-viewer.css'
import { Viewer } from '@toast-ui/vue-editor'
export default {
  components: {
    viewer: Viewer
  },
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
      this.$router.push({
        path: '/noticeinfo',
        query: { id: this.notice.id }
      })
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
  .viewer /deep/ p{
    font-size: 150%;
  }
  .el-row-div {
    margin-bottom: 8%;
    display: flex;
    justify-content:space-around;
  }
  .homeinfo-div {
    display: flex;
    justify-content:center;
    align-items: center;
  }
</style>
