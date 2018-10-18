<template>
	<div class="update-assets-con">
		<div class="father-box">
			<div class="content">
				<el-card class="box-card" v-for="(item,key) in attrTypeList" :key="key">
					<h3 class="title">{{item.name}}</h3>
					<div class="tags">
						<el-tag type="info" v-for="(attr,i) in item.attr" :key="i">{{attr.attrName}}</el-tag>
					</div>
					<el-button type="primary" class="btn" @click="update(item)">修改</el-button>
				</el-card>
			</div>
			<div class="assets-footer tc">
				<el-pagination
						background
						prev-text="<上一页"
						next-text="下一页>"
						layout="prev, pager, next,total,jumper"
						:total="1000">
				</el-pagination>
			</div>
		</div>
		<el-dialog title="修改资源类别" width="30%" :visible.sync="dialogFormVisible">
			<el-form :model="form">
				<el-form-item label="资源类别名称" :label-width="formLabelWidth">
					<el-input v-model="form.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="属性名称" :label-width="formLabelWidth"
				              v-for="(item,key) in attrList" :key="key">
					<el-input v-model="item.attrName" auto-complete="off"></el-input>
					<el-button type="primary" @click.prevent="delThisLine(item,key)">删除</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click.prevent="addAttr" v-if="!hasAdded">增加属性</el-button>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button type="primary" @click="confirmUpdate">确定修改</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
    export default {
        name: "update-assets-con",
        data() {
            return {
                dialogFormVisible: false,
                formLabelWidth: "100px",
                attrTypeList: [],
                form: {
                    name: ""
                },
                type1: "",
                type2: "",
                attrList: [],
                updateTypeKey: "",
                hasAdded: false,
            };
        },
        methods: {
            /**
             * 修改 按钮弹窗
             * @param item
             */
            update(item) {
                this.hasAdded = false;
                let params = {
                    typekey: item.typeKey,
                };
                this.updateTypeKey = item.typeKey;
                this.form.name = item.name;
                this.$ajax.attr
                    .getAttrAll(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            this.attrList = JSON.parse(data[0].data);
                            this.dialogFormVisible = true;
                        }
                    });
            },
            addAttr() {
                this.attrList.push({attrName: ""});
                this.hasAdded = true;
            },
            delThisLine(item, key) {
                this.$confirm("是否确定删除该属性?", "提示", {
                    confirmButtonText: "提交审核",
                    cancelButtonText: "我再想想",
                    type: "warning",
                    center: true
                }).then(() => {
                    let params = {
                        attkey: item.attrkey,
                    };
                    this.$ajax.attr
                        .delAttr(params)
                        .then((response) => {
                            if (response.status === 200) {
                                let data = response.data;
                                console.log(data);
                                this.attrList.splice(key, 1);
                                this.dialogFormVisible = true;
                            }
                        });
                }).catch((err) => {
                    console.log(err);
                });
            },
            /**
             * 确认修改按钮
             */
            confirmUpdate() {
                if (this.form.name === "") {
                    this.$message({
                        type: "warning",
                        message: "资源名称不能为空!"
                    });
                    return;
                }
                this.$confirm("是否确定保存?", "提示", {
                    confirmButtonText: "提交审核",
                    cancelButtonText: "我再想想",
                    type: "warning",
                    center: true
                }).then(() => {
                    let params = {
                        typekey: this.updateTypeKey,
                    };
                    this.$ajax.def
                        .updateDef(params)
                        .then((response) => {
                            if (response.status === 200) {
                                let data = response.data;
                                this.$message({
                                    type: "info",
                                    message: data[0].message
                                });
                                this.dialogFormVisible = false;
                            }
                        });
                    // 增加属性
                    let newAttr = this.attrList[this.attrList.length - 1].attrName;
                    if (this.hasAdded && newAttr !== "") {
                        let json = {
                            attrKey: encodeURI("c测试attrKey"),
                            attrLevel: encodeURI("0"),
                            attrName: encodeURI(newAttr),
                            attrType: encodeURI("default"),
                            remark: encodeURI(""),
                            // typeKey: encodeURI(this.updateTypeKey)
                            typeKey: this.updateTypeKey
                        };
                        console.log(json);
                        let params = {
                            json: JSON.stringify(json)
                        };
                        this.$ajax.attr
                            .addAttr(params)
                            .then((response) => {
                                if (response.status === 200) {
                                    let data = response.data;
                                    console.log(data);
                                    this.$message({
                                        type: "info",
                                        message: "添加成功"
                                    });
                                    this.refreshIndex();
                                }
                            });
                    }
                }).catch((err) => {
                    console.log(err);
                });
            },
            getResAttr(i) {
                let params = {
                    typekey: this.attrTypeList[i].typeKey,
                };
                this.$ajax.attr
                    .getAttrAll(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            this.$set(this.attrTypeList[i], "attr", JSON.parse(data[0].data));
                        }
                    });
            },
            refreshIndex() {
                let params = {
                    typekey: "RDf示例表ID",
                };
                this.$ajax.def
                    .getDefAll(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            this.attrTypeList = JSON.parse(data[0].data);
                            this.getResAttr(0);
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            }
        },
        mounted() {
            this.refreshIndex();
        }
    };
</script>

<style lang="scss" type="text/scss" scoped>
	$herderH: 60px;
	.update-assets-con {
		height: 100%;
		.father-box {
			background: #273238;
			position: relative;
			width: 100%;
			height: 100%;
			padding-left: 40px;
			box-sizing: border-box;
			.content {
				background: #E6E6E6;
				height: calc(100vh - 140px);
				padding: 20px;
				overflow: auto;
				.box-card {
					margin-bottom: 10px;
					position: relative;
					.title {
						padding-bottom: 10px;
					}
					.tags {
						width: 80%;
					}
					.btn {
						position: absolute;
						right: 20px;
						top: 50%;
						transform: translateY(-50%);
					}
				}
			}
			.assets-footer {
				position: absolute;
				bottom: 0;
				right: 0;
				height: $herderH;
				width: calc(100% - 40px);
				background: #fff;
				.el-pagination {
					margin-top: 15px;
				}
			}
		}
		.el-dialog {
			.el-input {
				width: 70%;
			}
		}
	}
</style>