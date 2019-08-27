<template>
    <div>
        当前登录人：{{name}}<br/>
        <input type="text" v-model="name"></input>
        <br>
        <button @click="login"> 登录</button>
        <button @click="logOut"> 登出</button>
        <br>
        <button @click="pampush"> 动态参数路由测试</button>
        <br>
        <button @click="pam2push"> params参数路由测试</button>

        <br>
        <button @click="query1push"> query参数路由测试</button>
    </div>
</template>
<script>
    import Cookies from 'js-cookie';
    export default {
        data() {
            return {
                name:'石头'
            };
        },
        mounted() {

        },
        beforeDestroy() {

        },
        methods: {
            login(){
                Cookies.set("user",this.name)
                this.$router.push({
                    name:'home'
                })
            },
            logOut(){
                this.name = ''
                Cookies.remove("user")
            },
            pampush(){
                this.$router.push({
                    path:'pam/12'
                })
            },
            pam2push(){
                //不生效, push 参数是根据path找路由时，params参数是无效的，要自己写完整的path
//                this.$router.push({
//                    path:'pam2',
//                    params:{
//                        id:13
//                    }
//                })
//                this.$router.push({
//                    name:'pam2',
//                    params:{
//                        id:13
//                    }
//                })
            },
            query1push(){
                this.$router.push({
                    path:'query1',
                    query:{
                        id:14
                    }
                })
            }
        }
    };
</script>

<style>

</style>