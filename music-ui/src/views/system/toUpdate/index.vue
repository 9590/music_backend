// 发布APP更新
<template>
  <div class="advertisement">
    <!-- 发布APP更新 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">发布APP更新</div>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="el-form">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="当前版本号: V">
            <el-button type="text">{{ ruleForm.versionNum }}</el-button>
          </el-form-item>
          <el-form-item label="新版本号: V" prop="name2">
            <el-input v-model="ruleForm.name2"></el-input>
          </el-form-item>
          <el-form-item>
            <el-upload
              class="upload-demo"
              :action="uploadUrl"
              :on-preview="handlePreview"
              :on-success="handlePictureCardPreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              :before-upload="beforeUpload_u"
              accept=".apk"
              multiple
              :limit="1"
              :on-exceed="handleExceed"
              :file-list="fileList"
              :headers="headers"
            >
              <el-button type="info">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传 apk 文件</div>
            </el-upload>
          </el-form-item>
          <el-form-item prop="remark" label="版本说明:">
            <el-input
              :rows="8"
              type="textarea"
              v-model="ruleForm.remark"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>
<script>
import { getEdition, appAdd } from "@/api/toUpdate/toUpdate";
import { getToken } from "@/utils/auth";
export default {
  data() {
    return {
      uploadUrl: process.env.VUE_APP_BASE_API + "/system/album/addImg",
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      fileList: [],
      ruleForm: {
        versionNum: "", //老版本号
        remark: "", //  版本说明
        name2: "", //  新版本号
        apkUrl: "", //  文件
      },
      rules: {
        remark: [
          { required: true, message: "请填写发布内容", trigger: "blur" },
        ],
        name2: [{ required: true, message: "请输入版本号", trigger: "blur" }],
      },
    };
  },
  created() {
    // 获取初始显示
    this.getEdition();
  },
  methods: {
    async getEdition() {
      const data = await getEdition();
      if (data.code == 200) {
        this.ruleForm.versionNum = data.data.versionNum;
      }
    },
    async submitForm(ruleForm) {
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          appAdd({
            versionNum: this.ruleForm.name2,
            remark: this.ruleForm.remark,
            apkUrl: this.ruleForm.apkUrl,
          }).then((res) => {
            if (res.code == 200) {
              this.$message({
                message: "提交成功",
                type: "success",
              });
              this.ruleForm.name2 = "";
              this.ruleForm.apkUrl = "";
              this.ruleForm.remark = "";
              this.fileList = [];
              this.getEdition();
            } else {
              this.$message.error("未提交成功!");
            }
          });
        }
      });
    },
    handlePictureCardPreview(file) {
      // console.log(file);
      if (file.code == 200) {
        this.ruleForm.apkUrl = file.url;
      }
    },
    beforeUpload_u(file, fileList) {
      var testmsg = file.name.substring(file.name.lastIndexOf(".") + 1);
      const extension = testmsg === "apk";
      var bool = false;
      if (extension) {
        bool = true;
      } else {
        bool = false;
      }
      if (!extension) {
        this.$confirm(`上传文件只能是apk格式!`);
      }
      return bool;
    },
    handleRemove(file, fileList) {},
    handlePreview(file) {},
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
  },
};
</script>

<style lang="scss" scoped>
.advertisement {
  .box-card {
    .card1 {
      height: 24px;
      font: 18px/24px "";
      color: orange;
      padding-left: 10px;
      border-left: 5px solid orange;
    }
    .el-form {
      width: 500px;
    }
    ::v-deep .el-button--text {
      color: #606266;
    }
    .card2 {
      ::v-deep .ql-toolbar {
        background-color: rgb(211, 214, 214);
      }
      ::v-deep .ql-bold {
        color: rgb(163, 50, 50);
      }
      height: 450px;
    }
  }
}
</style>
