<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="10" :sm="10">
        <div class="grid-content bg-purple">
          <div class="grid-content bg-purple">
            <el-table
              :data="tableData"
              style="width:100%"
              max-height="100%">
              <el-table-column
                prop="zip"
                label="邮编">
              </el-table-column>
              <el-table-column
                fixed="right"
                label="操作"
                width="120">
                <template slot-scope="scope">
                  <el-button
                    @click.native.prevent="deleteRow(scope.$index, tableData)"
                    type="text"
                    size="small">
                    移除
                  </el-button>
                  <el-button
                    @click.native.prevent="addRow(scope.$index, tableData)"
                    type="text"
                    size="small">
                    添加
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </el-col>
      <el-col :xs="1" :sm="1">
        <div class="grid-content bg-purple-light">
          <div style="float: right;">>></div>
        </div>
      </el-col>
      <el-col :xs="10" :sm="10">
        <div class="grid-content bg-purple-light">
          <div class="grid-content bg-purple">
            <el-table
              :data="tableData1"
              style="width:100%"
              max-height="100%">
              <el-table-column
                prop="zip"
                label="属性"/>
              <el-table-column
                label="setValue">
                <template slot-scope="scope">
                <el-input v-model="scope.row.name" placeholder="设置属性的值"></el-input>
                </template>
              </el-table-column>
              <el-table-column
                prop="zip"
                label="value"/>
              <el-table-column
                prop="zip"
                label="comment"/>
              <el-table-column
                fixed="right"
                label="操作"
                width="120">
                <template slot-scope="scope">
                  <el-button
                    @click.native.prevent="deleteRow(scope.$index, tableData1)"
                    type="text"
                    size="small">
                    移除
                  </el-button>
                  <el-button
                    @click.native.prevent="upRow(scope.$index, tableData1)"
                    type="text"
                    size="small">
                    上
                  </el-button>
                  <el-button
                    @click.native.prevent="downRow(scope.$index, tableData1)"
                    type="text"
                    size="small">
                    下
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div style="margin-top: 20px;float: right">
            <el-button>生成代码</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  var data = [{
    date: '2016-05-03',
    name: '王小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: 1
  }, {
    date: '2016-05-02',
    name: '王小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: 2
  }];

  var data1 = [{
    date: '2016-05-03',
    name: '王小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: 200333
  }, {
    date: '2016-05-02',
    name: '王小虎',
    province: '上海',
    city: '普陀区',
    address: '上海市普陀区金沙江路 1518 弄',
    zip: 200333
  }];
  export default {
    methods: {
      deleteRow(index, rows) {
        rows.splice(index, 1);
      },
      addRow(index, rows) {
        // rows.splice(index, 1);
        console.info(index);
        console.info(rows);
        console.info(rows[index]);
        data1.push(rows[index]);
      },
      upRow(index, rows) {
        console.info(index);
        if (0 == index){
          return;
        }
        var obj = rows[index];
        var objDown = rows[index - 1];
        rows.splice(index - 1, 2,obj, objDown);
      },
      downRow(index, rows) {
       if (rows.length-1 == index){
         return;
       }
        // rows.splice(index, 1);
        console.info(rows);
        var obj = rows[index];
        var objDown = rows[index + 1];
        rows.splice(index,2, objDown,obj);
        // rows[index - 1] = objTemp;
        // data1.push();
        // data1 = rows;
        // console.info(data1);
      }
    },
    data() {
      return {
        tableData: data,
        tableData1: data1,
      }
    }
  }

  var fieldData=[
    {fiedName:""}
    ];
</script>
