
<template>
  <div class="advertisement">
    <!-- 商品管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">用户管理</div>
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
          <el-form-item label="" prop="userName">
            <el-input
              v-model="queryParams.userName"
              placeholder="请输入账号/名称"
              size="small"
              style="width: 200px"
            ></el-input>
          </el-form-item>
          <el-form-item label="" prop="">
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
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/userss/userss/add-user`)"
          >添加</el-button
        >
        <!-- <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/userss/userss/level`)"
          >积分和等级设置</el-button
        > -->
        <div class="card3-tab">
          <!-- 排序基于等级 level-->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'level', order: 'descending' }"
            @sort-change="sortChange"
          >
            <el-table-column
              fixed
              prop="number"
              label="序号"
              width="180"
              key="number"
              type="index"
            >
            </el-table-column>
            <el-table-column
              prop="userAccount"
              label="用户账号"
              sortable="custom"
              key="userAccount"
            >
            </el-table-column>
            <el-table-column
              prop="userName"
              label="用户名称"
              sortable="custom"
              key="userName"
            >
            </el-table-column>
            <el-table-column
              prop="userAvatar"
              label="头像"
              width="130"
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
            <el-table-column
              prop="userLevel"
              label="等级"
              width="110"
              sortable="custom"
              key="userLevel"
            >
            </el-table-column>
            <el-table-column
              prop="userExp"
              label="积分"
              width="100"
              sortable="custom"
              key="userExp"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="注册时间"
              width="120"
              sortable="custom"
              key="createTime"
            >
            </el-table-column>
            <el-table-column
              prop="lastLoginTime"
              label="最后登录时间"
              width="150"
              sortable="custom"
              key="lastLoginTime"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="scope">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="
                    $router.push(`/userss/userss/look-user?id=` + scope.row.id)
                  "
                  >查看</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="
                    $router.push(
                      `/userss/userss/editor-user?id=` + scope.row.id
                    )
                  "
                  >编辑</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="handleDelete(scope.row.id)"
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
import { userList, add, edit, remove } from "@/api/userss/userss";
export default {
  data() {
    return {
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // input: "",
      // currentPage4: 4,
      options: [
        {
          value: "0",
          label: "全部",
        },
        {
          value: "1",
          label: "月",
        },
        {
          value: "2",
          label: "年",
        },
        {
          value: "3",
          label: "非会员",
        },
      ],
      // locationvalue: "",
      queryParams: {
        userType: 1,
        userName: "", //请输入账号/名称
        pageSize: 10,
        pageNum: 1,
      },
      tableData: [
        {
          number: "number",
          account: "account",
          name: "name",
          head: "",
          level: "level",
          integral: "integral",
          time: "time",
          timed: "timed",
        },
      ],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    sortChange({ column, prop, order }) {
      if (order == null) {
        this.queryParams = {

          pageSize: 10,
          pageNum: 1,
        };
        this.getList();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.getList();
      }
    },
    //获取用户列表
    // async userList() {
    //   const a = await userList({ userType: "1" });
    //   console.log(a);
    // },
    async getList() {
      const { rows, total } = await userList(this.queryParams);
      this.tableData = rows;
      this.total = total;
      // userList(this.queryParams).then((res) => {
      //   console.log(res);
      // })
    },
    //  删除
    async handleDelete(id) {
      this.$confirm("确认删除这条数据?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          remove(id).then((res) => {
            if (res.code == 200) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              setTimeout(() => {
                this.getList();
              }, 1000);
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
      console.log(id);
    },
    // async handleDelete(id){
    //      try{
    //    await this.$confirm("确定删除吗?");
    //   //  await delDynList(id);
    //    this.listDynList();
    //    this.$message.success("删除成功");
    //  }catch(error){
    //   //  console.log("error",error);
    //  }
    // },
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
        margin-left: 20px;
        // text-align: center;
        margin-bottom: 20px;
        width: 120px;
        float: left;
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