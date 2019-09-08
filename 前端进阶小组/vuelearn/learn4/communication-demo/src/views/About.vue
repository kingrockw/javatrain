<template>
  <div class="about">
    <h1>{{msg}}</h1>
  </div>
</template>
<script>
    import bus from '../eventBus'
    export default {
        name: "About",
        data(){
            return {
                msg:'This is an about page'
            }
        },
        mounted(){
            debugger
            bus.$on('click', () => {
                debugger
                console.log(this.msg)
                this.msg += this.msg
            })
        },
        methods: {

        }
    };
</script>