import Vue from "/js/vue.js";
import LayuiTable from "/component/LayuiTable.js";

// language=Vue
const template = `
    <layui-table :tableName="tableName"
                 :addDataObject="addDataObject"
                 :apiData="apiData"
                 :apiField="apiField"
                 :tablesName="tablesName"
                 :rowOnClick="rowOnClick">
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
    methods: {
        rowOnClick: (obj) => window.open("/cpms/notice.html?id=" + obj.data.id)
    }
});

export default NoticeList
