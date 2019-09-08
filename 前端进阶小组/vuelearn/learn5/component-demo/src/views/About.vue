<template>
    <div class="about">
        <div>异步组件</div>
        <button @click="isShow = true">show</button>
        <!--<async-example v-show="isShow"></async-example>-->
        <tooltip v-if="isShow"></tooltip>
        <async-example v-if="isShow"></async-example>
        <!--<asyncComponent></asyncComponent>-->
    </div>
</template>
<script>
//    https://www.w3cplus.com/vue/async-vuejs-components.html
    import ErrorComponent from '../components/ErrorComponent.vue'
    import LoadingComponent from '../components/LoadingComponent.vue'
    export default {
        components: {
            Tooltip: () => import('../components/Tooltip'),
            asyncExample:(resolve, reject)=> {
                setTimeout(function () {
                    // 向 `resolve` 回调传递组件定义
                    resolve({
                        template: '<div>I am async!</div>'
                    })
                }, 1000)
            },
            asyncComponent:()=>({
                // 需要加载的组件 (应该是一个 `Promise` 对象)
                component: new Promise((resolve,reject)=>{
                    setTimeout(()=>{
                        resolve({
                            template: '<div>I am MyComponent!</div>'
                        })
                    },5000)
                }),
//                    import('../components/MyComponent.vue'),
                // 异步组件加载时使用的组件
                loading: LoadingComponent,
                // 加载失败时使用的组件
                error: ErrorComponent,
                // 展示加载时组件的延时时间。默认值是 200 (毫秒)
                delay: 2000,
                // 如果提供了超时时间且组件加载也超时了，
                // 则使用加载失败时使用的组件。默认值是：`Infinity`
                timeout: 6000
            })

        },
        data(){
            return {
                isShow: false
            }
        }
    }

</script>
