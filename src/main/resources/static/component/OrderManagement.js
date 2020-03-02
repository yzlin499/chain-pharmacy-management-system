import Vue from "/js/vue.js";
import LayuiTable from "/component/LayuiTable.js";

// language=Vue
const template = `
    <div>
        <layui-table :tableName="tableName"
                     :isCanAdd="false"
                     :apiField="apiField"
                     :initFunction="initFunction">
            <template v-slot:toolBarSlot>
                <button class="layui-btn layui-btn-sm orderDateChoose">选择日期</button>
            </template>
        </layui-table>
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
        initFunction: (t) => layui.use('laydate', () => {
            layui.laydate.render({
                elem: layui.jquery(".orderDateChoose")[0],
                trigger: 'click',
                done: (value, date) => t.tableObj.reload({url: `/api/salesOrders/search/date/${value}`,})
            })
        })
    }
});

export default OrderManagement