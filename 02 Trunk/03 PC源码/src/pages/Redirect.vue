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
            let json = {
                "msg": "success",
                "userInfo": {
                    "createTime": {
                        "date": 17,
                        "day": 1,
                        "hours": 23,
                        "minutes": 31,
                        "month": 11,
                        "seconds": 45,
                        "time": 1545060705000,
                        "timezoneOffset": -480,
                        "year": 118
                    },
                    "gender": 1,
                    "lastTime": {
                        "date": 17,
                        "day": 1,
                        "hours": 23,
                        "minutes": 31,
                        "month": 11,
                        "seconds": 45,
                        "time": 1545060705000,
                        "timezoneOffset": -480,
                        "year": 118
                    },
                    "lastValidTime": 1545233361873,
                    "nickName": "薛利飞",
                    "openId": "oVEQd0SO9VTr0WkHR4uJCmq7MWdo",
                    "phone": "13347176976",
                    "reason": "厉害了",
                    "roles": [{"remark": "", "roleKey": "writer", "roleName": "录入人员", "roleType": 0}],
                    // rolekey;admin/管理员,checker/审核人员，writer/录入人员，common/一般员工，visitor/游客
                    "status": 1, //-1、0、1、2==未注册、待审核、已审核、被拒绝
                    "uid": 26,
                    "unionId": "ogOHU1HNbJPVM6izCoA6ZrWaRwms"
                },
                "code": 1,
                "status": 1
            };
            try {
                this.$ajax.autoService
                    .login(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            console.log(data);
                            if (data.msg === "success") {
                                console.log(data);
                                // this.$router.push("/main");
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