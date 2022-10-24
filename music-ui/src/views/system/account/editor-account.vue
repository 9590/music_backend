// 添加艺术家管理
<template>
  <div class="advertisement">
    <!-- 艺术家管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">编辑操作员</div>
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
          <el-form-item label="账号" prop="account">
            <!-- <el-input class="card2-input" v-model="ruleForm.account"></el-input> -->
            {{ ruleForm.account }}
          </el-form-item>
          <el-form-item label="密码" prop="psd">
            <el-input
              type="password"
              class="card2-input"
              v-model="ruleForm.psd"
            ></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="name">
            <el-input class="card2-input" v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="角色" prop="role">
            <el-select
              v-model="ruleForm.role"
              placeholder="请选择"
              class="card3-input"
            >
              <el-option
                v-for="item in options"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId"
                :disabled="item.status == 1"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select
              v-model="ruleForm.sex"
              placeholder="请选择"
              class="card3-input"
            >
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="生日" prop="birthday">
            <el-date-picker
              class="card3-input"
              v-model="ruleForm.birthday"
              align="right"
              type="date"
              placeholder="选择生日"
              :picker-options="pickerOptions"
              value-format="yyyy-MM-dd"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="地区">
            <el-cascader
              :key="modalKey"
              class="card3-input"
              ref="treeOfAdress"
              @change="selectChange"
              placeholder="请选择地点"
              :props="adressProps"
              v-model="adressArray"
            />
          </el-form-item>
          <el-form-item label="头像" prop="userAvatar">
            <!-- :headers="headers" -->
            <el-upload
              v-model="ruleForm.userAvatar"
              class="avatar-uploader"
              :action="uploadUrl"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="个人简介" prop="remark">
            <el-input
              class="card2-input"
              type="textarea"
              :rows="6"
              placeholder="请输入内容"
              v-model="ruleForm.remark"
            >
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >保存</el-button
            >
            <!-- <el-button class="card2-btn" @click="resetForm('ruleForm')"
              >重置</el-button
            > -->
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";
import {
  listUser,
  getUser,
  delUser,
  addUser,
  updateUser,
  resetUserPwd,
  changeUserStatus,
} from "@/api/system/user";
import { getToken } from "@/utils/auth";
import { treeselect } from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
export default {
  name: "User",
  dicts: ["sys_normal_disable", "sys_user_sex"],
  data() {
    return {
      modalKey: 0,
      imageUrl: "",
      // 头像上传
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      //选择地区
      adressArray: ["", ""],
      adressProps: {
        lazy: true, //可以理解为开关，什么时候结束
        lazyLoad: this.cascaderLazyLoad,
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
      },
      options: [],

      ruleForm: {
        account: "",
        name: "",
        region: "",
        role: "",
        psd: "",
        sex: "",
        birthday: "",
        areaName: "",
        cityName: "",
        userAvatar: "",
        remark: "",
        userId: "",
      },
      rules: {
        psd: [{ required: true, message: "请输入密码", trigger: "blur" }],
        account: [{ required: true, message: "请输入账号", trigger: "blur" }],
        role: [{ required: true, message: "请选择角色", trigger: "change" }],
        name: [{ required: true, message: "请输入昵称", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getRoleList();
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = res.url;
      // this.ruleForm.userAvatar = res.fileName;
      this.ruleForm.userAvatar = res.url;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 20;

      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    getRoleList() {
      console.log(this.$route.query.id);
      getUser(this.$route.query.id).then((response) => {
        this.options = response.roles;
        this.ruleForm.areaName = response.data.areaName;
        this.ruleForm.cityName = response.data.cityName;
        this.ruleForm.name = response.data.nickName;
        this.ruleForm.account = response.data.userName;
        this.ruleForm.sex = response.data.sex;
        this.ruleForm.birthday = response.data.birthday;
        this.ruleForm.remark = response.data.remark;
        this.ruleForm.userAvatar = response.data.avatar;
        this.ruleForm.userId = response.data.userId;
        this.ruleForm.psd = response.data.password;
        this.ruleForm.role = response.roleIds[0];
        this.imageUrl = response.data.avatar;
        console.log(this.ruleForm);
        this.getAdress();
      });
    },
    async getAdress() {
      const { data } = await axios({
        url: "/dev-api/common/provinceList",
        method: "get",
      });
      let res = data.data;
      for (let key in res) {
        if (res[key].name == this.ruleForm.cityName) {
          let params = {
            id: res[key].id,
          };
          this.adressArray[0] = res[key].id;
          this.getCity(params);
        }
      }
    },
    async getCity(params) {
      const { data } = await axios({
        url: "/dev-api/common/cityList",
        method: "get",
        params: params,
      });
      let res = data.data;
      for (let key in res) {
        if (res[key].name == this.ruleForm.areaName) {
          this.adressArray[1] = res[key].id;
        }
      }
      this.modalKey++;
    },
    cascaderLazyLoad(node, resolve) {
      if (!node) {
        return false;
      }
      const { level } = node;
      let url = "/dev-api/common/provinceList"; //请求省份的接口
      let params = {};
      //level代表当前点击选择哪一项，，比如0代表第一次进去加载数据，1是选择省后的操作
      if (level == 1) {
        this.ruleForm.cityName = node.label;
        url = "/dev-api/common/cityList"; //换成请求市的接口
        params = {
          id: node.value, //通过省的id查市
        };
      }
      axios({
        url: url,
        method: "get",
        params: params,
      }).then((res) => {
        resolve(
          res.data.data.map((val) => {
            return {
              value: val.id,
              label: val.name,
              leaf: level >= 1, //因为省市二项，所以第二次点击就不用在加载了，所以 >=1
            };
          })
        );
      });
    },
    //选择省市
    selectChange() {
      let nodes = this.$refs["treeOfAdress"].getCheckedNodes()[0].label;
      this.ruleForm.areaName = nodes;
      console.log(this.adressArray);
      console.log(this.ruleForm);
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var a = {
            userName: this.ruleForm.account,
            nickName: this.ruleForm.name,
            password: this.ruleForm.psd,
            sex: this.ruleForm.sex,
            status: "0",
            remark: this.ruleForm.remark,
            avatar: this.ruleForm.userAvatar,
            cityName: this.ruleForm.cityName,
            areaName: this.ruleForm.areaName,
            birthday: this.ruleForm.birthday,
            roleIds: [this.ruleForm.role],
            userId: this.ruleForm.userId,
          };

          updateUser(a).then((response) => {
            this.$modal.msgSuccess("修改成功");
            this.$router.push(`/all/account/index`);
          });
        } else {
          console.log("error 提交!!");
          return false;
        }
      });
    },
    // resetForm(formName) {
    //   this.$refs[formName].resetFields();
    // },
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
      .card3-input {
        width: 200px;
      }
    }
  }
}
</style>
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