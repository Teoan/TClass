<template>
  <el-card shadow="hover" :body-style="{ padding: '20px', height:'100%'}">
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
</template>

<script>
import '@toast-ui/editor/dist/toastui-editor-viewer.css'

import { Viewer } from '@toast-ui/vue-editor'
export default {
  components: {
    viewer: Viewer
  },
  props: {
    notice: {
      type: Object,
      default: null
    }
  },
  data() {
    return {

    }
  },
  methods: {
    readMoreNotice() {
      this.$router.push({
        path: '/noticeinfo',
        query: { id: this.$props.notice.id }
      })
    },
    getSubstr(str, start, end) {
      return str.substring(start, end)
    }
  }
}
</script>

<style>
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
</style>
