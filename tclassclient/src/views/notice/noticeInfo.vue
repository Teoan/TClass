<template>
  <div class="noticeinfo-div">
    <div class="card-div">
      <el-card shadow="hover" :body-style="{ padding: '20px', height:'100%'}">
        <div slot="header" class="header-div">
          <div class="title">{{ notice.title }}</div>
          <div>
            <span class="homeinfo-icon">
              <i class="fas fa-clock" />
            </span>
            {{ notice.createTime }}
            <span class="homeinfo-icon sname-margin">
              <i class="fas fa-user" />
            </span>
            {{ notice.sname }}
            <span v-if="notice.editSName!==null">
              <span class="homeinfo-icon sname-margin">
                <i class="fas fa-user-edit" />
              </span>
              {{ notice.editSName }}
            </span>
          </div>
        </div>
        <div class="card-body">
          <viewer :initial-value="notice.content" class="viewer" />
        </div>
      </el-card>
    </div>
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
      notice: null
    }
  },
  created() {
    this.getNationsData()
  },
  methods: {
    getNationsData() {
      this.getRequest('/notice/' + this.$route.query.id).then(resp => {
        if (resp.code === 0) {
          this.notice = resp.data
        }
      }).catch(error => {
        console.log(error)
      })
    }
  }

}
</script>

<style>
  .box-text {
    font-size: 100%;
    height: auto;
  }
  .sname-margin {
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

  .header-div {
    font-size:15px;
    width: 100%;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
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
  .viewer p{
    font-size: 150%;
  }
  .noticeinfo-div {
    display: flex;
    justify-content:center;
    align-items: center;
    width: 100%;
  }
  .card-div {
    width: 80%;
  }
</style>
