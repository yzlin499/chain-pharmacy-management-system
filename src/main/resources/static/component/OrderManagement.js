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
        }),
        rowOnClick: (obj) => {
            obj.data;
            layer.open({
                id: `orderManagementForm`,
                type: 1,
                title: '查看详情',
                area: ['500px', '400px'],
                shade: 0,
                maxmin: true,
                content: `<table id="orderManagementForm" lay-filter="orderManagementFormFilter"></table>`,
                btn: ['确定'],
                yes: (index, layero) => layer.close(index),
                success: layero => {
                    layui.table.render({
                        elem: '#orderManagementForm',
                        height: 312,
                        url: '/demo/table/user/', //数据接口
                        cols: [[ //表头
                            {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
                            , {field: 'username', title: '用户名', width: 80}
                            , {field: 'sex', title: '性别', width: 80, sort: true}
                            , {field: 'city', title: '城市', width: 80}
                            , {field: 'sign', title: '签名', width: 177}
                            , {field: 'experience', title: '积分', width: 80, sort: true}
                            , {field: 'score', title: '评分', width: 80, sort: true}
                            , {field: 'classify', title: '职业', width: 80}
                            , {field: 'wealth', title: '财富', width: 135, sort: true}
                        ]]
                    });

                    layer.setTop(layero);
                    openUI.vueObj = new Vue({el: `#${this.tableName}Form`})
                }
            });
        }

    }
});

export default OrderManagement