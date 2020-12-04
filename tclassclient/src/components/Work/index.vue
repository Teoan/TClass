<template>
  <div class="work-div">
    <el-card shadow="hover" :body-style="{ padding: '20px', height:'100%'}">
      <div slot="header" class="clearfix">
        <div class="title">{{ work.name }}</div>
        <span class="homeinfo-icon">
          <i class="fas fa-user" />
        </span>
        {{ work.sname }}
        <span v-if="work.editorName!==null">
          <span class="homeinfo-icon sname-margin">
            <i class="fas fa-user-edit" />
          </span>
          {{ work.editorName }}
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
            文件扩展名:{{ work.extensionName }}
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
        <el-tooltip :disabled="disShowUploadTooltip" content="超过提交时间不予许提交哦！" placement="bottom">
          <div class="read-more-btn">
            <el-button
              size="normal"
              type="primary"
              round
              style="width:60%"
              :disabled="isOutTime() && !isUploaded"
              @click="dialogVisible=true"
            >{{ buttonText }}<i class="el-icon-upload el-icon--right" /></el-button>
          </div>
        </el-tooltip>
        <el-dialog
          title="上传作业"
          :visible.sync="dialogVisible"
          width="35%"
        >
          <el-tooltip :disabled="isOutTime()" effect="dark" content="若要重新上传文件请删除旧文件" placement="top">
            <el-table
              v-if="isUploaded"
              v-loading="fileTableLoading"
              :data="uploadInfo"
              style="width: 100%"
            >
              <el-table-column
                prop="fileName"
                label="文件名"
                width="230"
              />
              <el-table-column
                prop="size"
                :formatter="fileSizeFormat"
                label="文件大小"
                width="100"
              />
              <el-table-column
                prop="createTime"
                label="上传时间"
                width="180"
              />
              <el-table-column label="操作" min-width="80" fixed="right">
                <template slot-scope="scope">
                  <el-tooltip :disabled="!isOutTime()" content="超过提交时间不予许删除文件！" placement="top">
                    <div class="cell-contain">
                      <el-button size="small" type="danger" :disabled="isOutTime()" @click="deleteFile(scope.row)">删除</el-button>
                    </div>
                  </el-tooltip>
                </template>
              </el-table-column>
            </el-table>
          </el-tooltip>
          <el-upload
            v-if="!isUploaded"
            drag
            :action="action"
            :accept="work.extensionName"
            show-file-list="true"
            :on-success="onSeccessUpload"
            :on-error="onErrorUpload"
            :disabled="uploadDisabled"
            :multiple="false"
            :data="{sId:currentUser.id}"
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
        '学号-作业名'],
      action: '/work/' + this.$props.work.id,
      uploadInfo: [],
      isUploaded: false,
      disShowUploadTooltip: true,
      buttonText: '提交作业',
      fileTableLoading: false
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
    this.isUserUpload()
  },
  methods: {
    formatDate(date) {
      var d = new Date(date)
      var month = '' + (d.getMonth() + 1)
      var day = '' + d.getDate()
      var year = d.getFullYear()

      if (month.length < 2) month = '0' + month
      if (day.length < 2) day = '0' + day

      return [year, month, day].join('-')
    },
    isOutTime() {
      var lastTime = new Date(this.work.lastTime)
      var nowTime = new Date()
      return nowTime.getTime() > lastTime.getTime()
    },
    onSeccessUpload(response, file, fileList) {
      console.log(response)
      this.$message.success(response.msg)
      this.dialogVisible = false
      this.isUserUpload()
    },
    onErrorUpload(err, file, fileList) {
      console.log(err)
      err = JSON.parse(err.message)
      this.$message.error(err.message)
    },
    deleteFile(upload) {
      this.fileTableLoading = true
      this.deleteRequest('/work/deleteFile/', { wId: this.$props.work.id, sId: this.currentUser.id, fileName: upload.fileName }).then(resp => {
        if (resp.code === 0 && resp.data === true) {
          this.$message.success('删除文件成功！')
          this.isUploaded = false
          this.buttonText = '提交作业'
          this.fileTableLoading = false
          this.dialogVisible = false
        }
      }).catch(error => {
        this.fileTableLoading = false
        this.$message.error(error.msg)
        console.log(error)
      })
    },
    isUserUpload() {
      this.getRequest('/work/isUpload/', { wId: this.$props.work.id, sId: this.currentUser.id }).then(resp => {
        if (resp.code === 0) {
          if (resp.data !== null) {
            this.isUploaded = true
            this.buttonText = '查看提交作业'
            this.uploadInfo[0] = resp.data
          } else if (this.isOutTime()) {
            this.disShowUploadTooltip = false
          }
        }
      }).catch(error => {
        console.log(error.msg)
      })
    },
    fileSizeFormat(row, column) {
      return (row.size / 1024 / 1024).toFixed(2) + 'MB'
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
