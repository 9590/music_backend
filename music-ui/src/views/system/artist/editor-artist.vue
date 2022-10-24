// 添加艺术家管理
<template>
  <div class="advertisement">
    <!-- 艺术家管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">艺术家管理</div>
    </el-card>

    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="账号" prop="userAccount">
            <el-input class="card2-input" v-model="form.userAccount"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              type="password"
              class="card2-input"
              v-model="form.password"
            ></el-input>
          </el-form-item>
          <el-form-item label="名称" prop="userName">
            <el-input class="card2-input" v-model="form.userName"></el-input>
          </el-form-item>
          <el-form-item label="头像" prop="userAvatar">
            <!-- <el-upload
              action="https://jsonplaceholder.typicode.com/posts/"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove"
              v-model="form.userAvatar"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="" />
            </el-dialog> -->
            <el-upload
              class="avatar-uploader"
              :action="uploadUrl"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              v-model="form.userAvatar"
            >
              <img v-if="dialogImageUrl" :src="dialogImageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="简介" prop="remark">
            <el-input
              type="textarea"
              :rows="6"
              placeholder="请输入内容"
              class="card2-input"
              v-model="form.remark"
            >
            </el-input>
          </el-form-item>
          <!-- <el-form-item label="参与专辑" prop="albumIds">
            <el-select
              filterable
              v-model="form.albumIds"
              multiple
              placeholder="请选择"
            >
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.albumName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item> -->
          <el-form-item>
            <el-button class="card2-btn" type="primary" @click="onSubmit"
              >保存</el-button
            >
            <!-- <el-button class="card2-btn">取消</el-button> -->
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { userList } from "@/api/album/album";
import { getActorInfo, updataActor } from "@/api/artist/artist";
export default {
  data() {
    return {
      opKey: 0,
      // 头像上传
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      dialogImageUrl: "",
      dialogVisible: false,
      form: {
        userAccount: "",
        password: "",
        userName: "",
        userAvatar: "",
        remark: "",
        // albumIds: [],
        id: "",
      },
      value: "",
      // options: [],
      rules: {
        userAccount: [
          {
            required: true,
            message: "请输入账号",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
        userName: [
          {
            required: true,
            message: "请输入名称",
            trigger: "blur",
          },
        ],
        remark: [
          {
            required: true,
            message: "请输入简介",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.userList();
  },
  methods: {
    async getActorInfo() {
      const { data } = await getActorInfo(this.$route.query.id);
      console.log("data", data);
      this.dialogImageUrl = data.userAvatar;
      let res = this.form;
      for (let key in res) {
        if (key != "albumIds") {
          res[key] = data[key];
        } else {
          for (let item in data[key]) {
            res[key].push(data[key][item].id);
          }
        }
      }
      console.log(this.form);
    },
    async userList() {
      //获取专辑列表
      // const { rows } = await userList();
      // console.log("专辑", rows);
      // this.options = rows;
      this.getActorInfo();
    },
    onSubmit() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          console.log(this.form);
          this.updataActor();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    async updataActor() {
      const { code } = await updataActor(this.form);
      if (code == 200) {
        this.$message({
          message: "修改成功",
          type: "success",
        });
        setTimeout(() => {
          this.$router.push({
            path: "/all/artist/index",
          });
        }, 1000);
      }
    },
    handleAvatarSuccess(res, file) {
      this.dialogImageUrl = res.url;
      this.form.userAvatar = res.url;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
  },
};
</script>
<style>
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
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
    }
  }
}
</style>