<template>
	<div>
		登录中...
	</div>
</template>

<script>
    export default {
        name: "redirect",
        methods: {
            getParams() {
                let url = location.href;
                return url.split("?")[1].split("&")[0].split("=")[1];
            }
        },
        mounted() {
            let code = this.getParams();
            let params = {
                code: code,
                noCheck: 1
            };
            try {
                this.$ajax.autoService
                    .login(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            // console.log(data);
                            if (data.msg === "success") {
                                // console.log(data.userInfo);
                                if (data.userInfo) {
                                    let myRoles = data.userInfo.roles;
                                    sessionStorage.setItem("myRoles", JSON.stringify(myRoles));
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
                                            this.$router.push("/main");
                                            break;
                                        }
                                    }
                                    if (!canIn) {
                                        this.$router.push("/NoAuthority");
                                        sessionStorage.removeItem("myRoles");
                                    }
                                }
                            } else {
                                this.$message.error(data.msg);
                                setTimeout(() => {
                                    this.$router.push("/login");
                                }, 2000);
                            }
                        }
                    }, (error) => {
                        this.$message.error(error);
                        setTimeout(() => {
                            this.$router.push("/login");
                        }, 2000);
                    });
            } catch (e) {
                this.$message.error(e);
                setTimeout(() => {
                    this.$router.push("/login");
                }, 2000);
            }
        }
    };
</script>

<style scoped>

</style>