// 文章管理
<template>
  <div class="advertisement">
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">文章管理</div>
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
          <el-form-item label="" prop="articleTitile">
            <el-input
              v-model="queryParams.articleTitile"
              placeholder="请输入标题"
              clearable
              size="small"
              style="width: 200px"
            ></el-input>
          </el-form-item>
          <el-form-item label="是否置顶" prop="articleIsTop">
            <el-select v-model="queryParams.articleIsTop" size="small">
              <el-option
                v-for="item in positionList"
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
    <!-- ----------------------- -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/article/article/add-art`)"
          >添加</el-button
        >
        <!--  -->
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
            <el-table-column fixed label="序号" width="180" type="index">
            </el-table-column>
            <el-table-column
              prop="articleTypeName"
              label="分类"
              sortable="custom"
              key="articleTypeName"
            >
            </el-table-column>
            <el-table-column
              prop="articleTitile"
              label="标题"
              width="120"
              sortable="custom"
              key="articleTitile"
            >
            </el-table-column>
            <el-table-column
              prop="albumNames"
              label="相关专辑"
              width="120"
              
              key="albumNames"
            >
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="发布时间"
              width="100"
              key="createTime"
              sortable="custom"
            >
            </el-table-column>
            <el-table-column
              prop="articleIsTopName"
              label="置顶"
              width="150"
              key="articleIsTopName"
              sortable="custom"
            >
            </el-table-column>
            <el-table-column
              prop="readNum"
              label="阅读量"
              width="150"
              key="readNum"
              sortable="custom"
            >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="130">
              <template slot-scope="{ row }">
                <!-- <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  >置顶</el-button
                > -->
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  v-if="row.articleIsTop == 1"
                  @click="Topping2(row.id)"
                  >取消置顶</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  v-if="row.articleIsTop == 0"
                  @click="Topping1(row.id)"
                  >置顶</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/article/article/editor-art${row.id}`)"
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
            <!-- 
            v-show="total>0"判断当total大于0时页码显示 -->
          </el-pagination>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import {
  listArticleList,
  delArticleList,
  Topping,
} from "@/api/article/article";
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
          value: "1",
          label: "是",
        },
        {
          value: "0",
          label: "否",
        },
      ],
      // 发布时间
      createTime: [],
      queryParams: {
        articleTitile: "", //文章标题
        articleIsTop: "", //置顶
        startTime: "", //发布开始时间
        endTime: "", //发布结束时间
        pageSize: 10,
        pageNum: 1,
      },

      tableData: [
        {
          articleTypeName: "", //分类
          articleTitile: "", //标题
          albumNames: "", //相关专辑
          createTime: "", //发布时间
          articleIsTopName: "", //置顶
          readNum: "", //阅读量
        },
      ],
    };
  },
  created() {
    this.listArticleList(); // 获取广告列表
  },
  // 监听时间删除后选择空
  watch: {
    createTime(newVal, oldVal) {
      console.log(newVal, oldVal);
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
        this.listArticleList();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.listArticleList();
      }
    },
    // 获取表格数据
    async listArticleList() {
      const { total, rows } = await listArticleList(this.queryParams);
      this.tableData = rows;
      this.total = total;
    },
    changePage(newPage) {
      // newPage是当前点击的页码
      this.page.page = newPage; // 将当前页码赋值给当前的最新页码
      this.listArticleList();
    },
    // 监听每页条数
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.listArticleList(); //重新获取数据接口
    },
    // 监听页码
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.listArticleList(); //重新获取数据接口
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.id = selection.map((item) => item.id);
    },
    //查询按钮
    async handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryParams.startTime = this.createTime[0];
      this.queryParams.endTime = this.createTime[1];
      this.listArticleList();
    },
    // //  // 置顶
    async Topping1(id) {
      console.log("id", id);
      // return
      try {
        await this.$confirm("确定置顶吗？");
        await Topping(id);
        this.listArticleList();
        this.$message.success("置顶成功");
      } catch (error) {
        console.log(error);
      }
    },
    async Topping2(id) {
      try {
        await this.$confirm("确定取消置顶吗？");
        await Topping(id);
        this.listArticleList();
        this.$message.success("取消置顶成功");
      } catch (error) {
        console.log(error);
      }
    },
    //删除
    async handleDelete(id) {
      try {
        await this.$confirm("您确定删除该文章吗");
        await delArticleList(id);
        this.listArticleList();
        this.$message.success("删除文章成功");
      } catch (error) {
        console.log(error);
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
        width: 100px;
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