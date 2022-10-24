// 乐曲管理
<template>
  <div class="advertisement">
    <!-- 曲目管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">曲目管理</div>
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
          <el-form-item label="" prop="songName">
            <el-input
              v-model="queryParams.songName"
              placeholder="请输入曲目名称"
              style="width: 300px; margin-bottom: 10px"
              size="small"
            ></el-input>
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
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-button
          class="card3-btn"
          type="warning"
          size="small"
          @click="$router.push(`/tracks/tracks/add-tack`)"
          >添加</el-button
        >
        <el-button
          class="card3-btn"
          type="warning"
          @click="handleImport"
          size="small"
          v-hasPermi="['system:user:import']"
          >批量导入</el-button
        >
        <div class="card3-tab">
          <!-- 排序基于曲目名称 -->
          <el-table
            :data="tableData"
            border
            style="width: 100%"
            :default-sort="{ prop: 'name', order: 'descending' }"
            @sort-change="sortChange"
          >
            <el-table-column fixed label="序号" width="180" type="index">
            </el-table-column>
            <el-table-column
              prop="songName"
              label="曲目名称"
              sortable="custom"
              key="songName"
            >
            </el-table-column>
            <el-table-column
              prop="albums"
              label="所属专辑"
              key="albums"
            >
            </el-table-column>

            <el-table-column
              prop="songPrecision"
              label="音频"
              key="songPrecision"
            >
            </el-table-column>

            <!-- <el-table-column
              prop="audio"
              label="曲目时长"
              sortable
              key="audio"
            >
            </el-table-column
            >
            <el-table-column
              prop="UPM"
              label="UPM价格"
              sortable
              key="UPM"
            >
            </el-table-column
            > -->
            <el-table-column fixed="right" label="操作" width="200">
              <template slot-scope="{ row }">
                <el-button
                  type="text"
                  size="small"
                  style="color: orange"
                  @click="$router.push(`/tracks/tracks/editor-tack${row.id}`)"
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
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            background
          >
          </el-pagination>
        </div>
      </div>
    </el-card>
    <!--  -->
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
          ><a href="/static/execl/pldrqm.xlsx" download="批量导入曲目模板.xlsx"
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
import { getList, remove } from "@/api/tracks/tracks";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "User",
  dicts: ["sys_normal_disable", "sys_user_sex"],
  components: { Treeselect },
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
    async getList() {
      const { total, rows } = await getList(this.queryParams);
      this.tableData = rows;
      this.total = total;
      // console.log(total, rows);
    },
    handleSizeChange(val) {
      this.queryParams.pageSize = val;
      this.getList();
      // console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.queryParams.pageNum = val;
      this.getList();
      // console.log(`当前页: ${val}`);
    },
    //查询
    async handleQuery() {
      // console.log("查询");
      this.queryParams.pageNum = 1;
      await getList(this.queryParams).then((res) => {
        this.tableData = res.rows;
        this.total = res.total;
      });
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
      this.getList();
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
        this.getList();
      } catch (error) {
        // this.$message.error(error);
      }
    },
  },
  data() {
    return {
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // currentPage4: 4,
      options: [
        {
          value: "0",
          label: "全部",
        },
        {
          value: "1",
          label: "单首曲mu",
        },
        {
          value: "2",
          label: "整张专辑",
        },
      ],
      // locationvalue: "",
      queryParams: {
        songName: undefined, //请输入艺术家名称/歌曲名称/专辑名称
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
        url: process.env.VUE_APP_BASE_API + "/system/song/importData",
      },

      tableData: [
        // {
        //   number: "number",
        //   name: "name",
        //   names:"names",
        //   album: "album",
        //   audio: "audio",
        //   UPM:"UPM",
        // },
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