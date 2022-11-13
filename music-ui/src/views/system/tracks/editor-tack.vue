// 添加分类管理
<template>
  <div class="advertisement">
    <!-- 分类管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">编辑乐曲</div>
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
          <el-form-item label="乐曲名称" prop="songName">
            <el-input
              class="card2-input"
              v-model="ruleForm.songName"
            ></el-input>
          </el-form-item>
          <el-form-item label="英文名称" prop="songEnglistName">
            <el-input
              class="card2-input"
              v-model="ruleForm.songEnglistName"
            ></el-input>
          </el-form-item>
          <el-form-item label="乐曲编号" prop="songNumber">
            <el-input
              class="card2-input"
              v-model="ruleForm.songNumber"
              placeholder="例如：HRP7188AG-01"
              @input="
                ruleForm.songNumber = ruleForm.songNumber.replace(
                  /[^\d\-\a-zA-Z]/g,
                  ''
                )
              "
            ></el-input>
          </el-form-item>
          <el-form-item label="ISRC" prop="songIsrc">
            <el-input
              class="card2-input"
              v-model="ruleForm.songIsrc"
              placeholder="例如：CN-F18-19-02182"
              @input="
                ruleForm.songIsrc = ruleForm.songIsrc.replace(
                  /[^\d\-\a-zA-Z]/g,
                  ''
                )
              "
            ></el-input>
          </el-form-item>
          <!-- <el-form-item label="乐曲时长" prop="music">
            <el-input
              class="card2-input"
              v-model="ruleForm.music"
              placeholder="例如：12:21"
            ></el-input>
          </el-form-item> -->
          <el-form-item label="乐曲时长" prop="songTimeMin">
            <el-input
              class="card2-input2"
              v-model="ruleForm.songTimeMin"
              placeholder=""
              maxlength="2"
              type="number"
              min="0"
            ></el-input>
            分
            <el-input
              class="card2-input2"
              v-model="ruleForm.songTimeSec"
              placeholder=""
              type="number"
              min="0"
              max="59"
              maxlength="2"
            ></el-input>
            秒
          </el-form-item>
          <el-form-item label="所属专辑" prop="region">
            <el-select
              class="card2-input"
              v-model="ruleForm.region"
              placeholder="请选择"
            >
              <el-option label="专辑一" value="a"></el-option>
              <el-option label="专辑二" value="b"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="资料" prop="songData">
            <el-input
              type="textarea"
              class="card2-input"
              :rows="6"
              placeholder="请输入内容"
              v-model="ruleForm.songData"
            >
            </el-input>
          </el-form-item>
          <!-- <el-form-item label="UPM价格" prop="VIP">
            <el-input class="card2-input" v-model="ruleForm.VIP"></el-input>
          </el-form-item> -->
          <el-form-item label="UPM价格" prop="upmPrice">
            <el-input
              class="card2-input3"
              v-model="ruleForm.upmPrice"
              type="number"
              min="0"
            ></el-input>
            元
          </el-form-item>
          <!-- <el-form-item label="非会员价格" prop="NVIP">
            <el-input class="card2-input" v-model="ruleForm.NVIP" ></el-input>
          </el-form-item> -->
          <el-form-item label="歌词" prop="songLyricUrl">
            <el-input
              type="textarea"
              class="card2-input"
              :rows="6"
              placeholder="请输入内容"
              v-model="ruleForm.songLyricUrl"
            >
            </el-input>
          </el-form-item>
          <!-- <el-form-item label="歌词" prop="songLyricUrl">
            <el-upload
              class="upload-demo"
              drag
              action="https://jsonplaceholder.typicode.com/posts/"
              v-model="ruleForm.songLyricUrl"
              multiple
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将文件拖到此处，或<em>点击上传</em>
              </div>
              <div class="el-upload__tip" slot="tip">只能上传xxxx文件，且不超过XXXX</div>
            </el-upload>
          </el-form-item> -->
          <el-form-item label="音频精度" prop="songPre">
            <el-checkbox-group v-model="song.songPre">
              <el-checkbox label="SQ" name="songPre" disabled></el-checkbox>
              <el-checkbox label="UPM" name="songPre" disabled></el-checkbox>
              <!-- <el-checkbox label="VIP" name="songPre"></el-checkbox> -->
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="上传SQ音频" prop="sqUrl"
            ><span style="color: #975252">(高格式MP3文件，上传成功后SQ自动打钩，原文件将被覆盖)</span>
            <el-upload
              class="upload-demo"
              :action="uploadFile"
              v-model="ruleForm.sqUrl"
              :on-success="handleAvatarSuccess"
              :on-preview="clickHasUploadFile"
              :on-remove="handleAvatarRemove"
              :limit="1"
              :file-list="sqUrl"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="上传UPM音频" prop="upmUrl"
            ><span style="color: #975252">(flac文件，上传成功后UPM自动打钩，原文件将被覆盖)</span>
            <el-upload
              class="upload-demo"
              :action="uploadFile"
              v-model="ruleForm.upmUrl"
              :on-success="handleAvatarSuccess2"
              :on-preview="clickHasUploadFile"
              :on-remove="handleAvatarRemove2"
              :limit="1"
              :file-list="upmUrl"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >保存</el-button
            >
            <!-- <el-button class="card2-btn" @click="resetForm('ruleForm')">重置</el-button> -->
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getInfo, edit } from "@/api/tracks/tracks";
export default {
  data() {
    return {
      uploadFile: process.env.VUE_APP_BASE_API + "/common/upload",
      options: [
        {
          value: "艺人1",
          label: "艺人1",
        },
        {
          value: "艺人2",
          label: "艺人2",
        },
        {
          value: "艺人3",
          label: "艺人3",
        },
      ],
      //sq路径
      sqUrl: [],
      //upm路径
      upmUrl: [],
      //音频精度
      song: {
        songPre: [],
      },
      ruleForm: {
        songName: "",
        songEnglistName: "",
        // music: "",
        // time: "",
        songPrecision: [],
        sqUrl: "",
        songLyricUrl: "",
        // VIP: "",
        songData: "",
        songNumber: "",
        songIsrc: "",
        //乐曲时长
        //分
        songTimeMin: "",
        //秒
        songTimeSec: "",
        upmPrice: "",
        upmUrl: "",
        id: this.$route.params.id,
      },
      rules: {
        upmPrice: [
          { required: true, message: "请输乐曲价格", trigger: "blur" },
        ],
        songName: [
          { required: true, message: "请输入乐曲名称", trigger: "blur" },
        ],
        // songTimeMin: [
        //  { required: true, message: "请输入乐曲时长", trigger: "blur" },
        // ],
        time: [{ required: true, message: "请选择时间", trigger: "blur" }],
        songNumber: [
          { required: true, message: "请输入乐曲编号", trigger: "change" },
        ],
        songTimeMin: [
          { required: true, message: "请输入乐曲时长", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getInfo();
  },
  methods: {
    //获取数据
    async getInfo() {
      const { data } = await getInfo(this.$route.params.id);
      this.ruleForm = data;
      this.song.songPre = data.songPrecision.split(",");
      // console.log(this.ruleForm);
      if (this.ruleForm.sqUrl) {
        let arr = this.ruleForm.sqUrl.split("/");
        let name = arr[arr.length - 1];
        this.sqUrl.push({ name: name, url: this.ruleForm.sqUrl });
      }
      if (this.ruleForm.upmUrl) {
        let arr = this.ruleForm.upmUrl.split("/");
        let name = arr[arr.length - 1];
        this.upmUrl.push({ name: name, url: this.ruleForm.upmUrl });
      }
    },
    async submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.interface();
        } else {
          console.log("error 提交!!");
          return false;
        }
      });
    },
    async interface() {
      await edit({
        songName: this.ruleForm.songName,
        songEnglistName: this.ruleForm.songEnglistName,
        songNumber: this.ruleForm.songNumber,
        songIsrc: this.ruleForm.songIsrc,
        songTimeMin: this.ruleForm.songTimeMin,
        songTimeSec: this.ruleForm.songTimeSec,
        songData: this.ruleForm.songData,
        upmPrice: this.ruleForm.upmPrice,
        songLyricUrl: this.ruleForm.songLyricUrl,
        songPrecision: this.ruleForm.songPrecision,
        sqUrl: this.ruleForm.sqUrl,
        upmUrl: this.ruleForm.upmUrl,
        id: this.$route.params.id,
      }).then((res) => {
        if (res.code == 200) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push("/track/tracks/index");
        }
      });
    },
    handleAvatarSuccess(res, file) {
      this.ruleForm.sqUrl = res.url;
      this.song.songPre.push("SQ");
      this.ruleForm.songPrecision = this.song.songPre.join(",");
    },
    handleAvatarSuccess2(res, file) {
      this.ruleForm.upmUrl = res.url;
      this.song.songPre.push("UPM");
      this.ruleForm.songPrecision = this.song.songPre.join(",");
    },
    handleAvatarRemove(res, file) {
      this.ruleForm.sqUrl = "";
      let pre = this.song.songPre;
      for (let item in pre) {
        if (pre[item] == "SQ") {
          this.song.songPre.splice(item, 1);
        }
      }
      this.ruleForm.songPrecision = this.song.songPre.join(",");
    },
    handleAvatarRemove2(res, file) {
      this.ruleForm.upmUrl = "";
      let pre = this.song.songPre;
      for (let item in pre) {
        if (pre[item] == "UPM") {
          this.song.songPre.splice(item, 1);
        }
      }
      this.ruleForm.songPrecision = this.song.songPre.join(",");
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    clickHasUploadFile(file) {
      // let reader = new FileReader();
      // reader.readAsDataURL(file.raw);
      // reader.onload = () => {
      //   console.log(reader.result);
      // };
      // let URL = window.URL || window.webkitURL;
      // this.linkDownload(URL.createObjectURL(file.url));
      this.linkDownload(file.url);
    },
    linkDownload(url) {
      window.open(url, "_bank");
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
      .card2-input2 {
        width: 80px;
      }
      .card2-input3 {
        width: 100px;
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