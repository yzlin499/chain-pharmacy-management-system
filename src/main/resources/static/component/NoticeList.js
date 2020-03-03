import Vue from "/js/vue.js";
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

export const NoticeList = "notice-list";
Vue.component(NoticeList, {
    template: template,
    data: () => ({
        tableName: "notice",
        tablesName: "notices",
        apiData: "",
        apiField: "pass.passNotice",
        addDataObject: {
            title: '获取公告'
        }
    }),
    props: {},
    created: function () {

    },
    methods: {}
});

export default NoticeList
