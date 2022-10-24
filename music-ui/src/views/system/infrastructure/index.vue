// 专辑分类
<template>
  <div class="advertisement">
    <!-- 专辑分类 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">专辑分类</div>
    </el-card>

    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/infrastructure/infrastructure/add-inf`)"
          >添加分类</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于分类名称 -->
          <el-table
            :data="tableData"
            border
            row-key="id"
            default-expand-all
            :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
            style="width: 100%"
            :default-sort="{ prop: 'number', order: 'descending' }"
            @sort-change="sortChange"
          >
            <el-table-column
              fixed
              prop="number"
              label="编号"
              key="number"
              sortable="custom"
              width="300px"
            >
            </el-table-column>

            <el-table-column prop="name" label="分类名称" sortable="custom" key="name">
            </el-table-column>
            <el-table-column fixed="right" label="操作" min-width="80">
              <template slot-scope="{ row }">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="handleEditor(row)"
                  >编辑</el-button
                >

                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="handleDelete(row.id)"
                  >删除</el-button
                >

                <el-button
                  v-if="row.parentId == '0'"
                  type="text"
                  size="small"
                  style="
                    color: #fff;
                    background-color: orange;
                    width: 90px;
                    height: 30px;
                  "
                  @click="handleEditorChildren(row)"
                  >+子分类</el-button
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
import {
  listInfraList,
  delInfraList,
} from "@/api/ainfrastructure/infrastructure";
export default {
  data() {
    return {
      total: 0,
      queryParams: {
        classifyType: 2, //必传，不能丢，所有页面
        pageSize: 10,
        pageNum: 1,
        
      },
      tableData: [
        {
          number: "number",
          id: "",
          name: "name",
        },
      ],
    };
  },
  created() {
    this.listInfraList(); //获取列表
  },
  methods: {
    sortChange({ column, prop, order }) {
      console.log(123);
      if (order == null) {
        this.queryParams = {
          pageSize: 10,
          pageNum: 1,
        };
        this.listInfraList();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.listInfraList();
      }
    },
    // 表格数据
    async listInfraList() {
      const { total, rows } = await listInfraList(
        this.queryParams
      );
      this.tableData = rows;
      this.total = total;
     
    },
    // 监听每页条数
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.queryParams.pageSize = val;
      this.listInfraList();
    },
    // 监听页码
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.queryParams.pageNum = val;
      this.listInfraList();
    },
    // 删除
    async handleDelete(id) {
      try {
        await this.$confirm("确定删除吗?");
        await delInfraList(id);
        this.listInfraList();
        this.$message.success("删除成功");
      } catch (error) {
        console.log(error);
      }
    },
    // 编辑<!--  $router.push(`/infrastructure/infrastructure/editor-inf`) -->
    handleEditor(row) {
      this.$router.push({
        path: "/infrastructure/infrastructure/editor-inf",
        query: { row: row },
      });
    },
    handleEditorChildren(row) {
      this.$router.push({
        path: `/infrastructure/infrastructure/add-infChild`,
        query: { row: row },
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
        // margin-left: 20px;
        margin-bottom: 20px;
        width: 100px;
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