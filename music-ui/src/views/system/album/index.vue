// 专辑管理
<template>
  <div class="advertisement">
    <!-- 曲目管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">专辑管理</div>
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
          <el-form-item label="" prop="">
            <el-input
              v-model="queryParams.albumName"
              placeholder="请输入艺术家名称/专辑名称"
              size="small"
              style="width: 330px; margin-bottom: 10px"
            ></el-input>
          </el-form-item>
          <el-form-item label="专辑分类" prop="">
            <el-select
              v-model="upResName"
              clearable
              placeholder="请选择"
              @clear="handleClear"
              ref="selectUpResId"
            >
              <el-option
                hidden
                key="options.number"
                :value="options.number"
                :label="options.name"
              >
              </el-option>
              <el-tree
                :data="options"
                :props="{ value: 'number', label: 'name' }"
                :expand-on-click-node="false"
                :check-on-click-node="true"
                @node-click="handleNodeClick"
              >
              </el-tree>
            </el-select>
          </el-form-item>
          <el-form-item label="发行时间" prop="createTime">
            <el-date-picker
              v-model="createTime"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="width: 370px"
              size="small"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
            >
            </el-date-picker>
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
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <!-- TODO: -->
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/album/album/add-alb`)"
          >添加</el-button
        >
        <!-- TODO: -->
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="handleImport"
          v-hasPermi="['system:user:import']"
          >批量导入</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于发行时间 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'time', order: 'descending' }"
            @sort-change="sortChange"
          >
            <el-table-column fixed label="序号" width="180" type="index">
            </el-table-column>
            <el-table-column
              prop="albumName"
              label="专辑名称"
              sortable="custom"
              key="albumName"
            >
            </el-table-column>
            <el-table-column
              prop="participateArtist"
              label="主要艺人"
              key="participateArtist"
            >
            </el-table-column>
            <el-table-column
              prop="albumClassifyName"
              label="所属分类"
              key="albumClassifyName"
            >
            </el-table-column>
            <el-table-column
              prop="issueTime"
              label="发行时间"
              sortable="custom"
              key="issueTime"
            >
            </el-table-column>
            <el-table-column
              prop="albumPrice"
              label="专辑价格"
              sortable="custom"
              key="albumPrice"
            >
            </el-table-column>

            <el-table-column fixed="right" label="操作" width="150">
              <template slot-scope="{ row }">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/album/album/editor-alb${row.id}`)"
                  >编辑</el-button
                >
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="handleDelete(row.id)"
                  >删除</el-button
                >
                <!-- <el-popconfirm
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
                </el-popconfirm> -->
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
    <!-- 用户导入对话框 -->
    <el-dialog
      :title="upload.title"
      :visible.sync="upload.open"
      width="400px"
      append-to-body
    >
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <span style="margin-right: 124px"
          ><a href="/static/execl/pldrzj.xlsx" download="批量导入专辑模板.xlsx"
            >下载模板</a
          ></span
        >
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { userList, getTreeList, remove } from "@/api/album/album";
import { getToken } from "@/utils/auth";
export default {
  data() {
    return {
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      value: [],
      options: [
        // {
        //   value: "",
        //   label: "全部",
        // },
      ],
      upResName: "",
      createTime: 0, //发行时间
      queryParams: {
        albumName: undefined, //请输入艺术家名称/专辑名称
        albumClassifyId: undefined, //专辑分类
        startTime: undefined, //发布开始时间
        endTime: undefined, //发布结束时间
        pageSize: 10,
        pageNum: 1,
      },
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // // 是否更新已经存在的用户数据
        // updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/album/importData",
      },

      tableData: [],
    };
  },
  created() {
    this.userList();
    this.getTreeList();
  },
  watch: {
    createTime(newVal, oldVal) {
      if (newVal == null) {
        this.createTime = [];
      }
    },
  },
  methods: {
    sortChange({ column, prop, order }) {
      if (order == null) {
        this.queryParams = {

          pageSize: 10,
          pageNum: 1,
        };
        this.userList();
      } else {
        this.queryParams.params = { name: prop, sort: "" };
        if (order == "descending") {
          this.queryParams.params.sort = "desc";
        } else {
          this.queryParams.params.sort = "asc";
        }
        this.queryParams.pageNum = 1;
        this.userList();
      }
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(
        "<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" +
          response.msg +
          "</div>",
        "导入结果",
        { dangerouslyUseHTMLString: true }
      );
      this.userList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    //删除
    async handleDelete(id) {
      try {
        await this.$confirm("您确定要删除该专辑吗 ");
        await remove(id);
        this.userList();
      } catch (error) {
        this.$message.error(error);
      }
    },
    handleNodeClick(data) {
      // console.log(data);
      this.upResName = data.name;
      this.queryParams.albumClassifyId = data.id;
      // 选择器执行完成后，使其失去焦点隐藏下拉框的效果
      this.$refs.selectUpResId.blur();
    },
    handleClear() {
      // 将选择器的值置空
      this.upResName = "";
      this.queryParams.albumClassifyId = "";
    },
    handleChange(value) {
      // this.queryParams.albumClassifyId = value;
    },
    async getTreeList() {
      const { data } = await getTreeList();
      this.options = data;
    },
    async userList() {
      const { total, rows } = await userList(this.queryParams);
      this.total = total;
      this.tableData = rows;
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.userList();
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.userList();
      // console.log(`当前页: ${val}`);
    },
    //查询
    async handleQuery() {
      // console.log("查询");
      this.queryParams.pageNum = 1;
      this.queryParams.startTime = this.createTime[0];
      this.queryParams.endTime = this.createTime[1];
      // console.log(this.queryParams);
      await userList(this.queryParams).then((res) => {
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
        margin-right: 20px;
        .card2-sp-span {
          margin-left: 10px;
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
        margin-right: 20px;
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