// 投稿管理
<template>
  <div class="advertisement">
    <!-- 投稿管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">投稿管理</div>
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
              size="small"
              placeholder="请输入投稿人名称/账号/相关专辑名"
              style="width: 300px"
            ></el-input>
          </el-form-item>
          <el-form-item label="投稿状态" prop="checkState">
            <el-select
              v-model="queryParams.checkState"
              placeholder="请选择"
              size="small"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
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
    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <div class="card3-tab">
          <!-- 排序基于投稿时间 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'time', order: 'descending' }"
          >
            <el-table-column fixed type="index" label="序号"> </el-table-column>
            <el-table-column
              prop="title"
              label="投稿标题"
              width="150"
              sortable
              key="title"
              v-if="columns[1].visible"
            >
            </el-table-column>
            <el-table-column
              prop="albumNames"
              label="相关专辑"
              width="120"
              key="albumNames"
              v-if="columns[2].visible"
            >
            </el-table-column>
            <el-table-column
              prop="userName"
              label="投稿人名称"
              width="130"
              sortable
              key="userName"
              v-if="columns[3].visible"
            >
            </el-table-column>
            <el-table-column
              prop="userAccount"
              label="投稿人账号"
              width="200"
              sortable
              key="userAccount"
              v-if="columns[4].visible"
            >
            </el-table-column>
            <el-table-column
              prop="readNum"
              label="阅读量"
              width="150"
              sortable
              key="readNum"
              v-if="columns[5].visible"
            >
            </el-table-column>
            <el-table-column
              prop="checkStateName"
              label="状态"
              sortable
              key="checkStateName"
              v-if="columns[6].visible"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="投稿时间"
              width="160"
              sortable
              key="createTime"
              v-if="columns[7].visible"
            >
            </el-table-column>
            <el-table-column
              prop="checkTime"
              label="审核时间"
              width="160"
              sortable
              key="checkTime"
              v-if="columns[8].visible"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="scope">
                <el-button
                  type="text"
                  style="color: orange"
                  @click="
                    $router.push(
                      `/contribution/contribution/look-con?id=` + scope.row.id
                    )
                  "
                  >查看</el-button
                ><!-- + scope.row.id -->
                <el-button
                  type="text"
                  style="color: orange"
                  @click="
                    $router.push(
                      `/contribution/contribution/editor-con?id=` + scope.row.id
                    )
                  "
                  >编辑</el-button
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
import { getlist } from "@/api/contribution/contribution";
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
          value: "1",
          label: "待审核",
        },
        {
          value: "2",
          label: "已拒绝",
        },
        {
          value: "3",
          label: "已通过",
        },
        {
          value: "4",
          label: "已停用",
        },
      ],
      queryParams: {
        remark: undefined, //请输入投稿名称/账号
        checkState: undefined, //投稿状态
        pageSize: 10,
        pageNum: 1,
      },
      // 列信息
      columns: [
        { key: 0, label: `序号`, visible: true },
        { key: 1, label: `投稿标题`, visible: true },
        { key: 2, label: `展示位置`, visible: true },
        { key: 3, label: `投稿人名称`, visible: true },
        { key: 4, label: `投稿人账号`, visible: true },
        { key: 5, label: `阅读量`, visible: true },
        { key: 6, label: `状态`, visible: true },
        { key: 7, label: `投稿时间`, visible: true },
        { key: 8, label: `审核时间`, visible: true },
      ],
      tableData: [
        // {
        //   number: "number",
        //   title: "title",
        //   location: "location",
        //   name: "name",
        //   account: "account",
        //   read: "read",
        //   locationvalue: "locationvalue", //状态
        //   time: "time",
        //   timed: "2021-09-09 12:22:21",
        // },
      ],
    };
  },
  created() {
    this.getlist();
  },
  methods: {
    //获取数据
    async getlist() {
      const { rows, total } = await getlist(this.queryParams);
      this.tableData = rows;
      this.total = total;
      // console.log(rows);
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getlist();
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getlist();
      // console.log(`当前页: ${val}`);
    },
    //查询
    handleQuery() {
      // console.log("查询");
      this.queryParams.pageNum = 1;
      this.getlist();
    },
    // formatter(row, column) {
    //       return row.address;
    //     }
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