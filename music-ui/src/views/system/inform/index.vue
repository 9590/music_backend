// 发布通知inform
<template>
  <div class="advertisement">
    <!-- 发布通知 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">发布通知</div>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <!-- <el-form class="card2-el" ref="form" :model="form" label-width="100px">
          <quill-editor v-model="form.textarea" class="editor"></quill-editor>
          <el-form-item class="card2-el-item" label="发布给用户">
            <el-checkbox
              :indeterminate="isIndeterminate"
              v-model="checkAll"
              @change="handleCheckAllChange"
              >全部用户</el-checkbox><br />
            <div class="card2-el-div" style="border: 1px solid #333">
              <el-select
                v-model="value"
                multiple
                filterable
                remote
                reserve-keyword
                placeholder="请输入账号/用户名称"
                :remote-method="remoteMethod"
                :loading="loading">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
              <el-checkbox-group
                v-model="checkedCities"
                @change="handleCheckedCitiesChange">
                <el-checkbox v-for="city in cities" :label="city" :key="city">{{
                  city
                }}</el-checkbox>
              </el-checkbox-group>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button class="card2-btn" type="primary" @click="onSubmit">发布</el-button>
          </el-form-item>
        </el-form> -->
        <div class="el-form">
          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item prop="remark" label="发布通知:">
              <el-input
                :rows="8"
                type="textarea"
                v-model="ruleForm.remark"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')"
                >发布</el-button
              >
            </el-form-item>
          </el-form>
        </div>

        <div class="el-table">
          <el-table :data="tableData" border class="card2-table">
            <el-table-column
              label="序号"
              width="80"
              type="index"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="remark"
              label="内容"
              width="310"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="发布时间"
              width="180"
              align="center"
            >
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="120"
              align="center"
            >
              <template slot-scope="{ row }">
                <el-button
                  @click="handleClick(row)"
                  type="text"
                  size="small"
                  style="color: orange"
                  >查看</el-button
                >
                <el-button
                  slot="reference"
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="handleDelete(row.id)"
                  >撤回</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="block">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            background
          >
          </el-pagination>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
// const cityOptions = [
//   "11111111111 用户名称",
//   "22222222222 用户名称",
//   "33333333333 用户名称",
//   "44444444444 用户名称",
// ];
import { getRelease, getNotices, revoke } from "@/api/arelease/arelease";
export default {
  data() {
    return {
      // checkAll: false,
      checkedCities: [], //默认选中
      // cities: cityOptions,
      // isIndeterminate: true,
      pageNum: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      rules: {
        remark: [
          { required: true, message: "请填写发布内容", trigger: "blur" },
        ],
      },
      ruleForm: {
        remark: "",
      },
      options: [],
      value: [],
      list: [],
      states: [], //检索内容
    };
  },
  mounted() {
    // this.list = this.states.map((item) => {
    //   return { value: `value:${item}`, label: `label:${item}` };
    // });
  },
  created() {
    // 获取列表数据
    this.getNotices();
  },
  methods: {
    //  获取列表数据
    async getNotices() {
      const { total, rows } = await getNotices({
        pageNum: this.pageNum,
        pageSize: this.pageSize,
      });
      this.total = total;
      this.tableData = rows;
    },
    //  发布
    async submitForm(ruleForm) {
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          getRelease(this.ruleForm).then((res) => {
            if (res.code == 200) {
              this.$message({
                message: "发布成功",
                type: "success",
              });
              this.ruleForm.remark = "";
              this.getNotices();
            } else {
              this.$message.error("发布失败!");
            }
          });
        }
      });
    },
    //  撤回
    async handleDelete(id) {
      try {
        await this.$confirm("确定撤回吗？");
        await revoke(id);
        this.getNotices();
        this.$message.success("撤回成功");
      } catch (error) {
        this.$message.success(error);
      }
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.getNotices();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.getNotices();
    },
    handleClick(row) {
      this.$alert(row.remark, {
        confirmButtonText: "确定",
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
      ::v-deep .ql-toolbar {
        background-color: rgb(211, 214, 214);
      }
      .el-form {
        width: 500px;
      }
      .el-table {
        margin-top: 20px;
        width: 700px;
      }
      .block {
        margin-top: 20px;
        margin-bottom: 20px;
        ::v-deep .active {
          background-color: orange;
        }
      }
      // .editor {
      //   height: 200px;
      //   width: 80%;
      //   // margin-top: -80px;
      //   // transform: translate(1%);
      // }
      // .card2-el {
      //   height: 600px;
      //   margin-top: 90px;
      //   margin-bottom: 20px;
      //   .card2-el-item {
      //     width: 500px;
      //     // border: 1px solid #333;
      //     margin-top: 100px;
      //     margin-bottom: 20px;
      //   }
      //   .card2-el-div {
      //     width: 250px;
      //     text-align: left;
      //     padding: 10px 0 10px 10px;
      //   }
      // }
      // .card2-btn {
      //   width: 100px;
      //   margin-bottom: 220px;
      // }
      // .card2-table {
      //   width: 100%;
      //   margin-top: 100px;
      // }
    }
  }
}
</style>