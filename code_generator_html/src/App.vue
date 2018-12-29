<template>
  <div>
    <h2>Constrain Draggable</h2>
    <!--<Panel :bodyStyle="{position: 'relative', width: '200px',height:'100%',border:'1px solid',padding:'2px'}">-->
    <!--<template v-for="(item,index) in items">-->
    <!--<div v-Draggable="{drag:onDrag,revert:false,dragEnd:onDragEnd,axis:axis}" :row="item" :id="index" :index="index"-->
    <!--:style="{border: '1px solid #ccc', float: 'left',width: '100%',height: '100px'}">-->
    <!--<p style="text-align:center">Drag Me{{item}}</p>-->
    <!--</div>-->
    <!--</template>-->
    <!--</Panel>-->


    <DataList style="height:100%"
              :data="items"
              itemCls="dataitem">
      <template slot-scope="{row,rowIndex}">
        <div v-Draggable="{drag:onDrag,revert:true,dragEnd:onDragEnd,axis:axis}" :index=rowIndex
             :style="{border: '1px solid #ccc',width: '100%',height: '100px'}">
          <p style="text-align:center">Drag Me{{row}}{{rowIndex}}</p>
        </div>
      </template>
    </DataList>
    <!--<DataGrid :data="data" style="height:250px">-->
    <!--</DataGrid>-->

  </div>
</template>

<script>
  export default {
    data() {
      var items = [];
      for (var i = 4; i < 8; i++) {
        items.push({"id": i, "name_": `zhangsan${i}`});
      }
      return {
        axis: "v",
        items: items,
        containerWidth: 500,
        // containerHeight: 300,
        // dragWidth: 100,
        dragHeight: 100
      };
    },
    // computed: {
    //   panelStyle() {
    //     return {
    //       // position: "relative",
    //       // width: this.containerWidth + "px",
    //       // height: this.containerHeight + "px"
    //     };
    //   },
    //   dragStyle() {
    //     return {
    //       border: "1px solid #ccc",
    //       float: "left",
    //       width: "100%",
    //       height: this.dragHeight + "px"
    //     };
    //   }
    // },
    methods: {
      onDragEnd(event) {
        let d = event;
        let top = d.top;
        let startTop = event.startTop;
        let moveTop = top - startTop;
        if (Math.abs(moveTop) < this.dragHeight / 2) {
         return
        }
        let moveStep = Math.ceil(moveTop / this.dragHeight);
        let target = event.target.$el.attributes;
        let cruIndex = Number(target.index.nodeValue);
        let cruRow = this.items[cruIndex];
        let insertInde = moveStep + cruIndex;
        this.items.splice(cruIndex, 1);
        this.items.splice(insertInde, 0, cruRow);
        let newItems = [...this.items];
        this.items.splice(0,newItems.length,...newItems);
      },
      onDrag(event) {
        // console.info(event);
        var d = event;
        if (d.left < 0) {
          d.left = 0;
        }
        if (d.top < 0) {
          d.top = 0;
        }
        // console.info(d.left + "," + d.top);
        // if (d.left + this.dragWidth > this.containerWidth - 2) {
        //   d.left = this.containerWidth - 2 - this.dragWidth;
        // }
        // if (d.top + this.dragHeight > this.containerHeight - 2) {
        //   d.top = this.containerHeight - 2 - this.dragHeight;
        // }
        d.target.applyDrag();
        // console.info("end");
      }
    }
  };


</script>
