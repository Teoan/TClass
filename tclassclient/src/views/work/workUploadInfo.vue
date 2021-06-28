<template>
  <span class="workUploadInfo-div">
    <el-card
      class="box-card-workuploadinfo"
      shadow="hover"
      :body-style="{height:'100%'}"
    >
      <span class="work-name">{{ work.name }}</span>
      <el-divider />
      <el-tooltip content="选中可进行批量删除" placement="top-start">
        <div class="table-div">
          <el-table
            v-loading="fileTableLoading"
            :data="uploadInfo"
            style="width: 100%"
            height="100%"
            @selection-change="handleSelectionChange"
          >
            <el-table-column
              type="selection"
              width="55"
            />
            <el-table-column
              prop="sname"
              label="上传者"
              width="150"
            />
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
            <el-table-column min-width="150" fixed="right">
              <template slot="header">
                <el-button v-if="uploadInfo.length>0" size="small" type="primary" @click="downloadFiles">打包下载全部文件</el-button>
                <el-button v-if="multipleSelection.length > 0" size="small" type="danger" icon="el-icon-delete" @click="deleteFiles">批量删除</el-button>
              </template>
              <template slot-scope="scope">
                <div class="cell-contain">
                  <el-button size="small" type="danger" @click="deleteFile(scope.row)">删除</el-button>
                  <el-button size="small" type="primary" @click="downloadFile(scope.row)">下载</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tooltip>
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
      uploadInfo: null,
      pageInfo: null,
      fileTableLoading: false,
      multipleSelection: []
    }
  },
  computed: {
    work() {
      return JSON.parse(localStorage.getItem('work'))
    }
  },
  created() {
    this.getUploadInfoByWorkId(1, 12, this.work.id)
  },
  methods: {
    fileSizeFormat(row, column) {
      return (row.size / 1024 / 1024).toFixed(2) + 'MB'
    },
    getUploadInfoByWorkId(current, size, wId) {
      this.fileTableLoading = true
      this.getRequest('/work/admin/upload/' + wId, { current: current, size: size }).then(resp => {
        if (resp.code === 200) {
          this.pageInfo = resp.data
          this.uploadInfo = this.pageInfo.records
          this.fileTableLoading = false
        }
      }).catch(error => {
        this.fileTableLoading = false
        console.log(error)
      })
    },
    currentChange(current) {
      this.getUploadInfoByWorkId(current, 12, this.work.id)
    },
    deleteFile(upload) {
      this.fileTableLoading = true
      this.deleteRequest('/work/deleteFile/', upload).then(resp => {
        if (resp.code === 200 && resp.data === true) {
          this.$message.success('删除成功！')
          this.fileTableLoading = false
          this.getUploadInfoByWorkId(this.pageInfo.current, 12, this.work.id)
        } else {
          this.fileTableLoading = false
          this.$message.success(resp.msg)
        }
      }).catch(error => {
        this.fileTableLoading = false
        this.$message.error(error.msg)
        console.log(error)
      })
    },
    downloadFiles() {
      // window.open('/work/admin/download/zip/' + this.work.id, '_parent')
      this.downLoadFileRequest('/work/admin/download/zip/' + this.work.id)
    },
    downloadFile(upload) {
      // window.open('/work/admin/download/?wId=' + this.work.id + '&fileName=' + upload.fileName, '_parent')
      this.downLoadFileRequest('/work/admin/download/?wId=' + this.work.id + '&fileName=' + upload.fileName)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    deleteFiles() {
      this.fileTableLoading = true
      this.deleteRequest('/work/admin/upload', this.multipleSelection).then(resp => {
        if (resp.code === 200 && resp.data === true) {
          this.$message.success('批量删除成功！')
          this.getUploadInfoByWorkId(this.pageInfo.current, 12, this.work.id)
        }
        this.fileTableLoading = false
      }).catch(error => {
        this.fileTableLoading = false
        this.$message.error(error.msg)
        console.log(error)
      })
    }
  }
}
</script>

<style>
.box-card-workuploadinfo {
    width: 80%;
    height: 100%;
}
.workUploadInfo-div {
    display: flex;
    justify-content:center;
    align-items:center;
    height: 100%;
}
.table-div {
    height: 70%;
    margin-bottom: 20px;
}
.work-name {
  font-size: 25px;
}
</style>
