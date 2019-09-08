<template>
  <div id="app">
    <div id="nav">
      <Button @click="showDialog">显示弹框</Button>
      <router-link to="/">动态组件</router-link> |
      <router-link to="/about">异步组件</router-link> |
      <router-link to="/homework">HOMEWORK</router-link>
      <!--<com1></com1>-->
    </div>
    <keep-alive>
      <router-view />
    </keep-alive>
  </div>
</template>

<script>
  export default {
      data(){
          return {

          }
      },
      methods:{
          showDialog(){
              this.$myDialog({
                  title:'提示',
                  content:'这里是提示内容'
              }).then((ret)=>{
                  console.log(ret);
                  if(ret === 'ok'){
                      console.log('点击了确定');
                  } else {
                      console.log('点击了取消');
                  }
              })
          }
      }
  }
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
