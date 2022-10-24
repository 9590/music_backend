// 订单管理
<template>
  <div class="advertisement">
    <!-- 广告管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">订单管理</div>
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
          <el-form-item label="" prop="remark">
            <el-input
              v-model="queryParams.remark"
              clearable
              placeholder="请输入订单编号/商品名称"
              size="small"
              style="width: 300px"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="购买类型" prop="shopType">
            <!-- <el-cascader
              v-model="queryParams.shopType"
              :options="options"
              @change="handleChange"
              :show-all-levels="false"
              clearable>
              <template slot-scope="{ node, data }">
                <span>{{ data.name }}</span>
                <span v-if="!node.isLeaf"> ({{ data.children.length }}) </span>
              </template>
            </el-cascader> -->
            <!-- <el-select
              v-model="queryParams.shopType"
              size="small">
            <el-tree :data="options" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select> -->
            <el-select
              v-model="upResName"
              clearable
              placeholder="请选择"
              @clear="handleClear"
              ref="selectUpResId"
            >
              <el-option
                hidden
                key="options.id"
                :value="options.id"
                :label="options.name"
              >
              </el-option>
              <el-tree
                :data="options"
                :props="defaultProps"
                :expand-on-click-node="false"
                :check-on-click-node="true"
                @node-click="handleNodeClick"
              >
              </el-tree>
            </el-select>
          </el-form-item>
          <el-form-item label="购买时间" prop="create">
            <el-date-picker
              v-model="create"
              type="daterange"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="width: 370px"
              size="small"
              clearable
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item>
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
    <!-- 导出excel -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          type="warning"
          @click="handleExport"
          size="small"
          v-hasPermi="['system:user:export']"
          >导出Excel</el-button
        ><!--  disabled -->
        <div class="card3-tab">
          <!-- 排序基于购买时间 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'createTime', order: 'descending' }"
            @selection-change="handleSelectionChange"
            @sort-change="sortChange"
          >
            <el-table-column
              fixed
              prop="number"
              label="序号"
              width="80"
              key="number"
              type="index"
            >
            </el-table-column>
            <el-table-column
              prop="id"
              label="订单编号"
              sortable="custom"
              width="120"
              key="id"
            >
            </el-table-column>
            <el-table-column
              prop="payNumber"
              label="支付编号"
              width="120"
              sortable="custom"
              key="payNumber"
            >
            </el-table-column>
            <el-table-column
              prop="payType"
              label="支付方式"
              width="120"
              sortable="custom"
              key="payType"
            >
            </el-table-column>
            <el-table-column
              prop="shopType"
              label="购买类型"
              width="120"
              key="shopType"
            >
            </el-table-column>
            <el-table-column prop="shopName" label="商品名称" key="shopName">
            </el-table-column>
            <el-table-column
              prop="writer"
              label="商品编号"
              width="120"
              key="writer"
            >
            </el-table-column>
            <el-table-column
              prop="shopPrice"
              label="价格（元）"
              width="130"
              sortable="custom"
              key="shopPrice"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="购买时间"
              width="160"
              sortable="custom"
              key="createTime"
            >
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
// import { treeSelect } from '@/components/tree-select.vue';
import { getOrdList, typeOrdList } from "@/api/aorder/order";
export default {
  // components: { treeSelect },
  name: "order",
  data() {
    return {
      defaultProps: {
        children: "children",
        label: "name",
      },
      upResName: "",
      // 显示搜索条件
      showSearch: true,
      total: 0,
      // 购买类型
      options: [
        {
          id: "",
          name: "全部",
        },
      ],
      create: [], //购买时间范围
      queryParams: {
        remark: "", //请输入订单编号/商品名称
        shopType: "", //购买类型
        startTime: "", //购买开始时间
        endTime: "", //购买开始时间
        pageSize: 10,
        pageNum: 1,
      },

      tableData: [
        {
          number: "", //序号
          id: "", //订单编号
          payNumber: "", //支付编号
          payType: "", //支付方式
          shopType: "", //购买类型
          shopName: "", //商品名称
          writer: "", //商品编号
          shopPrice: "", //价格
          createTime: "", //购买时间
        },
      ],
    };
  },
  created() {
    this.getOrdList(); //获取订单管理表格数据
    this.typeOrdList(); //获取购买类型数据
  },
  // 监听时间删除后选择空
  watch: {
    create(newVal, oldVal) {
      // console.log(newVal, oldVal);
      if (newVal == null) {
        this.create = [];
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
        this.getOrdList();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.getOrdList();
      }
    },
    handleNodeClick(data) {
      this.upResName = data.name;
      this.queryParams.shopType = data.id;
      // 选择器执行完成后，使其失去焦点隐藏下拉框的效果
      this.$refs.selectUpResId.blur();
    },
    // 选择器配置可以清空选项，用户点击清空按钮时触发
    handleClear() {
      // 将选择器的值置空
      this.upResName = "";
      this.queryParams.shopType = "";
    },

    // popoverHide (checkedIds, checkedData) {
    //   console.log(checkedIds);
    //   console.log(checkedData);
    // },
    //  获取表格数据
    async getOrdList() {
      const { total, rows } = await getOrdList({
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
      });
      this.tableData = rows;
      this.total = total;
    },
    // 获取购买类型数据并赋值给下拉框
    async typeOrdList() {
      await typeOrdList().then((res) => {
        //  下拉列表框
        this.options = [...this.options, ...res.data];
      });
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getOrdList();
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getOrdList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.id = selection.map((item) => item.id);
    },

    //查询
    async handleQuery() {
      await getOrdList({
        remark: this.queryParams.remark,
        shopType: this.queryParams.shopType,
        startTime: this.create[0],
        endTime: this.create[1],
        pageSize: this.queryParams.pageSize,
        pageNum: this.queryParams.pageNum,
      }).then((res) => {
        this.tableData = res.rows;
      });
    },
    // /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/order/export",
        {
          ...this.queryParams,
        },
        `订单信息.xlsx`
      );
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
        margin-left: 10px;
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
        // margin-left: 20px;
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