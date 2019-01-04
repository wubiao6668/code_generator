<template>
  <div>
    <h2>TreeGrid Template</h2>
    <TreeGrid style="height:250px"
              :data="data" :footerData="footerData" :showFooter="true"
              idField="id" treeField="name">
      <GridColumn field="name" title="Name" width="30%">
        <template slot="footer" slot-scope="{row}">
          <span class="tree-file icon-sum"></span>
          {{row.name}}
        </template>
      </GridColumn>
      <GridColumn field="persons" title="Persons" align="right"></GridColumn>
      <GridColumn field="begin" title="Begin Date"></GridColumn>
      <GridColumn field="end" title="End Date"></GridColumn>
      <GridColumn field="progress" title="Progress">
        <template slot="cell" slot-scope="{row}">
          <div class="progress">
            <div class="progress-inner" :style="{width:row.progress+'%'}">
              {{row.progress}} %
            </div>
          </div>
        </template>
      </GridColumn>
    </TreeGrid>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                data: null,
                footerData: null
            };
        },
        created() {
            this.data = this.getData();
            this.footerData = [
                {
                    name: "Total Persons:",
                    persons: this.sum(this.data)
                }
            ];
        },
        methods: {
            sum(data) {
                let persons = 0;
                for (let row of data) {
                    persons += row.persons || 0;
                    if (row.children) {
                        persons += this.sum(row.children);
                    }
                }
                return persons;
            },
            getData() {
                return [
                    {
                        id: 1,
                        name: "All Tasks",
                        begin: "3/4/2017",
                        end: "3/20/2017",
                        progress: 60,
                        iconCls: "icon-ok",
                        children: [
                            {
                                id: 2,
                                name: "Designing",
                                begin: "3/4/2017",
                                end: "3/10/2017",
                                progress: 100,
                                state: "closed",
                                children: [
                                    {
                                        id: 21,
                                        name: "Database",
                                        persons: 23,
                                        begin: "3/4/2017",
                                        end: "3/6/2017",
                                        progress: 100
                                    },
                                    {
                                        id: 22,
                                        name: "UML",
                                        persons: 12,
                                        begin: "3/7/2017",
                                        end: "3/8/2017",
                                        progress: 100
                                    },
                                    {
                                        id: 23,
                                        name: "Export Document",
                                        persons: 13,
                                        begin: "3/9/2017",
                                        end: "3/10/2017",
                                        progress: 100
                                    }
                                ]
                            },
                            {
                                id: 3,
                                name: "Coding",
                                persons: 21,
                                begin: "3/11/2017",
                                end: "3/18/2017",
                                progress: 80
                            },
                            {
                                id: 4,
                                name: "Testing",
                                persons: 18,
                                begin: "3/19/2017",
                                end: "3/20/2017",
                                progress: 20
                            }
                        ]
                    }
                ];
            }
        }
    };
</script>
<style>
  .progress {
    width: 100%;
    height: 20px;
    border: 1px solid #ccc;
  }
  .progress-inner {
    height: 100%;
    background: red;
    color: #fff;
    padding: 0 4px;
  }
</style>