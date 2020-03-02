import Vue from "/js/vue.js";
import LayuiTable from "/component/LayuiTable.js";

// language=Vue
const template = `
    <div>
        <layui-table :tableName="tableName" :addDataObject="addDataObject"></layui-table>
    </div>
`;

export const CustomerManagement = "customer-management";
Vue.component(CustomerManagement, {
    template: template,
    data: () => ({
        tableName: "customer",
        addDataObject: {
            title: '添加客户'
        }
    }),
    created: function () {
    },
    methods: {}
});

export default CustomerManagement