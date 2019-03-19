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
					<el-form-item>
						<img :src="previewUrl" v-if="previewUrl!==''" width="100">
						<el-upload
								ref="upload"
								:limit="1"
								:multiple="false"
								:on-success="uploadSuc"
								:on-error="uploadFail"
								:on-remove="fileRemove"
								:action="refLogo"
								accept=".jpg,.jpeg,.png,.gif,.bmp,.JPG,.JPEG,.PBG,.GIF,.BMP"
								:on-change="uploadChange"
								:auto-upload="false">
							<el-button slot="trigger" size="small" type="primary">
								选取资源类型logo
							</el-button>
						</el-upload>
					</el-form-item>
					<el-form-item label="属性名称" v-for="(item,key) in attrList" :key="key">
						<el-input placeholder="请输入属性名称" v-model="item.attrName">
							<el-select v-model="item.type" slot="append" placeholder="请选择属性类型">
								<el-option label="文字" value="default"></el-option>
								<el-option label="图片" value="picture"></el-option>
								<el-option label="视频" value="video"></el-option>
							</el-select>
						</el-input>
						<el-input placeholder="字符限制" style="width: 100px"
						          v-if="item.type==='default'"
						          v-model="item.attrlen"></el-input>
						<el-button type="primary" @click.prevent="addAttr()" v-if="key === attrList.length-1">
							增加
						</el-button>
					</el-form-item>
					<p class="tc ">
						<el-button type="success" class="add-confirm-btn" @click="addConfirm" :loading="isFetch">确认新增
						</el-button>
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
                refLogo: "",
                form: {
                    name: "",
                    typeKey: "RDf示例表ID"
                },
                attrList: [
                    {
                        attrName: "",
                        type: "default"
                    }
                ],
                attrTypeList: [],
                file: null,
                previewUrl: "",
                isFetch: false
            };
        },
        methods: {
            beforeUpload(file) {

            },
            uploadSuc(response) {
                console.log("upload success");
                this.$refs.upload.clearFiles();
                let params = {
                    logoUrl: response[0].data,
                    typeKey: this.file.typeKey,
                    name: this.file.name,
                    remark: this.file.remark
                };
                let json = {
                    typekey: this.file.typeKey,
                    json: decodeURI(encodeURI(JSON.stringify(params)))
                };
                this.file = null;
                this.$ajax.def
                    .updateDef(json)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            this.previewUrl = "";
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            }
                        }
                    });
            },
            uploadFail() {
                console.log("upload Fail");
                this.previewUrl = "";
            },
            uploadChange(file) {
                this.file = file;
                this.previewUrl = file.url;
                if (file.raw.type.indexOf("image") === -1) {
                    this.$message.error("请选择图片格式的文件");
                    this.$refs.upload.clearFiles();
                    this.previewUrl = "";
                }
            },
            fileRemove() {
                this.previewUrl = "";
            },
            addConfirm() {
                if (this.form.name === "") {
                    this.$message.error("资源类别名称不能为空");
                    return;
                }
                let isEmpty = false;
                for (let i = 0; i < this.attrList.length; i++) {
                    if (this.attrList[i].attrName === ""
                        || this.attrList[i].type === "") {
                        isEmpty = true;
                        break;
                    }
                }
                if (isEmpty) {
                    this.$message.error("属性名称或类型不能为空");
                    return;
                }
                if (!this.file) {
                    this.$message.error("资源类型logo必选");
                    return;
                }
                this.addResType();
            },
            addResType() {
                let params = {
                    name: this.form.name,
                };
                let json = {
                    json: decodeURI(encodeURI(JSON.stringify(params)))
                };
                this.isFetch = true;
                this.$ajax.def
                    .addDef(json)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            this.isFetch = false;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            } else {
                                this.uploadPic(data[0].object);
                                this.$message.success("资源类型添加成功");
                                this.form.name = "";
                                this.form.typeKey = "";
                                this.getLeft();
                                this.$ajax.def
                                    .getDefAll(params)
                                    .then((response) => {
                                        if (response.status === 200) {
                                            let data = response.data;
                                            let json = JSON.parse(data[0].data);
                                            for (let i = 0; i < json.length; i++) {
                                                if (json[i].name === params.name) {
                                                    this.addAttrFun(json[i].typeKey);
                                                    break;
                                                }
                                            }
                                        }
                                    }, (error) => {
                                        this.$message.error(error.message);
                                    });
                            }
                        }
                    }, (error) => {
                        this.isFetch = false;
                        this.$message.error(error.message);
                    });
            },
            uploadPic(obj) {
                if (obj) {
                    obj = JSON.parse(obj);
                    this.refLogo = `https://www.hwyst.net/rs/file/add.do?json={"typeKey":"${obj.typeKey}"}`;
                    this.file = obj;
                    setTimeout(() => {
                        this.$refs.upload.submit();
                    }, 600);
                }
            },
            addAttrFun(typeKey) {
                for (let i = 0; i < this.attrList.length; i++) {
                    this.addAttrItemFun(this.attrList[i], typeKey, i);
                }
            },
            addAttrItemFun(item, typeKey, i) {
                let json = {
                    attrName: item.attrName,
                    attrType: item.type,
                    attrPosition: i,
                    typeKey: typeKey,
                    attrlen: item.attrlen,
                    attrLevel: 1
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
                                this.$message.success("资源属性新增成功");
                                this.attrList.splice(1);
                                this.attrList[0].attrName = "";
                            }
                        }
                    });
            },
            addAttr() {
                this.attrList.push({
                    attrName: "",
                    type: "default"
                });
            },
            getLeft() {
                let params = {
                    typekey: "",
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
        },
        mounted() {
            this.getLeft();
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