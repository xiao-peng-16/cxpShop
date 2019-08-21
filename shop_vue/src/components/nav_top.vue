<template>
    <div :class="{homePage:flag_home,serachPage:!flag_home}">

        <div class="containerBox">

          <div class="left box">
              <router-link :to="{name:'home'}" tag="div"  v-if="!flag_home" id="TianGou">
                <span class="iconfont" style="color: #FF0036">&#xe867;</span>
                <span>天狗首页</span>
              </router-link>


            <ul v-if="flag_enter" >
              <li><span>Hi, {{this.user.username}}</span></li>
              <li><span>余额{{(this.user.money).toFixed(2)}}</span></li>
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
                <span class="iconfont" style="font-size: 12px;" :style="{color: flag_color}">&#xe63a;</span>
                <span>购物车</span>
                <span style="color: #FF4400;font-weight: 700" :style="{color: flag_color}">
                  {{getShop_CarCount()}}
                </span>
              </router-link>

              <router-link :to="{name:'favorite'}" tag="li">
                <span class="iconfont" style="font-size: 12px;">&#xe636;</span>
                <span>收藏夹</span>
              </router-link>
              <router-link :to="{name:'soller_center'}" tag="li"><span>卖家中心</span></router-link>

<!--              <router-link :to="{name:'service'}" tag="li"><span>联系客服</span></router-link>-->
<!--              <li><span>联系客服</span></li>-->

              <transition leave-active-class="animated hinge">
                <li v-show="flag_tou_su" @click="flag_tou_su=false" ><span>投诉</span></li>
              </transition>

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
            flag_color:'white',
            flag_tou_su:true,
            flag_enter:false,
            user:{
              username:"",
              money:""
            }
          }
        },
        methods:{
          outenter(){
            this.$axios.post('/outEnter')
              .then(res=>{
                this.$router.push({name:'enter'})
              })
          },
          getShop_CarCount(){
            if (null != this.user && 0<this.user.shop_CarCount){
              return this.user.shop_CarCount;
            }
          }
        },
        created() {
          if (!this.flag_home){
            this.flag_color='#FF4400';
          }


            this.$axios.post('/selUserById')
              .then(res=>{
              this.flag_enter=res.data.success;
              this.GLOBAL.flag_enter=res.data.success;
              this.user=res.data.data;
              this.GLOBAL.user=res.data.data;
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
  .homePage .containerBox{
    margin-left: 100px;
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
    width: 100%;
    position: fixed;
    z-index: 999;
  }
  .serachPage .containerBox{
    margin-left: 80px;
    background: black;
    max-width: 1395px;
  }
  .serachPage span{
    color: #777777;
    font-size: 11px;
    font-weight: lighter;
  }
  .serachPage li{
    margin: 0px 10px;
    position: relative;
    bottom: 6px;
  }



.containerBox{
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
