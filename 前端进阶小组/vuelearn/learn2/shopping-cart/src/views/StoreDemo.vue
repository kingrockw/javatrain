<template>
    <div>
        <div>{{$store.state.count}}</div>
        {{mycount}}
        <br>{{number}}
        <div>
            <Button @click="add">增加</Button>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                mycount:this.$store.state.count
            };
        },
        mounted() {

        },
        beforeDestroy() {

        },
        computed:{
            number(){
                return this.$store.state.count
            }
        },
        methods: {
            add(){
                this.$store.commit('increment')
            }
        }
    };
</script>

<style>

</style>