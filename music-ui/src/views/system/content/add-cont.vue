// 添加内容管理
<template>
  <div class="advertisement">
    <!-- 广告管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">内容管理</div>
    </el-card>

    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="添加标题" prop="name">
            <el-input class="card2-input" v-model="ruleForm.name"></el-input>
          </el-form-item>

          <el-form-item label="缩略图" prop="photo">
            <el-upload
              action="uploadUrl"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              v-model="ruleForm.photo"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="" />
            </el-dialog>
          </el-form-item>
          <el-form-item label="展示位置" prop="region">
            <el-select
              class="card2-input"
              v-model="ruleForm.region"
              placeholder="请选择"
            >
              <el-option
                v-for="item in positionList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="添加内容" prop="desc">
            <quill-editor v-model="ruleForm.desc" class="editor"></quill-editor>
            <!-- <el-input
              class="card2-input"
              type="textarea"
              :rows="6"
              v-model="ruleForm.desc"
            ></el-input> -->
          </el-form-item>
          <!-- <div style="width:100px;"></div> -->
          <el-form-item label="是否置顶" prop="top" class="card2-item">
            <el-radio-group v-model="ruleForm.top">
              <el-radio label="是"></el-radio>
              <el-radio label="否"></el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >保存</el-button
            >
            <el-button class="card2-btn" @click="resetForm('ruleForm')"
              >重置</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      dialogImageUrl: "",
      dialogVisible: false,
      positionList: [
        {
          value: 0,
          label: "欢迎界面",
        },
        {
          value: 1,
          label: "首页轮播图",
        },
        {
          value: 2,
          label: "商城轮播图",
        },
        {
          value: 3,
          label: "乐讯",
        },
        {
          value: 4,
          label: "发烧乐园",
        },
      ],
      ruleForm: {
        name: undefined, //标题*
        region: undefined, //展示位置*

        desc: undefined, //添加内容*
        top: undefined, //是否置顶*
        photo: undefined, //略缩图*
      },
      rules: {
        name: [
          { required: true, message: "请输入标题", trigger: "blur" },
          // { min: 0, message: '最小排序为0', trigger: 'blur' }
        ],
        region: [
          { required: true, message: "请选择展示位置", trigger: "change" },
        ],
        top: [{ required: true, message: "请选择是否置顶", trigger: "change" }],
        desc: [{ required: true, message: "请填写有关内容", trigger: "blur" }],
        photo: [
          { required: true, message: "请添加至少一张缩略图", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({
          message: '提交成功',
          type: 'success'});
        } else {
          console.log("error 提交!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
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
    .card2 {
      ::v-deep .ql-toolbar {
        background-color: rgb(211, 214, 214);
      }
      .editor {
        height: 200px;
        width: 80%;
        transform: translate(0%, 3%);
      }
      .card2-input {
        width: 400px;
      }
      .card2-input1 {
        width: 550px;
      }
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
      .card2-item {
        margin-top: 160px;
      }
    }
  }
}
</style>