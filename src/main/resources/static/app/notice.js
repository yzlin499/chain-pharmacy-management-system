import NavigationBarHeader from "/component/NavigationBarHeader.js";
import Vue from "/js/vue.js"
// language=Vue
const template = `
    <div class="layui-layout layui-layout-admin">
        <navigation-bar-header></navigation-bar-header>
        <div class="layui-container">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend><h1>{{notice.title}}</h1></legend>
            </fieldset>
            <div class="easyeditor-panel detail-box">
                <div class="easyeditor-content" v-html="notice.content">
                </div>
            </div>
        </div>

    </div>
`;


export const appName = "notice";
export default {
    template: template,
    data: () => ({
        notice: {}
    }),
    methods: {},
    created: function () {
        layui.use(['easyeditor', 'jquery'], () => layui.jquery.get("/api/notices/" + GLOBAL.GetQueryString("id"), data => {
            Vue.set(this, "notice", data);
            setTimeout(() => layui.easyeditor.render({
                elem: ".easyeditor-content"
            }), 100);

        }))
    }
}
