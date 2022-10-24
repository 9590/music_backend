// 商品管理
<template>
  <div class="advertisement">
    <!-- 商品管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">商品管理</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-input
          v-model="queryParams.shopName"
          placeholder="请输入商品名称"
          size="small"
          style="width: 200px"
        ></el-input>
        <span class="card2-sp">
          <span class="card2-sp-span">分类</span>
          <el-select v-model="queryParams.classifyName" size="small">
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.classifyName"
              :value="item.id"
            >
            </el-option>
          </el-select> </span
        ><span class="card2-sp">
          <span class="card2-sp-span">是否置顶</span>
          <el-select v-model="queryParams.shopIsTopName" size="small">
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </span>
        <div style="height: 10px"></div>
        <span class="card2-sp">
          <span class="card2-sp-span">发布时间</span>
          <div class="block" style="display: inline-block">
            <el-date-picker
              v-model="create"
              type="datetimerange"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="width: 370px"
              size="small"
            >
            </el-date-picker>
          </div> </span
        ><el-button
          class="card2-btn"
          size="small"
          type="warning"
          @click="handleQuery"
          >查询</el-button
        >
      </div>
    </el-card>
    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/goods/goods/add-good`)"
          >添加</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于发布时间 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'time', order: 'descending' }"
            @sort-change="sortChange"
          >
            <el-table-column
              fixed
              type="index"
              prop="number"
              label="序号"
              width="180"
              key="number"
            >
            </el-table-column>
            <el-table-column
              prop="shopNumber"
              label="商品编号"
              width="180"
              sortable="custom"
              key="shopNumber"
            >
            </el-table-column>
            <el-table-column
              prop="shopName"
              label="商品名称"
              sortable="custom"
              key="shopName"
            >
            </el-table-column>
            <el-table-column
              prop="classifyName"
              label="分类"
              width="120"
              sortable="custom"
              key="classifyName"
            >
            </el-table-column>
            <el-table-column
              prop="shopPrice"
              label="价格(元)"
              width="120"
              sortable="custom"
              key="shopPrice"
            >
            </el-table-column>
            <el-table-column
              prop="shopIsTopName"
              label="置顶"
              width="110"
              sortable="custom"
              key="shopIsTopName"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="发布时间"
              width="220"
              sortable="custom"
              key="createTime"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="160">
              <template slot-scope="{ row }">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  v-if="row.shopIsTop == 1"
                  @click="Topping2(row.id)"
                  >取消置顶</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  v-if="row.shopIsTop == 0"
                  @click="Topping1(row.id)"
                  >置顶</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/goods/goods/editor-good${row.id}`)"
                  >编辑</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="handleDelete(row.id)"
                  >删除</el-button
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
import {
  managementList,
  Topping,
  classIfication,
  manDelete,
} from "@/api/amallManagement/amallManagement";
export default {
  data() {
    return {
      total: 0, //总条数
      tableData: [],
      classifyType: 1,
      create: [], //购买时间范围
      // 分类
      options: [
        {
          id: "",
          classifyName: "全部",
        },
      ],
      // locationvalue: "",
      // 置顶
      option: [
        {
          value: "",
          label: "全部",
        },
        {
          value: "1",
          label: "是",
        },
        {
          value: "0",
          label: "否",
        },
      ],
      queryParams: {
        shopName: "", //请输入商品名称
        classifyName: "", //分类
        shopIsTopName: "", //是否置顶
        startTime: "", //开始时间
        endTime: "", //结束时间
        pageSize: 10,
        pageNum: 1,
      },
      pageNum: 1,
      pageSize: 10,
    };
  },
  created() {
    // 获取列表数据
    this.managementList();
    // 分类下拉数据
    this.classIfication();
  },
  methods: {
    sortChange({ column, prop, order }) {
      if (order == null) {
        this.pageNum = 1
        this.handleQuery();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.pageNum = 1
        this.handleQuery();
      }
    },
    async managementList() {
      const { total, rows } = await managementList({
        pageNum: this.pageNum,
        pageSize: this.pageSize,
      });
      this.total = total;
      this.tableData = rows;
    },
    // 分类下拉数据
    async classIfication() {
      const { data } = await classIfication({
        classifyType: this.classifyType,
      });
      this.options = [...this.options, ...data];
    },
    //  置顶
    async Topping1(id) {
      try {
        await this.$confirm("确定置顶吗？");
        await Topping({
          id: id,
        });
        this.interface();
        this.$message.success("置顶成功");
      } catch (error) {
        this.$message.success(error);
      }
    },
    async Topping2(id) {
      try {
        await this.$confirm("确定取消置顶吗？");
        await Topping({
          id: id,
        });
        this.interface();
        this.$message.success("取消置顶成功");
      } catch (error) {
        this.$message.success(error);
      }
    },
    // 删除
    async handleDelete(id) {
      try {
        await this.$confirm("确定删除吗？");
        await manDelete(id);
        this.interface();
        this.$message.success("删除成功");
      } catch (error) {
        this.$message.success(error);
      }
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.interface();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.interface();
    },
    //查询
    async handleQuery() {
      this.pageNum = 1;
      this.interface();
    },
    interface() {
      managementList({
        shopName: this.queryParams.shopName,
        classifyId: this.queryParams.classifyName,
        shopIsTop: this.queryParams.shopIsTopName,
        startTime: this.create[0],
        endTime: this.create[1],
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        params:this.queryParams.params
      }).then((res) => {
        this.tableData = res.rows;
        this.total = res.total;
      });
    },
  },
  watch: {
    create(newVal, oldVal) {
      // console.log(newVal,oldVal);
      if (newVal == null) {
        this.create = [];
      }
    },
    immediate: true,
    deep: true,
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
      // height: 100px;
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
        // margin-left: 20px;
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