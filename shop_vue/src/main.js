// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios';
import qs from 'qs';
import global from '@/components/global_variable'


import VueAwesomeSwiper from 'vue-awesome-swiper'

// require styles
import 'swiper/dist/css/swiper.css'

Vue.use(VueAwesomeSwiper, /* { default global options } */)



Vue.prototype.GLOBAL=global;
Vue.prototype.$qs = qs;
Vue.prototype.$axios = axios;

Vue.config.productionTip = false

axios.defaults.baseURL = global.baseURL;

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

axios.defaults.withCredentials = true;
require('promise.prototype.finally').shim();

require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')


Vue.directive('myFocus',{
  inserted:function (el) {
    el.focus();
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
