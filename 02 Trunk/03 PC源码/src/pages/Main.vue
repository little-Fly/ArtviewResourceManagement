<template>
	<div class="main-content">
		<el-container>
			<el-header>
				<el-row>
					<el-col :span="4" class="logo tc">
						<img width="150" src="@/assets/image/logo.png" style="margin-top: -10px" alt="logo">
					</el-col>
					<el-col :span="12" :offset="2" class="search-content">
						<el-input placeholder="输入关键字" @focus="searchFocus">
							<el-select v-model="searchType"
							           slot="prepend"
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
									icon="el-icon-search" @click.native="searchFocus">搜索
							</el-button>
						</el-input>
					</el-col>
					<el-col :span="6" class="exit-btn tr">
						<span class="exit" @click="exit"></span>
					</el-col>
				</el-row>
			</el-header>
			<el-container>
				<el-aside width="200px">
					<div class="type-list">
						<ul>
							<li class="type-list-item no-wrap tc" :class="{'isActive':isActive === key}"
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
						          style="width: 100%"
						          stripe
						          :header-cell-style="{
							            color: '#000',
	                                    fontSize: '1.1rem'
									}"
						          border
						          @selection-change="handleSelectionChange">
							<el-table-column
									type="selection"
									width="55">
							</el-table-column>
							<template v-for="(col ,index) in attrData">
								<el-table-column
										resizable
										:prop="col.attrKey"
										align="center"
										:label="col.attrName">
									<template slot-scope="scope">
										<div v-html="$options.filters.stateFilters(scope.row[col.attrKey])"></div>
									</template>
								</el-table-column>
							</template>
						</el-table>
					</div>
					<div class="main-foot tc">
						<el-pagination
								background
								@current-change="pagination"
								prev-text="<上一页"
								next-text="下一页>"
								layout="prev, pager, next,total,jumper"
								:total="total">
						</el-pagination>
					</div>
					<el-dialog :title="addForm.title" width="30%" :visible.sync="dialogFormVisible">
						<el-form :model="addForm">
							<el-form-item :label="item.attrName" :label-width="formLabelWidth"
							              v-for="(item,key) in attrData" :key="key"
							              v-if="item.attrKey !== 'attrState' && item.attrKey !== 'approvalMess'&& item.attrKey !== 'approvalUser'">
								<p style="font-size: 12px;line-height: 15px" v-if="item.attrType === 'picture'||item.attrType === 'video'">
									推荐图片大小“<2M”,图片类型“jpg、png”;视频大小"<20M",视频类型"mp4、3gp"
								</p>
								<el-upload
										v-if="item.attrType === 'picture'||item.attrType === 'video'"
										ref="upload"
										:limit="1"
										:multiple="false"
										:on-success="uploadSuc"
										:on-error="uploadFail"
										:on-remove="fileRemove"
										:on-change="uploadChange"
										:before-upload="beforeUpload"
										:action="getAction(item, key)"
										:auto-upload="true">
									<el-button slot="trigger" @click="chooseFile(item)" size="small" type="primary">
										选取文件({{item.attrType}})
									</el-button>
								</el-upload>
								<img :src="item.previewUrl"
								     v-if="item.attrType === 'picture' && item.previewUrl !== undefined && item.previewUrl !== ''"
								     width="100">
								<video :src="item.previewUrl"
								       v-if="item.attrType === 'video' && item.previewUrl !== undefined && item.previewUrl !== ''"
								       controls="controls" width="100"></video>
								<br>
								<el-select v-if="item.attrType === 'picture'||item.attrType === 'video'"
								           v-model="item.attrLevel" placeholder="请选择数据级别">
									<el-option label="任何人可见" value="0"></el-option>
									<el-option label="成员可见" value="1"></el-option>
									<el-option label="管理员可见" value="2"></el-option>
								</el-select>
								<el-input v-model="addForm[item.attrKey]"
								          v-if="item.attrType !== 'picture'&&item.attrType !== 'video'"
								          :maxlength="item.attrlen > 0 ? item.attrlen :100"
								          :placeholder="item.attrlen >0 ? `限制${item.attrlen}个字符` :``"
								          auto-complete="off">
									<el-select v-model="item.attrLevel" slot="append" placeholder="请选择数据级别">
										<el-option label="任何人可见" value="0"></el-option>
										<el-option label="成员可见" value="1"></el-option>
										<el-option label="管理员可见" value="2"></el-option>
									</el-select>
								</el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button type="primary" @click="submitAudit">提交审核</el-button>
						</div>
					</el-dialog>
					<el-dialog title="请输入搜索条件" width="30%" :visible.sync="searchVisible">
						<el-form :model="addForm">
							<el-form-item :label="'查询条件'+(key+1)" :label-width="formLabelWidth"
							              v-for="(item,key) in searchData" :key="key">
								<el-input placeholder="请输入属性名,如:姓名" v-model="item.attr"></el-input>
								<el-input placeholder="请输入属性值,如:张三" v-model="item.value"></el-input>
							</el-form-item>
							<el-form-item class="tc">
								<el-button type="info" @click="addSearchItem">增加搜索条件</el-button>
								<el-button type="primary" @click="searchFun">搜索</el-button>
							</el-form-item>
						</el-form>
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
                dialogFormVisible: false,
                searchVisible: false,
                formLabelWidth: "100px",
                addForm: {},
                multipleSelection: [],
                attrData: [], // 表头数据
                tableData: [],// 表格数据
                isActive: 0,
                currentTypeKey: "",
                currentActiveItem: "",
                currentUploadItem: {},
                addDetailData: [],
                operatingMode: "", // 当前行为 是add、update
                fileArr: [],
                currentUploadAttrKey: "",
                searchData: [{attr: "", value: ""}], //attrkey
                total: 0,
                pageSize: 10
            };
        },
        filters: {
            stateFilters(data) {
                if (!data) {
                    return "";
                }
                let text = "";
                if (typeof data === "object") {
                    switch (data.attrType) {
                        case "default":
                            text = data.attrValue;
                            break;
                        case "picture":
                            if (data.attrValue === "") {
                                text = `无`;
                                break;
                            }
                            let imgUrl = data.attrValue.indexOf("/rs/file/getfile.do?filekey") === -1
                                ? `https://www.hwyst.net/rs/file/getfile.do?filekey=${data.attrValue}`
                                : data.attrValue;
                            text = `<img src="${imgUrl}" style="width: 90%;min-width: 100px">`;
                            break;
                        case "video":
                            if (data.attrValue === "") {
                                text = `无`;
                                break;
                            }
                            let url = data.attrValue.indexOf("/rs/file/getfile.do?filekey") === -1
                                ? `https://www.hwyst.net/rs/file/getfile.do?filekey=${data.attrValue}`
                                : data.attrValue;
                            text = `<video src="${url}" controls="controls" style="width: 90%;min-width: 100px"></video>`;
                            break;
                    }
                } else {
                    switch (data) {
                        case "Available":
                            text = "可用";
                            break;
                        case "ApprovalAdd":
                            text = "待审核添加";
                            break;
                        case "ApprovalDel":
                            text = "待审核删除";
                            break;
                        case "ApprovalUpdate":
                            text = "待审核修改";
                            break;
                        case "ApprovalReject":
                            text = "驳回";
                            break;
                        default:
                            text = data;
                            break;
                    }
                }
                return text;
            }
        },
        methods: {
            pagination(i) {
                this.getTableByType((i - 1) * this.pageSize, this.pageSize);
            },
            /**
             * 搜索获取焦点
             */
            addSearchItem() {
                this.searchData.push({attr: "", value: ""});
            },
            searchFocus() {
                this.searchData = [{attr: "", value: ""}];
                this.searchVisible = true;
            },
            searchFun() {
                let obj = {};
                for (let i = 0; i < this.searchData.length; i++) {
                    if (this.searchData[i].attr.trim() === "" || this.searchData[i].value.trim() === "") {
                        this.$message.warning("搜索条件不能为空");
                        return;
                    }
                    for (let j = 0; j < this.attrData.length; j++) {
                        if (this.searchData[i].attr === this.attrData[j].attrName) {
                            obj[this.attrData[j].attrKey] = this.searchData[i].value;
                            break;
                        }
                    }
                }
                if (JSON.stringify(obj) === "{}") {
                    this.$message.warning("搜索条件有误");
                    return;
                }
                let json = {
                    typekey: this.searchType,
                    searchkey: decodeURI(encodeURI(JSON.stringify(obj)))
                };
                this.$ajax.search
                    .searchAll(json)
                    .then((response) => {
                        if (response.status === 200) {
                            let data = response.data;
                            if (data[0].state === "error") {
                                this.$message.error(data[0].message);
                            } else {
                                // console.log("searchAll", data);
                                let jsonD = JSON.parse(data[0].data);
                                this.searchVisible = false;
                                this.getSearchAttr(jsonD);
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            getSearchAttr(json) {
                let params = {
                    typekey: this.searchType
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
                            this.getLineData(json);
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            /**
             * 点击资源类别 获取资源
             */
            getResTable(item, i) {
                this.currentTypeKey = item.typeKey;
                this.currentActiveItem = item.name;
                this.isActive = i;
                this.searchType = this.currentTypeKey;
                this.getTableByType(0, 10);
            },
            getTableByType(_from, _size) {
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
                                // console.log("this.attrData", this.attrData);
                                let arr = [
                                    {attrKey: "attrState", attrName: "审核状态"},
                                    {attrKey: "approvalMess", attrName: "审核意见"},
                                    {attrKey: "approvalUser", attrName: "审核人"}
                                ];
                                this.attrData = [...this.attrData, ...arr];
                            }
                            // this.getLineData(json);
                            // return;
                            /*********************************************************************************/
                            this.getResTableDetail(_from, _size);
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
                                this.total = detail[0].total;
                                let json = JSON.parse(detail[0].data);
                                // console.log("表格数据11", json);
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
                    // obj[data[i].attrKey] = data[i].attrValue;
                    obj[data[i].attrKey] = data[i];
                    obj.resourceKey = data[i].resourceKey;
                    obj.approvalMess = data[i].approvalMess;
                    obj.approvalUser = data[i].approvalUser;
                    obj.attrState = data[i].attrState;
                    if (i === dataLen - 1) {
                        arr[index - 1] = obj;
                    }
                }
                this.tableData = arr;
                // console.log("this.tableData", this.tableData);
            },
            clearForm() {
                for (let key in this.addForm) {
                    if (this.addForm.hasOwnProperty(key) && key !== "title") {
                        this.addForm[key] = "";
                    }
                }
            },
            /**
             * 新增资源按钮
             */
            addSource() {
                if (this.$refs.upload) {
                    for (let i = 0; i < this.$refs.upload.length; i++) {
                        this.$refs.upload[i].clearFiles();
                    }
                }
                this.attrData.forEach((item, i) => {
                    this.$set(this.attrData[i], "previewUrl", "");
                });
                this.operatingMode = "add";
                this.clearForm();
                this.addForm = {title: `新增${this.currentActiveItem}资源`};
                this.dialogFormVisible = true;
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
                if (this.$refs.upload) {
                    for (let i = 0; i < this.$refs.upload.length; i++) {
                        this.$refs.upload[i].clearFiles();
                    }
                }
                this.attrData.forEach((item, i) => {
                    this.$set(this.attrData[i], "previewUrl", "");
                });
                this.clearForm();
                this.operatingMode = "update";
                this.addForm.title = `修改${this.currentActiveItem}资源`;
                this.dialogFormVisible = true;
                for (let i = 0; i < this.attrData.length; i++) {
                    let key = this.attrData[i].attrKey;
                    if (this.multipleSelection[0][key]) {
                        this.addForm[key] = this.multipleSelection[0][key].attrValue;
                        if (this.attrData[i].attrType === "picture" || this.attrData[i].attrType === "video") {
                            // let url = `https://www.hwyst.net/rs/file/getfile.do?filekey=${ this.addForm[key]}`;
                            let url = this.addForm[key].indexOf("/rs/file/getfile.do?filekey") === -1
                                ? `/rs/file/getfile.do?filekey=${this.addForm[key]}`
                                : this.addForm[key];
                            this.$set(this.attrData[i], "previewUrl", "https://www.hwyst.net" + url);
                        }
                        for (let j = 0; j < this.attrData.length; j++) {
                            if (this.multipleSelection[0][key].attrKey === this.attrData[j].attrKey) {
                                this.$set(this.attrData[j], "attrLevel", this.multipleSelection[0][key].attrLevel);
                            }
                        }
                    }
                }
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
                                this.getTableByType(0, 10);
                                this.dialogFormVisible = false;
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
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
                                this.getTableByType(0, 10);
                                this.dialogFormVisible = false;
                            }
                        }
                    }, (error) => {
                        this.$message.error(error.message);
                    });
            },
            /**
             * 提交审核
             */
            submitAudit() {
                let json = [];
                let len = 0;
                for (let i = 0; i < this.attrData.length; i++) {
                    let item = this.attrData[i].attrKey;
                    if (item === "attrState"
                        || item === "approvalMess"
                        || item === "approvalUser"
                        || this.attrData[i].attrType === undefined) {
                        continue;
                    }
                    len++;
                }
                for (let key in this.addForm) {
                    if (key === "attrState"
                        || key === "approvalMess"
                        || key === "approvalUser") {
                        continue;
                    }
                    if (this.addForm.hasOwnProperty(key) &&
                        key !== "title" &&
                        key !== "resourceKey") {
                        let obj = {
                            "attrKey": key,
                            "attrName": "",
                            "attrValue": this.addForm[key],
                            "typeKey": this.currentTypeKey,
                        };
                        for (let i = 0; i < this.attrData.length; i++) {
                            if (this.attrData[i].attrKey === obj.attrKey) {
                                obj.attrType = this.attrData[i].attrType;
                                obj.attrName = this.attrData[i].attrName;
                                obj.attrLevel = this.attrData[i].attrLevel;
                            }
                        }
                        if (obj.attrType !== undefined) {
                            json.push(obj);
                        }
                    }
                }
                // console.log("this.attrData:", this.attrData);
                // console.log("this.addForm:", this.addForm);
                // console.log("json:", json);
                // console.log("len:", len);
                if (json.length === 0) {
                    this.$message.error("请输入内容");
                    return;
                }
                if (json.length < len) {
                    this.$message.error("全部字段必填1");
                    return;
                }
                for (let i = 0; i < json.length; i++) {
                    let val = json[i].attrValue;
                    if (val === "") {
                        this.$message.error("全部字段必填2");
                        return;
                    }
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
                                    this.getTableByType(0, 10);
                                    this.$message.success("请等待审核");
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
            getDefAll(def) {
                let params = {
                    typekey: def ? def : "",
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
            uploadSuc(response) {
                this.addForm[this.currentUploadAttrKey] = "/rs/file/getfile.do?filekey=" + response[0].data;
                console.log("upload success");
            },
            getAction(item, i) {
                let url = `https://www.hwyst.net/rs/file/add.do?json={'attrKey':'${item.attrKey}','typeKey':'${item.typeKey}'}`;
                this.$set(this.attrData[i], "url", url);
                return url;
            },
            chooseFile(item) {
                this.currentUploadAttrKey = item.attrKey;
                this.currentUploadItem = item;
                if (this.addForm[item.attrKey] && this.addForm[item.attrKey] !== "") {
                    return;
                }
                this.addForm[item.attrKey] = "";
            },
            beforeUpload(file) {
                if (this.currentUploadItem.attrType === "picture") {
                    if (file.type.indexOf("image") === -1) {
                        this.$message.error("请选择图片格式的文件");
                        return false;
                    }
                } else if (this.currentUploadItem.attrType === "video") {
                    if (file.type.indexOf("video") === -1) {
                        this.$message.error("请选择视频格式的文件");
                        return false;
                    }
                }
                return true;
            },
            uploadFail() {
                console.log("upload Fail");
                this.attrData.forEach((item, i) => {
                    if (this.currentUploadAttrKey === item.attrKey) {
                        this.$set(this.attrData[i], "previewUrl", "");
                    }
                });
            },
            fileRemove() {
                this.attrData.forEach((item, i) => {
                    if (this.currentUploadAttrKey === item.attrKey) {
                        this.$set(this.attrData[i], "previewUrl", "");
                    }
                });
            },
            uploadChange(file) {
                this.attrData.forEach((item, i) => {
                    if (this.currentUploadAttrKey === item.attrKey) {
                        this.$set(this.attrData[i], "previewUrl", file.url);
                    }
                });
            },
        },
        mounted() {
            this.$chargeAuthority().then((t) => {
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