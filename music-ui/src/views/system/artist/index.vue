// 艺术家管理
<template>
  <div class="advertisement">
    <!-- 艺术家管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">艺术家管理</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入账号/名称"
          style="width: 200px"
          size="small"
        ></el-input>

        <el-button
          class="card2-btn"
          type="warning"
          size="small"
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
          @click="$router.push(`/artist/artist/add-artist`)"
          >添加</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于添加时间 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'time', order: 'descending' }"
            @sort-change="sortChange"
          >
            <!-- v-if="columns[0].visible" -->
            <el-table-column
              fixed
              prop="number"
              label="序号"
              width="180"
              key="number"
              type="index"
            >
            </el-table-column>
            <!-- v-if="columns[1].visible" -->
            <el-table-column
              prop="userAccount"
              label="艺术家账号"
              width="150"
              sortable="custom"
              key="userAccount"
            >
            </el-table-column>
            <!-- v-if="columns[2].visible" -->
            <el-table-column
              prop="userName"
              label="名称"
              width="150"
              sortable="custom"
              key="userName"
            >
            </el-table-column>
            <!-- v-if="columns[3].visible" -->
            <el-table-column
              prop="userAvatar"
              label="头像"
              width="150"
            
              key="userAvatar"
            >
              <template slot-scope="scope">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="scope.row.userAvatar"
                  fit="cover"
                ></el-image>
              </template>
            </el-table-column>
            <!-- v-if="columns[4].visible" -->
            <el-table-column prop="remark" label="简介" key="remark">
            </el-table-column>
            <!-- v-if="columns[5].visible" -->
            <el-table-column
              prop="createTime"
              label="添加时间"
              width="200"
              sortable="custom"
              key="createTime"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="scope">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="
                    $router.push(
                      `/artist/artist/editor-artist?id=` + scope.row.id
                    )
                  "
                  >编辑</el-button
                >
                <el-popconfirm
                  title="确认删除吗?删除后不可恢复"
                  @confirm="del(scope.row.id)"
                  style="margin-left: 10px"
                >
                  <el-button
                    slot="reference"
                    type="text"
                    size="small"
                    style="color: orange"
                    >删除</el-button
                  >
                </el-popconfirm>
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
import { list, remove } from "@/api/artist/artist";
export default {
  created() {
    this.list();
  },
  methods: {
    sortChange({ column, prop, order }) {
      if (order == null) {
        this.queryParams = {

          pageSize: 10,
          pageNum: 1,
        };
        this.list();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.list();
      }
    },
    async del(id) {
      const { code } = await remove(id);
      if (code == 200) {
        this.$message({
          type: "success",
          message: "删除成功!",
        });
        setTimeout(() => {
          this.list();
        }, 1000);
      }
    },
    async list() {
      const { total, rows } = await list(this.queryParams);
      this.total = total;
      this.tableData = rows;
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.list();
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.list();
      // console.log(`当前页: ${val}`);
    },
    //查询
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.list();
    },
  },
  data() {
    return {
      total: 0,
      // input: "",
      // currentPage4: 4,
      queryParams: {
        userType: 2,
        userName: undefined, //请输入账号/名称
        pageSize: 10,
        pageNum: 1,
      },
      // columns: [
      //   { key: 0, label: `序号`, visible: true },
      //   { key: 1, label: `艺术家账号`, visible: true },
      //   { key: 2, label: `名称`, visible: true },
      //   { key: 3, label: `头像`, visible: true },
      //   { key: 4, label: `简介`, visible: true },
      //   { key: 5, label: `添加时间`, visible: true },
      // ],
      tableData: [
        {
          number: "number",
          userAccount: "userAccount",
          userName: "userName",
          userAvatar: "userAvatar",
          remark: "remark",
          createTime: "createTime",
        },
      ],
    };
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