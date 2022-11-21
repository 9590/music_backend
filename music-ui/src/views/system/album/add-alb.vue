// 添加专辑管理
<template>
  <div class="advertisement">
    <!-- 专辑管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">添加专辑</div>
    </el-card>

    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm">
          <el-form-item label="专辑名称" prop="albumName">
            <el-input class="card2-input" v-model="ruleForm.albumName"></el-input>
          </el-form-item>
          <el-form-item label="英文名称" prop="albumEnglishName">
            <el-input
              class="card2-input" v-model="ruleForm.albumEnglishName"></el-input>
          </el-form-item>
          <el-form-item label="专辑编号" prop="albumNumber">
            <el-input
              class="card2-input"
              v-model="ruleForm.albumNumber"
              @input="
                ruleForm.albumNumber = ruleForm.albumNumber.replace(
                  /[^\d\-\a-zA-Z]/g,
                  ''
                )
              "
            ></el-input>
          </el-form-item>
          <el-form-item label="EAN码" prop="albumEan">
            <el-input
              class="card2-input"
              v-model="ruleForm.albumEan"
              @input="
                ruleForm.albumEan = ruleForm.albumEan.replace(
                  /[^\d\-\a-zA-Z]/g,
                  ''
                )
              "
            ></el-input>
          </el-form-item>
          <el-form-item label="分类" prop="albumClassifyId">
            <el-select
              v-model="upResName"
              clearable
              placeholder="请选择"
              @clear="handleClear"
              ref="selectUpResId"
            >
              <el-option
                hidden
                key="positionList.number"
                :value="positionList.number"
                :label="positionList.name"
              >
              </el-option>
              <el-tree
                :data="positionList"
                :props="{ value: 'number', label: 'name' }"
                :expand-on-click-node="false"
                :check-on-click-node="true"
                @node-click="handleNodeClick"
              >
              </el-tree>
            </el-select>
            <!-- <el-select
              class="card2-input"
              v-model="ruleForm.albumClassifyId"
              placeholder="请选择"
            >
              <el-option
                v-for="item in positionList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select> -->
          </el-form-item>
          <div class="artStyle">
            <el-form-item label="参与艺人" prop="userIds">
              <el-select
                class="card2-input"
                v-model="ruleForm.userIds"
                multiple
                filterable
                remote
                reserve-keyword
                placeholder="请选择艺人"
              >
                <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.userName"
                  :value="item.id"
                >
                </el-option>
              </el-select>
              <br />
            </el-form-item>

            <div class="artBox">
              <div class="boxTop">
                <el-input
                  @input="artChange"
                  placeholder="请输入内容"
                  v-model="artData"
                >
                  <i slot="prefix" class="el-input__icon el-icon-search"></i>
                </el-input>
              </div>
              <div class="boxBottom">
                <ul>
                  <li v-for="(item, index) in searchData" :key="index">
                    <p>{{ item.artistName }}</p>
                    <span @click="addClick(item)">加入</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <!-- TODO: -->
          <el-form-item label="封面图片" prop="imgUrl">
            <el-upload
              class="avatar-uploader"
              :headers ="header"
              :action="updateImg()"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="dialogImageUrl" :src="dialogImageUrl" class="avatar" alt=""/>
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <span style="color: #c0c0c0">建议尺寸:400*400</span>
          </el-form-item>
          <el-form-item label="发行时间" prop="issueTime">
            <el-date-picker
              class="card2-input"
              v-model="ruleForm.issueTime"
              type="date"
              placeholder="选择日期"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="专辑价格" prop="albumPrice">
            <el-input
              class="card2-input"
              v-model="ruleForm.albumPrice"
              @input="priceChange"
            ></el-input
            ><span style="color: #999"></span>
          </el-form-item>
          <el-form-item label="实体唱片购买链接" prop="buyUrl">
            <el-input class="card2-input" v-model="ruleForm.buyUrl"></el-input
            ><span style="color: #999">(电商链接)</span>
          </el-form-item>

          <el-form-item label="专辑简介" prop="albumIntroduce">
            <el-input
              class="card2-input"
              type="textarea"
              :rows="6"
              placeholder="请输入内容"
              v-model="ruleForm.albumIntroduce"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="录音资料" prop="productionData">
            <el-input
              class="card2-input"
              type="textarea"
              :rows="6"
              placeholder="请输入内容"
              v-model="ruleForm.productionData"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="包含曲目" prop="songIds">
            <el-transfer
              :props="{ key: 'id', label: 'songName' }"
              style="text-align: left; display: inline-block"
              v-model="value"
              filterable
              :left-default-checked="[2, 3]"
              :right-default-checked="[1]"
              :titles="['选择曲目', '已选曲目']"
              :button-texts="['到左边', '到右边']"
              :format="{
                noChecked: '${total}',
                hasChecked: '${checked}/${total}',
              }"
              @change="handleChange"
              :data="data"
              filter-placeholder="请输入歌曲名称"
            >
            </el-transfer>
          </el-form-item>
          <el-form-item>
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >添加</el-button
            >
            <!-- <el-button
            class="card2-btn"
            @click="resetForm('ruleForm')"
              >重置</el-button
            > -->
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { appUserList, songList, add, getTreeList, addImg } from '@/api/album/album'
import Cookies from 'js-cookie'
import { listArtist } from '@/api/album/artist'
export default {
  data() {
    const generateData = (_) => {
      // const data = [];
      // for (let i = 1; i <= 15; i++) {
      //   data.push({
      //     key: i,
      //     label: `备选项 ${i}`,
      //     // disabled: i % 4 === 0
      //   });
      // }
      // return data;
    };
    return {
      header:{},
      //搜索到的数据
      searchData: [],
      //搜索内容需要的
      artData: "",
      uploadUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      // 封面图片
      dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
      //
      data: generateData(),
      value: [1],
      //分类名称
      upResName: "",
      positionList: [],
      options: [],
      list: [],
      loading: false,
      states: [], //这个是element里写了数据的
      ruleForm: {
        albumName: "",
        albumEnglishName: "",
        albumNumber: "",
        albumEan: "",
        imgUrl: "",
        userIds: [],
        issueTime: "",
        buyUrl: "",
        albumClassifyId: "",
        // textarea: "",
        songIds: "",
        albumIntroduce: "",
        productionData: "",
        albumPrice: "",
      },
      rules: {
        albumName: [
          { required: true, message: "请输入专辑名称", trigger: "change" },
        ],
        albumNumber: [
          { required: true, message: "请输入编号", trigger: "change" },
        ],
        songIds: [{ required: true, message: "请添加曲目", trigger: "change" }],
        albumClassifyId: [
          { required: true, message: "请选择分类", trigger: "change" },
        ],
        userIds: [
          { required: true, message: "请选择艺人（多选）", trigger: "change" },
        ],
        issueTime: [{ required: true, message: "请选择时间", trigger: "blur" }],
        upload: [
          { required: true, message: "请选择专辑曲目", trigger: "blur" },
        ],
        song: [{ required: true, message: "请选择本地歌词", trigger: "blur" }],
        imgUrl: [
          { required: true, message: "请上传封面图片", trigger: "change" },
        ],
        albumPrice: [
          { required: true, message: "请输入专辑价格", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.appUserList();
    this.songList();
    this.getTreeList();
    this.artChange();
    this.getArtistList()
    this.header = { Authorization: Cookies.get('Admin-Token') }
  },
  methods: {
    //更新歌曲图片
    updateImg() {
      return `http://119.29.153.101/prod-api/system/album/addImg`
    },
    //加入
    addClick(item) {
      let res = this.ruleForm.userIds;
      if (res.length) {
        let flag = true;
        for (let key in res) {
          if (res[key] == item.id) {
            flag = false;
            this.$message("已经添加过，不能重复添加");
            return;
          }
        }
        if (flag) {
          this.ruleForm.userIds.push(item.id);
          this.$refs.ruleForm.validateField("userIds");
        }
      } else {
        this.ruleForm.userIds.push(item.id);
        this.$refs.ruleForm.validateField("userIds");
      }
    },
    //收缩艺人
    async artChange() {
      let res = [];
      // if (this.artData) {
      const { data } = await appUserList();
      data.map((item) => {
        if (item.userName.search(this.artData) != -1) {
          res.push(item);
        }
      });
      // }
      //this.searchData = res;
    },
    //专辑价格
    priceChange() {
      this.ruleForm.albumPrice = this.ruleForm.albumPrice.replace(
        /[^\d\.]/g,
        ""
      );
      this.ruleForm.albumPrice = this.ruleForm.albumPrice.replace(/^\./g, ""); //必须保证第一个为数字而不是.
      this.ruleForm.albumPrice = this.ruleForm.albumPrice.replace(
        /\.{2,}/g,
        "."
      ); //保证只有出现一个.而没有多个.
      this.ruleForm.albumPrice = this.ruleForm.albumPrice
        .replace(".", "$#$")
        .replace(/\./g, "")
        .replace("$#$", "."); //保证.只出现一次，而不能出现两次以上
    },

    //艺人列表
    async appUserList() {
      let { data } = await appUserList();
      this.options = data;
    },


    /** 查询参与艺人列表 */
    getArtistList() {
      listArtist(this.queryParams).then(response => {
        this.searchData = response.rows;
        console.log(this.searchData)

      });
    },


    async songList() {
      const { data } = await songList();
      this.data = data;
    },
    //分类
    async getTreeList() {
      const { data } = await getTreeList();
      this.positionList = data;
    },
    // 封面图片
    handleAvatarSuccess(res, file) {
      console.log(res.data,file)
      this.dialogImageUrl = URL.createObjectURL(file.raw);
      // this.dialogImageUrl = file.response.url;
      this.ruleForm.imgUrl = res.data;
    },
    beforeAvatarUpload(file) {
      const littleName = file.name.toLowerCase()
      const copyFile = new File([file], littleName)

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

    handleChange(value, direction, movedKeys) {
      this.ruleForm.songIds = value;
      // console.log(value, direction, movedKeys);
    },
    //
    // remoteMethod(query) {
    //   if (query !== "") {
    //     this.loading = true;
    //     setTimeout(() => {
    //       this.loading = false;
    //       this.options = this.list.filter((item) => {
    //         return item.label.toLowerCase().indexOf(query.toLowerCase()) > -1;
    //       });
    //     }, 200);
    //   } else {
    //     this.options = [];
    //   }
    // },
    //分类
    handleClear() {
      // 将选择器的值置空
      this.upResName = "";
      this.ruleForm.albumClassifyId = "";
    },
    handleNodeClick(data) {
      this.upResName = data.name;
      this.ruleForm.albumClassifyId = data.id;
      // 选择器执行完成后，使其失去焦点隐藏下拉框的效果
      this.$refs.selectUpResId.blur();
    },
    submitForm(formName) {
      console.log(this.ruleForm);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.interface();
        } else {
          // console.log("error 提交!!");
          return false;
        }
      });
    },
    async interface() {
      const { code, msg } = await add(this.ruleForm);
      if (code == 200) {
        this.$message({
          message: "提交成功",
          type: "success",
        });
        this.$router.push(`/track/album/index`);
      }
    },
    // resetForm(formName) {
    //   this.$refs[formName].resetFields();
    // },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
  },
  mounted() {
    this.list = this.states.map((item) => {
      return { value: `value:${item}`, label: `label:${item}` };
    });
  },
};
</script>
<style lang="scss" scoped>
li {
  list-style: none;
}
ul {
  padding: 0;
}
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
      ::v-deep .el-transfer-panel {
        border: none;
      }
      ::v-deep .el-transfer-panel__header {
        border-bottom: none;
        background-color: white;
      }
      ::v-deep .el-transfer-panel__body {
        border: 1px solid #333;
      }

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
  border: 1px dashed #d9d9d9;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.artStyle {
  position: relative;
  .artBox {
    z-index: 1000;
    background-color: #fff;
    border: 1px solid #ccc;
    position: absolute;
    width: 300px;
    height: 200px;
    top: 0;
    left: 550px;
    .boxTop {
      :nth-child(1) {
        margin-top: 5px;
        width: 80%;
        height: 30px;
        margin-left: 10px;
      }
      > :nth-child(1) {
        > :nth-child(2) {
          i {
            line-height: 30px;
          }
        }
      }
    }
  }
  .boxBottom {
    width: 100%;
    ul {
      overflow: auto;
      height: 148px;
      li {
        height: 34px;
        line-height: 34px;
        text-indent: 20px;
        p {
          display: inline-block;
          margin: 0;
          text-indent: 0;
        }
        span {
          text-indent: 0;
          display: inline-block;
          background-color: skyblue;
          font-size: 12px;
          line-height: 23px;
          padding: 0 10px;
          margin-left: 20px;
          cursor: pointer;
          border-radius: 30px;
        }
      }
    }
  }
}
</style>
