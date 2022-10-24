// 投稿管理
// 待解决:表头上下箭头、排序位可编辑方框、状态表格里状态不同显示颜色不同
<template>
  <div class="advertisement">
    <!-- 投稿管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">投稿管理</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card-div">投稿信息</div>
      <div class="card2">
        <div class="card2-div">
          <el-row>
            <el-col :span="8"
              ><div class="grid-content bg-purple">
                <span class="">投稿人名称: {{ ruleForm.userName }}</span>
              </div></el-col
            >
            <el-col :span="8"
              ><div class="grid-content bg-purple-light">
                <span>当前状态:{{ ruleForm.checkStateName }}</span>
              </div></el-col
            >
          </el-row>
        </div>
        <div class="card2-div">
          <el-row>
            <el-col :span="8"
              ><div class="grid-content bg-purple">
                <span>投稿人账号:{{ ruleForm.userAccount }}</span>
              </div></el-col
            >
            <el-col :span="8"
              ><div class="grid-content bg-purple-light">
                <span>投稿时间:{{ ruleForm.createTime }}</span>
              </div></el-col
            >
          </el-row>
        </div>
      </div>
    </el-card>
    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card3">
        <el-form
          :model="ruleForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="内容" prop="content">
            <!-- <quill-editor
              v-model="ruleForm.content"
              class="editor"
            ></quill-editor> -->
            <el-input type="textarea" v-model="ruleForm.content"></el-input>
          </el-form-item>
          <el-form-item label="图片" prop="">
            <el-upload
              :file-list="fileList"
              :action="uploadUrl"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              :on-success="handleSuccess"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="" />
            </el-dialog>
          </el-form-item>
          <div>
            <el-form-item label="相关专辑" prop="">
              <div
                style="
                  border: 1px solid #c0c0c0;
                  width: 400px;
                  min-height: 40px;
                  float: left;
                "
              >
                <el-tag
                  v-model="ruleForm.next"
                  v-for="tag in tags"
                  :key="tag.id"
                  closable
                  :type="tag.type"
                  style="margin: 5px"
                  @close="handleClose(tag)"
                >
                  {{ tag.albumName }}
                </el-tag>
              </div>
              <div
                style="
                  border: 1px solid #c0c0c0;
                  width: 200px;
                  float: left;
                  margin-left: 10px;
                "
              >
                <el-input
                  class="card2-input"
                  @input="inputChange(ruleForm.link)"
                  v-model="ruleForm.link"
                  placeholder="搜索专辑"
                  style="width: 150px; margin: 5px"
                ></el-input>
                <ul class="ulStyle">
                  <li v-for="(item, index) in albumList" :key="index">
                    <p>{{ item.albumName }}</p>
                    <span @click="addClick(item)">＋</span>
                  </li>
                </ul>
              </div>
            </el-form-item>
          </div>

          <el-form-item label="" prop="checked">
            <el-checkbox v-model="checked" style="color: red">停用</el-checkbox>
          </el-form-item>
          <el-form-item label="" prop="">
            <el-button class="card3-btn" type="primary" @click="submitClick"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getInfo, edit } from "@/api/contribution/contribution";
import { userList } from "@/api/album/album";
export default {
  data() {
    return {
      fileList: [],
      dialogImageUrl: "",
      dialogVisible: false,
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      content: null,
      // 最近标签
      tags: [
        // { albumName: "aa", type: "" },
        // { albumName: "bb", type: "" },
      ],
      imgUrlList: "",
      checked: false, //停用
      ruleForm: {
        content: "", //富文本
        next: "", //相关专辑
        link: "", //文章外链
      },
      albumList: [],
    };
  },
  created() {
    this.getInfo();
    this.userList();
  },
  methods: {
    submitClick() {
      let albumOfIds = [];
      for (let key in this.tags) {
        albumOfIds.push(this.tags[key].id);
      }
      let res = {
        content: this.ruleForm.content,
        albumIds: albumOfIds,
        imgUrlList: this.imgUrlList,
        id: this.ruleForm.id,
      };
      if (this.checked) {
        res.checkState = 4;
      }
      this.getSave(res);
    },
    async getSave(data) {
      // console.log(params);
      let { code, msg } = await edit(data);
      if (code == 200) {
        this.$message({
          message: msg,
          type: "success",
        });
        this.$router.push({
          path: "/content/contribution/index",
        });
      }
    },
    handleRemove(file, fileList) {
      // console.log(file, fileList);
      let res = [];
      for (let key in fileList) {
        if (fileList[key].response) {
          res.push({
            url: fileList[key].response.url,
          });
        } else {
          res.push({
            url: fileList[key].url,
          });
        }
      }
      this.imgUrlList = res;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleSuccess(response, file, fileList) {
      // console.log(fileList);
      let res = [];
      for (let key in fileList) {
        if (fileList[key].response) {
          res.push({
            url: fileList[key].response.url,
          });
        } else {
          res.push({
            url: fileList[key].url,
          });
        }
      }
      this.imgUrlList = res;
    },
    inputChange(val) {
      this.userList({ albumName: val });
    },
    addClick(val) {
      let res = this.tags;
      let flag = true;
      for (let key in res) {
        if (res[key].id == val.id) {
          flag = false;
        }
      }
      if (flag) {
        this.tags.push(val);
      } else {
        this.$message("标签重复");
      }
    },
    async userList(val) {
      const { rows } = await userList(val);
      this.albumList = rows;
    },
    handleClose(tag) {
      this.tags.splice(this.tags.indexOf(tag), 1);
    },
    async getInfo() {
      const { data } = await getInfo(this.$route.query.id);
      // console.log(data);
      this.ruleForm = data;
      this.tags = data.albums ? data.albums : [];
      this.fileList = data.imgUrlList;
      let res = [];
      for (let key in data.imgUrlList) {
        res.push({
          url: data.imgUrlList[key].url,
        });
      }
      this.imgUrlList = res;
    },
  },
};
</script>

<style lang="scss" scoped>
li {
  list-style: none;
  text-indent: 10px;
}
ul {
  padding: 0;
}
.ulStyle {
  height: 200px;
  overflow: auto;
  li {
    display: flex;
    align-items: center;
    height: 35px;
    span {
      display: block;
      height: 20px;
      line-height: 10px;
      padding: 5px 8px;
      background-color: skyblue;
      text-align: center;
      text-indent: 0;
      border-radius: 50px;
      cursor: pointer;
      margin-left: 20px;
    }
  }
}
.advertisement {
  .box-card {
    .card-div {
      font-size: 14px/28px "";
      height: 30px;
      margin-bottom: 20px;
    }
    .card1 {
      height: 24px;
      font: 18px/24px "";
      color: orange;
      padding-left: 10px;
      border-left: 5px solid orange;
    }
    .card2 {
      .card2-div {
        margin-top: 10px;
        margin-bottom: 10px;
      }
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
      ::v-deep .ql-toolbar {
        background-color: rgb(243, 241, 241);
      }
      // height: 400px;
      ::v-deep .el-table th {
        background-color: rgb(216, 215, 215);
        color: black;
        font: 16px/20px "Microsoft Yahei";
        text-align: center;
      }
      ::v-deep .cell {
        text-align: center;
      }
      .card3-div {
        font-size: 30px;
        font-weight: 900;
      }
      .block {
        margin-top: 20px;
        margin-bottom: 20px;
        float: right;
        ::v-deep .active {
          background-color: orange;
        }
      }
      .editor {
        // height: 300px;
      }
      .card3-btn {
        width: 100px;
      }
    }
  }
}
</style>