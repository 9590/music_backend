// 优惠券发放管理
<template>
  <div class="advertisement">
    <!-- 优惠券发放管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">优惠券发放管理</div>
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
          <el-form-item label="选择优惠券" prop="couponId">
            <el-select
              class="card2-sel"
              v-model="ruleForm.couponId"
              placeholder="请选择优惠券类型"
            >
              <el-option
                v-for="item in region"
                :key="item.id"
                :label="item.couponName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <!-- TODO: -->
          <!-- <el-form-item label="有效期" prop="">
            <el-input
            label="1"
              class="card2-sel"
              v-model="ruleForm.periodOfValidity"
              :disabled="disabledInput"
              placeholder="请输入具体天数"
            ></el-input
            ><br />
            <el-checkbox v-model="ruleForm.checked"  label="1"
            @change="inputToDisabled"
              >长期有效</el-checkbox
            >&nbsp;
            <span style="color: #c0c0c0">(勾选长期有效优惠券将不会过期)</span>
          </el-form-item> -->
          <el-form-item label="有效期" prop="periodOfValidity">
            <el-input
              class="card2-sel"
              v-model="ruleForm.periodOfValidity"
              :disabled="disabledInput"
              placeholder="请输入具体天数"
              @input="dayChange"
            ></el-input>
            <br />
            <!-- <el-radio
            v-model="ruleForm.checked"
            label="1"
            :disabled="disabledCheckbox">
            长期有效</el-radio> -->
            <el-checkbox
              v-model="checked"
              :disabled="disabledCheckbox"
              @change="dateChange"
            >
              长期有效
            </el-checkbox>
            <span style="color: #c0c0c0; margin-left: 10px"
              >(勾选长期有效优惠券将不会过期)</span
            >
          </el-form-item>
          <!-- TODO: -->
          <el-form-item label="选择用户" prop="userIds">
            <el-transfer
              style="text-align: left; display: inline-block"
              v-model="value"
              filterable
              :filter-method="filterMethod"
              filter-placeholder="请输入账号/用户名称"
              :left-default-checked="[]"
              :right-default-checked="[]"
              :titles="['选择用户', '已选用户']"
              :render-content="renderFunc"
              :format="{
                noChecked: '${total}',
                hasChecked: '${checked}/${total}',
              }"
              @change="handleChange"
              :data="data"
            >
              <!-- <span slot-scope="{ option }">{{ option.key }} - {{ option.label }}</span> -->
            </el-transfer>
          </el-form-item>
          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >发放</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import {
  selectListCouList,
  userCouList,
  couCouList,
} from "@/api/acoupons/coupons";
export default {
  data() {
    const generateData = (_) => {
      const data = [];
      userCouList().then((res) => {
        for (let i = 0; i < res.data.length; i++) {
          let b = res.data[i];
          data.push({
            key: b.id,
            label: b.userName, //  名称
            id: b.userAccount, //  账号
          });
        }
      });
      return data;
    };
    return {
      data: generateData(),
      // data1:[],
      // 默认天数可输入
      disabledInput: false,
      disabledCheckbox: false,
      renderFunc(h, option) {
        return (
          <span>
            {option.id} - {option.label}
          </span>
        );
      },
      filterMethod(query, item) {
        if (item.label.indexOf(query) != -1) {
          return item.label.indexOf(query) > -1;
        } else if (item.key.indexOf(query) != -1) {
          return item.key.indexOf(query) > -1;
        }
      },
      //优惠券类型
      region: [],
      checked: "", //长期有效
      ruleForm: {
        // region: "", //选择优惠券类型
        periodOfValidity: "", //有效期
        // userAccount: "",
        // userName: "",
        // checked: "", //长期有效
        couponId: "",
        userIds: [], //选择用户
      },
      value: [],

      drag: false,
      rules: {
        couponId: [
          { required: true, message: "请选择优惠券", trigger: "change" },
        ],
        periodOfValidity: [
          { required: true, message: "请输入具体天数", trigger: "change" },
        ],
        userIds: [{ required: true, message: "请选择用户", trigger: "change" }],
      },
    };
  },
  created() {
    this.selectListCouList(); //获取优惠券类型
    // this.couCouList(); //发放接口
    this.userCouList(); //穿梭框选项
  },
  watch: {
    immediate: true,
    deep: true,
    "ruleForm.periodOfValidity"(newVal, oldVal) {
      // console.log("新", newVal, "旧", oldVal);
      // if ((this.newVal = "")) {
      //   this.disabledCheckbox = true;
      // }
      // console.log(this.disabledCheckbox);
    },
  },
  methods: {
    //有效期发生改变时
    dayChange() {
      if (this.ruleForm.periodOfValidity !== "长期有效") {
        this.ruleForm.periodOfValidity = this.ruleForm.periodOfValidity.replace(
          /\D/g,
          ""
        );
      } else {
        this.ruleForm.periodOfValidity = "长期有效";
      }
    },
    dateChange(val) {
      if (val) {
        this.ruleForm.periodOfValidity = "长期有效";
      } else {
        this.ruleForm.periodOfValidity = "";
      }
    },
    //选择用户发放接口
    async userCouList() {
      const res = await userCouList();
      this.data1 = res.data;
    },
    // 穿梭框value,

    handleChange(id) {
      this.ruleForm.userIds = id;
      console.log(id);
    },
    // 选择优惠券
    async selectListCouList() {
      await selectListCouList().then((res) => {
        this.region = [...this.region, ...res.data];
      });
    },
    async addUserCoupon() {
      if (this.ruleForm.periodOfValidity == "长期有效") {
        delete this.ruleForm.periodOfValidity;
      }
      await couCouList(this.ruleForm).then((res) => {
        this.$router.push(`/operation/coupons/index`);
      });
    },
    //提交按钮ing
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.addUserCoupon();
        } else {
          console.log("error 提交!!");
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
      ::v-deep .el-transfer-panel {
        border: none;
      }
      ::v-deep .el-transfer-panel__header {
        border-bottom: none;
        background-color: white;
      }
      ::v-deep .el-transfer-panel__body {
        border: 1px solid #333;
      }

      .card2-sel {
        margin-right: 30px;
        width: 250px;
      }
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
    }
  }
}
</style>