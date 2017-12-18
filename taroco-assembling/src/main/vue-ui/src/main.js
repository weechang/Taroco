import Vue from 'vue';
import iView from 'iview';
import { router } from './router/index';
import { appRouter } from './router/router';
import store from './store';
import App from './app.vue';
import '@/locale';
import 'iview/dist/styles/iview.css';
import VueI18n from 'vue-i18n';
import util from '@/libs/util';
import qs from 'qs';
import axios from 'axios';
import Cookies from 'js-cookie';
import env from '../build/env';

Vue.use(VueI18n);
Vue.use(iView);
Vue.prototype.http = axios;
const ajaxUrl = env === 'development'
? '/api'
: env === 'production'
? 'https://api.xiuyisheng.vip'
: 'https://api.xiuyisheng.vip';

axios.defaults.baseURL = ajaxUrl;
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
axios.defaults.headers.common['Accept'] = '*/*';

// axios.defaults.headers['Content-Type'] = 'application/json';
axios.defaults.headers.post['Content-Type'] = 'application/json';

axios.interceptors.request.use(function (config) {
    if (config.data) {
        config.data.token = Cookies.get('token');
    } else {
        if (config.url.indexOf('login') > -1 || config.url.indexOf('register') > -1) {
            config.url += '';
        } else {
            if (config.url.indexOf('?') > 0) {
              // 判断是否已经有‘?’存在,如果有就直接添加query字段
                config.url += '&token=' + Cookies.get('token');
            } else {
                config.url += '?token=' + Cookies.get('token');
            }
        }
    }
    // if (config.method !== 'get') {
    //     config.data = qs.stringify(config.data);
    // }
    return config;
}, function (error) {
    return Promise.reject(error);
});

axios.interceptors.response.use(
  response => {
      let res = {};
      let data = response.data;
      if (data.status === 'SUCCEED') {
          res.error = false;
          res.result = data.result;
          res.msg = data.errorMessage;
      } else {
          res.error = true;
          res.result = data.result;
          res.msg = data.errorMessage;
          if (data.errorCode === 401) {
            // 登录过期
          }
      }
      return res;
  },
  error => {
      if (error.response) {
          switch (error.response.status) {
              case 404:
                  alert(404);
                  break;
              case 500:
                  break;
              default:
                  break;
          }
      }
      return Promise.reject(error);   // 返回接口返回的错误信息
  }
  );

new Vue({
    el: '#app',
    router: router,
    store: store,
    render: h => h(App),
    data: {
        currentPageName: ''
    },
    mounted () {
        this.currentPageName = this.$route.name;
        this.$store.commit('initCachepage');
        // 权限菜单过滤相关
        this.$store.commit('updateMenulist');

    },
    created () {
        let tagsList = [];
        appRouter.map((item) => {
            if (item.children.length <= 1) {
                tagsList.push(item.children[0]);
            } else {
                tagsList.push(...item.children);
            }
        });
        this.$store.commit('setTagsList', tagsList);
    }
});
