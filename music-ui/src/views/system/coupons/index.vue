// 优惠券管理
<template>
  <div class="advertisement">
    <!-- 优惠券管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">优惠券管理</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-form
          :model="queryParams"
          ref="queryForm"
          v-show="showSearch"
          :inline="true"
        >
          <el-form-item label="" prop="couponName">
            <el-input
              v-model="queryParams.couponName"
              placeholder="请输入优惠券类型名称"
              size="small"
              clearable
              style="width: 250px"
            ></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="couponState">
            <el-select v-model="queryParams.couponState" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                size="small"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="创建时间" prop="createTime">
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
          <el-form-item label="" prop="">
            <el-button
              class="card2-btn"
              type="warning"
              @click="handleQuery"
              size="small"
              >查询</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/coupons/coupons/add-cou`)"
          >添加</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于抵扣金额 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'createTime', order: 'descending' }"
            @sort-change="sortChange"
          >
            <el-table-column
              fixed
              prop="number"
              label="序号"
              width="80"
              type="index"
              key="number"
            >
            </el-table-column>
            <el-table-column
              prop="couponName"
              label="优惠券类型"
              key="couponName"

            >
            </el-table-column>
            <el-table-column
              prop="couponDeduction"
              label="抵扣金额（元）"
              width="180"
              sortable="custom"
              key="couponDeduction"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="创建时间"
              width="220"
              sortable="custom"
              key="createTime"
            >
            </el-table-column>
            <el-table-column
              prop="couponStateName"
              label="状态"
              width="120"
              sortable="custom"
              key="couponStateName"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="160">
              <template slot-scope="{ row }">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/coupons/coupons/editor-cou${row.id}`)"
                  >编辑</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/coupons/coupons/index2`)"
                  >查看</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="
                    $router.push(
                      `/coupondistribution/coupondistribution/add-coup`
                    )
                  "
                  >发放</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
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
          </el-pagination>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getCouList } from "@/api/acoupons/coupons";
export default {
  data() {
    return {
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // TODO:
      options: [
        {
          value: "",
          label: "全部",
        },
        {
          value: "1",
          label: "启用",
        },
        {
          value: "2",
          label: "禁用",
        },
      ],
      createTime: [], //创建时间
      queryParams: {
        couponName: "", //请输入名称
        couponState: "", //适用类型
        startTime: "", //创建开始时间
        endTime: "", //创建开始时间
        pageSize: 10,
        pageNum: 1,
      },
      tableData: [
        {
          // number: "number", //序号
          couponName: "couponName", //优惠券类型
          couponDeduction: "couponDeduction", //抵扣金额
          createTime: "createTime", //创建时间
          couponStateName: "couponStateName", //状态
        },
      ],
    };
  },
  created() {
    this.getCouList(); //获取列表
  },
  // 监听时间删除后选择空
  watch: {
    createTime(newVal, oldVal) {
      // console.log(newVal,oldVal);
      if (newVal == null) {
        this.createTime = [];
      }
    },
    immediate: true,
    deep: true,
  },
  methods: {
    sortChange({ column, prop, order }) {
      if (order == null) {
        this.queryParams = {
          pageSize: 10,
          pageNum: 1,
        };
        this.getCouList();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.getCouList();
      }
    },
    // 获取表格数据
    async getCouList() {
      const { total, rows } = await getCouList(this.queryParams);
      this.tableData = rows;
      this.total = total;
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getCouList();
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getCouList();
    },
    //查询
    async handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryParams.startTime = this.createTime[0];
      this.queryParams.endTime = this.createTime[1];
      await getCouList(this.queryParams).then((res) => {
        this.tableData = res.rows;
        this.total = res.total;
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
      .card2-sp {
        margin-right: 30px;
        .card2-sp-span {
          margin-left: 20px;
          margin-right: 10px;
        }
      }
      .card2-btn {
        width: 100px;
        margin-left: 20px;
      }
    }
    .card3 {
      ::v-deep .el-table th {
        background-color: rgb(216, 215, 215);
        color: black;
        font: 16px/20px "Microsoft Yahei";
        text-align: center;
      }
      ::v-deep .cell {
        text-align: center;
      }
      .card3-btn {
        width: 100px;
        margin-bottom: 20px;
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