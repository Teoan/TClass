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
            命名格式:{{ work.fileNameFormat }}
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
        <div class="read-more-btn">
          <el-button
            size="normal"
            type="primary"
            round
            style="width:100%"
            @click="uploadFile"
          >提交作业<i class="el-icon-upload el-icon--right" /></el-button>
        </div>
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
    }
  }
}
</script>

<style scoped>
  .read-more-btn {
   margin-bottom: 10px;
   margin-top: 10px;
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
