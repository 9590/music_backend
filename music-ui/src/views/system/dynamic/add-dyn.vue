// 编辑动态管理
<template>
  <div class="advertisement">
    <!-- 动态管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">添加动态</div>
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
              :action="uploadUrl"
              accept=".jpg,.jif,.png"
              list-type="picture-card"
              :on-success="handlePictureCardPreview"
              :limit="10"
              :on-remove="handleRemove"
              v-model="ruleForm.imgUrls"
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
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="ruleForm.imgUrls" alt="" />
            </el-dialog>
          </el-form-item>
          <!-- <el-form-item label="添加视频" prop="videoUrl">
            <el-upload
              :action="uploadUrl"
              accept=".MP4,.AVI,.rm,.wmv"
              list-type="picture-card"
              :on-success="handleVideoCardPreview"
              :on-remove="handleRemoves"
              v-model="ruleForm.videoUrl"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <video width="100%" :src="ruleForm.videoUrl" alt="" />
            </el-dialog>
          </el-form-item> -->
          <el-form-item label="添加视频" prop="videoUrl">
            <!-- action必选参数, 上传的地址 -->
            <el-upload
              class="avatar-uploader el-upload--text"
              :action="uploadUrl"
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
              >添加</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { addDynList, UserDynList } from "@/api/adynamic/dynamic";
import { getToken } from "@/utils/auth";
export default {
  data() {
    return {
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      // headers: {
      //   Authorization: "Bearer " + getToken(),
      // },
      // 艺人
      isDisplayList: [
        // {id:"",
        //  userName:"输入艺术家姓名/账号检索",
        // }
      ],
      videoFlag: false,
      dialogVisible: false,
      videoUrl: [],
      ruleForm: {
        userId: [], //选择艺术家
        content: "", //动态内容
        imgUrls: [], //添加图片
        videoUrl: [], //添加视频
      },
      rules: {
        userId: [
          { required: true, message: "请输入艺术家姓名/账号", trigger: "blur" },
        ],
        content: [
          { required: true, message: "请填写动态内容", trigger: "blur" },
        ],
      },
      videoUploadPercent: 0,
    };
  },
  created() {
    this.UserDynList(); //艺人信息
  },
  methods: {
    // 获取艺人并赋值
    async UserDynList() {
      // await UserDynList().then((res) => {
      //   console.log("res", res);
      //   // this.isDisplayList=[...this.isDisplayList,...res.data];
      //   // console.log("this.isDisplayList",this.isDisplayList);
      // });
      const { data } = await UserDynList();
      this.isDisplayList = data;
    },
    // 图片上传数组
    handlePictureCardPreview(file) {
      // console.log("file", file);
      if (file.code == 200) {
        delete file.code;
        delete file.msg;
        delete file.fileName;
        this.ruleForm.imgUrls.push(file);
        // this.ruleForm.videoUrl.push(file);
      }
      // console.log("this.ruleForm.imgUrls", this.ruleForm.imgUrls);
      //  console.log("this.ruleForm.videoUrl",this.ruleForm.videoUrl);
      this.dialogVisible = false;
    },
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
    // uploadVideoProcess(event, file, fileList) {
    //   this.videoFlag = true;
    //   this.videoUploadPercent = file.percentage.toFixed(0);
    // },
    handleVideoSuccess(res, file) {
      //获取上传视频地址
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      if (res.code == 200) {
        this.videoUrl = res.url;
        this.ruleForm.videoUrl.push({ url: res.url });
      } else {
        this.$message.error("添加视频失败，请重新上传！");
      }
    },
    // 图片移除
    handleRemove(file) {
      console.log("file", file);
      let s = file.response;
      for (var i = 0; i <= this.ruleForm.imgUrls.length; i++) {
        if (this.ruleForm.imgUrls[i] === s) {
          this.ruleForm.imgUrls.splice(i, 1);
        }
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
    // 提交
    async submitForm(ruleForm) {
      if (this.ruleForm.imgUrls.length == 0) {
        delete this.ruleForm.imgUrls;
      }
      if (this.ruleForm.videoUrl.length == 0) {
        delete this.ruleForm.videoUrl;
      }
      await addDynList(this.ruleForm);
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push(`/content/dynamic/index`);
        } else {
          return false;
        }
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
      .card2-input {
        width: 400px;
      }
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
    }
  }
}
</style>