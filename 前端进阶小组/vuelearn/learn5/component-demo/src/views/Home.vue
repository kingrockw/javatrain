<template>
    <div class="home">
        <button @click="change">切换</button>
        <!--<transition>-->
            <keep-alive>
                <component :is="current"></component>
            </keep-alive>
        <!--</transition>-->
    </div>
</template>

<script>

    /**
     * 1，动态组件不用注册
     * 2，keep-alive 可以保持组件不会被销毁（再次切换为该组件时，还是数据还是保留的）,但是只针对组件内做缓存
     */
    import com1 from '../components/Com-1.vue'
    import com2 from '../components/Com-2.vue'
    import com3 from '../components/Com-3.vue'
    export default {
        name: "home",
        components: {},
        data(){
            return {
                current: com1,
                index: 0,
                comArr: [com1, com2, com3]
            }
        },
        methods: {
            change(){
                this.index++
                this.current = this.comArr[this.index % 3]
            }
        }
    };
</script>
