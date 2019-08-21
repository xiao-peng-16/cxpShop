<template>
  <div>
    <nav_top :flag_home="false"/><br/>


    <div style="position: fixed;width: 100%;height: 60px; background: white;z-index: 100;">
      <img id="tiangou" src="../assets/tmall.jpg">
      <span id="shop_car">购物车</span>
    </div>
    <div style="height: 40px;"></div>



    <div v-if="flag_show">

      <div class="container itenListBox">
        <div class="row">
          <div class="col-lg-1 col-md-2"></div>
          <div class="col-3 cName"><span>商品信息</span></div>
          <div class="col-1 center offset-1" style="padding-left:40px"><span>单价</span></div>
          <div class=" offset-1  center"><span>数量</span></div>
          <div class="offset-1 col-1 center" style="padding-left:35px"><span>金额</span></div>
          <div class="offset-1 col-1 center" style="padding-left:11px"><span>操作</span></div>
        </div>



        <div class="itemBox row" v-for="(item,index) in dataList">
          <input  v-model="checkIndexList" :value="index" type="checkbox" >
          <div class="col-lg-1 col-md-2">
            <img :src="item.cPhotoname" @click="click_img(item.cID)" style="cursor: pointer;">
          </div>
          <div class="col-3 cNameBox"  @click="click_img(item.cID)">
            <span>{{item.cName}}</span>
          </div>
          <div class="cPrice col-1 center down" style="padding-left: 40px">
            <span>￥{{item.cPrice.toFixed(2)}}</span>
          </div>
          <div class="center down" style="padding-left: 80px">
            <button type="button" class="btn btn-info" @click="less(item)"><span>-</span></button>
            <input v-model.number="item.shop_number" @input="input(item)">
            <button type="button" class="btn btn-info" @click="item.shop_number++"><span>+</span></button>
          </div>
          <div class="price col-1 center down" style="margin-left: 88px">
            <span>￥{{(item.cPrice*item.shop_number).toFixed(2)}}</span>
          </div>
          <div class="price col-1 center " style="margin-left: 89px;margin-top: 25px">
            <div style="padding-left: 20px"><span class="operationSpan" @click="delByID(item,index)" >删除</span></div>
            <div style="width: 200px"><span class="operationSpan" @click="toFavoriteByID(item,index)" >移入收藏夹</span></div>
          </div>
        </div>
        <!--        不要删除  调列表 到底部距离-->
        <div class="placeholder"></div>


      </div>
      <div class="bottom_bj">
        <!--      <img src="../assets/shop_car_bj.jpg">-->
      </div>
      <div class="bottomMessage">
        <div class="operationBox">
          <input  v-model="checkAllFlag" type="checkbox" style="top: 3px; cursor: pointer">
          <span class="operationSpan" @click="checkAllFlag=!checkAllFlag">全选</span>
          <span class="operationSpan" @click="delByCheckIndexList" >删除</span>
          <span class="operationSpan" @click="toFavoriteByCheckIndexList" >移入收藏夹</span>
        </div>

        <div class="shop" @click="shop"><span>结 算</span></div>
        <div class="sumPriceBox">
          <span id="heji">合计 : </span>
          <span id="sumPrice">{{(this.sumPrice).toFixed(2)}}</span>
        </div>
      </div>


    </div>
    <div v-else>
      <div class="notData">
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

            dataList:[],
            ckeckDataList:[],
            checkIndexList:[],
            checkAllFlag:false
          }
        },
        computed:{
          sumPrice(){
            var sunPrice=0;
            for (var i=0;i<this.ckeckDataList.length;i++) {
              sunPrice+=this.ckeckDataList[i].shop_number*this.ckeckDataList[i].cPrice;
              }
            return sunPrice;
          },
          flag_show(){
            return this.dataList.length>0;
          },
        },
        watch:{
          checkIndexList:function (val) {
            this.ckeckDataList=[];
            for (var i=0;i<this.checkIndexList.length;i++) {
              //添加 要修改的数据
              this.ckeckDataList.push(this.dataList[this.checkIndexList[i]])
            }
          },
          checkAllFlag:function (val) {
            if (val){
              this.checkIndexList =[];
              for (var i = 0; i < this.dataList.length; i++) {
                this.checkIndexList.push(i);
              }
            } else {
              this.checkIndexList =[];
            }
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
          returnOrderList(){
            var orderList =[];
            for (var i=0;i<this.ckeckDataList.length;i++) {
              orderList.push({
                shop_carID:this.ckeckDataList[i].shop_carID,
                cID:this.ckeckDataList[i].cID,
                shop_number:this.ckeckDataList[i].shop_number
              })
            }
            console.log(orderList);
            return orderList;
          },
          //  直接对 某一行 删除/移入收藏夹   需要对dataList和checkIndexList更新
          updList_specific(index){
            this.dataList.splice(index,1);

            for (var i=0;i<this.checkIndexList.length;i++){
              if (this.checkIndexList[i]>index){
                this.checkIndexList[i]--;
              } else if (this.checkIndexList[i] === index){
                this.checkIndexList.splice(i,1);
                i--;
              }
            }
          },
          //通过勾选 多行 删除/移入收藏夹   需要对dataList和checkIndexList更新
          updList_multi(){
            //先降序 这样从后面删除 不影响前面元素
            this.checkIndexList.sort(function (a, b) {
              return b-a;
            });
            for (var i=0;i<this.checkIndexList.length;i++) {
              this.dataList.splice(this.checkIndexList[i],1);
            }
            this.checkIndexList = [];
          },
          delByID(item,index){
              this.$axios.post('/delShop_Car',[{shop_carID:item.shop_carID}])
                .then(res=>{
                this.updList_specific(index);
              })
          },
          delByCheckIndexList(){
            var delList =this.returnOrderList();
            if (delList.length ==0){
              return;
            }
            this.$axios.post('/delShop_Car',delList)
              .then(res=>{
                this.updList_multi();
              })
          },
          toFavoriteByID(item,index){
            this.$axios.post('/shop_car_To_Favorite',[{shop_carID:item.shop_carID,cID:item.cID,}])
              .then(res=>{
                this.updList_specific(index);
              })
          },
          toFavoriteByCheckIndexList(){
            var toFavoriteList =this.returnOrderList();
            if (toFavoriteList.length ==0){
              return;
            }
            this.$axios.post('/shop_car_To_Favorite',toFavoriteList)
              .then(res=>{
                this.updList_multi();
              })
          },
          shop(){
            var shopList =this.returnOrderList();
            if (shopList.length ==0){
              return;
            }
            this.$axios.post('/Shop_Car_Shop',shopList)
              .then(res=>{
              if (res.data.success){
                this.$router.push({name:'shop_success'});
              }else {
                if (res.data.status==this.GLOBAL.ResponseStatus.USER_LOGIN_OVERDUE){
                  alert("请先登录");
                  this.$router.push({name:'enter'});
                } else if (res.data.status==this.GLOBAL.ResponseStatus.SHOP_NOT_SUFFICIENT_FUNDS){
                    alert('用户余额不足');
                }else {
                  this.$router.push({name:'error',params:{response_data:res.data}});
                }
              }
            })
          },
          click_img(cID){
            this.$router.push({name:'commodityPage',query:{cID}})
          },
          init(){
            this.$axios.post('/selShop_Car_PageDataByUID')
              .then(res=>{
                if (res.data.success){
                  this.dataList=res.data.data;
                  this.checkAllFlag = true;
                  console.log(this.dataList)

                  for (var i=0;i<this.dataList.length;i++) {
                    this.dataList[i].cPhotoname=this.GLOBAL.commodityImagesUrl+this.dataList[i].cPhotoname;
                  }
                }else {
                  if (res.data.status==this.GLOBAL.ResponseStatus.USER_LOGIN_OVERDUE){
                    alert("请先登录");
                    this.$router.push({name:'enter'});
                  } else {
                    this.$router.push({name:'error',params:{response_data:res.data}});
                  }
                }
              })
          }
        },
        created() {
          this.init();
        }

    }
</script>

<style scoped>

  .container{
    max-width: 1280px;
  }


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
    padding: 10px 0px;
  }
  .itemBox img{
    height: 110px;
    max-width: 115px;
  }

  . center{
    text-align: center;
  }
  .cNameBox{
    margin-left: 20px;
    margin-top: 15px;
    cursor: pointer;
  }
  .cNameBox:hover{
    text-decoration:underline;
    color: #FF6600;
  }
  .itemBox input{
    width: 50px;
    padding-left:8px;
    margin: 0px;
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
  .down{
    margin-top: 30px;
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
    width: 1330px;
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


  input[type="checkbox"] {
    zoom: 155%;
    position: relative;
    top: 28px;
  }

  .operationBox{
    display: inline-block;
    padding-top: 8px;
  }
  .operationSpan{
    cursor: pointer;
    margin-right: 36px;
  }
  .operationSpan:hover{
    color: #FF4400;
    text-decoration:underline;
  }
</style>
