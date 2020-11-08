<template>
  <div class="work-div">
    <el-card shadow="hover" :body-style="{ padding: '20px', height:'100%'}">
      <div slot="header" class="clearfix">
        <div class="title">{{ work.name }}</div>
        <span class="homeinfo-icon">
          <i class="fas fa-user" />
        </span>
        {{ work.sname }}
        <span v-if="work.editSName!==null">
          <span class="homeinfo-icon sname-margin">
            <i class="fas fa-user-edit" />
          </span>
          {{ work.editSName }}
        </span>
      </div>
      <div class="card-body">
        <div class="box-text">
          <div class="item">
            <i class="fas fa-clock" />
            发布时间:{{ formatDate(work.createTime) }}
          </div>
          <div class="item">
            <i class="fas fa-italic" />
            命名格式:{{ fileNameFormats[work.fileNameFormatEnum-1] }}
          </div>
          <div class="item">
            <i class="fas fa-file" />
            文件拓展名:{{ work.extensionName }}
          </div>
          <div class="item">
            <i class="fas fa-hourglass-half" />
            截止时间:{{ formatDate(work.lastTime) }}
          </div>
          <div v-if="work.remarks!==null" class="item">
            <i class="fas fa-pencil-alt" />
            备注:{{ work.remarks }}
          </div>
        </div>
        <el-tooltip :disabled="!isDisAbleUpload()" content="超过提交时间不予许提交哦！" placement="bottom">
          <div class="read-more-btn">
            <el-button
              size="normal"
              type="primary"
              round
              style="width:60%"
              :disabled="isDisAbleUpload()"
              @click="dialogVisible = true"
            >提交作业<i class="el-icon-upload el-icon--right" /></el-button>
          </div>
        </el-tooltip>
        <el-dialog
          title="上传作业"
          :visible.sync="dialogVisible"
          width="30%"
        >
          <el-upload
            drag
            action=""
            :accept="work.extensionName"
            show-file-list="true"
            :on-success="onSeccessUpload"
            :on-error="onErrorUpload"
            :before-upload="beforeUpload"
            :disabled="uploadDisabled"
            :multiple="false"
          >
            <i class="el-icon-upload" />
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </el-dialog>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  props: {
    work: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      dialogVisible: false,
      fileNameFormats: ['无', '学号-姓名-作业名', '姓名-学号-作业名', '姓名-作业名',
        '学号-作业名']
    }
  },
  methods: {
    uploadFile() {
      this.$message.info('uploadFile' + this.$props.work.id)
    },
    formatDate(date) {
      var d = new Date(date)
      var month = '' + (d.getMonth() + 1)
      var day = '' + d.getDate()
      var year = d.getFullYear()

      if (month.length < 2) month = '0' + month
      if (day.length < 2) day = '0' + day

      return [year, month, day].join('-')
    },
    isDisAbleUpload() {
      var lastTime = new Date(this.work.lastTime)
      var nowTime = new Date()
      return nowTime.getTime() > lastTime.getTime()
    },
    onSeccessUpload(response, file, fileList) {
      console.log(response)
      this.$message.success(response.msg)
    },
    onErrorUpload(err, file, fileList) {
      console.log(err)
      this.$message.error('文件上传失败')
    },
    beforeUpload(file) {

    }
  }
}
</script>

<style scoped>
  .read-more-btn {
   margin-top: 10px;
   display: flex;
   justify-content:center;
  }
  .clearfix {
    font-size:15px;
  }
  .sname-margin {
    margin-left: 10px;
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
  .box-text {
    font-size: 100%;
    height: auto;
  }
  .title {
    font-size: 30px;
    font-weight: 700;
    margin-bottom: 10px;
  }
  .work-div {
    width: 32%;
  }
  .item {
    margin: 5px 0px;
  }
</style>
