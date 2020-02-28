import NavigationBarHeader from "/component/NavigationBarHeader.js";
import NavigationBarLeftSide from "/component/NavigationBarLeftSide.js";
import MainTabBody from "/component/MainTabBody.js";
import Vue from "/js/vue.js";

// language=HTML
const template = `
    <div class="layui-layout layui-layout-admin">
        <navigation-bar-header></navigation-bar-header>
        <navigation-bar-left-side @OnNavSelect="onNavSelect"
                                  :selectCallBack="this">
        </navigation-bar-left-side>
        <div class="layui-body">
            <main-tab-body @OnTabDelete="onTabDelete"
                           @OnTabSelect="onTabSelect"
                           :tabEventNode="this" :tab="indexTab">
            </main-tab-body>
        </div>
    </div>
`;


export const appName = "cpms-index";
export default {
    template: template,
    data: () => ({
        indexTab: {
            id: "index",
            title: "首页",
            content: `首页`,
            isComponent: false
        }
    }),
    methods: {
        onNavSelect: function (comp, name, isComponent) {
            import("/component/" + comp + ".js").then(mod => this.$emit('tabEvent', {
                id: comp,
                title: name,
                content: mod[comp],
                isComponent: isComponent
            }));
        },
        onTabSelect: function (data, layId) {
            this.$emit('selectCallBack', layId);
        },
        onTabDelete: function (data, layId) {
        },

    }
}
