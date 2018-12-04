<template>
	<div class="update-assets-con">
		<div class="father-box">
			<div class="content">
				<el-card class="box-card" v-for="(item,key) in attrTypeList" :key="key">
					<h3 class="title">{{item.name}}</h3>
					<div class="tags">
						<el-tag type="info" v-for="(attr,i) in item.attr" :key="i">{{attr.attrName}}</el-tag>
					</div>
					<el-button type="warning" class="btn" @click="update(item)">修改</el-button>
					<el-button type="primary" class="btn btn-del" @click="deleteRs(item)">删除</el-button>
				</el-card>
			</div>
			<div class="assets-footer tc">
				<el-pagination
						background
						prev-text="<上一页"
						next-text="下一页>"
						layout="prev, pager, next,total,jumper"
						:total="2">
				</el-pagination>
			</div>
		</div>
		<el-dialog title="修改资源类别" width="50%" :visible.sync="dialogFormVisible">
			<el-form :model="form">
				<el-form-item label="资源类别名称" :label-width="formLabelWidth">
					<el-input v-model="form.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="属性名称" :label-width="formLabelWidth"
				              v-for="(item,key) in attrList" :key="key">
					<el-input v-model="item.attrName" readonly auto-complete="off"></el-input>
					<el-button type="primary" @click.prevent="delThisLine(item,key)">删除</el-button>
					<el-button type="warning" @click.prevent="updateThisLine(item,key)">修改</el-button>
				</el-form-item>
				<el-form-item class="tc">
					<el-button type="danger" @click.prevent="addAttr">增加属性</el-button>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button type="success" @click="confirmUpdate">确定修改</el-button>
			</div>
		</el-dialog>
		<el-dialog title="增加资源属性" width="30%" :visible.sync="dialogResVisible">
			<el-form>
				<el-form-item label="attrKey" :label-width="formLabelWidth">
					<el-input v-model="resAttr.attrKey" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="属性名称" :label-width="formLabelWidth">
					<el-input v-model="resAttr.attrName" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="备注" :label-width="formLabelWidth">
					<el-input v-model="resAttr.remark" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button type="success" @click="confirmAddAttr">确定增加</el-button>
			</div>
		</el-dialog>
		<el-dialog title="修改资源属性" width="30%" :visible.sync="resUpdateVisible">
			<el-form>
				<el-form-item label="attrKey" :label-width="formLabelWidth">
					<el-input v-model="resAttr.attrKey" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="属性名称" :label-width="formLabelWidth">
					<el-input v-model="resAttr.attrName" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="备注" :label-width="formLabelWidth">
					<el-input v-model="resAttr.remark" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button type="success" @click="confirmUpdateAttr">确定修改</el-button>
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
                dialogResVisible: false,
                resUpdateVisible: false,
                formLabelWidth: "100px",
                attrTypeList: [],
                form: {
                    name: ""
                },
                // 资源属性  弹窗字段
                resAttr: {
                    attrKey: "",
                    attrName: "",
                    remark: ""
                },
                type1: "",
                type2: "",
                attrList: [],
                updateTypeKey: "",
            };
        },
        methods: {
            /**
             * 修改 按钮弹窗
             * @param item
             */
            update(item) {
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
            //新增资源按钮
            addAttr() {
                this.dialogResVisible = true;
                this.resAttr.attrKey = "";
                this.resAttr.attrName = "";
                this.resAttr.remark = "";
            },
            //修改资源属性按钮
            updateThisLine(item){
	            this.resAttr.attrKey = item.attrKey;
	            this.resAttr.attrName = item.attrName;
	            this.resAttr.remark = item.remark;
                this.resUpdateVisible = true;
            },
            //删除资源属性
            delThisLine(item, key) {
                this.$confirm("是否确定删除该属性?", "提示", {
                    confirmButtonText: "提交审核",
                    cancelButtonText: "我再想想",
                    type: "warning",
                    center: true
                }).then(() => {
                    let params = {
                        attrkey: item.attrKey,
                    };
                    this.$ajax.attr
                        .delAttr(params)
                        .then((response) => {
                            if (response.status === 200) {
                                let data = response.data;
                                if (data[0].state === "error") {
                                    this.$message.error(data[0].message);
                                } else {
                                    this.$message.success("删除成功");
                                    this.dialogFormVisible = false;
                                    this.refreshIndex();
                                }
                            }
                        });
                }).catch((err) => {
                    console.log(err);
                });
            },
            /**
             * 确认增加资源属性
             */
            confirmAddAttr() {
                if (this.resAttr.attrKey === "") {
                    this.$message.warning("attrKey不能为空!");
                    return;
                }
                if (this.resAttr.attrName === "") {
                    this.$message.warning("属性名称不能为空!");
                    return;
                }
                let json = {
                    attrKey: this.resAttr.attrKey,
                    attrLevel: "0",
                    attrName: this.resAttr.attrName,
                    attrType: "default",
                    remark: this.resAttr.remark,
                    typeKey: this.updateTypeKey
                };
                let params = {
                    json: decodeURI(encodeURI(JSON.stringify(json)))
                };
                this.$ajax.attr
                    .addAttr(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            } else {
                                this.$message.success("新增成功");
                                this.refreshIndex();
                                this.dialogFormVisible = false;
                                this.dialogResVisible = false;
                            }
                        }
                    });
            },
            /**
             * 确认修改资源属性
             */
            confirmUpdateAttr(){
                if (this.resAttr.attrKey === "") {
                    this.$message.warning("attrKey不能为空!");
                    return;
                }
                if (this.resAttr.attrName === "") {
                    this.$message.warning("属性名称不能为空!");
                    return;
                }
                let json = {
                    attrKey: this.resAttr.attrKey,
                    attrLevel: "0",
                    attrName: this.resAttr.attrName,
                    attrType: "default",
                    remark: this.resAttr.remark,
                    typeKey: this.updateTypeKey
                };
                let params = {
                    json: decodeURI(encodeURI(JSON.stringify(json)))
                };
                this.$ajax.attr
                    .updateAttr(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            } else {
                                this.$message.success("修改成功");
                                this.refreshIndex();
                                this.dialogFormVisible = false;
                                this.dialogResVisible = false;
                                this.resUpdateVisible = false;
                            }
                        }
                    });
            },
            /**
             * 确认修改资源类别按钮
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
                        typeKey: this.updateTypeKey,
                        remark: "",
                        name: this.form.name
                    };
                    let json = {
                        json: decodeURI(encodeURI(JSON.stringify(params)))
                    };
                    this.$ajax.def
                        .updateDef(json)
                        .then((response) => {
                            if (response.status === 200) {
                                let data = response.data;
                                if (data[0].state === "error") {
                                    this.$message.error(data[0].message);
                                } else {
                                    this.$message.success("修改成功");
                                    this.refreshIndex();
                                    this.dialogFormVisible = false;
                                }
                            }
                        });
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
                            for (let i = 0; i < this.attrTypeList.length; i++) {
                                this.getResAttr(i);
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            //删除资源类型
            deleteRs(item) {
                this.$confirm("确定删除该属性吗?", "提示", {
                    confirmButtonText: "提交审核",
                    cancelButtonText: "我再想想",
                    type: "warning",
                    center: true
                }).then(() => {
                    let params = {
                        typekey: item.typeKey,
                    };
                    this.$ajax.def
                        .delDef(params)
                        .then((response) => {
                            if (response.status === 200) {
                                let data = response.data;
                                if (data[0].state === "error") {
                                    this.$message.error(data[0].message);
                                } else {
                                    this.$message.success("删除成功");
                                    this.refreshIndex();
                                }
                            }
                        });
                }).catch();
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
						right: 120px;
						top: 50%;
						transform: translateY(-50%);
						&.btn-del {
							right: 20px;
						}
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