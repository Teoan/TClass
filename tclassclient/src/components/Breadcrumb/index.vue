<template>
  <div class="breadcrumb">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="item in levelList" :key="item.path" :to="item.path">{{ item.meta.title }}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'Navbar',
  data() {
    return {
      levelList: []
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb() {
      let matched = this.$route.matched.filter(item => item.name)
      const first = matched[0]
      // if (first && first.name.trim().toLocaleLowerCase() !== 'home'.toLocaleLowerCase()) {
      if (first && first.path !== '/home') {
        matched = [{ path: '/home', meta: { title: '首页' }}].concat(matched)
      } else {
        matched = []
      }
      this.levelList = matched
    }
  }
}
</script>

<style>
.breadcrumb .el-breadcrumb__item .el-breadcrumb__inner {
  color:#fafbfc;
}
.breadcrumb .el-breadcrumb__item:last-child .el-breadcrumb__inner {
  font-weight: 400;
  cursor: text;
  color:#fafbfc;
}

</style>
