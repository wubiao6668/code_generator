<template>
  <div>
    <h2>code generator</h2>
    <Layout style="height:250px;max-width:100%">
      <LayoutPanel region="west" style="width:50%;"
                   bodyCls="f-row"
                   :bodyStyle="{}">

        <TreeGrid style="height:250px"
                  :data="data" v-on:rowCheck="onRowCheck" :checkbox="true" idField="id" treeField="name">
          <GridColumn field="name" title="Name"></GridColumn>
          <GridColumn field="size" title="Size"></GridColumn>
          <GridColumn field="date" title="Date"></GridColumn>
        </TreeGrid>

      </LayoutPanel>
      <LayoutPanel region="east" style="width:50%;"
                   bodyCls="f-row"
                   :bodyStyle="{}">

        <TreeGrid style="height:250px"
                  :data="data2" :checkbox="true" idField="id" treeField="name">
          <GridColumn field="name" title="Name"></GridColumn>
          <GridColumn field="size" title="Size"></GridColumn>
          <GridColumn field="date" title="Date"></GridColumn>
          <GridColumn title="opt">
            <template slot="cell" slot-scope="{row}">
              <LinkButton btnCls="c5" style="width:120px">Button5</LinkButton>
            </template>
          </GridColumn>
        </TreeGrid>
      </LayoutPanel>
    </Layout>
    <div>
      <Tabs :tabPosition="position" style="height:250px">
        <TabPanel :title="'生成set方法'">
          <p>Tab Panel1 生成set方法</p>
        </TabPanel>
        <TabPanel :title="'生成JSON文档'">
          <p>Tab Panel2</p>
        </TabPanel>
        <TabPanel :title="'生成wiki文档'">
          <p>Tab Panel3</p>
        </TabPanel>
        <TabPanel :title="'Help'" :closable="true" iconCls="icon-help">
          <p>This is the help content.</p>
        </TabPanel>
      </Tabs>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      let data = this.getData();
      data.forEach((value, index) => value.index = index);
      return {
        position:"right",
        data: data,
        data2: this.getData(),
      };
    },
    methods: {
      onRowCheck(row) {

        console.info(row)
        let newRow = this.getData().find((value, index, arr) => {
          console.info(value)
          return index == row.index
        })
        console.info("newRow",newRow);
        this.data2 = [...this.data2, newRow]
      },
      deepCopy(obj, deep) {
        // debugger
        var o = obj instanceof Array ? [] : {};
        for (var k in obj) {
          var val = obj[k];
          if (deep && typeof val === "object") {
            o[k] = this.deepCopy(val, deep);
          } else {
            o[k] = val;
          }
        }
        return o;
      },
      getData() {
        return [
          {
            id: 1,
            name: "C",
            size: "",
            date: "02/19/2010"
          },
          {
            id: 2,
            name: "C",
            size: "",
            date: "02/19/2010"
          },
          {
            id: 3,
            name: "C",
            size: "",
            date: "02/19/2010"
          },
          {
            id: 4,
            name: "C",
            size: "",
            date: "02/19/2010",
            children: [{
              id: 8,
              name: "Program Files",
              size: "120 MB",
              date: "03/20/2010"
            }]
          }
        ];
      }
    }
  };
</script>
<style>

</style>
﻿
