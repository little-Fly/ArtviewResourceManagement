<template>
	<div class="main-content">
		<el-container>
			<el-header>
				<el-row>
					<el-col :span="4" class="logo tc">
						<img width="150" src="@/assets/image/logo.png" style="margin-top: -10px" alt="logo">
					</el-col>
					<el-col :span="12" :offset="2" class="search-content">
						<el-input placeholder="输入关键字" v-model="searchInput">
							<el-select v-model="searchType"
							           slot="prepend"
							           disabled
							           placeholder="选择资源类型">
								<el-option
										v-for="item in searchOptions"
										:key="item.typeKey"
										:label="item.name"
										:value="item.typeKey">
								</el-option>
							</el-select>
							<el-button
									slot="append"
									icon="el-icon-search" @click.native="search">搜索
							</el-button>
						</el-input>
					</el-col>
					<el-col :span="6" class="exit-btn tr">
						<span class="exit" @click="exit"></span>
					</el-col>
				</el-row>
			</el-header>
			<el-container>
				<el-aside width="300px">
					<div class="type-list">
						<ul>
							<li class="type-list-item tc" :class="{'isActive':isActive === key}"
							    @click="getResTable(item,key)" v-for="(item,key) in attrTypeList">
								{{item.name}}
							</li>
						</ul>
					</div>
					<div class="bottom-btn tc" @click="goAssets" v-if="canWrite">
						<img src="@/assets/image/sourceM.png" alt="icon" width="30" height="30">
						资源类别管理
					</div>
				</el-aside>
				<el-main>
					<div class="main-head">
						<div class="title">资源录入</div>
						<ul class="btns fr" v-if="canWrite">
							<li class="fl"><span class="operate-btn type-in" @click="addSource"></span></li>
							<li class="fl"><span class="operate-btn delete" @click="deleteConfirm"></span></li>
							<li class="fl"><span class="operate-btn update" @click="updateSource"></span></li>
						</ul>
					</div>
					<div class="main-body">
						<el-table :data="tableData"
						          height="100%"
						          stripe
						          :header-cell-style="{
							            color: '#000',
	                                    fontSize: '1.1rem'
									}"
						          border
						          @selection-change="handleSelectionChange"
						          style="width: 100%">
							<el-table-column
									type="selection"
									width="55">
							</el-table-column>
							<template v-for="(col ,index) in attrData">
								<el-table-column
										:prop="col.attrKey"
										align="center"
										:label="col.attrName">
									<template slot-scope="scope">
										<img v-if="(scope.row[col.attrKey]+'').indexOf('RFl') === 0"
										     :src="`https://www.hwyst.net/ttzy/rs/file/getfile.do?filekey=`+scope.row[col.attrKey]"
										     alt="" width="40" height="40">
										<span v-else>{{scope.row[col.attrKey] | stateFilters}}</span>
									</template>
								</el-table-column>
							</template>
						</el-table>
					</div>
					<div class="main-foot tc">
						<el-pagination
								background
								prev-text="<上一页"
								next-text="下一页>"
								layout="prev, pager, next,total,jumper"
								:total="1">
						</el-pagination>
					</div>
					<el-dialog :title="addForm.title" width="30%" :visible.sync="dialogFormVisible">
						<el-form :model="addForm">
							<el-form-item :label="item.attrName" :label-width="formLabelWidth"
							              v-for="(item,key) in attrData" :key="key"
							              v-if="item.attrKey !== 'attrState' && item.attrKey !== 'approvalMess'&& item.attrKey !== 'approvalUser'">
								<el-upload
										v-if="item.attrType === 'picture'||item.attrType === 'video'"
										class="upload-demo"
										ref="upload"
										:limit="1"
										:multiple="false"
										:on-success="uploadSuc"
										:on-error="uploadFail"
										:before-upload="beforeUpload(item,key)"
										:action="item.url"
										:auto-upload="true">
									<el-button slot="trigger" size="small" type="primary">选取文件</el-button>
								</el-upload>
								<el-input v-model="addForm[item.attrKey]"
								          v-if="item.attrType !== 'picture'&&item.attrType !== 'video'"
								          :maxlength="item.attrlen > 0 ? item.attrlen :100"
								          :placeholder="item.attrlen >0 ? `限制${item.attrlen}个字符` :``"
								          auto-complete="off"></el-input>
							</el-form-item>
							<el-form-item label="数据级别" :label-width="formLabelWidth">
								<el-input v-model="attrLevel" @blur="inputModalBlur"
								          placeholder="0:任何人可见;1:成员可见;2:管理员可见"
								          type="number" max="2" min="0"
								          auto-complete="off"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button type="primary" @click="submitAudit">提交审核</el-button>
						</div>
					</el-dialog>
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
                canWrite: false,
                attrTypeList: [],
                searchType: "",
                searchOptions: [],
                searchInput: "",
                dialogFormVisible: false,
                formLabelWidth: "100px",
                addForm: {},
                multipleSelection: [],
                attrData: [], // 表头数据
                tableData: [],// 表格数据
                isActive: 0,
                currentTypeKey: "",
                currentActiveItem: "",
                addDetailData: [],
                operatingMode: "", // 当前行为 是add、update
                fileArr: [],
                currentUploadAttrKey: "",
                attrLevel: ""
            };
        },
        filters: {
            stateFilters(data) {
                let text = data;
                switch (data) {
                    case "Available":
                        text = "可用";
                        break;
                    case "ApprovalAdd":
                        text = "同意添加";
                        break;
                    case "ApprovalDel":
                        text = "同意删除";
                        break;
                    case "ApprovalUpdate":
                        text = "同意修改";
                        break;
                    case "ApprovalReject":
                        text = "驳回";
                        break;
                    default:
                        break;
                }
                return text;
            }
        },
        methods: {
            /**
             * 点击资源类别 获取资源
             */
            getResTable(item, i) {
                this.currentTypeKey = item.typeKey;
                this.currentActiveItem = item.name;
                this.isActive = i;
                this.searchType = this.currentTypeKey;
                this.getTableByType();
            },
            getTableByType() {
                let params = {
                    typekey: this.currentTypeKey,
                };
                this.$ajax.attr
                    .getAttrAll(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            this.attrData = JSON.parse(data[0].data);
                            if (this.attrData.length > 0) {
                                let arr = [
                                    {attrKey: "attrState", attrName: "审核状态"},
                                    {attrKey: "approvalMess", attrName: "审核意见"},
                                    {attrKey: "approvalUser", attrName: "审核人"}
                                ];
                                this.attrData = [...this.attrData, ...arr];
                            }
                            /*********************************************************************************/
                            this.getResTableDetail(0, 10);
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            getResTableDetail(start, len) {
                let json = {
                    typekey: this.currentTypeKey,
                    start: start,
                    len: len,
                };
                this.$ajax.detail
                    .getDetailAll(json)
                    .then((response) => {
                        if (response.status === 200) {
                            let detail = response.data;
                            if (detail.length > 0) {
                                if (detail[0].state === "error") {
                                    this.$message.error(detail[0].message);
                                    return;
                                }
                                let json = JSON.parse(detail[0].data);
                                console.log("表格数据11", json);
                                this.getLineData(json);
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            getLineData(data) {
                this.tableData.length = 0;
                let obj = {};
                let dataLen = data.length;
                let one;
                let index = 0;
                let arr = [];
                for (let i = 0; i < dataLen; i++) {
                    if (one !== data[i].resourceKey) {
                        if (i !== 0) {
                            arr[index - 1] = obj;
                        }
                        one = data[i].resourceKey;
                        index++;
                        obj = {};
                    }
                    obj[data[i].attrKey] = data[i].attrValue;
                    obj.resourceKey = data[i].resourceKey;
                    obj.approvalMess = data[i].approvalMess;
                    obj.approvalUser = data[i].approvalUser;
                    obj.attrState = data[i].attrState;
                    if (i === dataLen - 1) {
                        arr[index - 1] = obj;
                    }
                }
                this.tableData = arr;
            },
            /**
             * 新增资源按钮
             */
            addSource() {
                this.operatingMode = "add";
                this.clearForm();
                this.addForm.title = `新增${this.currentActiveItem}资源`;
                this.dialogFormVisible = true;
            },
            clearForm() {
                this.attrLevel = "";
                for (let key in this.addForm) {
                    if (this.addForm.hasOwnProperty(key) && key !== "title") {
                        this.addForm[key] = "";
                    }
                }
            },
            /**
             * 提交审核
             */
            submitAudit() {
                let json = [];
                for (let key in this.addForm) {
                    if (key === "attrState"
                        || key === "approvalMess"
                        || key === "approvalUser") {
                        break;
                    }
                    if (this.addForm.hasOwnProperty(key) &&
                        key !== "title" &&
                        key !== "resourceKey") {
                        let obj = {
                            "attrKey": key,
                            "attrValue": this.addForm[key],
                            "typeKey": this.currentTypeKey,
                            attrLevel: this.attrLevel
                        };
                        for (let i = 0; i < this.attrData.length; i++) {
                            if (this.attrData[i].attrKey === obj.attrKey) {
                                obj.attrType = this.attrData[i].attrType;
                            }
                        }
                        json.push(obj);
                    }
                }
                if (json.length === 0) {
                    this.$message.error("请输入内容");
                    return;
                }
                this.$confirm("确认提交审核吗?", "提示", {
                    confirmButtonText: "提交审核",
                    cancelButtonText: "我再想想",
                    type: "warning",
                    center: true
                }).then(() => {
                    let params = {
                        json: decodeURI(encodeURI(JSON.stringify(json)))
                    };
                    if (this.operatingMode === "add") {
                        this.addDetailFun(params);
                    } else if (this.operatingMode === "update") {
                        this.updateDetailFun(json);
                    }
                }).catch(() => {

                });
            },
            beforeUpload(item, i) {
                let url = `https://www.hwyst.net/ttzy/rs/file/add.do?json={'attrKey':'${item.attrKey}','typeKey':'${item.typeKey}'}`;
                this.$set(this.attrData[i], "url", url);
                if (this.addForm[item.attrKey] && this.addForm[item.attrKey] !== "") {
                    return;
                }
                this.addForm[item.attrKey] = "";
                this.currentUploadAttrKey = item.attrKey;
            },
            addDetailFun(data) {
                this.$ajax.detail
                    .addDetail(data)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            } else {
                                this.$message({
                                    type: "success",
                                    message: "提交成功!请等待审核！"
                                });
                                this.getTableByType();
                                this.dialogFormVisible = false;
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            /**
             * 修改资源按钮
             */
            updateSource() {
                if (this.multipleSelection.length > 1) {
                    this.$message({
                        type: "warning",
                        message: "一次最多只能修改一条数据!"
                    });
                    return;
                }
                if (this.multipleSelection.length < 1) {
                    this.$message({
                        type: "warning",
                        message: "请先选择需要修改的数据!"
                    });
                    return;
                }
                let canUpdate = false;
                switch (this.multipleSelection[0].attrState) {
                    case "ApprovalReject":
                        canUpdate = true;
                        break;
                    case "Available":
                    case "ApprovalAdd":
                    case "ApprovalDel":
                    case "ApprovalUpdate":
                    default:
                        break;
                }
                if (!canUpdate) {
                    this.$message({
                        type: "warning",
                        message: "审核通过的数据不能修改!"
                    });
                    return;
                }
                this.clearForm();
                this.operatingMode = "update";
                this.addForm.title = `修改${this.currentActiveItem}资源`;
                this.dialogFormVisible = true;
                for (let i = 0; i < this.attrData.length; i++) {
                    if (this.attrData[i].attrLevel) {
                        this.attrLevel = this.attrData[i].attrLevel;
                    }
                    let key = this.attrData[i].attrKey;
                    this.addForm[key] = this.multipleSelection[0][key];
                }
            },
            updateDetailFun(json) {
                let data = {
                    json: decodeURI(encodeURI(JSON.stringify(json))),
                    resourcekey: this.multipleSelection[0].resourceKey
                };
                this.$ajax.detail
                    .updateDetail(data)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            } else {
                                this.$message({
                                    type: "success",
                                    message: "提交成功!请等待审核！"
                                });
                                this.getTableByType();
                                this.dialogFormVisible = false;
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            /**
             * 删除资源
             */
            deleteConfirm() {
                if (this.multipleSelection.length > 1) {
                    this.$message({
                        type: "warning",
                        message: "一次最多只能删除一条数据!"
                    });
                    return;
                }
                if (this.multipleSelection.length < 1) {
                    this.$message({
                        type: "warning",
                        message: "请先选择需要删除的数据!"
                    });
                    return;
                }
                this.$confirm("是否删除该条记录?", "提示", {
                    confirmButtonText: "提交审核",
                    cancelButtonText: "我再想想",
                    type: "warning",
                    center: true
                }).then(() => {
                    let params = {
                        resourcekey: this.multipleSelection[0].resourceKey,
                    };
                    this.$ajax.detail
                        .delDetail(params)
                        .then((response) => {
                            if (response.status === 200) {
                                let data = response.data;
                                if (data[0].state === "error") {
                                    this.$message.error(data[0].message);
                                } else {
                                    this.getTableByType();
                                    this.$message.success("删除成功");
                                }
                            }
                        });
                }).catch(() => {

                });
            },
            /**
             * 表格多选事件
             */
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            /**
             * 退出登录
             */
            goAssets() {
                this.$router.push("/assets");
            },
            /**
             * 退出登录
             */
            exit() {
                this.$router.push("/login");
                sessionStorage.removeItem("myRoles");
            },
            getDefAll() {
                let params = {
                    typekey: "RDf示例表ID",
                };
                this.$ajax.def
                    .getDefAll(params)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            this.attrTypeList = JSON.parse(data[0].data);
                            this.searchOptions = this.attrTypeList;
                            if (this.attrTypeList.length > 0) {
                                this.getResTable(this.attrTypeList[0], 0);
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            /**
             * 搜索
             */
            search() {
                let json = {
                    typekey: this.searchType,
                    start: 0,
                    len: 10,
                    searchkey: {"searchKeyMap": "[{\"RDt示例值ID\":\"1234\"}]"}
                };
                this.$ajax.search
                    .searchAll(json)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            } else {
                                let json = JSON.parse(data[0].data);
                                this.getLineData(json);
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            uploadSuc(response) {
                this.addForm[this.currentUploadAttrKey] = response[0].data;
                console.log("upload success");
            },
            uploadFail() {
                console.log("upload Fail");
            },
            inputModalBlur() {
                this.attrLevel = this.attrLevel < 0 ? 0 : this.attrLevel > 2 ? 2 : this.attrLevel;
            }
        },
        mounted() {
            this.$chargeAuthority().then((t) => {
                console.log(t);
                this.getDefAll();
                if (t === "writer") {
                    this.canWrite = true;
                }
            }, (ee) => {
                console.log(ee);
                this.$router.replace("/");
            });
            // this.getLoadPic();
        }
    };
</script>

<style lang="scss" type="text/scss" scoped>
	$herderH: 60px;
	.el-header {
		background: #e5e5e5;
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
		background: #365562;
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
				&.isActive {
					color: #DC3D0F;
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