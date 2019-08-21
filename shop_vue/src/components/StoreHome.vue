<template>
  <div style="padding-left: 30px;padding-top: 1px">

    <div class="top">
      <img src="../assets/defaultHead.jpg" style="padding-left: 13px;">
      <div class="left_message">
        <span style="font-weight: 600;font-size: 25px;letter-spacing:5px;">{{this.GLOBAL.user.username}}</span>
        <div style="margin-top: 33px">
          <span>店铺名称 : </span>
          <span style="padding-left: 8px">{{soller.storeName}}</span>
        </div>
        <div style="margin-top: 15px">
          <span>店铺地址 : </span>
          <span style="padding-left: 8px">{{soller.sProvince}}  {{soller.sCity}}</span>
        </div>
      </div>
      <div class="right_message">
        <span style="font-weight: 700;font-size: 18px;color: black">店铺评分</span>
        <div>
          <span>如实描述 : </span>
          <span>{{soller.sGradeDescribe}}</span>
        </div>
        <div>
          <span>服务态度 : </span>
          <span>{{soller.sGradeAttitude}}</span>
        </div>
        <div>
          <span>发货速度 : </span>
          <span>{{soller.sGradeDeliverySpeed}}</span>
        </div>
      </div>


    </div>

    <div class="sollerLable">
      <img src="../assets/StoreHomeLable.jpg">
      <br/>
      <div class="lable_1"><span >{{fullStoreSales.currentMonth.salesVolume}}</span></div>
      <div class="lable_2"><span >{{fullStoreSales.totality.salesVolume}}</span></div>
      <div class="lable_3"><span >{{fullStoreSales.currentMonth.earnings}}</span></div>
      <div class="lable_4"><span >{{fullStoreSales.totality.earnings}}</span></div>
    </div>



  </div>


</template>

<script>
    export default {
        name: "StoreHome",
      data(){
        return{
          soller:{},
          fullStoreSales:{
            currentMonth: {
              salesVolume: 0,
              earnings: 0
            },
            totality: {
              salesVolume: 0,
              earnings: 0
            }
          }


        }
      },
      created() {
        this.$axios.post('selStoreById')
          .then(res=>{
            if (res.data.success){
              this.soller = res.data.data;
            }else {
              if (res.data.status==this.GLOBAL.ResponseStatus.USER_LOGIN_OVERDUE){
                alert("请先登录");
                this.$router.push({name:'enter'});
              }
            }
          })

          this.$axios.post('/selFullStoreSales')
            .then(res=>{
              if (res.data.success){
                  if (null!=res.data.data.currentMonth)
                      this.fullStoreSales.currentMonth = res.data.data.currentMonth;
                  if (null!=res.data.data.totality)
                      this.fullStoreSales.totality = res.data.data.totality;
              }
            })
      }
    }
</script>

<style scoped>

  .top{
    padding: 20px;
    height: 230px;
    box-shadow: 0px 63px 30px -50px #E1ECF5 inset;
  }
  
  .top img{
    float: left;
    height: 130px;
  }
  .left_message{
    float: left;
    display: inline-block;
    margin-left: 30px;
  }
  .left_message div{
    color:gray;
    font-size: 17.5px;
  }

  .right_message{
    float: right;
    padding: 10px 20px 10px 40px;
    display: inline-block;
    border-left: 1.2px dashed #7BD4F2;
    font-size: 16px;
    color: gray;
    margin-top: 12px;
  }


  .sollerLable img{
    float: left;
  }
  .sollerLable div{
    display: inline-block;
    width: 267.2px;
    position: relative;
    bottom: 98px;
    padding-left: 45px;
    float: left;
  }
.sollerLable span{
  bottom: 105px;
  font-size: 40px;
  font-weight: 600;
}


  .lable_1{
      color: #FEFAEE;
  }
  .lable_2{
    color: #FEFDFC;
  }
  .lable_3{
    color: #FCFBFE;
  }
  .lable_4{
    color: #FFF6F8;
  }


</style>
