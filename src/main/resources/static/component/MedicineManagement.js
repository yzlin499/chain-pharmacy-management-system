import Vue from "/js/vue.js"
import LayuiTable from "/component/LayuiTable.js";

// language=Vue
const template = `
    <layui-table :tableName="tableName" :addDataObject="addDataObject"></layui-table>
`;

export const MedicineManagement = "medicine-management";
Vue.component(MedicineManagement, {
    template: template,
    data: () => ({
        tableName: "medicine",
        addDataObject: {
            title: '添加药品'
        }
    })
});

export default MedicineManagement
