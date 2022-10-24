// 总览
<template>
  <div class="dashboard-container">
    <!-- 总览 -->
    <!-- <el-card class="box-card card1" shadow="hover" style="margin-bottom: 20px">
      <div class="item">
        总览
      </div>
    </el-card> -->
    <el-card class="box-card" style="margin-bottom: 10px">
      <div class="card1">总览</div>
    </el-card>
    <el-card class="box-card card2">
      <el-row>
        <!-- 销售额 -->
        <el-col :span="12" class="col">
          <div class="grid-content bg-purple" style=" margin:10px;">
            <el-card class="box-card">
              <el-row>
                <el-col :span="14">
                  <div>
                    <h2><b>销售额</b></h2>
                    <el-button size="mini" class="btn" @click="checkBtn('xse_1')">近一周</el-button>
                    <el-button size="mini" class="btn" style="border-left: none;" @click="checkBtn('xse_2')">近一月
                    </el-button>
                    <el-button size="mini" class="btn" style="border-left: none;" @click="checkBtn('xse_3')">近半年
                    </el-button>
                  </div>
                </el-col>
                <el-col :span="10">
                  <div style="margin-top: 30px;">
                    <el-date-picker
                      v-model="date1"
                      type="daterange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      value-format='yyyy-MM-dd '
                      @change="changeDate(0,date1)"
                       size="mini"
                      style="width:200px;">
                    </el-date-picker>
                  </div>
                </el-col>
              </el-row>
              <div id="ec1" :style="{height:'500px',width:'100%'}"></div>
            </el-card>
          </div>
        </el-col>
        <!-- 在线播放量 -->
        <el-col :span="12" class="col">
          <div class="grid-content bg-purple-light" style=" margin:10px;">
            <el-card class="box-card">
              <div>
                <h2><b>在线播放量</b></h2>
                <div id="zxbf" :style="{height:'530px',width:'100%'}"></div>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <!-- 用户注册数 -->
        <el-col :span="12" class="col">
          <div class="grid-content bg-purple" style=" margin:10px;">
            <el-card class="box-card">
              <el-row>
                <el-col :span="14">
                  <h2><b>用户注册数</b></h2>
                  <el-button size="mini" class="btn" @click="userRegistration('user_1')">近一周</el-button>
                  <el-button size="mini" class="btn" style="border-left: none;" @click="userRegistration('user_2')">
                    近一月
                  </el-button>
                  <el-button size="mini" class="btn" style="border-left: none;" @click="userRegistration('user_3')">
                    近半年
                  </el-button>
                </el-col>
                <el-col :span="10">
                  <div style="margin-top: 30px;">
                    <el-date-picker
                      v-model="date2"
                      type="daterange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      value-format='yyyy-MM-dd '
                      @change="changeDate(1,date2)"
                       size="mini"
                      style="width:200px;">
                    </el-date-picker>
                  </div>
                </el-col>
              </el-row>
              <div id="ec2" :style="{height:'500px',width:'100%'}"></div>
            </el-card>
          </div>
        </el-col>
        <!-- 注册用户情况 -->
        <el-col :span="12" class="col">
          <div class="grid-content bg-purple-light" style=" margin:10px;">
            <el-card class="box-card">
              <h2><b>注册用户情况</b></h2>
              <div id="zcyh" :style="{height:'530px',width:'100%'}"></div>
            </el-card>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <!-- 用户注册数 -->
        <el-col :span="12" class="col">
          <div class="grid-content bg-purple col" style=" margin:10px;">
            <el-card class="box-card">
              <el-row>
                <el-col :span="14">
                  <h2><b>用户注册数</b></h2>
                  <el-button size="mini" class="btn" @click="userRegistration_there('user_a')">近一周</el-button>
                  <el-button size="mini" class="btn" style="border-left: none;"
                             @click="userRegistration_there('user_a')">近一月
                  </el-button>
                  <el-button size="mini" class="btn" style="border-left: none;"
                             @click="userRegistration_there('user_c')">近半年
                  </el-button>
                </el-col>
                <el-col :span="10">
                  <div style="margin-top: 30px;">
                    <el-date-picker
                      v-model="date3"
                      type="daterange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      value-format='yyyy-MM-dd '
                      @change="changeDate(2,date3)"
                       size="mini"
                      style="width:200px;">
                    </el-date-picker>
                  </div>
                </el-col>
              </el-row>
              <div id="ec3" :style="{height:'510px',width:'100%'}"></div>
            </el-card>
          </div>
        </el-col>
        <!-- 异常量统计 -->
        <el-col :span="12" class="col">
          <div class="grid-content bg-purple-light" style=" margin:10px;">
            <el-card class="box-card">
              <el-row>
                <h2><b>异常量统计</b></h2>
                <el-col :span="14">
                  <span style="font-size: 30px;display: inline-block">{{number}}</span>
                  <h2 style="margin-left: 20px;display: inline-block">今日新增</h2>
                </el-col>
                <el-col :span="10">
                  <div style="margin-top: 30px;">
                    <el-date-picker
                      v-model="date4"
                      type="daterange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      value-format='yyyy-MM-dd '
                      @change="changeDate(3,date4)"
                      size="mini"
                      style="width:200px;">
                    </el-date-picker>
                  </div>
                </el-col>
              </el-row>
              <div id="yctj" :style="{height:'500px',width:'100%'}"></div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
  import * as echarts from 'echarts';

  export default {
    name: "Dashboard",
    computed: {
      // ...mapGetters([
      //   'name'
      // ])
    },
    data() {
      return {
        number: 250, //今日新增
        user: 200,
        seriesData: [],
        date1: undefined,
        date2: undefined,
        date3: undefined,
        date4: undefined,
        //销售额
        xAxisList: this.getDay(7),
        yAxisList: this.numberFormat(7),
        //用户注册数--左图二
        xAxisUserList: this.getDay(7),
        yAxisUserList: this.numberFormat(7),
        //用户注册数--左图三
        xAxisUserList_three: this.getDay(7),
        yAxisUserList_three: this.numberFormat(7),
        //在线播放量
        xAxisOnlineList1: this.getDay(7),
        yAxisOnlineList1: this.numberFormat(7),
        yAxisOnlineList2: this.numberFormat(7),
        //饼图数据
        barData: [
          {value: 1048, name: '杭州'},
          {value: 735, name: '成都'},
          {value: 580, name: '深圳'},
          {value: 484, name: '广州 '},
          {value: 300, name: '上海 '}
        ],
        //柱状图数据
        xAxisAbnormalList: this.getDay(7),
        yAxisAbnormalList1: this.numberFormat(7),
        yAxisAbnormalList2: this.numberFormat(7),

      }
    },
    mounted() {
      //销售额
      this.drawLine()
      //用户注册数-左图二
      this.userDrawLine()
      //用户注册数-左图三
      this.userDrawLine_there()
      //在线播放量
      this.onlineDrawLine()
      //注册用户情况(饼图)
      this.userPieChart()
      //异常量统计
      this.abnormal()
    },
    created() {
    },
    methods: {
      // 销售额按钮
      checkBtn(data) {
        if (data == 'xse_1') {
          this.xAxisList = this.getDay(7)
          this.yAxisList = this.numberFormat(7)
        } else if (data == 'xse_2') {
          this.xAxisList = this.getDay(30)
          this.yAxisList = this.numberFormat(30)
        } else if (data == 'xse_3') {
          this.xAxisList = this.getDay(183)
          this.yAxisList = this.numberFormat(183)
        }
        this.drawLine()
      },
      //用户注册数量按钮-左图二
      userRegistration(data) {
        if (data == 'user_1') {
          this.xAxisUserList = this.getDay(7)
          this.yAxisUserList = this.numberFormat(7)
        } else if (data == 'user_2') {
          this.xAxisUserList = this.getDay(30)
          this.yAxisUserList = this.numberFormat(30)
        } else if (data == "user_3") {
          this.xAxisUserList = this.getDay(183)
          this.yAxisUserList = this.numberFormat(183)
        }
        this.userDrawLine()
      },
      //用户注册数量按钮-左图三
      userRegistration_there(data) {
        if (data == 'user_a') {
          this.xAxisUserList_three = this.getDay(7)
          this.yAxisUserList_three = this.numberFormat(7)
        } else if (data == 'user_b') {
          this.xAxisUserList_three = this.getDay(30)
          this.yAxisUserList_three = this.numberFormat(30)
        } else if (data == "user_c") {
          this.xAxisUserList_three = this.getDay(183)
          this.yAxisUserList_three = this.numberFormat(183)
        }
        this.userDrawLine_there()
      },
      //销售额
      drawLine() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('ec1'))
        // 绘制图表
        myChart.setOption({
          title: {},
          tooltip: {},
          type: 'category',
          xAxis: {
            boundaryGap: false,
            data: this.xAxisList
          },
          tooltip: {
            trigger: 'axis'
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            type: 'line',
            data: this.yAxisList,
            areaStyle: {},
            smooth: true,
            color: '#1E90FF'
          }]
        })
      },
      //用户注册数量-左图二
      userDrawLine() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('ec2'))
        // 绘制图表
        myChart.setOption({
          title: {},
          tooltip: {},
          type: 'category',
          xAxis: {
            boundaryGap: false,
            data: this.xAxisUserList
          },
          tooltip: {
            trigger: 'axis'
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            type: 'line',
            data: this.yAxisUserList,
            areaStyle: {},
            smooth: true,
            color: '#1E90FF'
          }]
        })
      },
      //用户注册数量-左图三
      userDrawLine_there() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('ec3'))
        // 绘制图表
        myChart.setOption({
          title: {},
          tooltip: {},
          type: 'category',
          xAxis: {
            boundaryGap: false,
            data: this.xAxisUserList_three
          },
          tooltip: {
            trigger: 'axis'
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            type: 'line',
            data: this.yAxisUserList_three,
            areaStyle: {},
            smooth: true,
            color: '#1E90FF'
          }]
        })
      },
      //在线播放量
      onlineDrawLine() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('zxbf'))
        // 绘制图表
        myChart.setOption({
          title: {},
          tooltip: {},
          type: 'category',
          xAxis: {
            boundaryGap: false,
            data: this.xAxisOnlineList1
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['播放次数', '播放时间'],
            right: 10,
          },
          yAxis: [
            {
              name: '',
              type: 'value',
              splitLine: {show: false}
            },
            {
              name: '',
              nameLocation: 'start',
              max: 20,
              type: 'value',
              splitLine: {show: false}
            }
          ],
          series: [
            {
              name: "播放次数",
              type: 'line',
              data: this.yAxisOnlineList1,
              areaStyle: {},
              smooth: true,
              color: '#1E90FF'
            },
            {
              name: "播放时间",
              type: 'line',
              data: this.yAxisOnlineList2,
              areaStyle: {},
              smooth: true,
              color: '#0efff0'
            }
          ]
        })
      },
      //注册用户情况
      userPieChart() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('zcyh'))
        // 绘制图表
        myChart.setOption({
          tooltip: {
            trigger: 'item',
             formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            top: '5%',
            right: 'right',
          },
          series: [
            {
              name: '',
              type: 'pie',
              radius: ['45%', '60%'],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: false,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: this.barData
            }],
          graphic: [
            {
              type: 'group',
              left: 'center',
              top: 'middle',
              children: [
                {
                  type: 'text',
                  z: 100,
                  left: 'center',
                  top: '0',
                  style: {
                    fill: '#333',
                    text: [this.user],
                    font: '32px Microsoft YaHei',
                  },
                },
                {
                  type: 'text',
                  z: 100,
                  left: 'center',
                  top: '32',
                  style: {
                    fill: '',
                    text: ["位用户"],
                    font: '16px Microsoft YaHei',
                  },
                },
              ],
            },
          ],
        })
      },
      //异常量统计
      abnormal() {
        // 基于准备好的dom，初始化echarts实例
        let myChart = echarts.init(document.getElementById('yctj'))
        // 绘制图表
        myChart.setOption({
          title: {},
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ["次数", "恢复时间"],
            right: 10,
          },
          grid: {
            left: '3%', //柱状图距离左边的距离，也可以用像素px
            right: '4%', //柱状图距离右边的距离，也可以用像素px
            bottom: '8%', //网格图（柱状图、折线图、气泡图等）离底部的距离，也可以用像素比如10px
            containLabel: true //grid 区域是否包含坐标轴的刻度标签。false可能溢出，默认为false
          },
          yAxis: [
            {
              type: 'value'
            }
          ],
          xAxis: {
            data: this.xAxisAbnormalList,
            axisLabel: {
              rotate: 45 //控制柱状图X轴label是否倾斜显示
            },
          },
          series: [
            {
              name: '次数',
              type: 'bar',
              barWidth: '30%', //barWidth设置每根柱状图的宽度
              data: this.yAxisAbnormalList1,
              color: '#1E90FF'
            },
            {
              name: '恢复时间',
              type: 'bar',
              barWidth: '30%', //barWidth设置每根柱状图的宽度
              data: this.yAxisAbnormalList2,
              color: '#0efff0'
            }
          ]
        })
      },
      //時間处理
      getDay(day) {
        let today = new Date();
        let dateArr = []
        for (let i = 0; i < day; i++) {
          let newDate = new Date(today.getTime() + i * 1000 * 60 * 60 * 24)
          let year = newDate.getFullYear()
          let month = (parseInt(newDate.getMonth()) + 1) > 9 ? (parseInt(newDate.getMonth()) + 1) : "0" + (parseInt(newDate.getMonth()) + 1)
          let day = (newDate.getDate()) > 9 ? newDate.getDate() : "0" + newDate.getDate()
          let fullDate = `${year}-${month}-${day}`
          dateArr.push(fullDate)
        }
        return dateArr;
      },
      //时间日期选择框
      changeDate(index,date) {
        if(index == 0){
           this.xAxisList = this.getDateList(date[0],date[1])
           this.yAxisList = this.numberFormat(this.xAxisList.length)
           this.drawLine()
        }else if(index == 1){
          this.xAxisUserList = this.getDateList(date[0],date[1])
          this.yAxisUserList = this.numberFormat(this.xAxisUserList.length)
          this.userDrawLine()
        }else if(index == 2){
          this.xAxisUserList_three = this.getDateList(date[0],date[1])
          this.yAxisUserList_three = this.numberFormat(this.xAxisUserList_three.length)
          this.userDrawLine_there()
        }else if(index == 3){
          this.xAxisAbnormalList = this.getDateList(date[0],date[1])
          this.yAxisAbnormalList1 = this.numberFormat(this.xAxisAbnormalList.length)
          this.yAxisAbnormalList2 = this.numberFormat(this.xAxisAbnormalList.length)
          this.abnormal()
        }
      },
      //获取两日期之间日期列表函数
      getDateList(startTime, endTime) {
        //初始化日期列表，数组
        let diffDate =[];
        let i=0;
        //开始日期小于等于结束日期,并循环
        while(startTime <= endTime) {
          diffDate[i] = startTime;
          //获取开始日期时间戳
          const startTime_ts = new Date(startTime).getTime();
          //增加一天时间戳后的日期
          const next_date = startTime_ts + (24 * 60 * 60 * 1000);
          //拼接年月日，这里的月份会返回（0-11），所以要+1
          const next_dates_y = new Date(next_date).getFullYear() + '-';
          const next_dates_m = (new Date(next_date).getMonth() + 1 < 10) ? '0' + (new Date(next_date).getMonth() + 1) + '-' : (new Date(next_date).getMonth() + 1) + '-';
          const next_dates_d = (new Date(next_date).getDate() < 10) ? '0' + new Date(next_date).getDate() : new Date(next_date).getDate();
          startTime = next_dates_y + next_dates_m + next_dates_d;
          //增加数组key
          i++;
        }
        return diffDate;
      },
      numberFormat(day) { // todo  测试处理数据
        let num = '';
        let number = []
        for (let i = 0; i < day; i++) {
          for (let i = 0; i < 3; i++) {
            num += Math.floor(Math.random() * 10);
          }
          number.push(num)
          num = ''
        }
        return number;
      },
    },
  };
</script>

<style scoped>
  .btn {
    margin-left: 0px;
    margin-right: 0px;
  }
</style>
<style lang="scss" scoped>
.dashboard-container{
  .box-card{
    //  margin-bottom: 20px;
    .card1 {
      height: 24px;
      font: 18px/24px "";
      color: orange;
      padding-left: 10px;
      border-left: 5px solid orange;
    }
  }
}
  // .dashboard {
  //   &-container {
  //     margin: 30px;
  //   }

  //   &-text {
  //     font-size: 30px;
  //     line-height: 46px;
  //   }

  //   // .card1 {
  //   //   margin-bottom: 20px;
      
  //   // }
    

  //   .box-card {
  //     .item {
  //       background-color: orange;
  //     }
  //     .card1 {
  //     height: 24px;
  //     font: 18px/24px "";
  //     color: orange;
  //     padding-left: 10px;
  //     border-left: 5px solid orange;
  //   }

  //     // .eachar {
  //     // }

  //     ::v-deep .col {
  //       margin-left: 10px;
  //       margin-bottom: 10px;
  //     }
  //   }
  // }
</style>
