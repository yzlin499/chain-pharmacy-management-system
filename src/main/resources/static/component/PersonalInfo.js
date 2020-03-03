import Vue from "/js/vue.js";
import LoggerShow from "/component/LoggerShow.js";

// language=Vue
const template = `
    <div>
        <div class="layui-row">
            <div class="layui-col-md6">
                <div class="layui-card">
                    <div class="layui-card-header">个人信息</div>
                    <div class="layui-card-body">
                        <table class="layui-table" lay-even="" lay-skin="nob">
                            <colgroup>
                                <col width="150">
                                <col>
                            </colgroup>
                            <tr v-for="(v,k) in userInfoHead">
                                <td>{{v}}:</td>
                                <td>{{userInfo[k]}}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>个人事件记录</legend>
        </fieldset>
        <logger-show :loggerData="userLogger"></logger-show>
    </div>
`;

export const PersonalInfo = "personal-info";
Vue.component(PersonalInfo, {
    template: template,
    data: () => ({
        userInfoHead: {
            username: "名字",
            storeName: "隶属商店",
            position: "职务",
            phone: "手机"
        },
        userInfo: {},
        userLogger: []
    }),
    created: function () {
        let user = GLOBAL.LocalUser().data;
        Vue.set(this, "userInfo", {
            username: user.name,
            storeName: user.store.name,
            position: user.authorities.map(i => i.name).join(" "),
            phone: user.phone
        });
        Vue.set(this, "userLogger", user.userLogger);
    },
    methods: {}
});

export default PersonalInfo
