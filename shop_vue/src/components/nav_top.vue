<template>
    <div :class="{homePage:flag_home,serachPage:!flag_home}">

        <div class="container">

          <div class="left box">
              <router-link :to="{name:'home'}" tag="div"  v-if="!flag_home" id="TianGou">
                <span class="iconfont" style="color: #FF0036">&#xe867;</span>
                <span>天狗首页</span>
              </router-link>


            <ul v-if="flag_enter" >
              <li><span>Hi, {{this.user.username}}</span></li>
              <li><span>余额{{this.user.money}}</span></li>
              <li><span @click="outenter">退出</span></li>
            </ul>
            <ul v-else>
              <li><span>汪，欢迎来到天狗</span></li>
              <router-link :to="{name:'enter'}" tag="li"><span>请登录</span></router-link>
              <router-link :to="{name:'login'}" tag="li"><span>免费注册</span></router-link>
            </ul>

          </div>

          <div class="right box">
            <ul>
              <router-link :to="{name:'shop_car'}" tag="li">
                <span class="iconfont" style="font-size: 12px">&#xe63a;</span>
                <span>购物车</span>
              </router-link>
              <router-link :to="{name:'favorite'}" tag="li"><span>收藏夹</span></router-link>
              <router-link :to="{name:'about_us'}" tag="li"><span>关于我们</span></router-link>
              <transition leave-active-class="animated hinge">
                <li v-show="flag_shou_hou" @click="hidden" ><span>售后</span></li>
              </transition>

              <router-link :to="{name:'tou_su'}" tag="li"><span>投诉</span></router-link>
            </ul>
          </div>

        </div>



    </div>
</template>

<script>
    export default {
        name: "nav_top",
        props:['flag_home'],
        data(){
          return{
            flag_shou_hou:true,
            flag_enter:false,
            user:{
              username:"",
              money:""
            }
          }
        },
        methods:{
          hidden(){
            this.flag_shou_hou=false;
          },
          outenter(){
            this.$axios.post('/outEnter')
              .then(res=>{
                this.$router.push({name:'enter'})
              })
          }
        },
        created() {
            this.$axios.post('/selUserById')
              .then(res=>{
              this.flag_enter=res.data.flag_enter;
              this.GLOBAL.flag_enter=res.data.flag_enter;
              this.user=res.data.user;
              this.GLOBAL.flag_enter=false;
            })

        }

    }
</script>

<style scoped>
  #TianGou{
    display: inline-block;
    position: relative;
    bottom: 6px;
    margin-right: 10px;
    cursor: pointer;
  }

  .homePage{
    background: #F12F04;
    height: 40px;
  }
  .homePage .container{
    background: black;
    max-width: 1330px;
  }
  .homePage span{
    color: white;
    font-size: 13px;
  }
  

  .serachPage{
    background: #F2F2F2;
    height: 26px;

  }
  .serachPage .container{
    background: black;
    max-width: 1395px;
  }
  .serachPage span{
    color: #777777;
    font-size: 11px;
    font-weight: lighter;
  }
  .serachPage li{
    margin: 0px 5px;
    position: relative;
    bottom: 6px;
  }



.container{
  background: black;
  max-width: 1330px;
}
  .box{
    margin: 5px 0px;
    height: 32px;

  }
  .left{
    float: left;
  }
  .right{
    float: right;
  }
ul,li{
  margin: 0px;
  padding: 0px;
  list-style: none;
  display: inline-block;
  height: 32px;

}
li{
  margin-left: 10px;
}
  span{
    font-style: normal;
    cursor: pointer;
  }
</style>
