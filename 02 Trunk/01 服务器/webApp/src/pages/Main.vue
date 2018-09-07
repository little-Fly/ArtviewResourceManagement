<template>
	<div class="main-content">
		<el-container>
			<el-header>
				<el-row>
					<el-col :span="4" class="logo tc">
						天天艺境
					</el-col>
					<el-col :span="12" :offset="2" class="search-content">
						<el-input placeholder="输入关键字" v-model="searchInput" class="input-with-select">
							<el-select v-model="searchType"
							           slot="prepend"
							           placeholder="输入关键字搜索">
								<el-option
										v-for="item in searchOptions"
										:key="item.value"
										:label="item.label"
										:value="item.value">
								</el-option>
							</el-select>
							<el-button
									slot="append"
									icon="el-icon-search">搜索
							</el-button>
						</el-input>
					</el-col>
					<el-col :span="6" class="exit-btn tr">
						<span class="exit"></span>
					</el-col>
				</el-row>
			</el-header>
			<el-container>
				<el-aside width="300px">
					<div class="type-list">
						<ul>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
							<li class="type-list-item tc">演员资源</li>
						</ul>
					</div>
					<div class="bottom-btn tc">
						<img src="@/assets/image/sourceM.png" alt="icon" width="30" height="30">
						资源类别管理
					</div>
				</el-aside>
				<el-main>
					<div class="main-head">
						<div class="title">资源录入</div>
						<ul class="btns fr">
							<li class="fl"><span class="operate-btn type-in"></span></li>
							<li class="fl"><span class="operate-btn delete"></span></li>
							<li class="fl"><span class="operate-btn update"></span></li>
						</ul>
					</div>
					<div class="main-body">
						<el-table
								:data="assetsData"
								height="100%"
								stripe
								border
								style="width: 100%">
							<el-table-column
									prop="date"
									label="日期"
									width="180">
							</el-table-column>
							<el-table-column
									prop="name"
									label="姓名"
									width="180">
							</el-table-column>
							<el-table-column
									prop="address"
									label="地址">
							</el-table-column>
						</el-table>
					</div>
					<div class="main-foot tc">
						<el-pagination
								background
								layout="prev, pager, next"
								:total="1000">
						</el-pagination>
					</div>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script>
    export default {
        name: "main-content",
        data() {
            return {
                searchType: "",
                searchOptions: [
                    {label: "资源类型", value: 1}
                ],
                searchInput: "",
                assetsData: [{
                    date: "2016-05-03",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1518 弄"
                }, {
                    date: "2016-05-02",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1518 弄"
                }, {
                    date: "2016-05-04",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1518 弄"
                }, {
                    date: "2016-05-06",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1518 弄"
                }, {
                    date: "2016-05-07",
                    name: "王小虎",
                    address: "上海市普陀区金沙江路 1518 弄"
                }]
            };
        }
    };
</script>

<style lang="scss" type="text/scss" scoped>
	$herderH: 60px;
	.el-header {
		background: #36474F;
		.logo {
			height: $herderH;
			line-height: $herderH;
			font-size: 25px;
			color: #fff;
			font-family: "Arial Rounded MT Bold";
		}
		.search-content {
			height: $herderH;
			line-height: $herderH;
			.el-select {
				width: 150px;
			}
		}
		.exit-btn {
			height: $herderH;
			position: relative;
			.exit {
				position: absolute;
				width: 30px;
				height: 30px;
				background: url("../assets/image/back.png") no-repeat;
				background-size: 30px 30px;
				top: 15px;
				right: 10px;
				cursor: pointer;
			}
		}
	}

	.el-main {
		padding: 0;
		position: relative;
		.main-head {
			height: $herderH;
			.title {
				display: inline-block;
				width: 150px;
				padding-left: 30px;
				height: $herderH;
				line-height: $herderH;
				color: #FE4E14;
				font-size: 23px;
			}
			.btns {
				height: $herderH;
				.operate-btn {
					display: inline-block;
					width: 100px;
					height: $herderH;
					background: url("../assets/image/typein.jpg") no-repeat 0 10px;
					background-size: 90px;
					cursor: pointer;
					border-left: 1px solid #AFAFAF;
					&.type-in {
						&:hover, &.active {
							background-image: url("../assets/image/typeR.jpg");
						}
					}
				}
				.delete {
					background-image: url("../assets/image/delete.jpg");
					&:hover, &.active {
						background-image: url("../assets/image/deleteR.jpg");
					}
				}
				.update {
					background-image: url("../assets/image/update.jpg");
					&:hover, &.active {
						background-image: url("../assets/image/updateR.jpg");
					}
				}
			}
		}
		.main-body {
			height: calc(100vh - 190px);
		}
		.main-foot {
			position: absolute;
			bottom: 0;
			left: 0;
			height: $herderH;
			width: 100%;
		}
	}

	.el-aside {
		height: calc(100vh - 60px);
		background: #273238;
		box-sizing: border-box;
		border-top: 1px solid #F43E04;
		position: relative;
		.type-list {
			margin-top: 10vh;
			height: 65vh;
			overflow-y: auto;
			.type-list-item {
				height: 40px;
				line-height: 40px;
				cursor: pointer;
				color: #fff;
				border-right: 2px solid transparent;
				&:hover, &.active {
					background: #2D3A42;
					color: #DC3D0F;
					border-color: #DC3D0F;
				}
			}
		}
		.bottom-btn {
			position: absolute;
			bottom: 0;
			left: 0;
			width: 100%;
			height: 50px;
			line-height: 50px;
			background: #FF3D00;
			color: #fff;
			font-size: 20px;
			cursor: pointer;
			.icon {
				display: inline-block;
				width: 45px;
				height: 45px;
				background: red;
			}
		}
	}
</style>