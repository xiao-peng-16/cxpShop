<template>
    <div>
      <div class="bj"><img src="../assets/login_bj.jpg"></div>
      <div class="container">
        <div class="fromBox offset-6">

          <div class="titleBox">
              <span id="first">欢迎注册天狗</span>
              <span id="second"> 每一天，乐在消费</span>
          </div>

          <br/><input v-my-focus v-model.lazy="username" @keyup.enter="focusPassword_1" type="text" placeholder="昵称">
          <span v-show="flag_username" class="iconfont right">&#xed1d;</span>
          <div class="hintBox">
            <div v-if="hint_username!=''">
              <span class="hint_icon iconfont">&#xeb65;</span>
              <span class="hint_msg">{{this.hint_username}}</span>
            </div>
          </div>

          <br/><input v-model.lazy="password" @keyup.enter="focusPassword_2" ref="Password_1" type="password" placeholder="密码">
          <span v-show="flag_password" class="iconfont right">&#xed1d;</span>
          <div class="hintBox">
            <div v-if="hint_password!=''">
              <span class="hint_icon iconfont">&#xeb65;</span>
              <span class="hint_msg">{{this.hint_password}}</span>
            </div>
          </div>

          <br/><input v-model.lazy="surepassword" @keyup.enter="insUser" ref="Password_2" type="password" placeholder="确认密码">
          <span v-show="flag_surepassword" class="iconfont right">&#xed1d;</span>
          <div class="hintBox">
            <div v-if="hint_surepassword!=''">
              <span class="hint_icon iconfont">&#xeb65;</span>
              <span class="hint_msg">{{this.hint_surepassword}}</span>
            </div>
          </div>

          <br/><button id="login" @click="insUser">注册</button>


        </div>
      </div>

    </div>
</template>

<script>
    export default {
        name: "login",
        data(){
            return{
              username:"",
              password:"",
              surepassword:"",
              flag_username:false,
              flag_password:false,
              flag_surepassword:false,
              hint_username:"",
              hint_password:"",
              hint_surepassword:""
            }
        },
        methods:{
          focusPassword_1(){
            this.$refs.Password_1.focus();
          },
          focusPassword_2(){
            this.$refs.Password_2.focus();
          },
          insUser(){
            this.check_username();
            this.check_password();
            if (this.flag_username && this.password && this.surepassword){
              this.$axios.post('/insUser',this.$qs.stringify({
                username:this.username,
                password:this.password
              }))
                .then(res=>{

                  if (res.data){
                    this.$axios.post('/selUserByPassword',this.$qs.stringify({
                      username : this.username,
                      password : this.password
                    })).then(res=>{
                      if (res.data){
                        this.GLOBAL.flag_enter=true;
                        this.$router.go(-1);
                      } else {
                        console.log("login-》enter异常")
                      }
                    })
                  } else {
                    console.log("login异常")
                  }



                })
            }
          },
          check_username(){
            if (this.username.length<3 || this.username.length>10){
              this.flag_username = false;
              this.hint_username="用户名请长度在3~10之间";
            } else {
              this.$axios.post('/is_usable_username',this.$qs.stringify({
                username:this.username
              }))
                .then(res=>{
                  this.flag_username = res.data;
                  if (res.data) {
                    this.flag_username = true;
                    this.hint_username="";
                  }else {
                    this.flag_username = false;
                    this.hint_username="该用户名已存在";
                  }
                })
            }
          },
          check_password(){
            if (this.password.match(/^\w{8,15}$/)){
              this.flag_password=true;
              this.hint_password = "";
            } else {
              this.flag_password=false;
              this.hint_password = "密码必须为8~15位的数字，字母，下划线";
            }
          },
          check_surepassword(){
            if (this.surepassword===this.password){
              this.flag_surepassword=true;
              this.hint_surepassword = "";
            } else{
              this.flag_surepassword=false;
              this.hint_surepassword = "两次密码不一致";
            }
          }
        },
        watch:{
          username:function () {
            this.check_username();
          },
          password:function () {
            this.check_password();
            if (this.surepassword!=""){
              this.check_surepassword();
            }
          },
          surepassword:function () {
            this.check_surepassword();
          }
        }

    }
</script>

<style scoped>
  span{
    font-weight: bold;
  }
  .bj{
    float: left;
    background-repeat:no-repeat ;
  }
  .bj img{
    width: 425px;
  }

  .fromBox{
    background: white;
    height: 600px;
    width: 550px;
    position: relative;
    right: 10px;
    top: 90px;
  }
  .titleBox{
    width: 500px;
    height: 110px;
    margin-bottom: 50px;
  }
  .titleBox span{
    display: block;
    font-weight: normal;
  }
  #first{
    font-size: 44px;
    margin-bottom: 20px;
    color: black;
  }
  #second{
    font-size: 28px;
    margin-bottom: 64px;
    line-height: 1.2;
    color: #333;
    margin-left: 20px;
  }
  input[type=text],input[type=password]{
    border: 1px #aaa solid;
    height: 50px;
    line-height: 50px;
    font-size: 20px;
    /*margin-top: 20px;*/
    border-radius: 4px;
    padding: 0 20px;
    width: 480px;
  }
  #login{
    width: 480px;
    height: 60px;
    line-height: 42px;
    background-color: #3587FF;
    color: white;
    border: none;
    border-radius:5px;
  }
  .hint_icon{
    margin: 0px 5px 0px 10px;
    color: #FF5B5B;
    font-size: 15px;
  }
  .hint_msg{
    color: #FF5B5B;
  }
  .hintBox{
    height: 5px;
    width: 480px;
  }
  .right{
    color: #6EF87E;
    font-size: 30px;
  }
</style>
