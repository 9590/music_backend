// 内容管理
<template>
  <div class="advertisement">
    <!-- 内容管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">内容管理</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          style="width: 250px"
          size="small"
        ></el-input>
        <span class="card2-sp">
          <span class="card2-sp-span">是否置顶</span>
          <el-select
            v-model="queryParams.top"
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
        </span>
        <span class="card2-sp">
          <span class="card2-sp-span">发布时间</span>
          <div class="block" style="display: inline-block">
            <el-date-picker
              v-model="queryParams.value1"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="width: 370px"
              size="small"
            >
            </el-date-picker>
          </div>
        </span>
        <el-button
          class="card2-btn"
          type="warning"
          @click="handleQuery"
          size="small"
          >查询</el-button
        >
      </div>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/content/content/add-cont`)"
          >添加</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于发布时间 -->
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
              v-if="columns[0].visible"
            >
            </el-table-column>
            <el-table-column
              prop="title"
              label="标题"
              sortable
              key="title"
              v-if="columns[1].visible"
            >
            </el-table-column>
            <el-table-column
              prop="location"
              label="展示位置"
              width="120"
              sortable
              key="location"
              v-if="columns[2].visible"
            >
            </el-table-column>
            <el-table-column
              prop="time"
              label="发布时间"
              width="300"
              sortable
              key="time"
              v-if="columns[3].visible"
            >
            </el-table-column>
            <el-table-column
              prop="top"
              label="置顶"
              width="120"
              sortable
              key="top"
              v-if="columns[4].visible"
            >
            </el-table-column>
            <el-table-column
              prop="reading"
              label="阅读量"
              width="120"
              sortable
              key="reading"
              v-if="columns[5].visible"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template>
                <!-- slot-scope="scope" -->
                <el-button type="text" size="small" style="color: orange"
                  >置顶</el-button
                >
                <!--  @click.stop="handleTop(scope.$index, scope.row)"
              v-if="scope.$index != 0" -->
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/content/content/editor-cont`)"
                  >编辑</el-button
                >
                <el-popconfirm
                  title="确认删除吗?删除后不可恢复"
                  @onConfirm="del([row.id])"
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
          </el-pagination>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>

export default {
  methods: {
    //  // 置顶
    // handleTop(index, row, type) {
    //   this.handleTopDown(index, row, type, "top");
    // },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    //查询
    handleQuery() {
      console.log("查询");
      this.queryParams.pageNum = 1;
      this.getList();
    },
  },
  data() {
    return {
      total: 0,
      // currentPage4: 4,
      options: [
        {
          value: "0",
          label: "是",
        },
        {
          value: "1",
          label: "否",
        },
      ],
      queryParams: {
        title: undefined, //请输入标题
        top: undefined, //是否置顶
        value1: undefined, //发布时间
        pageSize: 10,
        pageNum: 1,
      },
      columns: [
        { key: 0, label: `序号`, visible: true },
        { key: 1, label: `标题`, visible: true },
        { key: 2, label: `展示位置`, visible: true },
        { key: 3, label: `发布时间`, visible: true },
        { key: 4, label: `置顶`, visible: true },
        { key: 5, label: `阅读量`, visible: true },
      ],
      tableData: [
        {
          number: "number",
          title: "title",
          location: "location",
          time: "time",
          top: "top",
          reading: "reading",
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