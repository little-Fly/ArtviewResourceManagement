<template>
	<div class="login">
		<div class="content">
			<div class="header tc">
				<img class="title" src="@/assets/image/logo.png" alt="logo">
				<!--<span class="title">天天艺境</span>-->
				<span class="title-low">后台管理系统</span>
			</div>
			<div class="qr tc">
				<wxlogin :appid="appId" :scope="scope" :redirect_uri="redirect_uri"></wxlogin>
				<!--<p class="tips">使用微信扫码登录</p>-->
			</div>
			<div class="footer">
				<p class="copy-right tr fl">深圳天天艺境文化传播有限公司</p>
				<p class="tl fl"> 粤 ICP 备 09109527 号-1 </p>
			</div>
		</div>
	</div>
</template>

<script>
    import wxlogin from 'vue-wxlogin';

    export default {
        name: "login",
        components: {
            wxlogin
        },
        data() {
            return {
                appId:"wxb41c2d9e3682c984",
                // appId:"wx84695f838010ce36",
                scope:"snsapi_login",
                redirect_uri: "https%3A%2F%2Fwww.hwyst.net%2Findex.html%23%2Fredirect",
            }
        },
	    methods:{
            goMain(){
                this.$router.push('/main');
            }
	    },
	    mounted(){
            console.log(this.redirect_uri);
        },
        beforeMount(){
            // let href = location.href.split("#")[0];
            // this.redirect_uri = encodeURIComponent(`${href}#Redirect`)
        }
    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" type="text/scss" scoped>
	.login {
		width: 100%;
		height: 100%;
		background: url("../assets/image/bg.jpg") no-repeat;
		background-size: 120% 120%;
		background-position: 50% 50%;
		.content {
			width: 50%;
			height: 100%;
			margin: 0 auto;
		}
		.header {
			position: relative;
			height: 25%;
			/*padding-top: 20%;*/
			.title {
				font-size: 50px;
				color: #446690;
				position: absolute;
				bottom: 25px;
				left: 40%;
				transform: translateX(-50%);
			}
			.title-low {
				font-size: 31px;
				color: #999999;
				position: absolute;
				bottom: 20px;
				left: 70%;
				transform: translateX(-50%);
			}
		}
		.qr{
			position: fixed;
			left: 50%;
			top: 50%;
			width: 400px;
			height: 350px;
			transform: translate(-50%,-50%);
			.tips{
				color: #FF3D00;
				font-size: 30px;
				margin-top: 20px;
			}
		}
		.footer{
			position: fixed;
			left: 50%;
			bottom: 10vh;
			transform: translateX(-50%);
			width: 75vw;
			height: 1px;
			p{
				width: 37.5vw;
				color: #333;
				height: 40px;
				line-height: 40px;
				box-sizing: border-box;
				padding-left: 30px;
				&.copy-right{
					padding-right: 30px;
				}
			}
		}
	}
</style>
