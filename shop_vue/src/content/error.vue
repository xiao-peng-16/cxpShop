<template>
    <div>
      {{msg}}
    </div>
</template>

<script>
    export default {
        name: "shop_error",
      data(){
          return{
            msg:'',
            response_data:this.$route.params.response_data
          }
      },
      created() {
        if (this.response_data.status==this.GLOBAL.ResponseStatus.USER_LOGIN_OVERDUE){
          alert("请先登录");
          this.$router.push({name:'enter'});
        } else if (this.response_data.status==this.GLOBAL.ResponseStatus.SHOP_NOT_SUFFICIENT_FUNDS){
          this.msg = '用户余额不足'
        }else if (this.response_data.status==this.GLOBAL.ResponseStatus.COMMODITY_STOCK_INSUFFICIENT){
          this.msg = '商品不足买家购买'
        }


      }
    }
</script>

<style scoped>

</style>
