// 添加用户
<template>
  <div class="add">
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">编辑用户</div>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
        >
          <el-form-item label="账号" prop="userAccount" class="card2-el">
            {{ ruleForm.userAccount }}
          </el-form-item>
          <el-form-item label="密码" prop="password" class="card2-el">
            <el-input
              placeholder="请输入密码"
              v-model="ruleForm.password"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="userName" class="card2-el">
            <el-input
              v-model="ruleForm.userName"
              placeholder="请输入昵称 "
            ></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex" class="card2-el">
            <el-select v-model="ruleForm.sex" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="生日" prop="birthday" class="card2-el">
            <el-date-picker
              v-model="ruleForm.birthday"
              align="right"
              type="date"
              placeholder="选择生日"
              :picker-options="pickerOptions"
              value-format="yyyy-MM-dd"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="地区" prop="input5" class="card2-el">
            <!-- <el-input
              v-model="ruleForm.input5"
              placeholder="请输入地区 "
            ></el-input> -->
            <el-cascader
              :key="modalKey"
              ref="treeOfAdress"
              @change="selectChange"
              placeholder="请选择地点"
              :props="adressProps"
              v-model="adressArray"
            />
          </el-form-item>
          <el-form-item
            label="收货地址"
            prop="shippingAddress"
            class="card2-el"
          >
            <el-input
              v-model="ruleForm.shippingAddress"
              placeholder="请输入收货地址 "
            ></el-input>
          </el-form-item>
          <el-form-item label="头像" prop="userAvatar" class="card2-el">
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
          <el-form-item label="个人简介" prop="remark" class="card2-el">
            <el-input
              type="textarea"
              :rows="6"
              placeholder="请输入内容"
              v-model="ruleForm.remark"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="" prop="" class="card2-el">
            <el-button class="card2-btn" type="primary" @click="editClick">
              保存
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>
<script>
import { getToken } from "@/utils/auth";
import { getInfo, edit } from "@/api/userss/userss";
import axios from "axios";

export default {
  data() {
    return {
      adressProps: {
        lazy: true, //可以理解为开关，什么时候结束
        lazyLoad: this.cascaderLazyLoad,
      },
      modalKey: 0,
      //选择地区
      adressArray: ["", ""],
      // 头像上传
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      // headers: {
      //   Authorization: "Bearer " + getToken(),
      // },
      imageUrl: "", //图片地址
      //    性别
      options: [
        {
          value: "2",
          label: "未知",
        },
        {
          value: "0",
          label: "男",
        },
        {
          value: "1",
          label: "女",
        },
      ],
      ruleForm: {
        userAccount: "",
        password: "",
        userName: "",
        sex: "2",
        birthday: "",
        // 省
        cityName: "",
        // 市
        areaName: "",
        shippingAddress: "",
        //头像
        userAvatar: "",
        remark: "",
      },
      rules: {
        // userAccount: [
        //   {
        //     required: true,
        //     message: "请输入账号",
        //     trigger: "blur",
        //   },
        // ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
      },
    };
  },
  created() {
    this.getInfo();
  },
  methods: {
    //保存
    async editClick() {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.getInterface();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    async getInterface() {
      let rule = {
        userAccount: this.ruleForm.userAccount,
        userName: this.ruleForm.userName,
        userAvatar: this.ruleForm.userAvatar,
        password: this.ruleForm.password,
        sex: this.ruleForm.sex,
        birthday: this.ruleForm.birthday,
        shippingAddress: this.ruleForm.shippingAddress,
        cityName: this.ruleForm.cityName,
        areaName: this.ruleForm.areaName,
        remark: this.ruleForm.remark,
        id: this.ruleForm.id,
      };
      const { code } = await edit(rule);
      if (code == 200) {
        this.$message({
          message: "修改成功",
          type: "success",
        });
        setTimeout(() => {
          this.$router.push({
            path: "/all/userss",
          });
        }, 1000);
      }
    },
    //进入界面回显省市数据
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
      console.log("点击", this.adressArray);
    },
    async getInfo() {
      const { data } = await getInfo(this.$route.query.id);
      this.ruleForm = data;
      this.imageUrl = data.userAvatar;
      this.getAdress();
    },
    handleAvatarSuccess(res, file) {
      this.ruleForm.userAvatar = res.url;
      this.imageUrl = process.env.VUE_APP_BASE_API + res.fileName;
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
<style lang="scss" scoped>
.add {
  .box-card {
    .card1 {
      height: 24px;
      font: 18px/24px "";
      color: orange;
      padding-left: 10px;
      border-left: 5px solid orange;
    }
    .card2 {
      .card2-btn {
        width: 100px;
        margin-right: 30px;
      }
      .card2-el {
        width: 50%;
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