// 账号管理
<template>
  <div class="advertisement">
    <!-- 账号管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">操作员管理</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入账号/姓名"
          style="width: 200px"
          size="small"
        ></el-input>

        <el-button
          class="card2-btn"
          type="warning"
          @click="handleQuery"
          size="small"
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
          @click="$router.push(`/account/account/add-account`)"
          >添加</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于添加时间 time-->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'time', order: 'descending' }"
          >
            <el-table-column
              fixed
              prop="number"
              label="序号"
              width="80"
              key="number"
              type="index"
              v-if="columns[0].visible"
            >
            </el-table-column>
            <el-table-column
              prop="userName"
              label="账号"
              
              key="userName"
              v-if="columns[1].visible"
            >
            </el-table-column>
            <el-table-column
              prop="nickName"
              label="姓名"
              width="120"
              
              key="nickName"
              v-if="columns[2].visible"
            >
            </el-table-column>
            <el-table-column
              prop="roleName"
              label="角色"
              width="120"
              
              key="roleName"
              v-if="columns[3].visible"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="添加时间"
              width="200"
              
              v-if="columns[4].visible"
            >
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
              </template>
            </el-table-column>
            <el-table-column
              prop="loginDate"
              label="最后登录时间"
              width="200"
              
              key="loginDate"
              v-if="columns[5].visible"
            >
            </el-table-column>
            <el-table-column
              label="状态"
              align="center"
              key="status"
              v-if="columns[6].visible"
            >
              <template slot-scope="scope">
                <el-switch
                  v-model="scope.row.status"
                  active-value="0"
                  inactive-value="1"
                  @change="handleStatusChange(scope.row)"
                ></el-switch>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="scope">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/account/account/editor-account?id=`+scope.row.userId)"
                  >编辑</el-button
                >
                <el-popconfirm
                  title="确认删除吗?删除后不可恢复"
                  @confirm="del(scope.row)"
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
            :limit.sync="queryParams.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            background
          >
            <!--             :page-sizes="[10, 20, 30, 40]" -->
          </el-pagination>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import {
  listUser,
  getUser,
  delUser,
  addUser,
  updateUser,
  resetUserPwd,
  changeUserStatus,
} from "@/api/system/user";
export default {
  created() {
    this.getList();
  },
  methods: {
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getList();
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getList();
    },
    //查询
    handleQuery() {
      this.queryParams.pageNum = 1;

      this.getList();
    },
    del(row) {
      delUser(row.userId).then(() => {
        this.getList();
          this.$modal.msgSuccess("删除成功");
      })
    },
    getList() {
      listUser(this.addDateRange(this.queryParams, this.dateRange)).then(
        (response) => {
          this.tableData = response.rows;
          this.total = response.total;
        }
      );
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal
        .confirm('确认要"' + text + '""' + row.userName + '"用户吗？')
        .then(function () {
          return changeUserStatus(row.userId, row.status);
        })
        .then(() => {
          this.$modal.msgSuccess(text + "成功");
        })
        .catch(function () {
          row.status = row.status === "0" ? "1" : "0";
        });
    },
  },
  data() {
    return {
      total: 0,
      // currentPage4: 4,
      options: [
        {
          value: "0",
          label: "全部",
        },
      ],
      // locationvalue: "",
      option: [
        {
          value: "0",
          label: "全部",
        },
        {
          value: "1",
          label: "是",
        },
        {
          value: "2",
          label: "否",
        },
      ],
      // deskvalue: "",
      queryParams: {
        nickName: '', //请输入账号/姓名
        pageSize: 10,
        pageNum: 1,
      },
      columns: [
        { key: 0, label: `序号`, visible: true },
        { key: 1, label: `账号`, visible: true },
        { key: 2, label: `姓名`, visible: true },
        { key: 3, label: `角色`, visible: true },
        { key: 4, label: `添加时间`, visible: true },
        { key: 5, label: `最后登录时间`, visible: true },
        { key: 6, label: `状态`, visible: true },
      ],
      tableData: [],
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