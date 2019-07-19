<template>
    <div class="bj">
      <nav_top :flag_home="false"/>
      <div class="box">
        <span>忘记了就在注册一个呗</span>
      </div>
    </div>
</template>

<script>
    import Nav_top from "@/components/nav_top";
    export default {
        name: "drop_password",
      components: {Nav_top}
    }
</script>

<style scoped>
.bj{
  background: #211327;
  height: 755px;
}
  .box{
    margin: 150px auto;
    height: 200px;
    width: 500px;
    border: 1px solid #FF0000;
    border-radius: 8px;
  }
  span{
    font-size: 30px;
    color: white;
    position: relative;
    top: 70px;
    left: 100px;
  }
</style>
