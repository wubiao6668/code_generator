// import Vue from 'vue'
import Vue from 'vue/dist/vue.js';
import App from './App.vue'

Vue.config.productionTip = false

import 'vx-easyui/dist/themes/default/easyui.css';
import 'vx-easyui/dist/themes/icon.css';
import 'vx-easyui/dist/themes/vue.css';
import EasyUI from 'vx-easyui';
Vue.use(EasyUI);

/*引入资源请求插件*/
import VueResource from 'vue-resource';
/*使用VueResource插件*/
Vue.use(VueResource);
Vue.http.options.root = 'http://localhost:1232';

new Vue({
  render: h => h(App),
}).$mount('#app')
