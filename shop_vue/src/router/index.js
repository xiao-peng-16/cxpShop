import Vue from 'vue'
import Router from 'vue-router'
import home from '@/content/home'
import enter from '@/content/enter'
import login from '@/content/login'
import shop_car from '@/content/shop_car'
import favorite from '@/content/favorite'
import tou_su from '@/content/tou_su'
import drop_password from '@/content/drop_password'
import searchPage from '@/content/searchPage'
import  commodityPage from '@/content/commodityPage'
import  shop_success from '@/content/shop_success'
import  error from '@/content/error'
import  soller_center from '@/content/soller_center'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect:'home'
    },
    {
      path:'/home',
      name:'home',
      component:home
    },
    {
      path:'/enter',
      name:'enter',
      component:enter
    },
    {
      path:'/login',
      name:'login',
      component:login
    },
    {
      path:'/shop_car',
      name:'shop_car',
      component:shop_car
    },
    {
      path:'/favorite',
      name:'favorite',
      component:favorite
    },
    {
      path:'/tou_su',
      name:'tou_su',
      component:tou_su
    },
    {
      path:'/drop_password',
      name:'drop_password',
      component:drop_password
    },
    {
      path:'/searchPage',
      name:'searchPage',
      component:searchPage
    },
    {
      path:'/commodityPage',
      name:'commodityPage',
      component:commodityPage
    },
    {
      path:'/shop_success',
      name:'shop_success',
      component:shop_success
    },
    {
      path:'/error',
      name:'error',
      component:error
    },
    {
      path:'/soller_center',
      name:'soller_center',
      component:soller_center
    }








  ]
})
