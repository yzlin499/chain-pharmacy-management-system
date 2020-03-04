import Vue from "/js/vue.js";
import LayuiTable from "/component/LayuiTable.js";

// language=Vue
const template = `
    <div>
        <layui-table :tableName="tableName" :addDataObject="addDataObject"></layui-table>
    </div>
`;

export const StoreManagement = "store-management";
Vue.component(StoreManagement, {
    template: template,
    data: () => ({
        tableName: "store",
        addDataObject: {
            title: '添加商店'
        }
    }),
    props: {},
    created: function () {
    },
    methods: {}
});

export default StoreManagement
