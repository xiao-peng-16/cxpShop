<template>
    <div>
      <nav_top :flag_home="false"/>
      <div class="imgBox"></div>
    </div>
</template>

<script>
    import Nav_top from "@/components/nav_top";
    export default {
        name: "about_us",
      components: {Nav_top}
    }
</script>

<style scoped>
  .imgBox{
    z-index: -1;
    position: relative;
    bottom: 10px;
    height: 740px;
    background-image: url("../assets/aboutUs_bj.jpg");
    background-repeat:no-repeat ;
    background-size:100% 100%;
  }
</style>
