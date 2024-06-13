import { createApp } from 'vue'

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import 'bootstrap/dist/js/bootstrap.bundle';
import router from './router/index'

import App from './App.vue'

createApp(App).use(router).mount('#app')
