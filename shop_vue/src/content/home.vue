<template>
    <div class="home">
      
        <div class="gg"><img src="../assets/618.jpg"></div>
        <div class="nav_topBox container-fluid"><nav_top :flag_home="true"/></div>
      <br/><br/>
      <div class=" container">
          <div class="row">

            <div class="col-3">
              <div class="gifBox"><img src="../assets/gif.gif"> </div>
              <div class="sortBox_Box">
                <div class="sortBox">
                  <div class="sordWordBox" v-for="item in sortList" @click="event_click_hot_word(item.word)">
                    <span class="iconfont" v-html="item.icon" style="font-size: 20px"></span>
                    <span style="font-size: 18px">{{item.word}}</span>
                  </div>
                </div>
              </div>

            </div>
            <div class="serachBox col-7">
              <serarch @event_click_hot_word="event_click_hot_word" @event_click_search="event_click_search" :flag_home="true"/>
            </div>
          </div>
      </div>



      <div class="row">
<!--           <div class="sortBox col-2 offset-1">-->
<!--             <div class="sordWordBox" v-for="item in sortList" @click="event_click_hot_word(item.word)">-->
<!--               <span class="iconfont" v-html="item.icon" style="font-size: 20px"></span>-->
<!--               <span style="font-size: 18px">{{item.word}}</span>-->
<!--             </div>-->
<!--           </div>-->
         </div>

         <swiper :options="swiperOption" ref="mySwiper" @someSwiperEvent="callback" class="swiperBox">
           <!-- slides -->
           <swiper-slide><img src="../assets/lv_dou_gao_home.jpg" @click="event_click_img(40)"></swiper-slide>
           <swiper-slide><img src="../assets/mk_44.jpg" @click="event_click_img(16)"></swiper-slide>
           <swiper-slide><img src="../assets/cxk_1.jpg" @click="event_click_img(35)"></swiper-slide>
           <swiper-slide><img src="../assets/cxk_3.jpg" @click="event_click_img(35)"></swiper-slide>
           <!-- Optional controls -->
           <div class="swiper-pagination"  slot="pagination"></div>
           <!--        <div class="swiper-button-prev" slot="button-prev"></div>-->
           <!--        <div class="swiper-button-next" slot="button-next"></div>-->
           <!--        <div class="swiper-scrollbar"   slot="scrollbar"></div>-->
         </swiper>

      <div class="bj"></div>







    </div>
</template>

<script>
    import Nav_top from "@/components/nav_top";
    import serarch from "@/components/serarch";
    export default {
        name: "home",
      components: {serarch, Nav_top},
      data() {
        return {
          sortList:[
            {
              icon:'&#xe60c;',
              word:'美食'
            },
            {
              icon:'&#xe603;',
              word:'装饰品'
            },
            {
              icon:'&#x3452;',
              word:'珠宝'
            },
            {
              icon:'&#xe62f;',
              word:'水果'
            },
            {
              icon:'&#xe64f;',
              word:'宠物'
            },
            {
              icon:'&#xee45;',
              word:'运动健身'
            },
            {
              icon:'&#xe60e;',
              word:'男装'
            },
            {
              icon:'&#xebc1;',
              word:'女装'
            },
            {
              icon:'&#xe609;',
              word:'童装'
            },
            {
              icon:'&#xe604;',
              word:'手机'
            }
          ],
          swiperOption: {
            // some swiper options/callbacks
            // 所有的参数同 swiper 官方 api 参数
            // ...
            autoplay:true,
            autoplay: {
              delay: 1300,//1秒切换一次
            },
            pagination :{
              el: '.swiper-pagination',
              clickable :true,
            }
          }
        }
      },
      methods:{
        event_click_hot_word(val_hot_word){
          this.$router.push({name:'searchPage',query:{props_search_word:val_hot_word}});
        },
        event_click_search(val_search_word){
          this.$router.push({name:'searchPage',query:{props_search_word:val_search_word}});
        },
        event_click_img(cID){
          const {href} = this.$router.resolve({
            name:'commodityPage',
            query:{
              cID:cID
            }
          });
          window.open(href,'_blank');
        }
      },

    }
</script>

<style scoped>
.home{height:753px; width:100%; overflow:hidden;}
  .swiperBox{
    width: 900px;
    height: 512px;
    position: absolute;
    top: 235px;
    left: 55%;
    transform: translate(-50%);
    
  }
  .swiperBox img{
    height: 100%;
    cursor: pointer;
  }


  *{
    padding: 0;
    margin: 0;
  }
.gg{
  background: #F12F04;
  position: relative;
  height: 80px;
}
.gg img{
  position: absolute;
  height: 100%;
  left: 50%;
  transform: translate(-50%);

}
  .nav_topBox{
    float: left;
  }
  .gifBox{
      position: relative;
      top: -7px;
  }
  .gifBox img{
    width: 290px;
  }
  .container{
    margin: 0px auto;
    max-width: 1380px;
  }
  .serachBox{
    margin-top: 9.5px;
    margin-left: 4px;
  }

  .bj{
    float: left;
    position: relative;
    bottom: 530px;
    z-index: -1;
    height: 550px;
    width: 100%;
    background-image: url("../assets/home_bj.jpg");
    background-repeat:no-repeat ;
    background-size:100% 100%;
  }

  .sortBox_Box{
    width: 290px;
  }

  .sortBox{
    position: relative;
    bottom: 7px;
    background: #211327;
    height: 500px;
    width: 250px;
    padding: 10px;
    margin: 0px auto;
    /*margin-left: 85px;*/
    /*padding-top: 10px;*/
  }
  .sordWordBox{
    border-radius:10px;
    border: 1px solid red;
    width: 150px;
    margin: 12px auto;
    cursor: pointer;
    padding-left: 30px;
  }
  .sordWordBox:hover{
    background: #F12F04;
  }
  .sordWordBox span{
    color: white;
  }

</style>
