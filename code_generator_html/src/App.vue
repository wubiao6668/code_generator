<template>
  <div>
    <h2>Constrain Draggable</h2>
    <Panel :bodyStyle="">
      <template v-for="(item,index) in items">
        <div v-Draggable="{drag:onDrag,revert:false,dragEnd:onDragEnd,axis:axis}" :row="item" :id="index" :index="index"
             :style="dragStyle">
          <p style="text-align:center">Drag Me{{item}}</p>
        </div>
      </template>
    </Panel>
  </div>
</template>

<script>
  export default {
    data() {
      var items = [];
      for (var i = 0; i < 4; i++) {
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
        console.info(d.left + "," + d.top);
        let top = d.top;
        let startTop = event.startTop;
        let moveTop = startTop - top;
        if (Math.abs(moveTop) < this.dragHeight / 2) {
          console.info("小于一半不排序");
        }
        // Math.ceil(
        console.info(moveTop);
        console.info(this.dragHeight);
        let moveStep = Math.ceil(moveTop / this.dragHeight);
        console.info(moveStep);
        let items = this.items;
        let target = event.target.$el.attributes;
        let cruIndex = target.index;
        let cruRow = items[cruIndex];
        let insertInde = moveStep + cruIndex;
        items.splice(cruIndex,1);
        items.splice(insertInde,0,cruRow);
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
