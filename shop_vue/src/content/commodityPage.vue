<template>
  <div>
      <nav_top :flag_home="false"/><br/>
      <div class="container">
        <div class="box row">
          
          <div class="col-6">
            <div class="imgVideoBox ">
              <div v-if="this.commodity.cVideoname!='' &&this.commodity.cVideoname!=null" class="videoBox"><video_component :resource="resource"/></div>
              <div v-else class="imgBox"><img :src="resource.poster"></div>
            </div>
          </div>

          <div class="col-6">
              <div class="messageBox">

                  <div class="topBox">
                    <span class="cName">{{commodity.cName}}</span><br/>
                    <div class="beizhu"><span>{{commodity.cDescribe}}</span></div>
                  </div>

                  <div class="priceBox">
                      <span class="price_left">价格</span>
                      <span class="price_right">{{parseInt(commodity.cPrice).toFixed(2)}}</span>
                  </div>

                  <div class="split"></div>
                  <div class="middleBox">
                    <div>
                      <span class="middleBox_left">销量</span>
                      <span class="middleBox_right">{{commodity.cSalesVolume}} </span>
                    </div>
                    <span class="middleBox_solit">|</span>
                    <div>
                      <span class="middleBox_left">人气</span>
                      <span class="middleBox_right">{{commodity.cPopularity}} </span>
                    </div>
                    <span class="middleBox_solit">|</span>
                    <div>
                      <span class="middleBox_left">送天狗积分</span>
                    </div>
                  </div>
                  <div class="split"></div>

                  <div class="shop_numberBox">
                    <span class="shu_liang">数量</span>
                    <div class="numBox">
                      <span @click="shop_number--" class="arrows_left iconfont">&#xe610;</span>
                      <input v-model.number="shop_number" type="text">
                      <span @click="shop_number++" class="arrows_right iconfont">&#xf034f;</span>
                    </div>
                    <span class="cStock">库存{{commodity.cStock}}件 </span>
                  </div>

                    <div class="buttonBox">
                      <div class="buttonBox_left" @click="shop"><span>立刻购买</span></div>
                      <div class="buttonBox_right" @click="addShop_Car" :class="{buttonBox_right_notAdd:this.flag_notAddShop_car,buttonBox_right_Add:!this.flag_notAddShop_car}">
                        <span class="iconfont">&#xe63a;</span>
                        <span>{{this.msg_addShopcar}}</span>
                      </div>
                    </div>
              </div>
          </div>
          
        </div>

      </div>


  </div>


</template>


<script>

  import Video_component from "@/components/video_component";
  import Nav_top from "@/components/nav_top";
    export default {
        name: "commodityPage",
      components: {Nav_top, Video_component},
      data(){
          return{
            shop_number:1,
            msg_addShopcar:'加入购物车',
            flag_notAddShop_car:true,
            commodity:{},
            resource:{
              poster:"",
              src:""
            }
          }
      },
      methods:{
        addShop_Car(){
          if(this.flag_notAddShop_car){
            this.$axios.get('/addShop_Car',{
              params:{
                cID:this.commodity.cID,
                shop_number:this.shop_number
              }
            }).then(res=>{
              if (res.data.flag_addShop_Car){
                this.flag_notAddShop_car=false;
                this.msg_addShopcar="已添加至购物车";
                alert("购物车添加成功");

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

          },
        shop(){
          this.$axios.post('/UserShop',[{
            cID:this.commodity.cID,
            shop_number:this.shop_number
          }]).then(res=>{
              if (res.data.flag_shop){
                this.$router.push({name:'shop_success'});
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
      },
      created() {
             //  this.commodity = this.$qs.parse(this.$route.query.CommodityMessage);
             // this.resource.poster = this.GLOBAL.commodityImagesUrl+this.commodity.cPhotoname;
             // this.resource.src = this.GLOBAL.commodityVideoUrl + this.commodity.cVideoname;
            this.$axios.get('/selSearchDataByCID',{
              params:{
                cID: this.$route.query.cID
              }
            }).then(res=>{
              this.commodity = res.data;
              this.resource.poster = this.GLOBAL.commodityImagesUrl+this.commodity.cPhotoname;
              this.resource.src = this.GLOBAL.commodityVideoUrl + this.commodity.cVideoname;
            })
        // this.resource.src = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
      },
      watch:{
        shop_number:function (val) {
          this.shop_number=this.shop_number.toString().replace(/[^\d]/g,'');

          if (this.shop_number=='' || this.shop_number<1){
            this.shop_number=1;
          }

        }
      }
    }
</script>

<style scoped>
  *{
    padding: 0px;
  }

  .middleBox_left, .cStock, .middleBox_solit , .shu_liang{
    color: #999;
  }
  .container{
    max-width: 1300px;
  }
  .videoBox{
    width: 650px;
    margin: auto auto;
    position: absolute;
    top: 10%;
    }
  .box{
    height: 460px;
  }
  .imgBox{
      position: absolute;
      left: 50%;
      transform: translate(-50%);
  }
  .imgBox img{
    margin: 0px auto;

    height: 460px;
  }
  .messageBox{
    margin-top: 40px;
    width: 440px;
    height: 400px;
  }
  .topBox{
    margin: 10px;
  }
  .cName{
    color: black;
    font-size: 26px;
    font-weight: bold;
  }
  .beizhu{
    margin-left: 10px;
    width: 100%;
    height: 25px;
    color: #FF3761;
  }
  .price_left{
    position: relative;
    top: 3px;
    left: 15px;
    font-size: 18px;
    color: #999;
  }
  .price_right{
    position: relative;
    top: 10px;
    left: 50px;
    color: #FF0036;
    font-size: 35px;
    font-weight: bold;
  }
  .priceBox{
    margin-top: 25px;
    margin-bottom: 10px;
    background-image: url("../assets/price_bj.png");
    width: 100%;
    height: 70px;
  }
  
  .middleBox div{
    display: inline-block;
    margin: 0px 30px;
  }
  .middleBox_right{
    color: #FF0070;
  }
  .split{
    margin: 7px auto;
    height: 0.8px;
    background:  #999;
  }
  .shop_numberBox{
    margin-top: 20px;
  }
  .shop_numberBox >span{
    margin-left: 10px;
    position: relative;
    bottom: 1px;
  }
  .numBox{
    display: inline-block;
  }
  input[type=text]{
    width: 50px;
    padding-left: 15px;
    font-size: 18px;
    line-height: 18px;
  }
  .arrows_left{
    cursor: pointer;
    font-size: 25px;
    position: relative;
    top:3px;
    left: 8px;
  }
  .arrows_right{
    cursor: pointer;
    font-size: 25px;
    position: relative;
    top:3px;
    right: 8px;
  }

  .buttonBox{
    margin-top: 30px;
    margin-left: 18px;
  }
  
  .buttonBox div{
    display: inline-block;
    text-align: center;

  }
  .buttonBox div span{
    position: relative;
    top: 7.5px;
  }
  .buttonBox_left{
    cursor: pointer;
    background: #FFEDED;
      color: #FF0036;
      border: 1px solid #FF0036;
      width: 180px;
      height: 40px;
      margin-right: 15px;
  }
  .buttonBox_right{
      background: #FF0036;
      color: white;
      width: 182px;
      height: 42px;
      position: relative;
      bottom: 2px;
  }
  .buttonBox_right_notAdd{
    cursor: pointer;
  }
  .buttonBox_right_Add{
    color: #713936;
  }
</style>
