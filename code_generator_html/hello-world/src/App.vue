<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    1111111111
    <pre v-highlightjs><code class="javascript">{{ sourcecode }}</code></pre>
    2222222222
    <!-- If your source-code lives in a variable called 'sourcecode' -->
    <pre v-highlightjs="sourcecode"><code class="javascript">
      {{ sourcecode }}
    </code></pre>
    33333333333333
    <!-- If you want to highlight hardcoded source-code -->
    <pre v-highlightjs>
      <code class="javascript">
</code></pre>
    444444444
  </div>
</template>

<script>
  import HelloWorld from './components/HelloWorld.vue'

  export default {
    name: 'app',
    data() {
      return {
        sourcecode: "        import Vue from 'vue'\n" +
          "\n" +
          "import App from './App.vue'\n" +
          "\n" +
          "Vue.config.productionTip = false\n" +
          "\n" +
          "import VueHighlightJS from 'vue-highlightjs'\n" +
          "\n" +
          "// Tell Vue.js to use vue-highlightjs\n" +
          "Vue.use(VueHighlightJS)\n" +
          "\n" +
          "new Vue({\n" +
          "  render: h => h(App),\n" +
          "}).$mount('#app')"
      }
    },
    components: {
      HelloWorld
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
