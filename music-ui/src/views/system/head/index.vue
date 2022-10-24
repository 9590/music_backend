// 首页管理
<template>
  <div class="head">
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">首页管理</div>
    </el-card>
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card2">
        <!-- <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        > -->
        <el-form
          :model="ruleForm"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <!-- <el-form-item label="精选专辑" prop="choicenessAlbums"
            >(8个)
            <el-select
              v-model="ruleForm.choicenessAlbums"
              multiple
              filterable
              allow-create
              default-first-option
              placeholder="搜索专辑"
            >
              <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.albumName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="热门曲目" prop="hotSongs"
            >(8个)
            <el-select
              v-model="ruleForm.hotSongs"
              multiple
              filterable
              allow-create
              default-first-option
              placeholder="搜索曲目"
            >
              <el-option
                v-for="item in songOptions"
                :key="item.id"
                :label="item.songName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="" prop="">
            <el-button
              class="card2-btn"
              type="primary"
              @click="submitForm('ruleForm')"
              >保存</el-button
            >
          </el-form-item> -->
          <el-form-item label="精选专辑" prop="choicenessAlbums"
            >(8个)
            <ul class="album">
              <li
                v-for="(item, index) in options"
                :key="index"
                :draggable="true"
                @dragstart="dragstart(item)"
                @dragenter="dragenter(item, $event)"
                @dragend="getDragend(options, 'options', $event)"
              >
                <div>
                  {{ item.albumName }}
                  <span @click="deleteClick('options', item.id)">×</span>
                </div>
                <img src="@/assets/images/tuozhuai.png" alt="" />
              </li>
            </ul>
            <ul class="search">
              <li>
                <input
                  v-model="inputAlbum"
                  @input="inputChange('albumName', inputAlbum)"
                  placeholder="搜索专辑"
                  style="text-indent: 0.5em"
                  type="text"
                />
              </li>
              <li>
                <ul>
                  <li v-for="(item, index) in albumList" :key="index">
                    <span>{{ item.albumName }}</span>
                    <div @click="addClick('album', item)">+</div>
                  </li>
                </ul>
              </li>
            </ul>
          </el-form-item>
          <el-form-item
            style="margin-top: 20px"
            label="热门曲目"
            prop="hotSongs"
            >(8个)
            <ul class="album">
              <li
                v-for="(item, index) in songOptions"
                :key="index"
                :draggable="true"
                @dragstart="dragstart(item)"
                @dragenter="dragenter(item, $event)"
                @dragend="getDragend(songOptions, 'songOptions', $event)"
              >
                <div>
                  {{ item.songName }}
                  <span @click="deleteClick('songOptions', item.id)">×</span>
                </div>
                <img src="@/assets/images/tuozhuai.png" alt="" />
              </li>
            </ul>
            <ul class="search">
              <li>
                <input
                  v-model="inputSong"
                  @input="inputChange('songName', inputSong)"
                  placeholder="搜索歌曲"
                  style="text-indent: 0.5em"
                  type="text"
                />
              </li>
              <li>
                <ul>
                  <li v-for="(item, index) in songList" :key="index">
                    <span>{{ item.songName }}</span>
                    <div @click="addClick('song', item)">+</div>
                  </li>
                </ul>
              </li>
            </ul>
          </el-form-item>
          <el-form-item label="" prop="">
            <el-button
              style="padding: 10px 50px; border-radius: 4px; margin: 20px 20px"
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
import { list, add, songList } from "@/api/head/head";
import { userList } from "@/api/album/album";
export default {
  data() {
    return {
      // 搜索专辑名称
      inputAlbum: "",
      // 搜索歌曲名称
      inputSong: "",
      oldData: [],
      newData: [],
      lists: [
        {
          id: "1",
          songName: "歌曲名称1",
        },
        {
          id: "2",
          songName: "歌曲名称2",
        },
      ],
      // 专辑列表
      albumList: [],
      // 歌曲列表
      songList: [],
      // value: [],
      // 精选专辑
      options: [],
      // 热门歌曲
      songOptions: [],
      ruleForm: {
        choicenessAlbums: "",
        hotSong: "",
      },
    };
  },
  created() {
    this.list();
  },
  methods: {
    async inputChange(type, val) {
      if (type == "albumName") {
        const { rows } = await userList({
          albumName: val,
        });
        this.albumList = rows;
      } else if (type == "songName") {
        const { data } = await songList({
          songName: val,
        });
        this.songList = data;
      }
    },
    deleteClick(type, id) {
      let res = this[type];
      let num = "";
      for (let key in res) {
        if (res[key].id == id) {
          num = key;
        }
      }
      this[type].splice(num, 1);
    },
    //点击加号
    addClick(type, val) {
      if (type == "album" && this.options.length < 8) {
        //类型是专辑
        let res = this.options;
        let flag = true;
        for (let key in res) {
          if (res[key].id == val.id) {
            flag = false;
          }
        }
        if (flag) {
          this.options.push(val);
        } else {
          this.$message("已经添加过该专辑");
        }
      } else if (type == "song" && this.songOptions.length < 8) {
        //类型是歌曲
        let res = this.songOptions;
        let flag = true;
        for (let key in res) {
          if (res[key].id == val.id) {
            flag = false;
          }
        }
        if (flag) {
          this.songOptions.push(val);
        } else {
          this.$message("已经添加过该歌曲");
        }
      } else {
        this.$message("只能添加8" + (type == "album" ? "张专辑" : "首歌曲"));
      }
    },
    // 精选专辑排序
    dragstart(value) {
      this.oldData = value;
    },
    dragenter(value) {
      this.newData = value;
    },
    getDragend(listData, type) {
      if (this.oldData !== this.newData) {
        let oldIndex = listData.indexOf(this.oldData);
        let newIndex = listData.indexOf(this.newData);
        let newItems = [...listData];
        // 删除之前DOM节点
        newItems.splice(oldIndex, 1);
        // 在拖拽结束图标位置增加新的DOM节点
        newItems.splice(newIndex, 0, this.oldData);
        // 每次拖拽结束后，将拖拽处理完成的数据，赋值原数组，使DOM视图更新，页面显示拖拽动画
        this[type] = newItems;
      }
    },
    //展示
    async list() {
      const { data } = await list();
      if (data.choicenessAlbums) {
        this.options = data.choicenessAlbums;
        this.songOptions = data.hotSong;
      }
      const { rows } = await userList();
      this.albumList = rows;
      const songData = await songList();
      this.songList = songData.data;
    },
    async add(choicenessAlbums, hotSongs) {
      const { msg, code } = await add({
        choicenessAlbums: choicenessAlbums,
        hotSongs: hotSongs,
      });
      if (code == 200) {
        this.$message({
          message: msg,
          type: "success",
        });
        this.list();
      } else {
        this.$message.error(msg);
      }
    },
    // 保存
    async submitForm(ruleForm) {
      let choicenessAlbums = [];
      let hotSongs = [];
      let res1 = this.options;
      let res2 = this.songOptions;
      for (let key1 in res1) {
        choicenessAlbums.push({
          type: "1", //1为精选专辑  2为热门曲目
          albumSongId: res1[key1].id, //专辑或者曲目id
        });
      }
      for (let key2 in res2) {
        hotSongs.push({
          type: "2",
          albumSongId: res2[key2].id,
        });
      }
      this.add(choicenessAlbums, hotSongs);
      // console.log(this.ruleForm);
      // console.log("专辑", this.options);
      // console.log("曲目", this.songOptions);
      // const { res } = await addAdvList(this.ruleForm);
      // this.add();
      // this.$refs[ruleForm].validate((valid) => {
      //   if (valid) {
      //     this.$message({
      //       message: "提交成功",
      //       type: "success",
      //     });
      //     // this.$router.push(`/operation/advertisement/index`);
      //   } else {
      //     console.log("未提交成功!!");
      //     return false;
      //   }
      // });
    },
  },
};
</script>
<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
}
li {
  list-style: none;
}
input {
  outline: none;
}
.album {
  overflow: hidden;
  width: 200px;
  border: 1px solid #797979;
  padding: 0;
  display: inline-block;
  vertical-align: top;
  height: 320px;
  li {
    margin: 12px;
    padding: 0;
    height: 26px;
    font-size: 0;
    div {
      background-color: #81d3f8;
      width: 110px;
      height: 26px;
      line-height: 26px;
      text-align: center;
      font-size: 14px;
      border-radius: 50px;
      display: inline-block;
      vertical-align: middle;
      margin-top: -10px;
      span {
        cursor: pointer;
      }
    }
    img {
      display: inline-block;
      vertical-align: middle;
      margin-left: 20px;
      margin-top: -10px;
    }
  }
}
.search {
  width: 255px;
  height: 300px;
  border: 1px solid #797979;
  display: inline-block;
  vertical-align: top;
  margin-left: 50px;
  padding: 3px 5px;
  overflow: hidden;
  input {
    width: 128px;
    height: 27px;
  }
  li {
    height: 30px;
    div {
      display: inline-block;
      width: 31px;
      height: 19px;
      text-align: center;
      line-height: 19px;
      background-color: #81d3f8;
      border-radius: 3px;
      margin-left: 20px;
      cursor: pointer;
    }
    ul {
      height: 263px;
      overflow: auto;
      margin-top: 5px;
    }
  }
}
</style>