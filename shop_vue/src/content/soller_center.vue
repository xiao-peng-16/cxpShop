<template>
    <div>
      <nav_top :flag_home="false" /><br/>
      <div style="position: fixed;width: 100%;height: 70px; background: white;z-index: 100;padding-top: 10px;border-bottom: 1px solid #DADADA">
        <img  src="../assets/tmall.jpg">
        <span id="title">— 卖家中心 —</span>
      </div>


      <div class="buttom" style="margin-top: 70px">
        <div class="left">
            <div @click="options=1" :class="{optionsBox:options==1,not_optionsBox:options!=1}"><span>店铺概况</span></div>
            <div @click="options=2" :class="{optionsBox:options==2,not_optionsBox:options!=2}"><span>商品管理</span></div>
            <div @click="options=3" :class="{optionsBox:options==3,not_optionsBox:options!=3}"><span>订单管理</span></div>
        </div>
        <div class="right">
          <component :is="optionsComponents" style="z-index:0"/>
        </div>
      </div>
    </div>
</template>

<script>
    import Nav_top from "@/components/nav_top";
    import StoreCommodityManage from "@/components/StoreCommodityManage";
    import StoreHome from "@/components/StoreHome";
    import StoreOrderManage from "@/components/StoreOrderManage";
    export default {
        name: "soller_center",
      components: {StoreOrderManage, StoreHome, StoreCommodityManage, Nav_top},
      data(){
          return{
            options:1,
            optionsComponents:'StoreHome',
          }
        },
        watch:{
          options(val){
            switch (val) {
              case 1: this.optionsComponents='StoreHome'; break;
              case 2: this.optionsComponents='StoreCommodityManage'; break;
              case 3: this.optionsComponents='StoreOrderManage'; break;
            }
          }
        }
    }
</script>

<style scoped>




  #title{
    position: relative;
    top: 7px;
    font-size: 16px;
    font-weight: 700;
    margin-left: 480px;
  }

  .buttom{
    margin-top: 10px;
  }


  /*.optionsBox{*/
  /*  background: red;*/
  /*}*/
  /*.left{*/
  /*  width: 220px;*/
  /*  background: #C2EB6B;*/
  /*  position: fixed;*/
  /*  margin-top: 10px;*/
  /*  margin-left: 38px;*/
  /*  border-radius: 5px;*/

  /*}*/
  /*.left div{*/
  /*  height: 30px;*/
  /*  padding-left: 70px;*/
  /*  padding-top: 2px;*/
  /*  letter-spacing:2px;*/
  /*  font-weight: 600;*/
  /*  cursor: pointer;*/

  /*}*/


  .optionsBox{
    background: #292929;
    border-left: 2px solid #F28328;

  }
  .not_optionsBox{
    border-left: 2px solid #333333;

  }
  .left{
    width: 260px;
    height: 100%;
    background: #333333;
    position: fixed;
  }
  .left div{
    padding: 10px 70px;
    color: #FAFAFA;
    letter-spacing:2px;
    font-weight: 400;
    cursor: pointer;

  }


  .right{
    left: 260px;
    /*background: #138496;*/
    position: absolute;
  }
</style>
