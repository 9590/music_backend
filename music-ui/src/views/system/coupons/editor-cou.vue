// 添加优惠券管理
<template>
  <div class="advertisement">
    <!-- 优惠券管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">编辑优惠券类型</div>
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
          <el-form-item label="类型名称" prop="couponName">
            <el-input
              class="card2-input"
              v-model="ruleForm.couponName"
              placeholder="请输入优惠券类型名称"
            ></el-input>
          </el-form-item>
          <el-form-item label="抵扣金额" prop="couponDeduction">
            <el-input
              class="card2-input"
              v-model="ruleForm.couponDeduction"
              placeholder="请输入抵扣金额"
              @input="couponChange"
            ></el-input
            ><span style="margin-left: 12px">元</span>
          </el-form-item>
          <el-form-item label="抵扣规则" prop="deductionRules">
            <el-input
              class="card2-input"
              v-model="ruleForm.deductionRules"
              placeholder="满多少元可用，0元为无门槛"
              @input="rulesChange"
            ></el-input
            ><span style="margin-left: 12px"></span>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input
              v-model="ruleForm.remark"
              placeholder="最终解释权归商家所有"
              class="card2-input"
            ></el-input>
          </el-form-item>
          <!-- TODO: -->
          <el-form-item label="状态" prop="couponState">
            <el-radio-group v-model="ruleForm.couponState">
              <el-radio :label="1">启用</el-radio>
              <el-radio :label="2">禁用</el-radio>
            </el-radio-group>
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
import { editCouList, getInCouList } from "@/api/acoupons/coupons";
export default {
  data() {
    return {
      // checked: false,
      ruleForm: {
        couponName: "",
        couponDeduction: "",
        deductionRules: "",
        remark: "",
        couponState: "", //状态
      },
      rules: {
        couponName: [
          { required: true, message: "请输入优惠券名称", trigger: "change" },
        ],
        couponDeduction: [
          { required: true, message: "请输入抵扣金额", trigger: "change" },
        ],
        deductionRules: [
          { required: true, message: "请输入抵扣规则", trigger: "change" },
        ],
        couponState: [
          { required: true, message: "请选择状态", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getInCouList(this.$route.params.id);
    // console.log("本id是",this.$route.params.id);
    this.id = this.$route.params.id; //获取单个广告信息
  },
  methods: {
    //抵扣金额发生改变
    couponChange() {
      this.ruleForm.couponDeduction = this.ruleForm.couponDeduction.replace(
        /[^\d\.]/g,
        ""
      );
      this.ruleForm.couponDeduction = this.ruleForm.couponDeduction.replace(
        /^\./g,
        ""
      ); //必须保证第一个为数字而不是.
      this.ruleForm.couponDeduction = this.ruleForm.couponDeduction.replace(
        /\.{2,}/g,
        "."
      ); //保证只有出现一个.而没有多个.
      this.ruleForm.couponDeduction = this.ruleForm.couponDeduction
        .replace(".", "$#$")
        .replace(/\./g, "")
        .replace("$#$", "."); //保证.只出现一次，而不能出现两次以上
    },
    //抵扣规则发生改变
    rulesChange() {
      this.ruleForm.deductionRules = this.ruleForm.deductionRules.replace(
        /[^\d\.]/g,
        ""
      );
      this.ruleForm.deductionRules = this.ruleForm.deductionRules.replace(
        /^\./g,
        ""
      ); //必须保证第一个为数字而不是.
      this.ruleForm.deductionRules = this.ruleForm.deductionRules.replace(
        /\.{2,}/g,
        "."
      ); //保证只有出现一个.而没有多个.
      this.ruleForm.deductionRules = this.ruleForm.deductionRules
        .replace(".", "$#$")
        .replace(/\./g, "")
        .replace("$#$", "."); //保证.只出现一次，而不能出现两次以上
    },
    async getInCouList() {
      const { data } = await getInCouList(this.$route.params.id);
      // console.log(data);
      this.ruleForm = data;
    },
    // 提交
    async submitForm(ruleForm) {
      // console.log(this.ruleForm);
      const { res } = await editCouList({
        id: this.id, //让新修改的数据覆盖原数据
        couponName: this.ruleForm.couponName,
        couponDeduction: this.ruleForm.couponDeduction,
        deductionRules: this.ruleForm.deductionRules,
        remark: this.ruleForm.remark,
        couponState: this.ruleForm.couponState,
      });
      // console.log(res);
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push(`/operation/coupons/index`);
        } else {
          // console.log("error 提交!!");
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
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
    }
  }
}
</style>
