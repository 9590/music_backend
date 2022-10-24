// 优惠券发放管理
<template>
  <div class="advertisement">
    <!-- 优惠券发放管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">优惠券发放管理</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称/账号"
          size="small"
          style="width: 250px"
        ></el-input>
        <span class="card2-sp">
          <span class="card2-sp-span">优惠券类型</span>
          <el-select
            v-model="queryParams.type"
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
        </span>
        <span class="card2-sp">
          <span class="card2-sp-span">发放时间</span>
          <div class="block" style="display: inline-block">
            <el-date-picker
              v-model="queryParams.value"
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
        <div style="height: 15px"></div>
        <span class="card2-sp">
          <span class="card2-sp-span">优惠券状态</span>
          <el-select
            v-model="queryParams.state"
            placeholder="请选择"
            size="small"
          >
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </span>
        <span class="card2-sp">
          <span class="card2-sp-span">截至时间</span>
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
          size="small"
          @click="handleQuery"
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
          @click="
            $router.push(`/coupondistribution/coupondistribution/add-coup`)
          "
          >添加</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于发放时间 -->
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
              width="180"
              key="number"
              v-if="columns[0].visible"
            >
            </el-table-column>
            <el-table-column
              prop="name"
              label="优惠券名称"
              sortable
              key="name"
              v-if="columns[1].visible"
            >
            </el-table-column>
            <el-table-column
              prop="account"
              label="发放账号"
              width="200"
              sortable
              key="account"
              v-if="columns[2].visible"
            >
            </el-table-column>
            <el-table-column
              prop="type"
              label="优惠券类型"
              width="130"
              sortable
              key="type"
              v-if="columns[3].visible"
            >
            </el-table-column>
            <el-table-column
              prop="time"
              label="发放时间"
              width="200"
              sortable
              key="time"
              v-if="columns[4].visible"
            >
            </el-table-column>
            <el-table-column
              prop="timed"
              label="截止时间"
              width="200"
              sortable
              key="timed"
              v-if="columns[5].visible"
            >
            </el-table-column>
            <el-table-column
              prop="state"
              label="状态"
              width="150"
              sortable
              key="state"
              v-if="columns[6].visible"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template>
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
      // 优惠券类型
      options: [
        {
          value: "0",
          label: "全部",
        },
        {
          value: "1",
          label: "单首曲目",
        },
        {
          value: "2",
          label: "整张专辑",
        },
      ],
      // 优惠券状态
      option: [
        {
          value: "0",
          label: "全部",
        },
        {
          value: "1",
          label: "已使用",
        },
        {
          value: "2",
          label: "已过期",
        },
        {
          value: "3",
          label: "未使用",
        },
      ],
      queryParams: {
        name: undefined, //请输入名称/账号
        type: undefined, //优惠券类型
        value: undefined, //发放时间
        state: undefined, //优惠券状态
        value1: undefined, //截至时间
        pageSize: 10,
        pageNum: 1,
      },
      columns: [
        { key: 0, label: `序号`, visible: true },
        { key: 1, label: `优惠券名称`, visible: true },
        { key: 2, label: `发放账号`, visible: true },
        { key: 3, label: `优惠券类型`, visible: true },
        { key: 4, label: `发放时间`, visible: true },
        { key: 5, label: `截止时间`, visible: true },
        { key: 6, label: `状态`, visible: true },
      ],
      tableData: [
        {
          number: "number",
          name: "name",
          account: "account",
          type: "type",
          time: "time",
          timed: "timed",
          state: "state",
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
        margin-bottom: 20px;
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