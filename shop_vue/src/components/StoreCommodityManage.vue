<template>
  <div  :style="{background:this.GLOBAL.soller_center_background}"  style="padding-left: 45px; padding-right:147px">

    <div style="z-index: 10;position: fixed;background:white;height: 30px" :style="{background:this.GLOBAL.soller_center_background}">
      <span style="padding-left: 150px">商品信息</span>
      <span style="padding-left: 300px">单价</span>
      <span style="padding-left: 110px">销量</span>
      <span style="padding-left: 130px">库存</span>
      <span style="padding-left: 300px"> </span>
    </div>

    <div style="height: 30px;"></div>

    <div class="itemBox" v-for="item in StoreCommodityList" >
      <div style="width: 130px;cursor: pointer;" @click="click_img(item.cID)">
        <img :src="item.cPhotoname" style="height: 100px;max-width:120px" ></div>
      <div class="cNameBox" @click="click_img(item.cID)">
        <span>{{item.cName}}</span>
      </div>
      <div style="width:160px;padding-left: 55px">
        <span>￥{{item.cPrice.toFixed(2)}} 元</span>
      </div>
      <div style="width:150px;padding-left: 50px">
        <span>{{item.cSalesVolume}} 件</span>
      </div>
      <div style="width:300px;padding-left: 50px">
        <span>{{item.cStock}} 件</span>

      </div>
    </div>
  </div>



</template>

<script>
    export default {
        name: "StoreCommodityManage",
      data(){
        return{
          StoreCommodityList:[]
        }
      },
      methods:{
        click_img(cID){
          this.$router.push({name:'commodityPage',query:{cID}})
        },
      },
      created() {
          this.$axios.get('/selStoreCommodityList')
            .then(res=>{
              if (res.data.success){
                this.StoreCommodityList=res.data.data;
                for (var i=0;i<this.StoreCommodityList.length;i++) {
                  this.StoreCommodityList[i].cPhotoname=this.GLOBAL.commodityImagesUrl+this.StoreCommodityList[i].cPhotoname;
                }
              }
            })
      }
    }
</script>



<style scoped>
  .itemBox{
    background: #FCFCFC;
    border: 1px solid #CCCCCC;
    padding:  10px 10px 10px 10px;
    width:1080px;
  }
  .itemBox div{
    display: inline-block
  }


  .cNameBox{
    width: 300px;
    padding-right: 20px;
    cursor: pointer;
  }
  .cNameBox:hover{
    text-decoration:underline;
    color: #FF6600;
  }
</style>
