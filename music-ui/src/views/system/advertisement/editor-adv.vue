// 添加广告管理
<template>
  <div class="advertisement">
    <!-- 广告管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">编辑广告</div>
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
          <el-form-item label="广告名称" prop="advName">
            <el-input class="card2-input" v-model="ruleForm.advName"></el-input>
          </el-form-item>
          <el-form-item label="投放位置" prop="advLocation">
            <el-select
              class="card2-input"
              v-model="ruleForm.advLocation"
              placeholder="请选择"
            >
              <el-option
                v-for="item in advLocation"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <!-- TODO: 添加图片-->
          <el-form-item label="添加图片" prop="advImgUrl">
           <el-upload
              class="avatar-uploader"
              action=""
              :http-request="httpRequest"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              v-model="ruleForm.advImgUrl"
            >
              <img v-if="ruleForm.advImgUrl" :src="ruleForm.advImgUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <!-- TODO: -->
          <el-form-item label="链接" prop="advUrl">
            <el-input
              class="card2-input1"
              placeholder="请输入内容"
              v-model="ruleForm.advUrl"
            >
              <template slot="append">选择站内文章</template>
            </el-input>
          </el-form-item>

          <el-form-item label="显示" prop="advShow">
            <el-radio-group v-model="ruleForm.advShow">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="2">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="排序" prop="advSort">
            <el-input
              class="card2-input"
              placeholder="数值越大排序越靠前"
              v-model="ruleForm.advSort"
              @input="ruleForm.advSort = ruleForm.advSort.replace(/[^\d]/g, '')"
            ></el-input>
          </el-form-item>

          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >提交</el-button
            >
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getInAdvList, editAdvList } from "@/api/advertisement/ment";
import { addImg } from "@/api/album/album";
import { getToken } from "@/utils/auth";
export default {
  data() {
    return {
      advLocation: [
        {
          value: 1,
          label: "欢迎界面",
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
      ruleForm: {
        advName: "", //广告名称*
        advLocation: "", //投放位置
        advImgUrl: "", //添加图片*
        advUrl: "", //链接
        advShow: "", //显示*
        advSort: "", //排序*
      },
      rules: {
        advName: [
          { required: true, message: "请输入广告名称", trigger: "blur" },
        ],
        advLocation: [
          { required: true, message: "请选择投放位置", trigger: "blur" },
        ],
        advImgUrl: [{ required: true, message: "请选择图片", trigger: "blur" }],
        advShow: [
          { required: true, message: "请选择是否显示", trigger: "blur" },
        ],
        advSort: [
          { required: true, message: "请输入排序数值", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getInAdvList(this.$route.params.id);
    this.id = this.$route.params.id; //获取单个广告信息
  },

  methods: {
    async getInAdvList() {
      const { data } = await getInAdvList(this.$route.params.id);
      this.ruleForm = data;

    },
    // 规定图片上传规范
    // handleAvatarSuccess(res, file) {

    //   this.imageUrl = process.env.VUE_APP_BASE_API + res.fileName;
    //   if (res.code == 200) {
    //     this.imageUrl = res.url;
    //     this.ruleForm.advImgUrl = res.url;
    //   } else {
    //     this.$message.error("图片插入失败");
    //   }
    // },
     handleAvatarSuccess(res, file) {
      console.log(res.data, file);
      this.dialogImageUrl = URL.createObjectURL(file.raw);
      // this.dialogImageUrl = file.response.url;
      this.ruleForm.imgUrl = res.data;
    },
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
        this.ruleForm.advImgUrl = res.data;
        this.imageUrl = res.data;
      });
    },
    //提交
    async submitForm(ruleForm) {
      const { res } = await editAdvList({
        id: this.id, //让新修改的数据覆盖原数据
        advName: this.ruleForm.advName, //广告名称*
        advImgUrl: this.ruleForm.advImgUrl, //添加图片*
        advLocation: this.ruleForm.advLocation, //投放位置
        advUrl: this.ruleForm.advUrl, //链接
        advShow: this.ruleForm.advShow, //显示*
        advSort: this.ruleForm.advSort, //排序*
      });
      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$router.push(`/operation/advertisement/index`);
        } else {
          console.log("未提交成功!!");
          return false;
        }
      });
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
    }
  }
}
</style>
