// 分类管理
<template>
  <div class="advertisement">
    <!-- 商品管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">分类管理</div>
    </el-card>

    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/classification/classification/add-cla`)"
          >添加分类</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于分类名称 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'classifyName', order: 'descending' }"
          >
            <el-table-column fixed label="序号" width="180" type="index">
            </el-table-column>
            <el-table-column
              prop="classifyName"
              label="分类名称"
              sortable
              key="classifyName"
            >
            </el-table-column>

            <el-table-column fixed="right" label="操作" min-width="70px">
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
import { listClaList, delClaList } from "@/api/aclassification/classification";
export default {
  data() {
    return {
      total: 0, //总条数
      queryParams: {
        classifyType: 3, //必传，不能丢，所有页面
        pageSize: 10,
        pageNum: 1,
      },
      tableData: [
        {
          classifyName: "classifyName", //名称分类
        },
      ],
    };
  },
  created() {
    this.listClaList();
  },
  methods: {
    //  获取表格数据
    async listClaList() {
      const { total, rows } = await listClaList(
        {
        classifyType:this.queryParams.classifyType,
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
      });
      this.tableData = rows;
      this.total = total;
      console.log("total", total);
    },
    // 监听每页条数
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.queryParams.pageSize = val;
      this.listClaList();
    },
    // 监听页码
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.queryParams.pageNum = val;
      this.listClaList();
    },
    // 删除
    async handleDelete(id){
      try{
        await this.$confirm("确定删除吗?");
        await delClaList(id);
        this.listClaList();
        this.$message.success("删除成功");
      }catch(error){
        console.log(error);
      }
    },
    // 编辑handleEditor协参传
    handleEditor(row){
      this.$router.push({
        path:'/classification/classification/editor-cla',
        query:{row:row}
      })
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