// 审核投稿
<template>
  <div class="advertisement">
    <!-- 投稿管理 -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">审核投稿</div>
    </el-card>
    <!-- input&btn -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card-div">投稿信息</div>
      <div class="card2">
        <div class="card2-div">
          <el-row>
            <el-col :span="8"
              ><div class="grid-content bg-purple">
                <span class="">投稿人名称: {{ ruleForm.userName }}</span>
              </div></el-col
            >
            <el-col :span="8"
              ><div class="grid-content bg-purple-light">
                <span>当前状态:{{ ruleForm.checkStateName }}</span>
              </div></el-col
            >
            <el-col :span="8"
              ><div class="grid-content bg-purple">
                <span>相关专辑:{{ ruleForm.albumNames }}</span>
              </div></el-col
            >
          </el-row>
        </div>
        <div class="card2-div">
          <el-row>
            <el-col :span="8"
              ><div class="grid-content bg-purple">
                <span>投稿人账号:{{ ruleForm.userAccount }}</span>
              </div></el-col
            >
            <el-col :span="8"
              ><div class="grid-content bg-purple-light">
                <span>投稿时间:{{ ruleForm.createTime }}</span>
              </div></el-col
            >
          </el-row>
        </div>
      </div>
    </el-card>
    <!--  -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card-div">投稿内容</div>
      <div class="card3">
        <div class="card3-div">{{ ruleForm.title }}</div>
        <div class="card3-div1">{{ ruleForm.content }}</div>
        <div class="imgBox">
          <!-- <span v-for="item in ruleForm.imgUrlList" :key="item.url">{{
            item.url
          }}</span> -->
          <img
            v-for="item in ruleForm.imgUrlList"
            :key="item.url"
            :src="item.url"
            alt=""
          />
        </div>
        <!-- <quill-editor
          ref="myQuillEditor"
          v-model="content"
          :options="editorOption"
          @blur="onEditorBlur($event)"
          @focus="onEditorFocus($event)"
          @ready="onEditorReady($event)"
          class="editor"
        /> -->
      </div>
      <div class="card4">
        <div class="card4-d1">
          <el-button
            class="card4-d1-btn"
            type="primary"
            @click="interfaceClick(3)"
            :disabled="checkState != 1"
            >通过</el-button
          ><el-button
            :disabled="checkState != 1"
            class="card4-d1-btn"
            @click="interfaceClick(2)"
            >拒绝</el-button
          >
        </div>
        <!-- 这个拒绝原因应联动拒绝btn当点击拒绝btn原因为空时提示输入原因，输入后点击拒绝并跳转上一个页面 -->
        <div>
          <span class="card4-span">拒绝原因</span>
        </div>
        <el-input
          type="textarea"
          :rows="4"
          placeholder="请留下您的拒绝原因"
          v-model="reason"
          class="card4-input"
        >
        </el-input>

        <!-- <el-form-item label="活动形式" prop="desc">
    <el-input type="textarea" v-model="desc" style="width:100px"></el-input>
  </el-form-item> -->
      </div>
    </el-card>
  </div>
</template>

<script>
import { getInfo, checkState } from "@/api/contribution/contribution";
// import "quill/dist/quill.core.css";
// import "quill/dist/quill.snow.css";
// import "quill/dist/quill.bubble.css";
// import { quillEditor } from "vue-quill-editor";
export default {
  components: {
    // quillEditor,
  },
  methods: {
    async interfaceClick(val) {
      let res = {
        checkState: val,
        id: this.$route.query.id,
      };
      if (val == 2) {
        res.remark = this.reason;
      }
      const { code, msg } = await checkState(res);
      if (code == 200) {
        this.$message({
          message: msg,
          type: "success",
        });
        this.$router.push({
          path: "/content/contribution/index",
        });
      }
    },
    async getInfo() {
      const { data } = await getInfo(this.$route.query.id);
      this.checkState = data.checkState;
      let res = this.ruleForm;
      for (let key in res) {
        res[key] = data[key];
      }
    },

    //  onEditorBlur(){//失去焦点事件
    //         },
    //         onEditorFocus(){//获得焦点事件
    //         },
    //         onEditorChange(){//内容改变事件
    //         },
  },
  created() {
    this.getInfo();
  },
  data() {
    return {
      // 审核状态
      checkState: "",
      // 原因
      reason: "",
      ruleForm: {
        userName: "",
        checkStateName: "",
        albumNames: "",
        userAccount: "",
        createTime: "",
        title: "",
        content: "",

        imgUrlList: [],
      },
      content: null,
      editorOption: {},
      // textarea: "",
      //   desc: [
      //         { required: true, message: '请填写活动形式', trigger: 'blur' }
      //       ]
    };
  },
};
</script>

<style lang="scss" scoped>
.advertisement {
  .box-card {
    .card-div {
      font-size: 14px/28px "";
      height: 30px;
      margin-bottom: 20px;
    }
    .card1 {
      height: 24px;
      font: 18px/24px "";
      color: orange;
      padding-left: 10px;
      border-left: 5px solid orange;
    }
    .card2 {
      .card2-div {
        margin-top: 10px;
        margin-bottom: 10px;
      }
      .card2-sp {
        margin-right: 30px;
        .card2-sp-span {
          margin-left: 20px;
          margin-right: 10px;
        }
      }
      .card2-btn {
        width: 100px;
        margin-left: 20px;
      }
    }
    .card3 {
      ::v-deep .ql-toolbar {
        background-color: rgb(243, 241, 241);
      }
      height: 400px;
      ::v-deep .el-table th {
        background-color: rgb(216, 215, 215);
        color: black;
        font: 16px/20px "Microsoft Yahei";
        text-align: center;
      }
      ::v-deep .cell {
        text-align: center;
      }
      .card3-div {
        font-size: 20px;
        font-weight: 600;
      }
      .imgBox {
        img {
          width: 150px;
          height: 150px;
          margin: 20px 20px 20px 0;
        }
      }
      .block {
        margin-top: 20px;
        margin-bottom: 20px;
        float: right;
        ::v-deep .active {
          background-color: orange;
        }
      }
      .editor {
        height: 300px;
      }
    }
    .card4 {
      .card4-d1 {
        margin-bottom: 30px;
        .card4-d1-btn {
          width: 120px;
          margin-right: 30px;
        }
      }
      .card4-input {
        width: 90%;
      }
    }
  }
}
</style>