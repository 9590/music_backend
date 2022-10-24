// 积分和等级设置
<template>
  <div class="level">
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">动态管理</div>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
        >
          <el-form-item label="积分来源" prop="">
            <el-row :gutter="20">
              <el-col :span="2">点击</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input1"></el-input
              ></el-col>
              <el-col :span="2">次</el-col>
              <el-col :span="2">可获</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input2"></el-input
              ></el-col>
              <el-col :span="2">积分</el-col>
            </el-row>

            <el-row :gutter="20">
              <el-col :span="2">播放</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input3"></el-input
              ></el-col>
              <el-col :span="2">次</el-col>
              <el-col :span="2">可获</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input4"></el-input
              ></el-col>
              <el-col :span="2">积分</el-col>
            </el-row>

            <el-row :gutter="20">
              <el-col :span="2">投稿</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input5"></el-input
              ></el-col>
              <el-col :span="2">次</el-col>
              <el-col :span="2">可获</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input6"></el-input
              ></el-col>
              <el-col :span="2">积分</el-col>
            </el-row>
          </el-form-item>

          <el-form-item label="积分等级区间" prop="">
            <el-row :gutter="20">
              <el-col :span="5">0</el-col>
              <el-col :span="6">-</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input7"></el-input
              ></el-col>
              <el-col :span="6">LV1</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input8"></el-input
              ></el-col>
              <el-col :span="6">-</el-col>
              <el-col :span="6"
                ><el-input type="number"  v-model="ruleForm.input9"></el-input
              ></el-col>
              <el-col :span="6">LV2</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input10"></el-input
              ></el-col>
              <el-col :span="6">-</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input11"></el-input
              ></el-col>
              <el-col :span="6">LV3</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input12"></el-input
              ></el-col>
              <el-col :span="6">-</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input13"></el-input
              ></el-col>
              <el-col :span="6">LV4</el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input14"></el-input
              ></el-col>
              <el-col :span="6">-</el-col>
              <el-col :span="6"
                ><el-input type="number" v-model="ruleForm.input15"></el-input
              ></el-col>
              <el-col :span="6">LV5</el-col>
            </el-row>
          </el-form-item>

          <el-form-item label="" prop="">
            <el-button class="card2-btn" type="primary"> 保存设置 </el-button>
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
      ruleForm: {
        input1: "",
        input2: "",
        input3: "",
        input4: "",
        input5: "",
        input6: "",
        input7: "",
        input8: "",
        input9: "",
        input10: "",
        input11: "",
        input12: "",
        input13: "",
        input14: "",
        input15: "",
      },
      rules: {
        laiyuan: [
          {
            required: true,
            message: "请填写完整积分来源",
            trigger: "blur",
          },
        ],
        level: [
          {
            required: true,
            message: "请填写完整积分等级区间",
            trigger: "blur",
          },
        ],
      },
    };
  },
};
</script>
<style lang="scss" scoped>
.level {
  .box-card {
    .card1 {
      height: 24px;
      font: 18px/24px "";
      color: orange;
      padding-left: 10px;
      border-left: 5px solid orange;
    }
    // .card2 {

    // }
  }
}
</style>