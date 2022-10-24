
<template>
  <div class="advertisement">
    <!-- 隐私政策及服务协议 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">隐私政策及服务协议</div>
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
          <el-form-item prop="remark">
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
import { preservation, getNotices } from "@/api/agreement/agreement";
export default {
  data() {
    return {
      ruleForm: {
        remark: "",
      },
      rules: {
        remark: [{ required: true, message: "请填写内容", trigger: "blur" }],
      },
      id: "",
    };
  },
  created() {
    // 获取初始显示
    this.getNotices();
  },
  methods: {
    async getNotices() {
      const data = await getNotices();
      if (data.code == 200) {
        this.ruleForm.remark = data.data.remark;
        this.id = data.data.id;
      }
    },
    async submitForm(ruleForm) {
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          preservation({
            remark: this.ruleForm.remark,
            id: this.id,
          }).then((res) => {
            if (res.code == 200) {
              this.$message({
                message: "提交成功",
                type: "success",
              });
              this.getNotices();
            } else {
              this.$message.error("未提交成功!");
            }
          });
          // this.ruleForm.remark = ''
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
    .el-form {
      width: 800px;
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