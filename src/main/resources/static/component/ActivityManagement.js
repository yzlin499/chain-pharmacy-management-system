import Vue from "/js/vue.js";
import LayuiTable from "/component/LayuiTable.js";

// language=Vue
const template = `
    <div>
        <layui-table :tableName="tableName"
                     :tablesName="tablesName"
                     :isCanAdd="false"></layui-table>
    </div>
`;

export const ActivityManagement = "activity-management";
Vue.component(ActivityManagement, {
    template: template,
    data: () => ({
        tableName: "activity",
        tablesName: "activities"
    }),
    created: function () {
    },
    methods: {}
});

export default ActivityManagement
