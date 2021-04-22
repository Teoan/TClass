<template>
  <el-container class="home-container">
    <el-drawer
      :visible.sync="drawer"
      :with-header="false"
      direction="ltr"
      size="250px"
      class="drawer"
    >
      <el-menu
        :router="true"
        :unique-opened="false"
        background-color="#303133"
        text-color="#fafbfc"
        active-text-color="#fafbfc"
        class="menu"
      >
        <template v-for="(item,index) in routes">
          <template v-if="!item.hidden">
            <el-submenu v-if="item.children.length>1&&isHaveRoles(item)" :key="index" :index="index+''">
              <template slot="title">
                <span class="home-icon">
                  <i :class="item.meta.icon" />
                </span>
                <span>{{ item.meta.title }}</span>
              </template>
              <template v-for="child in item.children">
                <el-menu-item v-if="!child.hidden&&isHaveRoles(child)" :key="child.path" :index="child.path">
                  {{ child.meta.title }}
                </el-menu-item>
              </template>
            </el-submenu>
            <template v-else-if="isHaveRoles(item.children[0])">
              <el-menu-item :key="item.children[0].path" :index="item.children[0].path">
                <template slot="title">
                  <span class="home-icon">
                    <i :class="item.children[0].meta.icon" />
                  </span>
                  <span>{{ item.children[0].meta.title }}</span>
                </template>
              </el-menu-item>
            </template>
          </template>
        </template>
      </el-menu>
    </el-drawer>
    <el-container>
      <el-header height="56px" class="home-header">
        <div class="breadcrumb-div">
          <el-button type="text" @click="drawer=true">
            <span class="bars-icon">
              <i class="fas fa-bars" />
            </span>
          </el-button>
          <breadcrumb />
        </div>
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link">
            <el-avatar :src="currentUser.avatarUrl" />
            <i class="fas fa-caret-down el-icon--right" />
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="home">首页</el-dropdown-item>
            <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-main class="home-main">
        <transition name="el-fade-in-linear">
          <router-view />
        </transition>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import breadcrumb from '@/components/Breadcrumb/index'

export default {
  components: { breadcrumb },
  data() {
    return {
      drawer: false
    }
  },
  computed: {
    currentUser() {
      if (this.$store.state.currentUser.role !== undefined) {
        return this.$store.state.currentUser
      } else {
        return JSON.parse(localStorage.getItem('INIT_CURRENTUSER'))
      }
    },
    routes() {
      return this.$router.options.routes
    },
    currentRole() {
      return this.currentUser.role.name
    }
  },
  methods: {
    handleCommand(command) {
      switch (command) {
        case 'home':
        {
          this.$router.push('/homeInfo')
          break
        }
        case 'userInfo': {
          this.$router.push('/userinfo')
          break
        }
        case 'logout': {
          this.$confirm('确定退出登录?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.getRequest('/logout')
            this.$store.commit('INIT_CURRENTUSER', [])
            this.$router.replace('/')
          }).catch(() => {
          })
          break
        }
      }
    },
    isHaveRoles(item) {
      if (item.meta.roles.indexOf(this.currentRole) === -1) {
        return false
      }
      return true
    }
  }
}
</script>
<style scoped>
  .home-container{
    width: 100%;
    height: 100%;
  }
  .home-header {
    background-color:#303133;
    color: #fafbfc;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .breadcrumb-div {
    display: flex;
    align-items: center;
  }
  .home-main {
    background-color: #f2f6fc;
    color: #333;
  }
  .home-icon {
    margin: 0 8px 0 0;
    font-size: 18px;
    color: #DCDFE6;
  }

  .el-dropdown-link img {
    width: 40px;
    height: 40px;
    border-radius: 24px;
  }
  .el-dropdown-link {
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
  }
  .caret-down {
    font-size: 18px;
    color: #606266;
  }
  .bars-icon {
    font-size:22px;
    margin-right: 15px;
    color: #fafbfc
  }
  .menu {
    height: 100%;
  }
</style>

