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
            type: 2,
            title: '标题',
            shade: 0,
            maxmin: true,
            content: addDataTemplate,
            btn: ['保存', '取消'],
            yes: () => {
            },
            btn2: () => {
            },
            zIndex: layer.zIndex,
            success: layero => layer.setTop(layero)
        }
    })
});

export default MedicineManagement
