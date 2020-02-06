import Vue from "/js/vue.js"

// language=Vue
const template = `
    <form class="layui-form" :lay-filter="tableName+'Form'">
        <div class="layui-form-item" v-for="item in formItems">
            <label class="layui-form-label">{{item.title}}</label>
            <div class="layui-input-block">
                <input type="number" name="title" lay-verify="required" placeholder="请输入标题" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </form>
`;


export const LayuiTable = "layui-table";
Vue.component(LayuiTable, {
    template: template,
    props: {
        tableName: String
    },
    data: () => ({
        formItems: [
            {
                title: "条形码",
                name: "",
                verify: {}
            }
        ]
    }),
    created: function () {
        layui.use('jquery', () => {

        })
    }
});

export default LayuiTable
