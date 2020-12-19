<template>
  <div class="edit-div">
    <el-card
      class="box-card"
      shadow="hover"
      :body-style="{height:'100%'}"
    >
      <el-form label-width="60px">
        <el-form-item label="标题">
          <el-input v-model="notice.title" placeholder="请输入标题" />
        </el-form-item>
      </el-form>
      <editor
        v-model="notice.content"
        :options="editorOptions"
        language="zh_CN"
        height="80%"
      />
      <div class="button-div">
        <el-button v-if="this.$route.query.noticeId !== undefined" type="primary" @click="commitEdit">完成编辑</el-button>
        <el-button v-else type="primary" @click="commitNotice">发布通知</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import MarkdownEditor from '@/components/MarkdownEditor'

export default {
  components: {
    editor: MarkdownEditor
  },
  data() {
    return {
      notice: {
        title: '',
        sid: '',
        content: '',
        id: null,
        editSId: null
      },
      editorOptions: {
        minHeight: '200px',
        useCommandShortcut: true,
        useDefaultHTMLSanitizer: true,
        usageStatistics: true,
        hideModeSwitch: false,
        toolbarItems: [
          'heading',
          'bold',
          'italic',
          'strike',
          'divider',
          'hr',
          'quote',
          'divider',
          'ul',
          'ol',
          'indent',
          'outdent',
          'divider',
          'table',
          'link'
        ]
      }
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
    this.getNoticeById()
  },
  methods: {
    getNoticeById() {
      if (this.$route.query.noticeId !== undefined) {
        this.getRequest('/notice/' + this.$route.query.noticeId).then(resp => {
          if (resp.code === 0) {
            this.notice.id = resp.data.id
            this.notice.sid = resp.data.sid
            this.notice.title = resp.data.title
            this.notice.content = resp.data.content
          }
        })
      }
    },
    commitEdit() {
      this.notice.editSId = this.currentUser.id
      this.putRequest('/admin/notice/', this.notice).then(resp => {
        if (resp.code === 0) {
          this.$message.success('编辑成功!')
          this.$router.push('/noticemana')
        }
      })
    },
    commitNotice() {
      this.notice.sid = this.currentUser.id
      this.postRequest('/admin/notice/', this.notice).then(resp => {
        if (resp.code === 0) {
          this.$message.success('发布成功!')
          this.$router.push('/noticemana')
        }
      })
    }
  }
}
</script>

<style scoped>
.edit-div {
  display: flex;
  justify-content:center;
  align-items: center;
  height: 100%;
}
.box-card {
  height: 100%;
  width: 80%;
}
.button-div {
  display: flex;
  justify-content:flex-end;
  margin-top: 20px;
}
.title-div {
  display: flex;
}
</style>
