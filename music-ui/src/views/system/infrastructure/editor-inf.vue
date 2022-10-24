// 添加专辑分类管理
<template>
  <div class="advertisement">
    <!-- 分类管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">编辑专辑分类</div>
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
              placeholder="输入分类编号"
              @input="
                ruleForm.classifyNumber = ruleForm.classifyNumber.replace(
                  /\D/g,
                  ''
                )
              "
            ></el-input>
          </el-form-item>
          <el-form-item label="分类名称" prop="classifyName">
            <el-input
              class="card2-input"
              v-model="ruleForm.classifyName"
              placeholder="输入专辑分类"
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
import { editInfraList } from "@/api/ainfrastructure/infrastructure";
export default {
  data() {
    return {
      ruleForm: {
        // classifyType:2,//专辑分类类别，必传
        classifyName: "",
        classifyNumber: "",
      },
      rules: {
        classifyName: [
          { required: true, message: "请输入专辑分类", trigger: "change" },
        ],
        classifyNumber: [
          { required: true, message: "请输入分类编号", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getQuery();
    this.$route.Query;
    console.log("11this.query", this.$route.query);
    this.id = this.$route.query.row.id;
    console.log("11this.$route.query.id", this.$route.query.row.id);
  },

  methods: {
    // 放入表单
    getQuery() {
      // const routerQuery=this.$route.query.row;
      // this.ruleForm.classifyName=routerQuery.name;
      // this.ruleForm.classifyNumber=routerQuery.number;
      this.ruleForm.id = this.$route.query.row.id;
      this.ruleForm.classifyName = this.$route.query.row.name;
      this.ruleForm.classifyNumber = this.$route.query.row.number;
      // console.log("routerQuery!!!",routerQuery);
      console.log("放入表单routerQueryname", this.ruleForm.classifyName);
      console.log("放入表单routerQuerynumber", this.ruleForm.classifyNumber);
    },
    // 保存
    async submitForm(ruleForm) {
      const { res } = await editInfraList({
        id: this.ruleForm.id,
        // id:this.id,
        classifyName: this.ruleForm.classifyName,
        classifyNumber: this.ruleForm.classifyNumber,
      });
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