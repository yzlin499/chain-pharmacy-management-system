import Vue from "/js/vue.js"
import LayuiTable from "/component/LayuiTable.js";

// language=Vue
const template = `
    <layui-table :tableName="tableName"
                 :addDataObject="addDataObject"
                 :apiData="apiData"
                 :apiField="apiField"
                 :tablesName="tablesName">
    </layui-table>
`;

export const GoodsManagement = "goods-management";
Vue.component(GoodsManagement, {
    template: template,
    data: () => ({
        tableName: "goods",
        tablesName: "goods",
        apiData: "",
        apiField: "pass.passGoods",
        addDataObject: {
            title: '添加药品'
        }
    }),
    created: function () {
        this.apiData = `/api/storeGoods/${GLOBAL.LocalUser().data.store.id}/goods`;

    },
    methods: {
    }
});

export default GoodsManagement
