// 添加专辑子分类管理
<template>
  <div class="advertisement">
    <!-- 子分类管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">添加专辑子分类</div>
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
          <el-form-item label="所属专辑分类" prop="classifyParentName">
            <el-input
              class="card2-input"
              v-model="classifyParentName"
              disabled
            ></el-input>
          </el-form-item>
          <el-form-item label="子分类编号" prop="classifyNumber">
            <el-input
              class="card2-input"
              v-model="ruleForm.classifyNumber"
              placeholder="输入子分类编号"
              @input="
                ruleForm.classifyNumber = ruleForm.classifyNumber.replace(
                  /\D/g,
                  ''
                )
              "
            ></el-input>
            <span style="color: #c0c0c0">(如:01)</span>
          </el-form-item>
          <el-form-item label="子分类名称" prop="classifyName">
            <el-input
              class="card2-input"
              v-model="ruleForm.classifyName"
              placeholder="输入专辑子分类"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >添加</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { addInfraList } from "@/api/ainfrastructure/infrastructure";
export default {
  data() {
    return {
      //上级分类名称
      classifyParentName: "",
      ruleForm: {
        classifyType: 2, //专辑子分类类别，必传
        classifyName: "", //分类名称
        classifyNumber: "", //分类编号
        classifyParent: "", //上级id
      },
      rules: {
        classifyName: [
          { required: true, message: "请输入专辑子分类", trigger: "change" },
        ],
        classifyNumber: [
          { required: true, message: "请输入子分类编号", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.classifyParentName = this.$route.query.row.name;
    this.ruleForm.classifyParent = this.$route.query.row.id;
  },
  methods: {
    // 添加
    async submitForm(ruleForm) {
      console.log("ruleForm", this.ruleForm);
      const { res } = await addInfraList(this.ruleForm);
      console.log("res", res);
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push(`/track/infrastructure/index`);
        } else {
          console.log("未提交成功!!");
          return false;
        }
      });
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