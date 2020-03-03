import Vue from "/js/vue.js";
import LayuiTable from "/component/LayuiTable.js";


// language=Vue
const template = `
    <div>
        <button class="layui-btn layui-btn-sm orderCellDateChoose">选择日期</button>
        <table class="layui-hide" id="saleOrderCellData" lay-filter="saleOrderCellFilter"></table>
    </div>
`;

export const OrderCellManagement = "order-cell-management";
Vue.component(OrderCellManagement, {
    template: template,
    data: () => ({
        tableObj: null
    }),
    created: function () {
        let apiField = "/api/tableField/pass.passOrderCellTotal";
        layui.use(['jquery', 'table', 'layer', 'laydate'], () => {
            layui.jquery.get("/api/tableField/pass.passOrderCellTotal", fieldData =>
                layui.jquery.get("/api/saleOrderCells", data =>
                    this.tableObj = layui.table.render({
                        elem: "#saleOrderCellData",
                        cols: [fieldData.data],
                        page: true,
                        totalRow: true,
                        height: 'full-250',
                        limit: 15,
                        limits: [15, 30, 45, 60],
                        data: data._embedded.storeGoodsCell
                    })
                )
            );
            layui.laydate.render({
                elem: layui.jquery(".orderCellDateChoose")[0],
                trigger: 'click',
                done: (value, date) => layui.jquery.get(`/api/saleOrderCells/${value}`, data =>
                    this.tableObj.reload({data: data._embedded.storeGoodsCell})
                )
            })
        })
    },
    methods: {}
});

export default OrderCellManagement
