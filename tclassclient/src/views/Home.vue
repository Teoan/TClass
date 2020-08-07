<template>
  <el-container class="homeContainer">
    <el-aside width="200px" class="homeAside">
      <el-menu
        :router="true"
        :unique-opened="false"
      >
        <template v-for="(item,index) in routes">
          <template v-if="!item.hidden">
            <el-submenu v-if="item.children.length>1" :key="index" :index="index+''">
              <template slot="title">
                <span class="homeIcon">
                  <i :class="item.meta.icon" />
                </span>
                <span>{{ item.meta.title }}</span>
              </template>
              <template v-for="child in item.children">
                <el-menu-item v-if="!child.hidden" :key="child.path" :index="child.path">
                  {{ child.meta.title }}
                </el-menu-item>
              </template>
            </el-submenu>
            <template v-else>
              <el-menu-item :key="item.children[0].path" :index="item.children[0].path">
                <template slot="title">
                  <span class="homeIcon">
                    <i :class="item.children[0].meta.icon" />
                  </span>
                  <span>{{ item.children[0].meta.title }}</span>
                </template>
              </el-menu-item>
            </template>
          </template>
        </template>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="homeHeader">
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link">
            下拉菜单<i class="el-icon-arrow-down el-icon--right" />
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="a">黄金糕</el-dropdown-item>
            <el-dropdown-item command="b">狮子头</el-dropdown-item>
            <el-dropdown-item command="c">螺蛳粉</el-dropdown-item>
            <el-dropdown-item command="d" disabled>双皮奶</el-dropdown-item>
            <el-dropdown-item command="e" divided>蚵仔煎</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>
      <el-main class="homeMain">
        <transition name="el-fade-in-linear">
          <router-view />
        </transition>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  components: { },
  data() {
    return {
    
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.currentUser
    },
    routes() {
      return this.$router.options.routes
    }
  },
  methods: {
    handleCommand(command) {
      this.$message('click on item ' + command)
    }
  }
}
</script>
<style>
  .homeContainer{
    width: 100%;
    height: 100%;
  }
  .homeHeader {
    background-color:#303133;
    color: #FFFFFF;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    line-height: 56px;
  }

  .homeAside {
    line-height: 200px;
  }

  .homeMain {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  .homeIcon {
    margin: 0 8px 0 0;
    font-size: 18px;
    color: #606266;
  }
</style>

