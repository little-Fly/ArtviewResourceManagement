<template>
	<div class="assets-manager">
		<el-container>
			<el-header height="80px">
				<el-row>
					<el-col :span="8" class="top-btn tc" :class="{'active' : headerActive === 0}">
						<img src="@/assets/image/addType.png" alt="icon" height="50" @click="headerActive = 0">
					</el-col>
					<el-col :span="8" class="top-btn tc" :class="{'active' : headerActive === 1}">
						<img src="@/assets/image/updateType.png" alt="icon" height="50" @click="headerActive = 1">
					</el-col>
					<el-col :span="8" class="top-btn tc" :class="{'active' : headerActive === 2}">
						<a href="javascript:void (0)" class="back-btn" @click="goIndex">返回首页</a>
					</el-col>
				</el-row>
			</el-header>
			<el-main>
				<add-assets-con v-if="headerActive === 0"></add-assets-con>
				<update-assets-con v-else></update-assets-con>
			</el-main>
		</el-container>
	</div>
</template>

<script>
    import addAssetsCon from "../components/addAssetsCon";
    import updateAssetsCon from "../components/updateAssetsCon";

    export default {
        name: "assets-manager",
        data() {
            return {
                headerActive: 0
            };
        },
        components: {
            addAssetsCon,
            updateAssetsCon
        },
        methods: {
            goIndex() {
                this.$router.push("/main");
                this.headerActive = 2;
            }
        },
        mounted() {
            this.$chargeAuthority().then(() => {
            }, () => {
                this.$router.replace("/");
            });
        }

    };
</script>

<style lang="scss" type="text/scss" scoped>
	$headerH: 80px;
	.el-header {
		padding: 0;
		.top-btn {
			height: $headerH;
			line-height: $headerH;
			background: #36474F;
			border: 1px solid transparent;
			border-right-color: #7B858D;
			&:last-child {
				border-right-color: transparent;
			}
			&.active {
				background: #FF3D00;
			}
			img {
				cursor: pointer;
			}
			.back-btn {
				color: #fff;
				font-size: 28px;
			}
		}
	}

	.el-main {
		padding: 0;
		height: calc(100vh - 80px);
	}
</style>