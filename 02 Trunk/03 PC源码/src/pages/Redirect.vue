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
                            if (data.msg === "success") {
                                this.$router.push("/main");
                            } else {
                                this.$message.error(data.msg);
                                setTimeout(()=>{
                                    this.$router.push("/login");
                                },2000);
                            }
                        }
                    }, (error) => {
                        this.$message.error(error);
                        setTimeout(()=>{
                            this.$router.push("/login");
                        },2000);
                    });
            } catch(e){
                this.$message.error(e);
                setTimeout(()=>{
                    this.$router.push("/login");
                },2000);
            }
            // this.$axios.get(`/login/pcLogin.do?code=${code}&noCheck=1`)
            //     .then((response) => {
            //         if (response.status === 200) {
            //             let data = response.data;
            //             if (data.msg === "success") {
            //                 this.$router.push("/main");
            //             } else {
            //                 this.$router.push("/login");
            //             }
            //         }
            //     })
            //     .catch((error) => {
            //         this.$message(error);
            //     });
        }
    };
</script>

<style scoped>

</style>