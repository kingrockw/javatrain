<template>
    <div>
        <h1>组件一</h1>
        <button @click="add"> 增加</button>
        <div style="color: #42b983"> {{count}}</div>
    </div>
</template>
<script>
    export default {
        name:'com1',
        data() {
            return {
                count:0
            };
        },
        mounted() {

        },
        beforeDestroy() {

        },
        methods: {
            add(){
                this.count++
            }
        }
    };
</script>

<style>

</style>