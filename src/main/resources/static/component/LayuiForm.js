import Vue from "/js/vue.js"

// language=Vue
const template = `
    <div class="layui-col-xs11" style="margin-top: 20px">
        <div class="layui-form" :lay-filter="tableName+'Form'">
            <div class="layui-form-item" v-for="item in formItems">
                <label class="layui-form-label">{{item.title}}</label>
                <div class="layui-input-block">
                    <input :type="item.type" :name="item.name" :lay-verify="item.verify" :placeholder="item.placeholder" class="layui-input">
                </div>
            </div>
        </div>
    </div>
`;


export const LayuiForm = "layui-form";
Vue.component(LayuiForm, {
    template: template,
    props: {
        tableName: String,
        apiField: String
    },
    data: () => ({
        formItems: [],
    }),
    created: function () {
        if (layui.data('formField')[this.tableName]) {
            Vue.set(this, "formItems", layui.data('formField')[this.tableName])
        } else {
            layui.use('jquery', () => {
                layui.jquery.get(`/api/formField/${this.apiField}`, res => {
                    layui.data('formField', {key: this.tableName, value: res.data});
                    Vue.set(this, "formItems", res.data)
                });
            })
        }
    }
});

export default LayuiForm
