<template>
  <div class="edit-div">
    <el-card
      class="box-card"
      shadow="hover"
      :body-style="{height:'100%'}"
    >
      <el-form ref="notice" :model="notice" :rules="rules" label-width="60px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="notice.title" style="width: 30%;min-width: 180px;" placeholder="请输入标题" />
          <el-button v-if="this.$route.query.noticeId !== undefined" type="primary" style="margin-left: 20px;" @click="commitEdit">完成编辑</el-button>
          <el-button v-else type="primary" style="margin-left: 20px;" @click="commitNotice">发布通知</el-button>
        </el-form-item>
      </el-form>
      <editor
        v-model="notice.content"
        :options="editorOptions"
        language="zh_CN"
        height="80%"
      />
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
      rules: {
        title: [
          { required: true, message: '请输入通知标题', trigger: 'blur' }
        ]
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
      this.$refs['notice'].validate((valid) => {
        if (valid) {
          this.notice.editSId = this.currentUser.id
          this.putRequest('/admin/notice/', this.notice).then(resp => {
            if (resp.code === 0) {
              this.$message.success('编辑成功!')
              this.$router.push('/noticemana')
            }
          })
        } else {
          return false
        }
      })
    },
    commitNotice() {
      this.$refs['notice'].validate((valid) => {
        if (valid) {
          this.notice.sid = this.currentUser.id
          this.postRequest('/admin/notice/', this.notice).then(resp => {
            if (resp.code === 0) {
              this.$message.success('发布成功!')
              this.$router.push('/noticemana')
            }
          })
        } else {
          return false
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
