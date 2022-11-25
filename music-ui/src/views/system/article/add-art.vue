// 添加文章
<template>
  <div class="advertisement">
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">添加文章</div>
    </el-card>

    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="添加标题" prop="articleTitile">
            <el-input
              class="card2-input"
              v-model="ruleForm.articleTitile"
              placeholder="请输入标题"
            ></el-input>
          </el-form-item>
          <el-form-item label="文章分类" prop="articleType">
            <el-select
              class="card2-input"
              v-model="ruleForm.articleType"
              placeholder="请输入标题"
            >
              <el-option
                v-for="item in advLocations"
                :key="item.id"
                :label="item.classifyName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <div>
            <el-form-item label="标签" prop="articleTag">
              <div style="float: left">
                <el-select
                  v-model="ruleForm.articleTag"
                  multiple
                  filterable
                  allow-create
                  default-first-option
                  placeholder="请输入标签内容"
                  style="width: 200px"
                >
                  <el-option
                    v-for="item in articleTag"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </div>
              <span style="float: left; margin-left: 5px; margin-right: 5px"
                >最近标签:</span
              >
              <div style="border: 1px solid #c0c0c0; width: 300px; float: left">
                <el-tag
                  v-for="tag in tags"
                  :key="tag.name"
                  closable
                  :type="tag.type"
                  style="margin: 5px"
                  @click="tagClick(tag.name)"
                >
                  {{ tag.name }}
                </el-tag>
              </div>
            </el-form-item>
          </div>
          <!-- TODO: 添加图片-->
          <el-form-item label="缩略图" prop="articleImgurl">
            <!-- <el-upload
              class="avatar-uploader"
              
              :action="uploadUrl"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :headers="headers"
              :on-remove="handleRemove"
              v-model="ruleForm.articleImgurl"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <span style="color: #c0c0c0">建议尺寸300*300</span> -->
            <!-- <el-upload
              :action="uploadUrl"
              list-type="picture-card"
              :on-success="handleAvatarSuccess"
              :on-remove="handleRemove"
              v-model="ruleForm.articleImgurl"
              :multiple="false"
              :limit="1"
            >
              <i class="el-icon-plus"></i>
              <div
                class="el-upload__tip"
                slot="tip"
                style="font-weight: bold; color: #c0c0c0"
              >
                建议尺寸大小为300*300
              </div>
            </el-upload> -->
            <el-upload
              class="avatar-uploader"
              action=""
              :http-request="httpRequest"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              v-model="ruleForm.articleImgurl"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <!-- TODO: -->

          <el-form-item label="添加内容" prop="articleContent">
            <quill-editor
              v-model="ruleForm.articleContent"
              class="editor"
            ></quill-editor>
          </el-form-item>
          <el-form-item class="card2-item" label="文章外链" prop="articlePlace">
            <el-input
              class="card2-input"
              v-model="ruleForm.articlePlace"
              placeholder="请输入文章链接,如公众号文章链接"
            ></el-input>
          </el-form-item>
          <div>
            <el-form-item label="相关专辑" prop="albumIds">
              <el-select
                v-model="ruleForm.albumIds"
                multiple
                filterable
                allow-create
                default-first-option
                placeholder="请选择文章标签"
              >
                <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.albumName"
                  :value="item.id"
                >
                </el-option>
              </el-select>
              <!-- <div style="border: 1px solid #c0c0c0; width: 400px; float: left">
                <el-tag
                  v-model="ruleForm.albumIds"
                  v-for="tag in tags"
                  :key="tag.name"
                  closable
                  :type="tag.type"
                  style="margin: 5px"
                >
                  {{ tag.name }}
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
                  v-model="ruleForm.albumIds"
                  placeholder="搜索专辑"
                  style="width: 150px; height: 200px; margin: 5px"
                ></el-input>
              </div> -->
            </el-form-item>
          </div>

          <el-form-item label="置顶" prop="articleIsTop">
            <el-radio-group v-model="ruleForm.articleIsTop">
              <el-radio label="1">是</el-radio>
              <el-radio label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >添加</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { addArticleList } from "@/api/article/article";
import { listClaList } from "@/api/aclassification/classification";
import { userList,addImg } from "@/api/album/album";

export default {
  data() {
    return {
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      // headers: {
      //   Authorization: "Bearer " + getToken(),
      // },
      //相关专辑
      options: [],
      // 文章分类
      advLocations: [
        // {
        //   value: "",
        //   label: "请选择文章分类",
        // },
        // {
        //   value: "1",
        //   label: "乐讯",
        // },
        // {
        //   value: "2",
        //   label: "碟评",
        // },
        // {
        //   value: "3",
        //   label: "闲话发烧",
        // },
      ],
      // 标签
      articleTag: [],
      imageUrl: "", //图片地址
      // 最近标签
      tags: [
        { name: "aa", type: "" },
        { name: "bb", type: "" },
      ],
      ruleForm: {
        articleTitile: "", //添加标题*
        articleType: "", //文章分类
        articleTag: "", //标签
        articleImgurl: "", //添加图片*
        articleContent: "", //富文本添加内容
        articlePlace: "", //文章外链
        albumIds: "", //相关专辑
        articleIsTop: "", //置顶*
      },
      rules: {
        articleTitile: [
          { required: true, message: "请输入标题名称", trigger: "blur" },
        ],
        articleType: [
          { required: true, message: "请选择文章分类", trigger: "blur" },
        ],
        articleImgurl: [
          { required: true, message: "请选择图片", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.listClaList();
    this.userList();
  },
  methods: {
    httpRequest(file) {
      let fileNameLen = file.file.name.split(".").length;
      let data = {
        file: file.file,
        fileType: file.file.name.split(".")[fileNameLen - 1],
        updatePath: "one",
      };
      let formData = new FormData();
      formData.append("file", file.file);
      formData.append("fileType", file.file.name.split(".")[fileNameLen - 1]);
      formData.append("updatePath", "there");

      console.log(formData);
      addImg(formData).then((res) => {
        console.log(res);
        this.ruleForm.articleImgurl = res.data;
        this.imageUrl = res.data;
      });
    },
    tagClick(val) {
      let res = this.ruleForm.articleTag;
      let flag = true;
      for (let key in res) {
        if (res[key] == val) {
          flag = false;
        }
      }
      if (flag) {
        this.ruleForm.articleTag.push(val);
      } else {
        this.$message("标签重复");
      }
    },
    //获取文章分类
    async listClaList() {
      const { rows } = await listClaList({
        classifyType: 3,
      });
      this.advLocations = rows;
    },
    //专辑列表
    async userList() {
      const { rows } = await userList();
      this.options = rows;
    },
    // 规定图片上传规范
    // handleAvatarSuccess(res, file) {
    //   console.log(this.ruleForm.articleImgurl);
    //   console.log("res", res);
    //   console.log("file", file);
    //   console.log(process.env.VUE_APP_BASE_API);
    //   this.imageUrl = process.env.VUE_APP_BASE_API + res.fileName;
    //   if (res.code == 200) {
    //     this.imageUrl = res.url;
    //     this.ruleForm.articleImgurl = res.url;
    //   } else {
    //     this.$message.error("图片插入失败");
    //   }
    // },
    handleAvatarSuccess(res, file) {
      console.log(res.data, file);
      this.dialogImageUrl = URL.createObjectURL(file.raw);
      // this.dialogImageUrl = file.response.url;
      this.imageUrl = res.data;
    },
    // 移除图片
    handleRemove(file) {
      console.log(file);
    },
    // 提交
    async submitForm(ruleForm) {
      if (this.ruleForm.articleTag.length !== 0) {
        await addArticleList(this.ruleForm);
        this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            this.$message({
              message: "提交成功",
              type: "success",
            });
            this.$router.push(`/content/article/index`);
          } else {
            console.log("未提交成功!!");
            return false;
          }
        });
      } else {
        this.$message({
          message: "没有添加标签",
        });
      }
    },
  },
};
</script>

<style >
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
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
      ::v-deep .ql-toolbar {
        background-color: rgb(211, 214, 214);
      }
      .editor {
        height: 200px;
        width: 80%;
        transform: translate(0%, 3%);
      }
      .card2-input {
        width: 400px;
      }
      .card2-input1 {
        width: 550px;
      }
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
      .card2-item {
        margin-top: 160px;
      }
    }
  }
}
</style>