// 动态管理
<template>
  <div class="advertisement">
    <!-- 广告管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">动态管理</div>
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
              placeholder="请输入艺术家姓名"
              style="width: 200px"
              size="small"
            ></el-input>
          </el-form-item>
          <el-form-item label="发布平台" prop="issueType">
            <el-select
              v-model="queryParams.issueType"
              size="small"
              placeholder="请选择"
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
          <el-form-item label="发布时间" prop="createTime">
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
              size="small"
              type="warning"
              @click="handleQuery"
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
          @click="$router.push(`/dynamic/dynamic/add-dyn`)"
          >添加</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于发布时间 -->
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
              type="index"
              key="number"
            >
            </el-table-column>
            <el-table-column
              prop="userName"
              label="艺术家姓名"
              key="userName"
              
            >
            </el-table-column>
            <el-table-column
              prop="userAccount"
              label="艺术家账号"
              width="200"
              
              key="userAccount"
            >
            </el-table-column>
            <el-table-column
              prop="issueTypeName"
              label="发布平台"
              width="190"
              
              key="issueTypeName"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="发布时间"
              width="200"
              sortable="custom"
              key="createTime"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="{ row }">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/dynamic/dynamic/editor-dyn${row.id}`)"
                  >编辑</el-button
                ><!-- + scope.row.id -->
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
import { listDynList, delDynList } from "@/api/adynamic/dynamic";
export default {
  data() {
    return {
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      options: [
        {
          value: "",
          label: "全部",
        },
        {
          value: "1",
          label: "前端",
        },
        {
          value: "2",
          label: "后台",
        },
      ],
      // 发布时间
      createTime: [],
      queryParams: {
        remark: "", //请输入艺术家姓名
        issueTypeName: "", //发布平台
        startTime: "", //开始时间
        endTime: "", //结束时间
        pageSize: 10,
        pageNum: 1,
      
      },
      tableData: [],
    };
  },
  created() {
    this.listDynList(); //获取列表
  },
  // 监听事件删除后选择空
  watch: {
    createTime(newVal, oldVal) {
      // console.log("newVal,oldVal", newVal, oldVal);
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
        this.interface();
      } else {
        
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.interface();
      }
    },
    //  获取列表数据
    async listDynList() {
      const { total, rows } = await listDynList({
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
      });
      this.tableData = rows;
      this.total = total;
    },
    // 监听每页条数
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`);
      this.queryParams.pageSize = val;
      this.interface(); //重新获取数据接口
    },
    // 监听页码
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      // console.log(`当前页: ${val}`);
      this.interface(); //重新获取数据接口
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.id = selection.map((item) => item.id);
    },
    // 删除
    async handleDelete(id) {
      try {
        await this.$confirm("确定删除吗?");
        await delDynList(id);
        this.interface();
        this.$message.success("删除成功");
      } catch (error) {
        //  console.log("error",error);
      }
    },
    //查询
    async handleQuery() {
      this.queryParams.pageNum = 1;
      //  console.log("startTime",this.createTime[0]);
      //  console.log("endTime",this.createTime[1]);
      this.interface();
    },
    async interface() {
      await listDynList({
        remark: this.queryParams.remark,
        issueType: this.queryParams.issueType,
        startTime: this.createTime[0],
        endTime: this.createTime[1],
        pageSize: this.queryParams.pageSize,
        pageNum: this.queryParams.pageNum,
        params: this.queryParams.params
      }).then((res) => {
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