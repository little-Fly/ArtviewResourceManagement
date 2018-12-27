<template>
	<div class="no-authority">
		<div v-if="!authority">
			<h1>对不起，你没有权限访问此网站，
				<br>请到微信小程序端注册用户或联系管理员</h1>
			<h2><span>{{ time }}</span>秒后
				<router-link tag='a' to="/">返回</router-link>
				登录界面
			</h2>
		</div>
		<div v-else>
			<h1>欢迎~~请稍后...</h1>
		</div>
	</div>
</template>

<script>
    export default {
        name: "no-authority",
        data() {
            return {
                time: 10,
                authority: true
            };
        },
        methods: {
            chargeAuthority() {
                let role = sessionStorage.getItem("myRoles");
                if (!role || role === "") {
                    this.authority = false;
                    this.timeDown();
                    return;
                }
                let myRoles = JSON.parse(role);
                let canIn = false;
                for (let i = 0; i < myRoles.length; i++) {
                    switch (myRoles[i].roleKey) {
                        case "admin":
                        case "checker":
                        case "writer":
                        case "common":
                            canIn = true;
                            break;
                        default:
                            canIn = false;
                    }
                    if (canIn) {
                        this.authority = true;
                        this.$router.replace("/main");
                        break;
                    }
                }
                if (!canIn) {
                    this.authority = false;
                    this.timeDown();
                }
            },
            timeDown() {
                let timer = setInterval(() => {
                    if (this.time > 0) {
                        this.time--;
                    } else {
                        this.$router.replace("/");
                        clearInterval(timer);
                    }
                }, 1000);
            }
        },
        mounted() {
            this.chargeAuthority();
        }
    };
</script>

<style scoped>
	.no-authority {
		padding: 20px;
		color: #000;
		line-height: 50px;
		span {
			color: #f00;
		}
		a {
			text-decoration: #2ca07b;
		}
	}
</style>