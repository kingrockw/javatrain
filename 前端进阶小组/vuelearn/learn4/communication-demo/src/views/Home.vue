<template>
    <div class="home">
        <img alt="Vue logo" src="../assets/logo.png"/>
        <input v-focus style="width: 200px"></input>
        <About></About>
        <HelloWorld msg="Welcome to Your Vue.js App"/>

    </div>
</template>

<script>
    // @ is an alias to /src
    import HelloWorld from "@/components/HelloWorld.vue";
    import About from  "./About.vue"
    export default {
        name: "home",
        components: {
            HelloWorld,
            About
        }
    };
</script>
