// 广告管理
<template>
  <div class="advertisement">
    <!-- 广告管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">广告管理</div>
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
          <el-form-item label="" prop="advName">
            <el-input
              v-model="queryParams.advName"
              placeholder="请输入广告名称"
              clearable
              size="small"
              style="width: 200px"
            ></el-input>
          </el-form-item>
          <el-form-item label="广告位置" prop="advLocation">
            <el-select v-model="queryParams.advLocation" size="small">
              <el-option
                v-for="item in positionList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="前台显示" prop="advShow">
            <el-select
              v-model="queryParams.advShow"
              placeholder="请选择"
              size="small"
            >
              <el-option
                v-for="item in isDisplayList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
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
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          icon="el-icon-plus"
          type="warning"
          size="small"
          @click="$router.push(`/advertisement/advertisement/add-adv`)"
          >新增广告图</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于操作时间 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'createTime', order: 'descending' }"
            @selection-change="handleSelectionChange"
            @sort-change="sortChange"
          >
            <el-table-column fixed label="序号" width="80" type="index">
            </el-table-column>
            <el-table-column prop="advName" label="名称"  key="advName">
            </el-table-column>
            <el-table-column
              prop="advLocation"
              label="广告位置"
              width="140"
              key="advLocation"
            >
            </el-table-column>
            <el-table-column
              prop="advShow"
              label="前台显示"
              width="120"
              key="advShow"
              sortable="custom"
            >
            </el-table-column>
            <el-table-column
              prop="advSort"
              label="排序位"
              width="100"
              key="advSort"
              sortable="custom"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="操作时间"
              width="180"
              key="createTime"
              sortable="custom"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="160">
              <template slot-scope="{ row }">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="
                    $router.push(
                      `/advertisement/advertisement/editor-adv${row.id}`
                    )
                  "
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
import { delAdvList, listAdvList } from "@/api/advertisement/ment";
export default {
  data() {
    return {
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 广告位置
      positionList: [
        {
          value: "",
          label: "全部",
        },
        {
          value: 1,
          label: "欢迎首页",
        },
        {
          value: 2,
          label: "首页轮播图",
        },
        {
          value: 3,
          label: "商城轮播图",
        },
      ],
      // 前台显示
      isDisplayList: [
        {
          value: "1",
          label: "是",
        },
        {
          value: "2",
          label: "否",
        },
      ],
      queryParams: {
        advName: "", //广告名称
        advLocation: "", //广告位置
        advShow: "", //前台显示
        pageSize: 10,
        pageNum: 1,
      },
      tableData: [
        {
          id: "", //序号
          advName: "", //名称
          advLocation: "", //广告位置
          advShow: "", //前台显示
          advSort: "", //排序位
          createTime: "", //操作时间
        },
      ],
    };
  },
  created() {
    this.listAdvList(); // 获取广告列表
  },
  methods: {
    sortChange({ column, prop, order }) {
      if (order == null) {
        this.queryParams = {

          pageSize: 10,
          pageNum: 1,
        };
        this.listAdvList();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.listAdvList();
      }
    },
    // 获取表格数据
    async listAdvList() {
      const { total, rows } = await listAdvList(this.queryParams);
      this.tableData = rows;
      this.total = total;
    },
    // 监听每页条数
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.listAdvList(); //重新获取数据接口
    },
    // 监听页码
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.listAdvList(); //重新获取数据接口
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.id = selection.map((item) => item.id);
    },
    //查询按钮
    async handleQuery() {
      this.queryParams.pageNum = 1;
      await listAdvList(this.queryParams).then((res) => {
        this.tableData = res.rows;
        this.total = res.total;
      });
    },
    //删除
    async handleDelete(id) {
      try {
        await this.$confirm("您确定删除该广告吗");
        await delAdvList(id);
        this.listAdvList();
        this.$message.success("删除广告成功");
      } catch (error) {
        this.$message.error(error);
      }
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
        margin-left: 10px;
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
      }
      .block {
        margin-top: 20px;
        margin-bottom: 20px;
        float: right;
        // ::v-deep .number{
        //   background-color: orange;
        //   // color: palegreen;
        // }
        ::v-deep .active {
          background-color: orange;
        }
      }
    }
  }
}
</style>
