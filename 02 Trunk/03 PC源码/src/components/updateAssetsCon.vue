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
				<el-form-item label="资源类别logo" :label-width="formLabelWidth">
					<img :src="nowLogoUrl" alt=""
					     v-if="form.logoUrl !== ''" width="100">
					<el-upload
							ref="upload"
							:limit="1"
							:multiple="false"
							:on-success="uploadSuc"
							:on-error="uploadFail"
							:on-remove="fileRemove"
							:action="uploadUrl"
							accept=".jpg,.jpeg,.png,.gif,.bmp,.JPG,.JPEG,.PBG,.GIF,.BMP"
							:on-change="uploadChange"
							:auto-upload="false">
						<el-button slot="trigger" size="small" type="primary">
							点我更换logo
						</el-button>
					</el-upload>
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
				<el-form-item label="属性名称" :label-width="formLabelWidth">
					<el-input v-model="resAttr.attrName" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="属性类型" :label-width="formLabelWidth">
					<el-select v-model="resAttr.attrType" placeholder="请选择属性类型">
						<el-option label="文字" value="default"></el-option>
						<el-option label="图片" value="picture"></el-option>
						<el-option label="视频" value="video"></el-option>
					</el-select>
					<el-input placeholder="字符限制" style="width: 100px"
					          v-if="resAttr.attrType==='default'"
					          v-model="resAttr.attrlen"></el-input>
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
				<el-form-item label="属性名称" :label-width="formLabelWidth">
					<el-input v-model="resAttr.attrName" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="属性类型" :label-width="formLabelWidth">
					<el-select v-model="resAttr.attrType" placeholder="请选择属性类型">
						<el-option label="文字" value="default"></el-option>
						<el-option label="图片" value="picture"></el-option>
						<el-option label="视频" value="video"></el-option>
					</el-select>
					<el-input placeholder="字符限制" style="width: 100px"
					          v-if="resAttr.attrType==='default'"
					          v-model="resAttr.attrlen"></el-input>
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
                    name: "",
                    logoUrl: ""
                },
                // 资源属性  弹窗字段
                resAttr: {
                    attrKey: "",
                    attrName: "",
                    remark: "",
                    attrPosition: 0,
                    attrlen: 1,
                    attrType: "default"
                },
                type1: "",
                type2: "",
                attrList: [],
                updateTypeKey: "",
                uploadUrl: "",
                nowLogoUrl: "",
                oldLogoUrl: "",
                file: null
            };
        },
        methods: {
            uploadSuc(response) {
                console.log("upload success");
                this.$refs.upload.clearFiles();
                let params = {
                    logoUrl: response[0].data,
                    typeKey: this.updateTypeKey,
                    name: this.form.name,
                };
                let json = {
                    typekey: this.updateTypeKey,
                    json: decodeURI(encodeURI(JSON.stringify(params)))
                };
                this.file = null;
                this.$ajax.def
                    .updateDef(json)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            }
                            this.refreshIndex();
                        }
                    });
            },
            uploadFail() {
                console.log("upload Fail");
                this.nowLogoUrl = this.oldLogoUrl;
            },
            uploadChange(file) {
                this.file = file;
                this.oldLogoUrl = this.nowLogoUrl;
                this.nowLogoUrl = file.url;
                this.form.logoUrl = file.url;
            },
            fileRemove() {
                this.nowLogoUrl = this.oldLogoUrl;
            },
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
                this.form.logoUrl = item.logoUrl;
                this.nowLogoUrl = `/rs/file/getfile.do?filekey=${this.form.logoUrl }`;
                this.uploadUrl = `https://www.hwyst.net/rs/file/add.do?json={"typeKey":"${item.typeKey}"}`;
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
                this.resAttr.attrPosition = 0;
                this.resAttr.attrlen = 2;
                this.resAttr.attrType = "default";
            },
            //修改资源属性按钮
            updateThisLine(item) {
                this.resAttr.attrKey = item.attrKey;
                this.resAttr.attrName = item.attrName;
                this.resAttr.remark = item.remark;
                this.resAttr.attrPosition = item.attrPosition;
                this.resAttr.attrlen = item.attrlen;
                this.resAttr.attrType = item.attrType;
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
                if (this.resAttr.attrName === "") {
                    this.$message.warning("属性名称不能为空!");
                    return;
                }
                let json = {
                    attrKey: this.resAttr.attrKey,
                    attrName: this.resAttr.attrName,
                    attrType: this.resAttr.attrType,
                    attrlen: this.resAttr.attrlen,
                    remark: this.resAttr.remark,
                    typeKey: this.updateTypeKey,
                    attrPosition: this.attrList.length + 1
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
            confirmUpdateAttr() {
                if (this.resAttr.attrName === "") {
                    this.$message.warning("属性名称不能为空!");
                    return;
                }
                let json = {
                    attrKey: this.resAttr.attrKey,
                    attrName: this.resAttr.attrName,
                    attrType: this.resAttr.attrType,
                    attrlen: this.resAttr.attrlen,
                    remark: this.resAttr.remark,
                    attrPosition: this.resAttr.attrPosition,
                    typeKey: this.updateTypeKey
                };
                let params = {
                    attrkey: this.resAttr.attrKey,
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
                        typekey: this.updateTypeKey,
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
                                    if (this.file) {
                                        setTimeout(() => {
                                            this.$refs.upload.submit();
                                        }, 600);
                                    }
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