<template>
	<div class="add-assets-con">
		<el-row>
			<el-col :span="6" class="content left">
				<h2 class="tc title">演员资源</h2>
				<div class="tags">
					<el-tag>标签一</el-tag>
					<el-tag type="success">标签二</el-tag>
					<el-tag type="info">标签三</el-tag>
					<el-tag type="warning">标签四</el-tag>
					<el-tag type="danger">标签五</el-tag>
					<el-tag type="danger">标签五</el-tag>
					<el-tag type="danger">标签五二</el-tag>
				</div>
			</el-col>
			<el-col :span="18" class="content right">
				<el-form ref="form" :model="form" label-width="150px">
					<el-form-item label="资源类别名称">
						<el-input v-model="form.name"></el-input>
						<el-button type="success" plain>上传图片</el-button>
						<el-button type="success" plain>上传视频</el-button>
					</el-form-item>
					<el-form-item label="属性名称">
						<el-input placeholder="请输入内容">
							<el-select v-model="type1" slot="append" placeholder="请选择属性类型">
								<el-option label="餐厅名" value="1"></el-option>
								<el-option label="订单号" value="2"></el-option>
								<el-option label="用户电话" value="3"></el-option>
							</el-select>
						</el-input>
					</el-form-item>
					<el-form-item label="属性名称">
						<el-input placeholder="请输入内容">
							<el-select v-model="type2" slot="append" placeholder="请选择属性类型">
								<el-option label="餐厅名" value="1"></el-option>
								<el-option label="订单号" value="2"></el-option>
								<el-option label="用户电话" value="3"></el-option>
							</el-select>
						</el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary">增加属性</el-button>
					</el-form-item>
					<p class="tc ">
						<el-button type="success" class="add-confirm-btn">确认新增</el-button>
					</p>
				</el-form>
			</el-col>
		</el-row>
	</div>
</template>

<script>
    export default {
        name: "add-assets-con",
        data() {
            return {
                form: {
                    name: ""
                },
                type1: "",
                type2: ""
            };
        },
    };
</script>

<style lang="scss" type="text/scss" scoped>
	.add-assets-con {
		height: 100%;
		.content {
			height: calc(100vh - 80px);
			&.left {
				border-right: 1px solid #5F5F5F;
				.title {
					height: 80px;
					line-height: 80px;
				}
				.tags {
					padding: 10px;
				}
			}
			&.right {
				.el-form {
					width: 90%;
					height: 100%;
					box-sizing: border-box;
					margin: 0 auto;
					padding: 50px 0;
					overflow: auto;
					.add-confirm-btn {
						padding: 22px 240px;
						margin-top: 50px;
						font-size: 25px;
					}
					.el-form-item {
						.el-input {
							width: 60%;
						}
					}
				}
			}
		}
	}
</style>