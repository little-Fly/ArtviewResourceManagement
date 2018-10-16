<template>
	<div class="add-assets-con">
		<el-row>
			<el-col :span="6" class="content left">
				<h2 class="tc title">资源类别</h2>
				<div class="tags">
					<el-tag v-for="(item,key) in attrTypeList" :key="key">{{item.name}}</el-tag>
					<!--<el-tag type="success">标签二</el-tag>-->
				</div>
			</el-col>
			<el-col :span="18" class="content right">
				<el-form ref="form" :model="form" label-width="150px">
					<el-form-item label="资源类别名称">
						<el-input placeholder="请输入名称" v-model="form.name"></el-input>
					</el-form-item>
					<el-form-item label="资源类别注释">
						<el-input placeholder="请输入注释" v-model="form.remark">
							<!--<el-select v-model="type1" slot="append" placeholder="请选择属性类型">-->
							<!--<el-option label="文字" value="1"></el-option>-->
							<!--<el-option label="图片" value="2"></el-option>-->
							<!--<el-option label="视频" value="3"></el-option>-->
							<!--</el-select>-->
						</el-input>
					</el-form-item>
					<el-form-item label="typeKey">
						<el-input placeholder="请输入typeKey" v-model="form.typeKey"></el-input>
					</el-form-item>
					<p class="tc ">
						<el-button type="success" class="add-confirm-btn" @click="addConfirm">确认新增</el-button>
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
                    name: "",
                    remark: "",
                    typeKey: "RDf示例表ID"
                },
                type1: "",
                attrTypeList: []
            };
        },
        methods: {
            addConfirm() {
                if (this.form.name === "") {
                    this.$message.error("资源类别名称不能为空");
                    return;
                }
                let params = {
                    name: this.form.name,
                    remark: this.form.remark,
                    typeKey: this.form.typeKey
                };
                let json = {
                    json: JSON.stringify(params)
                };
                this.$ajax.def
                    .addDef(json)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            } else {
                                this.$message.success("添加成功");
                                this.form.name = "";
                                this.form.remark = "";
                                this.form.typeKey = "";
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            }
        },
        mounted() {
            let params = {
                typekey: "RDf示例表ID",
            };
            this.$ajax.def
                .getDefAll(params)
                .then((response) => {
                    if (response.status === 200) {
                        let data = response.data;
                        this.attrTypeList = JSON.parse(data[0].data);
                    }
                }, (error) => {
                    this.$message.error(error.message);
                });
        }
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