// 角色管理
<template>
  <div class="advertisement">
    <!-- 角色管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">角色管理</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-input
          v-model="queryParams.roleName"
          placeholder="请输入角色名称"
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
          @click="$router.push(`/roles/roles/add-role`)"
          >添加</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于状态 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            v-loading="loading"
            :default-sort="{ prop: 'state', order: 'descending' }"
          >
            <el-table-column
              label="序号"
              prop="roleId"
              width="120"
              type="index"
            />
            <el-table-column
              label="角色名称"
              prop="roleName"
              :show-overflow-tooltip="true"
            />
            <el-table-column
              label="权限字符"
              prop="roleKey"
              :show-overflow-tooltip="true"
              width="150"
            />
            <el-table-column label="显示顺序" prop="roleSort" width="100" />
            <el-table-column label="状态" align="center" width="100">
              <template slot-scope="scope">
                <el-switch
                  v-model="scope.row.status"
                  active-value="0"
                  inactive-value="1"
                  @change="handleStatusChange(scope.row)"
                ></el-switch>
              </template>
            </el-table-column>
            <el-table-column
              label="创建时间"
              align="center"
              prop="createTime"
              width="180"
            >
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="{ row }">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="
                    $router.push(`/roles/roles/editor-role?id=` + row.roleId)
                  "
                  >编辑</el-button
                >

                <el-button
                  slot="reference"
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="handleDelete(row)"
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
            :limit.sync="queryParams.pageSize"
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
import { listRole, delRole, changeRoleStatus } from "@/api/system/role";
export default {
  created() {
    this.getList();
  },
  methods: {
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getList();
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getList();
      console.log(`当前页: ${val}`);
    },
    //查询
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const roleIds = row.roleId;
      this.$modal
        .confirm('是否确认删除角色编号为"' + roleIds + '"的数据项？')
        .then(function () {
          return delRole(roleIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    getList() {
      this.loading = true;
      listRole(this.addDateRange(this.queryParams, this.dateRange)).then(
        (response) => {
          this.tableData = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    // 角色状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$modal
        .confirm('确认要"' + text + '""' + row.roleName + '"角色吗？')
        .then(function () {
          return changeRoleStatus(row.roleId, row.status);
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
      // 遮罩层
      loading: true,
      // input: "",
      // currentPage4: 4,
      queryParams: {
        roleName: "", //请输入名称/账号
        pageSize: 10,
        pageNum: 1,
      },
      columns: [
        { key: 0, label: `序号`, visible: true },
        { key: 1, label: `角色`, visible: true },
        { key: 2, label: `描述`, visible: true },
        { key: 3, label: `状态`, visible: true },
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