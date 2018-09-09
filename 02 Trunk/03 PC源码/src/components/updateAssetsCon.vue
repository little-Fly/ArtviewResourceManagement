<template>
	<div class="update-assets-con">
		<div class="father-box">
			<div class="content">
				<el-card class="box-card">
					<h3 class="title">演员资源</h3>
					<div class="tags">
						<el-tag>标签一</el-tag>
						<el-tag type="info">标签二</el-tag>
						<el-tag type="info">标签三</el-tag>
						<el-tag type="info">标签四</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五二</el-tag>
					</div>
					<el-button type="primary" class="btn" @click="update">修改</el-button>
				</el-card>
				<el-card class="box-card">
					<h3 class="title">演员资源</h3>
					<div class="tags">
						<el-tag>标签一</el-tag>
						<el-tag type="info">标签二</el-tag>
						<el-tag type="info">标签三</el-tag>
						<el-tag type="info">标签四</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五二</el-tag>
					</div>
					<el-button type="primary" class="btn" @click="update">修改</el-button>
				</el-card>
				<el-card class="box-card">
					<h3 class="title">演员资源</h3>
					<div class="tags">
						<el-tag>标签一</el-tag>
						<el-tag type="info">标签二</el-tag>
						<el-tag type="info">标签三</el-tag>
						<el-tag type="info">标签四</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五二</el-tag>
					</div>
					<el-button type="primary" class="btn" @click="update">修改</el-button>
				</el-card>
				<el-card class="box-card">
					<h3 class="title">演员资源</h3>
					<div class="tags">
						<el-tag>标签一</el-tag>
						<el-tag type="info">标签二</el-tag>
						<el-tag type="info">标签三</el-tag>
						<el-tag type="info">标签四</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五二</el-tag>
					</div>
					<el-button type="primary" class="btn" @click="update">修改</el-button>
				</el-card>
				<el-card class="box-card">
					<h3 class="title">演员资源</h3>
					<div class="tags">
						<el-tag>标签一</el-tag>
						<el-tag type="info">标签二</el-tag>
						<el-tag type="info">标签三</el-tag>
						<el-tag type="info">标签四</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五</el-tag>
						<el-tag type="info">标签五二</el-tag>
					</div>
					<el-button type="primary" class="btn">修改</el-button>
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
				<el-form-item label="属性名称" :label-width="formLabelWidth">
					<el-input v-model="form.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="属性名称" :label-width="formLabelWidth">
					<el-input v-model="form.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="属性名称" :label-width="formLabelWidth">
					<el-input v-model="form.name" auto-complete="off"></el-input>
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
                form: {
                    name: ""
                },
                type1: "",
                type2: ""
            };
        },
        methods: {
            update() {
                this.dialogFormVisible = true;
            },
            confirmUpdate() {
                this.$confirm("是否确定保存?", "提示", {
                    confirmButtonText: "提交审核",
                    cancelButtonText: "我再想想",
                    type: "warning",
                    center: true
                }).then(() => {
                    this.$message({
                        type: "success",
                        message: "提交成功!"
                    });
                    this.dialogFormVisible = false;
                }).catch(() => {

                });
            }
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
	}
</style>