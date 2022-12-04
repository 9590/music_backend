// 添加商品管理
<template>
  <div class="advertisement">
    <!-- 商品管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">商品管理</div>
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
          <el-form-item label="商品名称" prop="shopName">
            <el-input
              class="card2-input"
              v-model="ruleForm.shopName"
              placeholder="输入商品名称"
            ></el-input>
          </el-form-item>
          <el-form-item label="商品编号" prop="shopNumber">
            <el-input
              class="card2-input"
              v-model="ruleForm.shopNumber"
              placeholder="输入商品编号"
              @input="
                ruleForm.shopNumber = ruleForm.shopNumber.replace(
                  /[^\d\-\a-zA-Z]/g,
                  ''
                )
              "
            ></el-input>
          </el-form-item>
          <el-form-item label="选择分类" prop="classifyName">
            <el-select
              class="card2-input"
              v-model="ruleForm.classifyId"
              placeholder="请选择商品分类"
            >
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.classifyName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="价格" prop="shopPrice">
            <el-input
              class="card2-input"
              v-model="ruleForm.shopPrice"
              placeholder="输入价格"
              @input="priceChange"
            ></el-input>
          </el-form-item>
          <el-form-item label="轮播图" prop="shopImgs">
            <el-upload
            :headers="headers"
              :action="uploadUrl"
              :data="{fileType:fileType}"
              list-type="picture-card"
              :on-success="handlePictureCardPreview"
              :on-remove="handleRemove"
              :limit="10"
              :file-list="ruleForm.shopImgs"
              :show-file-list="true"
            >
              <i class="el-icon-plus"></i>
              <div class="el-upload__tip" slot="tip" style="font-weight: bold">
                建议尺寸大小为320*640,图片不超过10张
              </div>
            </el-upload>

            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="ruleForm.shopImgs" alt="" />
            </el-dialog>
          </el-form-item>
          <el-form-item label="添加视频" prop="videoUrl">
            <el-upload
              :headers="headers"
              class="avatar-uploader el-upload--text"
              :action="uploadUrl"
              :data="{fileType:fileType}"
              :show-file-list="false"
              :on-success="handleVideoSuccess"
              :before-upload="beforeUploadVideo"
              :on-remove="handleRemoves">
              <video
                v-if="ruleForm.videoUrl != [] && videoFlag == false"
                :src="videoUrl"
                class="avatar"
                controls="controls"
              >
                您的浏览器不支持视频播放
              </video>
              <i
                v-else-if="ruleForm.videoUrl == [] && videoFlag == false"
                class="el-icon-plus avatar-uploader-icon"
              ></i>
              <el-progress
                v-if="videoFlag == true"
                type="circle"
                :percentage="videoUploadPercent"
                style="margin-top: 30px"
              ></el-progress>
            </el-upload>
          </el-form-item>
          <el-form-item label="添加内容" prop="shopContent">
            <quill-editor
              v-model="ruleForm.shopContent"
              class="editor"
            ></quill-editor>
          </el-form-item>
          <el-form-item label="是否置顶" prop="shopIsTop" class="card2-item">
            <el-radio-group v-model="ruleForm.shopIsTop">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="购买链接" prop="shopUrl">
            <el-input class="card2-input" v-model="ruleForm.shopUrl"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import {
  manAdd,
  classIfication,
  manEdit,
  editAdd,
} from "@/api/amallManagement/amallManagement";
import { getToken } from "@/utils/auth";
import Cookies from "js-cookie";
export default {
  data() {
    return {
      videoUploadPercent: 0,
      fileType:'jpg',
      videoFlag: false,
      uploadUrl: process.env.VUE_APP_BASE_API + "/system/album/addImg", // 上传的图片服务器地址
      headers: {
        Authorization: Cookies.get("Admin-Token")
      },
      dialogVisible: false,
      disabled: false,
      input2: "",
      options: [], //分类
      classifyType: 1,
      videoUrl:[],
      ruleForm: {
        shopName: "", //商品名称
        shopNumber: "", //商品编号
        shopImgs: [], //轮播图
        classifyName: "", //分类id
        shopPrice: "", //价格
        shopContent: "", //富文本内容
        shopIsTop: "", //是否顶置
        shopUrl: "", //购买链接
        classifyId: "",
        videoUrl:[]
      },
      rules: {
        shopName: [
          { required: true, message: "请输入商品名称", trigger: "blur" },
          // { min: 0, message: '最小排序为0', trigger: 'blur' }
        ],
        shopNumber: [
          { required: true, message: "请输入商品编号", trigger: "blur" },
        ],
        shopImgs: [
          { required: true, message: "请放入至少两张图片", trigger: "blur" },
        ],
        classifyName: [
          { required: true, message: "请选择商品分类", trigger: "change" },
        ],
        shopPrice: [{ required: true, message: "请输入价格", trigger: "blur" }],
        shopIsTop: [
          { required: true, message: "请选择是否置顶", trigger: "blur" },
        ],
        shopUrl: [{ required: true, message: "请输入链接", trigger: "blur" }],
        shopContent: [
          { required: true, message: "请输入内容", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    // 分类下拉数据
    this.classIfication();
    this.manEdit(this.$route.params.id);
  },
  methods: {
    // 添加视频
    handleVideoCardPreview(file) {
      console.log("file", file);
      if (file.code == 200) {
        delete file.code;
        delete file.msg;
        delete file.fileName;
        this.ruleForm.videoUrl.push(file);
      }
      console.log("this.ruleForm.videoUrl", this.ruleForm.videoUrl);
      this.dialogVisible = false;
    },
    beforeUploadVideo(file) {
      const isLt10M = file.size / 1024 / 1024 < 20;
      if (
        [
          "video/mp4",
          "video/ogg",
          "video/flv",
          "video/avi",
          "video/wmv",
          "video/rmvb",
        ].indexOf(file.type) == -1
      ) {
        this.$message.error("请上传正确的视频格式");
        return false;
      }
      if (!isLt10M) {
        this.$message.error("上传视频大小不能超过20MB哦!");
        return false;
      }
    },
    handleVideoSuccess(res, file) {
      //获取上传视频地址
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      if (res.code == 200) {
        this.videoUrl = res.data;
        this.ruleForm.videoUrl = [];
        this.ruleForm.videoUrl.push({ url: res.data });
      } else {
        this.$message.error("添加视频失败，请重新上传！");
      }
    },
    //视频移除
    handleRemoves(file) {
      console.log("file", file);
      let s = file.response;
      for (var i = 0; i <= this.ruleForm.videoUrl.length; i++) {
        if (this.ruleForm.videoUrl[i] === s) {
          this.ruleForm.videoUrl.splice(i, 1);
        }
      }
    },

    //价格
    priceChange() {
      this.ruleForm.shopPrice = this.ruleForm.shopPrice.replace(/[^\d\.]/g, "");
      this.ruleForm.shopPrice = this.ruleForm.shopPrice.replace(/^\./g, ""); //必须保证第一个为数字而不是.
      this.ruleForm.shopPrice = this.ruleForm.shopPrice.replace(/\.{2,}/g, "."); //保证只有出现一个.而没有多个.
      this.ruleForm.shopPrice = this.ruleForm.shopPrice
        .replace(".", "$#$")
        .replace(/\./g, "")
        .replace("$#$", "."); //保证.只出现一次，而不能出现两次以上
    },
    async manEdit() {
      const { data } = await manEdit(this.$route.params.id);
      // data.shopIsTop = data.shopIsTop.replace(/"/g, '')
      this.ruleForm = data;
    },
    //  图片上传数组
    handlePictureCardPreview(file) {
      if (file.code == 200) {
        delete file.code;
        delete file.msg;
        delete file.fileName;
        this.ruleForm.shopImgs.push({ url: file.data });
      }
      // console.log('上传的图',this.ruleForm.shopImgs);
      this.dialogVisible = false;
    },
    //  图片移除
    handleRemove(file) {
      // console.log('选中',file.url);
      // console.log('移除前的图',this.ruleForm.shopImgs);
      let s = file.url;
      for (var i = 0; i <= this.ruleForm.shopImgs.length; i++) {
        if (this.ruleForm.shopImgs[i].url === s) {
          this.ruleForm.shopImgs.splice(i, 1);
        }
      }
    },
    // 分类下拉数据
    async classIfication() {
      const { data } = await classIfication({
        classifyType: this.classifyType,
      });
      this.options = data;
    },
    async submitForm(ruleForm) {
      const { res } = await editAdd({
        id: this.$route.params.id,
        shopName: this.ruleForm.shopName, //商品名称
        shopNumber: this.ruleForm.shopNumber, //商品编号
        shopImgs: this.ruleForm.shopImgs, //轮播图
        classifyId: this.ruleForm.classifyId, //分类id
        shopPrice: this.ruleForm.shopPrice, //价格
        shopContent: this.ruleForm.shopContent, //富文本内容
        shopIsTop: this.ruleForm.shopIsTop, //是否顶置
        shopUrl: this.ruleForm.shopUrl, //购买链接
        videoUrl:this.ruleForm.videoUrl
      });
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push(`/mall/goods/index`);
        } else {
          this.$message.error("未提交成功!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
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
