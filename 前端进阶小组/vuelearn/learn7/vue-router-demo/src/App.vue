<template>
  <div id="app">
    <div id="nav">
      <div>当前路由{{mypath}}</div>
        <router-link to="/login">login</router-link> |
        <router-link to="/home">Home</router-link> |
        <router-link to="/pam/11">动态路由匹配</router-link> |
        <router-link to="/pam2">params参数</router-link> |
        <router-link to="/query1">query参数</router-link> |
        <router-link to="/about">About</router-link> |
    </div>
    <transition name="fade">
      <router-view />
    </transition>
  </div>
</template>


<script>
    export default {
        name: "app",
        components: {

        },
        computed:{
            mypath(){
                debugger
               return this.$store.state.RouteModule.fullPath
            }
        },
        watch:{
            //监听路由变化方式1
            '$route.name'(newVal,oldVal){
                console.log(newVal)
            },
            '$route'(newVal,oldVal){
                console.log(newVal)
            }
        }
    };
</script>
<style lang="scss">
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
