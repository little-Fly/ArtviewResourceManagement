<template>
	<div class="login">
		<div class="content">
			<div class="header tc">
				<span class="title">天天艺境</span>
				<span class="title-low">后台管理系统</span>
			</div>
			<div class="qr tc">
				<img src="@/assets/image/qr.png" alt="二维码">
				<p class="tips" @click="goMain">使用微信扫码登录</p>
			</div>
			<div class="footer">
				<p class="copy-right tc">深圳天天艺境文化传播有限公司</p>
				<p class="tc"> 粤 ICP 备 09109527 号-1 </p>
			</div>
		</div>
	</div>
</template>

<script>
    export default {
        name: "login",
        data() {
            return {}
        },
	    methods:{
            goMain(){
                this.$router.push('/main');
            }
	    }
    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" type="text/scss" scoped>
	.login {
		width: 100%;
		height: 100%;
		background: url("../assets/image/bg.jpg") no-repeat;
		background-size: 100% 100%;
		.content {
			width: 50%;
			height: 100%;
			margin: 0 auto;
		}
		.header {
			position: relative;
			height: 30%;
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
			width: 100%;
			height: 350px;
			padding-top: 50px;
			vertical-align: middle;
			.tips{
				color: #FF3D00;
				font-size: 30px;
				margin-top: 20px;
			}
		}
		.footer{
			.tc{
				color: #333;
				height: 40px;
				line-height: 40px;
			}
		}
	}
</style>
