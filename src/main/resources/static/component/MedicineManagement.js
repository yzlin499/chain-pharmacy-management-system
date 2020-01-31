import Vue from "/js/vue.js"
import LayuiTable from "/component/LayuiTable.js";

const template = `
<layui-table :tableName="tableName"></layui-table>
`;


export const MedicineManagement = "medicine-management";
Vue.component(MedicineManagement, {
    template: template,
    data: () => ({
        tableName: "medicine"
    })
});

export default MedicineManagement
