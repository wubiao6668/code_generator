import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false
import VueHighlightJS from 'vue-highlightjs'
import 'highlight.js/styles/atom-one-dark.css'

// import VueHighlightJS from 'vue-highlightjs'

// Tell Vue.js to use vue-highlightjs
Vue.use(VueHighlightJS)

new Vue({
  render: h => h(App),
}).$mount('#app')
