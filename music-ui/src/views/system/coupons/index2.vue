// 优惠券使用统计
<template>
  <div class="index2">
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">优惠券使用统计</div>
    </el-card>
    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-form
          :model="queryParams"
          ref="queryForm"
          v-show="showSearch"
          :inline="true"
        >
          <el-form-item label="优惠券类型" prop="couponId">
            <el-select size="small" v-model="queryParams.couponId">
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.couponName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="使用情况" prop="useState" style="width: 300px">
            <el-select
              v-model="queryParams.useState"
              placeholder="请选择"
              size="small"
            >
              <el-option
                v-for="item in isDisplayList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="发放时间" prop="createTime" style="width: 470px">
            <el-date-picker
              v-model="createTime"
              type="daterange"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="width: 370px"
              size="small"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="使用时间" prop="useTime" style="width: 470px">
            <el-date-picker
              v-model="useTime"
              type="daterange"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="width: 370px"
              size="small"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item style="width: 100px">
            <el-button
              class="card2-btn"
              type="warning"
              size="small"
              @click="handleQuery"
              >查询</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card2-btn"
          type="warning"
          size="small"
          @click="handleExport"
          >导出Excel</el-button
        >
        <!-- 排序基于发放时间 -->
        <el-table
          :data="tableData"
          border
          style="width: 100%"
          :default-sort="{ prop: 'createTime', order: 'descending' }"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="index" label="序号" width="80" fixed>
          </el-table-column>
          <el-table-column prop="useState" sortable label="状态" width="180">
          </el-table-column>
          <el-table-column
            prop="couponName"
            sortable
            label="优惠券类型"
            width="180"
          >
          </el-table-column>
          <el-table-column
            prop="couponDeduction"
            sortable
            label="抵扣金额（元）"
            width="180"
          >
          </el-table-column>
          <el-table-column
            prop="createTime"
            sortable
            label="发放时间"
            width="220"
          >
          </el-table-column>
          <el-table-column prop="endTime" sortable label="失效时间" width="220">
          </el-table-column>
          <el-table-column prop="useTime" sortable label="使用时间" width="220">
          </el-table-column>
          <el-table-column prop="userName" sortable label="用户" width="180">
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <div class="block">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryParams.pageNum"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="queryParams.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            background
          >
            <!--
            v-show="total>0"判断当total大于0时页码显示 -->
          </el-pagination>
        </div>
      </div>
    </el-card>
  </div>
</template>
<script>
import { opeCouList, selectListCouList } from "@/api/acoupons/coupons";
export default {
  data() {
    return {
      value: "",
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 优惠券类型
      options: [
        {
          id: "",
          couponName: "全部",
        },
      ],
      // 使用情况
      isDisplayList: [
        {
          value: "",
          label: "全部",
        },
        {
          value: "1",
          label: "未使用",
        },
        {
          value: "2",
          label: "已使用",
        },
        {
          value: "3",
          label: "已过期",
        },
      ],
      createTime: [], //发放时间
      useTime: [], //使用时间
      queryParams: {
        couponId: "", //优惠券类型
        useState: "", //使用情况
        startTime: "", //开始time
        endTime: "", //结束time
        pageSize: 10,
        pageNum: 1,
      },
      tableData: [
        {
          useState: "", //   状态
          couponName: "", // 优惠券类型
          couponDeduction: "", // 抵扣金额
          createTime: "", // 发放时间
          endTime: "", // 失效时间
          useTime: "", // 使用时间
          userName: "", //用户
        },
      ],
    };
  },
  created() {
    this.opeCouList(); // 获取表格列表
    this.selectListCouList(); //获取优惠券类型
  },
  // createTime  useTime
  // 监听时间删除后选择空
  watch: {
    createTime(newVal, oldVal) {
      if (newVal == null) {
        this.createTime = [];
      }
    },
    useTime(newVal, oldVal) {
      if (newVal == null) {
        this.useTime = [];
      }
    },
    immediate: true,
    deep: true,
  },
  // watch:{
  //   useTime(newVal,oldVal){
  //     console.log(newVal,oldVal);
  //     if(newVal==null){
  //       this.useTime=[]
  //     }
  //   },
  //   immediate:true,
  //   deep:true,
  // },
  methods: {
    // 获取表格数据
    async opeCouList() {
      const { total, rows } = await opeCouList(this.queryParams);
      this.tableData = rows;
      this.total = total;
    },
    // 获取购买类型数据并赋值给下拉框
    async selectListCouList() {
      await selectListCouList().then((res) => {
        this.options = [...this.options, ...res.data];
      });
    },
    // 监听每页条数
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.opeCouList();
    },
    // 监听页码
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.opeCouList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.id = selection.map((item) => item.id);
    },
    // 查询
    async handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryParams.startTime = this.createTime[0];
      this.queryParams.endTime = this.createTime[1];
      await opeCouList(this.queryParams).then((res) => {
        this.tableData = res.rows;
        this.total = res.total;
      });
    },
    // 导出
    handleExport() {
      this.download(
        "/system/coupon/export",
        {
          ...this.queryParams,
        },
        `优惠券使用统计.xlsx`
      );
    },
  },
};
</script>
<style lang="scss" scoped>
.index2 {
  .box-card {
    .card1 {
      height: 24px;
      font: 18px/24px "";
      color: orange;
      padding-left: 10px;
      border-left: 5px solid orange;
    }
    .card2 {
      .card2-btn {
        width: 100px;
        margin-left: 10px;
      }
    }
    .card3 {
      ::v-deep .el-table__cell {
        text-align: center;
      }
      .card2-btn {
        width: 100px;
        margin-left: 10px;
        margin-bottom: 30px;
        float: right;
      }
      .block {
        margin-top: 20px;
        margin-bottom: 20px;
        float: right;
        ::v-deep .active {
          background-color: orange;
        }
      }
    }
  }
}
</style>