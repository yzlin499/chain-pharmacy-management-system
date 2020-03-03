import Vue from "/js/vue.js";
import LayuiTable from "/component/LayuiTable.js";

// language=Vue
const template = `
    <div>
        <layui-table :tableName="tableName"
                     :isCanAdd="false"
                     :isCanDelete="false"
                     :isCanSelect="false"
                     :apiField="apiField"
                     :initFunction="initFunction"
                     :rowOnClick="rowOnClick">
        </layui-table>
        <button class="layui-btn layui-btn-sm orderDateChoose">选择日期</button>
    </div>
`;

export const OrderManagement = "order-management";
Vue.component(OrderManagement, {
    template: template,
    data: () => ({
        tableName: "salesOrder",
        apiField: "pass.passSalesOrder",
    }),
    created: function () {
    },
    methods: {
        initFunction: (t) => layui.use(['laydate', 'jquery'], () => {
            layui.laydate.render({
                elem: layui.jquery(".orderDateChoose")[0],
                trigger: 'click',
                done: (value, date) => t.tableObj.reload({url: `/api/salesOrders/search/date/${value}`,})
            })
        }),
        rowOnClick: (obj) => {
            layui.jquery.get("/api/tableField/pass.passOrderCell", fieldData => {
                layui.jquery.get(`/api/salesOrders/${obj.data.id}/salesOrderCellList`, data => {
                    layer.open({
                        id: `orderManagementForm`,
                        type: 1,
                        title: '查看详情',
                        // area: '510px',
                        shade: 0,
                        resize: false,
                        content: `<table id="orderManagementForm"></table>`,
                        btn: ['确定'],
                        yes: (index, layero) => layer.close(index),
                        success: layero => {
                            layui.table.render({
                                elem: '#orderManagementForm',
                                area: ['800px', '700px'],
                                cols: [fieldData.data],
                                data: data._embedded.salesOrderCellList
                            });
                            layer.setTop(layero);
                        }
                    });
                })
            })
        }


    }
});

export default OrderManagement
