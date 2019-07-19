<template>
    <div>
        <nav_top :flag_home="false"/><br/>
        <div class="container">
          <div class="row second">
              <img src="../assets/tmall.jpg">
              <div id="serarch">
                <serarch @event_click_search="event_click_search" :props_search_word="search_word"/>
              </div>
          </div>

          <div class="row"><div class="split"></div></div>

          <div v-show="flag_containData">
            <div class="row list">
              <div class="col-lg-1-5 spc" v-for="itemData in commodityList">
                <SearchPage_commodity  :props_CommodityMessage="itemData"/>
              </div>
            </div>
            <div class="pageBox">
              <span class="commoditySum">共{{commoditySum}}条数据</span>
              <div class="right">
                <span @click="pageNo--">上一页</span>
                {{pageNo}}/{{pageSum}}
                <span @click="pageNo++">下一页</span>
              </div>
            </div>
          </div>

        <div class="notData" v-show="!flag_containData">
          <img src="../assets/search_notdata.png">
          <span class="side">旺~没找到与</span>
          <span class="maddle">“ {{this.search_word}} ”</span>
          <span class="side">相关的商品哦。</span>
        </div>

      </div>

      <img v-show="btnFlag" class="go-top" src="../assets/huo_jian.png" @click="backTop">

    </div>
</template>

<script>
    import Nav_top from "@/components/nav_top";
    import serarch from "@/components/serarch";
    import SearchPage_commodity from "@/components/searchPage_commodity";
    export default {
        name: "searchPage",
      components: {SearchPage_commodity, serarch, Nav_top},
      data(){
          return{
            btnFlag:false,
            flag_containData:true,
            search_word:this.$route.query.props_search_word,
            commoditySum:0, //搜索到的商品总数
            commodityList:[],  //搜索到的商品列表 分页

            pageNo:1,//  分页 页码
            pageNoCache:1,//  分页 页码缓存   保存上一次的值
            pageStepSize:10 ,//  分页 步长
          }
      },
      computed:{
        pageSum(){
          return Math.ceil(this.commoditySum/this.pageStepSize);
        },
      },
      watch:{
        pageNo(val){
          this.pageNo=this.pageNo.toString().replace(/[^\d]/g,'');
          if (this.pageNo<1){
            this.pageNo=1;
          } else if (this.pageNo>this.pageSum){
            this.pageNo=this.pageSum;
          }

          if (this.pageNoCache!=this.pageNo){
            this.sendSearch();
            this.pageNoCache=this.pageNo;
            this.backTop();
          }
        }
      },
      methods:{// 点击图片回到顶部方法，加计时器是为了过渡顺滑
        backTop () {
          let that = this;
          let timer = setInterval(() => {
            let ispeed = Math.floor(-that.scrollTop / 5)
            document.documentElement.scrollTop = document.body.scrollTop = that.scrollTop + ispeed;
            if (that.scrollTop === 0) {
              clearInterval(timer)
            }
          }, 1)
        },
        // 为了计算距离顶部的高度，当高度大于60显示回顶部图标，小于60则隐藏
        scrollToTop () {
          let that = this;
          let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
          that.scrollTop = scrollTop;
          if (that.scrollTop > 30) {
            that.btnFlag = true
          } else {
            that.btnFlag = false
          }
        },


          sendSearch(){
            this.$axios.get('/selSearchPageData',{
              params:{
                searchWord: this.search_word,
                pageNo : this.pageNo,
                pageStepSize : this.pageStepSize
              }
            }).then(res=>{
              this.commoditySum = res.data.commoditySum;
              this.commodityList = res.data.commodityList;
              this.flag_containData=this.commoditySum>0;
            })
          },
          event_click_search(val_search_word){
              this.pageNo = 1;
              this.search_word = val_search_word;
              this.sendSearch(val_search_word);
          }
      },
      created() {
        this.sendSearch(this.search_word);
      },
      mounted () {
        window.addEventListener('scroll', this.scrollToTop)
      },
      destroyed () {
        window.removeEventListener('scroll', this.scrollToTop)
      },
    }
</script>

<style scoped>
  
  .go-top{
    cursor: pointer;
    height: 90px;
    position: fixed;
    bottom: 20px;
    right: 20px;
  }
  
  
  .spc{
    margin-top: 20px;
  }
  
  .container{
    max-width: 1380px;
  }
.second{
}
  .second img{
    width: 220px;
    height: 40px;
  }
  #serarch{
    position: relative;
    bottom: 18px;
    left: 80px;
  }
  .split{
    margin: 0 auto;
    height: 1px;
    width: 98%;
    background: #E5E5E5;

  }
  .list{
    position: relative;
    left: 13px;
  }
  .notData{
    background: #FFF8F6;
    border: 1px solid #F7EAE7;
    height: 90px;
  }
  .notData img{
    height: 60px;
    margin: 15px;
  }
  .side{
    color: #595959;
  }
  .maddle{
    color: #790103;
    font-weight: bold;
  }
  .pageBox{
    display: inline-block;
    float: right;
    margin-top: 30px;
    margin-bottom: 80px;
  }

  .right{
    display: inline-block;
    font-size: 17.5px;
  }
  .right span{
    cursor: pointer;
    color: black;
  }
  .commoditySum{
    margin-right: 35px;
    color: #454d55;
  }



  @media (min-width: 768px) {
    .col-sm-1-5 {
      width: 20%;
      float: left;
    }
  }

  @media (min-width: 1200px) {
    .col-md-1-5 {
      width: 20%;
      float: left;
    }
  }

  @media (min-width: 1300px) {
    .col-lg-1-5 {
      width: 20%;
      float: left;
    }
  }

</style>
