import Vue from "/js/vue.js";
import LoggerShow from "/component/LoggerShow.js";

// language=Vue
const template = `
    <div>
        <div class="layui-row">
            <div class="layui-col-md6">
                <div class="layui-card">
                    <div class="layui-card-header">商店基本信息</div>
                    <div class="layui-card-body">
                        <table class="layui-table" lay-even="" lay-skin="nob">
                            <colgroup>
                                <col width="150">
                                <col>
                            </colgroup>
                            <tr v-for="i in storeInfoHead">
                                <td>{{i.title}}</td>
                                <td>{{storeInfo[i.field]}}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>商店相关记录</legend>
        </fieldset>
        <div class="layui-collapse" lay-filter="storeInfo">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">商店重大事件</h2>
                <div class="layui-colla-content layui-show">
                    <logger-show :loggerData="storeLogger"></logger-show>
                </div>
            </div>
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">商店人员变动事件</h2>
                <div class="layui-colla-content layui-show">
                    <logger-show :loggerData="employeesChangeLogger"></logger-show>
                </div>
            </div>
        </div>
    </div>
`;

export const StoreInfo = "store-info";
Vue.component(StoreInfo, {
    template: template,
    data: () => ({
        storeInfoHead: [],
        storeInfo: {},
        employeesChangeLogger: [],
        storeLogger: []
    }),
    created: function () {
        let user = GLOBAL.LocalUser().data;
        Vue.set(this, "storeInfo", user.store);
        Vue.set(this, "storeLogger", user.store.storeLogger);
        Vue.set(this, "employeesChangeLogger", user.store.employeesChangeLogger);
        layui.jquery.get("/api/tableField/store", re => Vue.set(this, "storeInfoHead", re.data));
        setTimeout(() => layui.element.render('collapse', 'storeInfo'), 50);
    }
});

export default StoreInfo
