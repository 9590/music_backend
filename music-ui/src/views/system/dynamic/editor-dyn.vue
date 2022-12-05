// 编辑动态管理
<template>
  <div class="advertisement">
    <!-- 动态管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">编辑动态</div>
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
          <el-form-item label="选择艺术家" prop="userId">
            <el-select
              filterable
              class="card2-input"
              v-model="ruleForm.userId"
              size="small"
              placeholder="输入艺术家姓名/账号检索"
            >
              <el-option
                v-for="item in isDisplayList"
                :key="item.id"
                :label="item.userName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="动态内容" prop="content">
            <el-input
              class="card2-input"
              type="textarea"
              maxlength="300"
              show-word-limit
              :rows="8"
              v-model="ruleForm.content"
            ></el-input>
          </el-form-item>
          <el-form-item label="添加图片" prop="imgUrls">
            <el-upload
            :headers="headers"
              :action="uploadUrl"
              :data="{fileType:fileType}"
              accept=".jpg,.jif,.png"
              list-type="picture-card"
              :on-success="handlePictureCardPreview"
              :limit="10"
              :on-remove="handleRemove"
              v-model="ruleForm.imgUrls"
              :file-list="imageUrl"
              :before-upload="beforeUpload"
            >
              <i class="el-icon-plus"></i>
              <div
                class="el-upload__tip"
                slot="tip"
                style="font-weight: bold; color: #c0c0c0"
              >
                建议尺寸:320*640(最多上传10张)
              </div>
            </el-upload>
            <!-- <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="ruleForm.shopImgs" alt="" />
            </el-dialog> -->
          </el-form-item>

          <el-form-item label="添加视频" prop="videoUrl">
            <!-- action必选参数, 上传的地址 -->
            <el-upload
            :headers="headers"
              class="avatar-uploader el-upload--text"
              :action="uploadUrl"
              :data="{fileType:fileType}"
              :show-file-list="false"
              :on-success="handleVideoSuccess"
              :before-upload="beforeUploadVideo"
              :on-remove="handleRemoves"

            >
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
  addDynList,
  UserDynList,
  getDynList,
  editDynList,
} from "@/api/adynamic/dynamic";
import { getToken } from "@/utils/auth";
import Cookies from "js-cookie";
export default {
  data() {
    return {
      uploadUrl: process.env.VUE_APP_BASE_API + "/system/album/addImg", // 上传的图片服务器地址
      fileType:'jpg',
      headers: {
        Authorization: Cookies.get("Admin-Token")
      },
      //图片路径
      imageUrl: [],
      // 艺人
      isDisplayList: [],
      dialogVisible: false,
      videoFlag: false,
      videoUrl: [],
      ruleForm: {
        userId: [], //选择艺术家
        content: "", //动态内容
        imgUrls: [], //添加图片
        videoUrl: [], //添加视频
      },
      rules: {
        name: [
          { required: true, message: "请输入艺术家姓名/账号", trigger: "blur" },
        ],
        desc: [{ required: true, message: "请填写动态内容", trigger: "blur" }],
      },
    };
  },
  created() {
    this.UserDynList(); //艺人信息
    this.getDynList();
  },
  methods: {
    beforeUpload(file){
      let fileNameLen = file.name.split(".").length;
      this.fileType=file.name.split(".")[fileNameLen - 1]
      console.log(this.fileType)
    },
    // 获取艺人并赋值
    async UserDynList() {
      const { data } = await UserDynList();
      this.isDisplayList = data;
    },
    //查询单个
    async getDynList() {
      const { data } = await getDynList(this.$route.params.id);
      this.ruleForm = data;
      this.ruleForm.imgUrls = data.imgUrlList;
      this.imageUrl = data.imgUrlList;
      console.log(this.ruleForm);
      if (this.ruleForm.videoUrlList) {
        this.videoUrl = this.ruleForm.videoUrlList[0].url;
        this.ruleForm.videoUrl = [];
        this.ruleForm.videoUrl.push({ url: this.ruleForm.videoUrlList[0].url });
      }
    },
    //保存修改
    async editDynList() {
      await editDynList({
        userId: this.ruleForm.userId,
        content: this.ruleForm.content,
        imgUrls: this.ruleForm.imgUrls,
        videoUrl: this.ruleForm.videoUrl,
        id: this.ruleForm.id,
      });
    },
    submitForm(formName) {

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.editDynList();
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push(`/content/dynamic/index`);
        } else {
          console.log("error 提交!!");
          return false;
        }
      });
    },
    handleVideoSuccess(res, file) {

      //获取上传图片地址
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      if (res.code == 200) {
        console.log(res)
        this.videoUrl = res.data;
        this.ruleForm.videoUrl = [];
        this.ruleForm.videoUrl.push({ url: res.data });
      } else {
        this.$message.error("添加视频失败，请重新上传！");
      }
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
    handleRemove(file, fileList) {
      console.log(file, fileList);
      console.log("file", file);
      let s = file.url;
      for (var i = 0; i <= this.ruleForm.imgUrls.length; i++) {
        console.log(this.ruleForm.imgUrls[i],s)
        if (this.ruleForm.imgUrls[i].url === s) {
          this.ruleForm.imgUrls.splice(i, 1);
        }
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.data;
      this.dialogVisible = true;
      this.ruleForm.imgUrls.push({ url: file.data });
    },
    handleVideoCardPreview(file) {
      console.log(file);
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
      .card2-input {
        width: 400px;
      }
      // .card2-input1{
      //     width: 550px;
      // }
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
    }
  }
}
</style>
