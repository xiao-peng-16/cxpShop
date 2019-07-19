<template>
    <div class="content ">
      <div class="search">

        <div class="search_form" :class="{flagHome:flag_home,flagSearch:!flag_home}">
          <div class="container-fluid">
            <input type="text" v-my-focus v-model="search_word" @keyup.enter="event_click_search" :placeholder="msg" :class="{red_hint:flag_red_hint}">
          </div>
          <input @click="event_click_search" type="button" value="搜 索">
        </div>
        <ul>
          <li v-for="(hot_word,index) in hot_words_list" >
            <span v-show="(index!=0)" class="split">|</span>
            <span @click="event_click_hot_word(hot_word)"
                  :class="{Home_hot_wordBox:flag_home,SearchPage_hot_wordBox:!flag_home}">
              {{hot_word}}
            </span>
          </li>
        </ul>
      </div>
    </div>
</template>

<script>
    export default {
        name: "serach",
      props:['flag_home','props_search_word'],
      data(){
          return{
            flag_red_hint:false,
            msg:"搜索 天猫 商品/品牌/店铺",
            search_word:this.props_search_word,
            hot_words_list:['美食','装饰品','珠宝','水果','宠物','运动健身','男装','女装','童装','手机']
          }
      },
      watch:{
        search_word(val){
          if (this.flag_red_hint) {
            this.flag_red_hint=false;
            this.msg="搜索 天猫 商品/品牌/店铺";
          }
        }
      },
      methods:{
        event_click_search(){
            if (this.search_word!=''&&this.search_word!=undefined) {
              this.$emit('event_click_search',this.search_word);
            }else {
              this.flag_red_hint=true;
              this.msg="请输入关键词";
            }
          },
          event_click_hot_word(val_hot_word){
            this.search_word = val_hot_word;
            this.$emit('event_click_search',this.search_word);
         }

      }
    }
</script>

<style scoped>


  .red_hint::-webkit-input-placeholder {
    color:red;
    font-weight: 700px;
  }

  *{
    padding: 0;
    margin: 0;
  }
  body{
    font-family: "微软雅黑";
  }
  .content{
    display: inline-block;
    padding:20px 20px 0px 20px;
  }
  .split{
    color: #999999;
    margin-right:5px;
  }
  ul,li{
    margin: 0px;
    padding: 0px;
    list-style: none;
    display: inline-block;
    height: 32px;

  }
  li{
    margin-right:10px;
  }

  li:nth-of-type(odd){
    color: #999999;
  }
  li:nth-of-type(even){
    color: #FF0036;
  }
  li span{
    cursor: pointer;
  }
  .split{
    cursor: none;
  }
  .search{
    display: inline-block;
  }
  .search_form{
    /*overflow:  hidden;*/
    display: flex;
  }
  .search_form input[type=text]{
    border: none;
    margin-left: 10px;
    margin-top: 3px;
    outline:none;
    float: left;
    background: white;

  }
  .search_form input[type=button]{
    font-size: 18px;
    border: none;
    cursor: pointer;
    text-align: center;
    font-family: "微软雅黑";
    position: relative;
    left: 2px;
    background: #FF0036;
    color: white;
  }

  .flagHome{
    height: 40px;
    width: 625px;
    border: 1.9px solid #FF0036
  }
  .flagHome input[type=text]{
    height: 25px;
    line-height: 25px;
    font-size: 16px;
    width: 100%;
  }
  .flagHome input[type=button]{

    height: 38px;
    line-height: 40px;
    font-weight:900;
    width: 165px;
  }


  .flagSearch{
    height: 35px;
    width: 850px;
    border: 3px solid #FF0036;
    overflow: hidden;

  }
  .flagSearch input[type=text]{
    height: 25px;
    line-height: 25px;
    font-size: 16px;
    width: 100%;
  }
  .flagSearch input[type=button]{

    height: 35px;
    line-height: 40px;
    font-weight:900;
    width: 130px;
    position: relative;
    bottom: 5px;
  }
  .Home_hot_wordBox{
    font-size: 13.8px;

  }
    .SearchPage_hot_wordBox{
      font-size: 12.5px;
    }
</style>
