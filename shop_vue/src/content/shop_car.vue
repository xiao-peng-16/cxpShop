<template>
  <div>
    <nav_top :flag_home="false"/><br/>
    <img id="tiangou" src="../assets/tmall.jpg">
    <span id="shop_car">购物车</span>


    <div v-if="flag_show">

      <div class="container itenListBox">
        <div class="row">
          <div class="col-lg-1 col-md-2"></div>
          <div class="col-3 cName"><span>商品信息</span></div>
          <div class="cPrice col-1 center"><span>单价</span></div>
          <div class="number offset-1  center"><span>数量</span></div>
          <div class=" price offset-1 col-1 center"><span>金额</span></div>
          <div class=" price offset-1 col-1 center"><span>操作</span></div>
        </div>

        <div class="itemBox row" v-for="(item,indrx) in listData">
          <div class="col-lg-1 col-md-2"><img :src="item.commodity.cPhotoname"></div>
          <div class="col-3 cNameBox"><span>{{item.commodity.cName}}</span></div>
          <div class="cPrice col-1 center down"><span>￥{{item.commodity.cPrice.toFixed(2)}}</span></div>
          <div class="offset-1  center down">
            <button type="button" class="btn btn-info" @click="less(item)"><span>-</span></button>
            <input v-model.number="item.shop_number" @input="input(item)">
            <button type="button" class="btn btn-info" @click="item.shop_number++"><span>+</span></button>
          </div>
          <div class="offset-1 col-1 center down"><span>￥{{(item.commodity.cPrice*item.shop_number).toFixed(2)}}</span></div>
          <div class="offset-1 col-1 center down"><span id="del" @click="delByID(item,indrx)" >删除</span></div>
        </div>
        <!--        不要删除  调列表 到底部距离-->
        <div class="placeholder"></div>


      </div>
      <div class="bottom_bj">
        <!--      <img src="../assets/shop_car_bj.jpg">-->
      </div>
      <div class="bottomMessage">
        <span id="msg">{{this.msg}}</span>
        <div class="shop" @click="shop"><span>结 算</span></div>
        <div class="sumPriceBox">
          <span id="heji">合计 : </span>
          <span id="sumPrice">{{(this.sumPrice).toFixed(2)}}</span>
        </div>
      </div>


    </div>
    <div v-else>
      <div class="notData" v-show="!flag_containData">
        <img src="../assets/search_notdata.png">
        <span class="side">旺~旺~旺~</span>
        <span class="maddle">你的购物车还没有商品哟，还不给我去购物</span>
      </div>
    </div>

  </div>
</template>

<script>
    import Nav_top from "@/components/nav_top";
    export default {
        name: "shopcar",
      components: {Nav_top},
      data(){
          return{
            msg:'',
            listData:[]
          }
        },
        computed:{
          sumPrice(){
            var sunPrice=0;
            for (var i=0;i<this.listData.length;i++) {
              sunPrice+=this.listData[i].shop_number*this.listData[i].commodity.cPrice;
              }
            return sunPrice;
          },
          flag_show(){
            return this.listData.length>0;
          }



    },
        methods:{
          less(item){
            if(item.shop_number>1){item.shop_number--}
          },
          input(item){
            item.shop_number=item.shop_number.toString().replace(/[^\d]/g,'');

            if (item.shop_number=='' || item.shop_number<1){
              item.shop_number=1;
            }
          },
          delByID(item,index){

              this.$axios.post('/delShop_CarByID',this.$qs.stringify({
                shop_carID:item.shop_carID
              })).then(res=>{
                this.listData.splice(index,1);
              })
          },
          shop(){
            var shopList =[];
            for (var i=0;i<this.listData.length;i++) {
              shopList.push({
                shop_carID:this.listData[i].shop_carID,
                cID:this.listData[i].commodity.cID,
                shop_number:this.listData[i].shop_number
              })
            }
            console.log(shopList);
            this.$axios.post('/Shop_Car_Shop',shopList)
              .then(res=>{
              if (res.data.flag_shop){
                this.$router.push({name:'shop_success'});
              }else {
                if (res.data.flag_enter==false){
                  alert("请先登录");
                  this.$router.push({name:'enter'});
                } else {
                  // this.$router.push({name:'error',params:{msg:res.data.msg}});
                  this.msg = res.data.msg.split(":")[1];
                }
              }
            })
          }
        },
        created() {
          this.$axios.post('/selShop_Car_PageDataByUID')
            .then(res=>{
            if (res.data.flag_selShop_Car){

              this.listData=res.data.listData;
              //这个报错
              // for (var item in this.listData) {
              //     item.commodity.cPhotoname=this.GLOBAL.commodityImagesUrl+item.commodity.cPhotoname
              // }
              for (var i=0;i<this.listData.length;i++) {
                this.listData[i].commodity.cPhotoname=this.GLOBAL.commodityImagesUrl+this.listData[i].commodity.cPhotoname;
              }
            }else {
              if (res.data.flag_enter==false){
                alert("请先登录");
                this.$router.push({name:'enter'});
              } else {
                this.$router.push({name:'error',params:{msg:res.data.msg}});
              }
            }
          })
        }

    }
</script>

<style scoped>
  *{
    padding: 0px;
  }
  .notData{
    background: #FFF8F6;
    border: 1px solid #F7EAE7;
    height: 90px;
    padding-left: 250px;
  }
  .notData img{
    height: 60px;
    margin: 15px;
  }
  .maddle{
    color: #790103;
    font-weight: bold;
  }

  #tiangou{
    margin-left: 70px;
    height: 35px;
    position: relative;
    bottom: 5px;
  }
  #shop_car{
    font-weight: 600;
    font-size: 28px;
  }
  .itenListBox{
    margin-top: 30px;
  }
  .itemBox{
    height: 130px;
    background: #FCFCFC;
    border: 1px solid #CCCCCC;
    padding: 10px;
  }
  .itemBox img{
    height: 110px;
    max-width: 115px;
  }

  .center{
    text-align: center;
  }
  .cName{
    margin-left: 20px;
  }
  .cNameBox{
    margin-left: 20px;
    margin-top: 15px;
  }
  .itemBox input{
    width: 50px;
    padding-left:8px;
    margin: 0px;
  }
  .cPrice{
    margin-left: 20px;
  }
  .btn{
    position: relative;
    bottom: 2px;
    padding-left: 10px;
    padding-right: 10px;
    width: 28px;
    height: 28px;
  }

  .btn span{
    color: #FCFCFC;
    font-size: 30px;
    line-height: 20px;
    position: absolute;
    top: 40%;
    left: 50%;
    transform: translate(-50%,-50%);
  }
  .number{
      position: relative;
      left: 30px;
  }
  .price{
    position: relative;
    left: 70px;
  }
  .down{
    margin-top: 30px;
  }

  #del{
    cursor: pointer;
  }
  
  

  .sumPriceBox{
    min-width: 160px;
    float: right;
    margin-right: 10px;
  }
  #heji{
    color: #4F4F4F;
    position: relative;
    top: 10px;
    font-size: 15px;
  }
  #sumPrice{
    color: #FE5525;
    font-size: 28px;
    font-weight: bold;
    float: right;
  }
  .shop{
    float: right;
    display: inline-block;
    background: #FF0036;
    width: 150px;
    height: 50px;
    text-align: center;
    border-radius:5px;
    cursor: pointer;
  }
  .shop span{
    color: white;
    font-size: 24px;
    position: absolute;
    top: 45%;
    transform: translate(-50%,-50%);
  }

  .bottomMessage{
    height: 50px;
    /*border: 1px solid red;*/
    border-radius:5px;
    padding-left: 30px;
    background: #E5E5E5;
    z-index: 999;
    position: fixed;
    left: 50%;
    transform: translate(-50%);
    bottom: 50px;
    margin: 0 auto;
    width: 1250px;
  }
  .bottom_bj{
    background: black;
    position: fixed;
    left: 50%;
    transform: translate(-50%);
    bottom: 0px;
    width: 1800px;
    height:100px;

    background-image: url("../assets/shop_car_bj.jpg");
    background-repeat:repeat;
  }
  .bottom_bj img{
    height: 60px;
  }
  .placeholder{
    height: 120px;
  }
  #msg{
    color: red;
    font-weight: bold;
    font-size: 30px;
  }
</style>
