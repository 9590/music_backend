// 添加分类管理
<template>
  <div class="advertisement">
    <!-- 分类管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">编辑商品分类</div>
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
              >保存</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>
<script>
import { editClaList } from "@/api/aclassification/classification";
export default {
  data() {
    return {
      ruleForm: {
        classifyName: "",
        classifyNumber: "",
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
  created() {
    // this.getParams();
    this.getQuery();
    this.$route.Query;
    this.id = this.$route.query.id;
  },
  methods: {
    // 放入表单
    getQuery() {
      const routerQuery = this.$route.query.row;
      this.ruleForm = routerQuery;
    },
    // 保存
    async submitForm(ruleForm) {
      const { res } = await editClaList({
        id: this.ruleForm.id,
        classifyNumber: this.ruleForm.classifyNumber,
        classifyName: this.ruleForm.classifyName,
      });
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push(`/mall/goodsclass/index`);
        } else {
          this.$message.error("未提交成功!");
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
  // 这个watch和上面的将数据放入表单有关
  watch: {
    $route: "getQuery",
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