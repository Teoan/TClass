<template>
  <span class="div-card">
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span>{{ notice.title }}</span>
      </div>
      <div class="text item">
        {{ notice.createTime }}
      </div>
      <div class="text item">
        {{ notice.sname }}
      </div>
      <div class="text item">
        {{ notice.content }}
      </div>
    </el-card>
    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span>卡片名称</span>
      </div>
      <div v-for="o in 4" :key="o" class="text item">
        {{ '列表内容 ' + o }}
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
    }
  }
}
</script>

<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 800px;
    height: 300px;
  }
  .div-card {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    height: 100%;
  }
</style>
