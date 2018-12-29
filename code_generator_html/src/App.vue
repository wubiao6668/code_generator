<template>
  <div>
    <h2>Shopping Cart</h2>
    <Layout style="height:250px;">

      <DataList style="height:100%"
                :data="productData"
                itemCls="dataitem">
        <template slot-scope="{row}">
          <div class="product" v-Draggable="{row:row,revert:true,proxy:$refs.proxy,dragStart:onDragStart}">
            <img draggable="false" :src="'https://www.jeasyui.com/tutorial/datagrid/images/'+row.itemid+'.png'">
            <p>{{row.name}}</p>
            <p>{{row.listprice}}</p>
          </div>
        </template>

      </DataList>

      <LayoutPanel region="east" v-Droppable="dropOpts" style="width:40%;"
                   bodyCls="f-row"
                   :bodyStyle="bodyStyle"
                   @dragEnter="isover=true"
                   @dragLeave="isover=false"
                   @drop="isover=false;onDrop()">
        <DataGrid class="f-full"
                  :border="false"
                  :showFooter="true"
                  :data="cartData"
                  :footerData="sumData">
          <GridColumn :field="'name'" :title="'Name'"></GridColumn>
          <GridColumn :field="'listprice'" :title="'Price'" align="right"></GridColumn>
        </DataGrid>
      </LayoutPanel>
    </Layout>

    <DraggableProxy ref="proxy">
      <div v-if="dragItem" class="product product-proxy">
        <img draggable="false" :src="'https://www.jeasyui.com/tutorial/datagrid/images/'+dragItem.itemid+'.png'">
        <p>{{dragItem.name}}</p>
        <p>{{dragItem.listprice}}</p>
      </div>
    </DraggableProxy>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        dropOpts: {
          drop: this.onDrop
        },
        isover: false,
        dragItem: null,
        cartData: [],
        sumData: [{ name: "Total", listprice: 0 }],
        productData: [
          {
            code: "FI-SW-01",
            name: "Koi",
            unitcost: 10.0,
            status: "P",
            listprice: 36.5,
            attr: "Large",
            itemid: "EST-1"
          },
          {
            code: "K9-DL-01",
            name: "Dalmation",
            unitcost: 12.0,
            status: "P",
            listprice: 18.5,
            attr: "Spotted Adult Female",
            itemid: "EST-2"
          },
          {
            code: "RP-SN-01",
            name: "Rattlesnake",
            unitcost: 12.0,
            status: "P",
            listprice: 38.5,
            attr: "Venomless",
            itemid: "EST-3"
          },
          {
            code: "RP-SN-01",
            name: "Rattlesnake",
            unitcost: 12.0,
            status: "P",
            listprice: 26.5,
            attr: "Rattleless",
            itemid: "EST-4"
          },
          {
            code: "RP-LI-02",
            name: "Iguana",
            unitcost: 12.0,
            status: "P",
            listprice: 35.5,
            attr: "Green Adult",
            itemid: "EST-5"
          },
          {
            code: "FL-DSH-01",
            name: "Manx",
            unitcost: 12.0,
            status: "P",
            listprice: 158.5,
            attr: "Tailless",
            itemid: "EST-6"
          },
          {
            code: "FL-DSH-01",
            name: "Manx",
            unitcost: 12.0,
            status: "P",
            listprice: 83.5,
            attr: "With tail",
            itemid: "EST-7"
          },
          {
            code: "FL-DLH-02",
            name: "Persian",
            unitcost: 12.0,
            status: "P",
            listprice: 23.5,
            attr: "Adult Female",
            itemid: "EST-8"
          },
          {
            code: "FL-DLH-02",
            name: "Persian",
            unitcost: 12.0,
            status: "P",
            listprice: 89.5,
            attr: "Adult Male",
            itemid: "EST-9"
          },
          {
            code: "AV-CB-01",
            name: "Amazon Parrot",
            unitcost: 92.0,
            status: "P",
            listprice: 63.5,
            attr: "Adult Male",
            itemid: "EST-10"
          }
        ]
      };
    },
    computed: {
      bodyStyle() {
        return this.isover ? { border: "1px solid red" } : null;
      }
    },
    methods: {
      onDragStart(state) {
        this.dragItem = state.target.row;
      },
      onDrop() {
        let index = this.productData.indexOf(this.dragItem);
        if (index >= 0) {
          this.productData = this.productData.filter((row, i) => i != index);
          this.cartData = [...this.cartData, this.dragItem];
          this.sumData = [
            {
              name: "Total",
              listprice: this.sumData[0].listprice + this.dragItem.listprice
            }
          ];
        }
      }
    }
  };
</script>
<style>
  .dataitem {
    width: 120px;
    height: 130px;
    float: left;
    margin: 6px;
  }
  .product {
    text-align: center;
  }
  .product img {
    height: 80px;
    padding: 10px;
  }
  .product p {
    font-size: 12px;
    margin: 5px 0;
  }
  .product-proxy {
    width: 120px;
    height: 130px;
    background: #fbec88;
    border: 1px solid #ccc;
    opacity: 0.6;
  }
  .over {
    border: 1px solid red;
  }
</style>
