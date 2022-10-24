// 添加分类管理
<template>
  <div class="advertisement">
    <!-- 分类管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">添加文章分类</div>
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
          <el-form-item label="分类编号" prop="classifyNumber">
            <el-input
              class="card2-input"
              v-model="ruleForm.classifyNumber"
              placeholder="请输入分类编号"
              @input="
                ruleForm.classifyNumber = ruleForm.classifyNumber.replace(
                  /[^\d\-\a-zA-Z]/g,
                  ''
                )
              "
            ></el-input>
          </el-form-item>
          <el-form-item label="分类名称" prop="classifyName">
            <el-input
              class="card2-input"
              v-model="ruleForm.classifyName"
              placeholder="请输入分类名称"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >添加</el-button
            >
            <!-- <el-button class="card2-btn" @click="resetForm('ruleForm')"
              >重置</el-button
            > -->
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { addClaList } from "@/api/aclassification/classification";
export default {
  data() {
    return {
      ruleForm: {
        classifyType: 3,
        classifyName: "", //商品名称
        classifyNumber: "", //商品编号
      },
      rules: {
        classifyName: [
          { required: true, message: "请输入分类名称", trigger: "blur" },
        ],
        classifyNumber: [
          { required: true, message: "请输入分类编号", trigger: "blur" },
        ],
      },
    };
  },

  methods: {
    // 添加

    async submitForm(ruleForm) {
      console.log("ruleform", this.ruleForm);

      const { res } = await addClaList(this.ruleForm);
      console.log("res", res);
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push(`/content/classification/index`);
        } else {
          console.log("未提交成功");
          return false;
        }
      });
    },
    // handleRemove(file, fileList) {
    //   console.log(file, fileList);
    // },
    // handlePictureCardPreview(file) {
    //   this.dialogImageUrl = file.url;
    //   this.dialogVisible = true;
    // },
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
      .card2-input {
        width: 400px;
      }
      // .card2-input1{
      //     width: 550px;
      // }
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
    }
  }
}
</style>