import NavigationBarHeader from "/component/NavigationBarHeader.js";
import LoginDialog from "/component/LoginDialog.js";
import NavigationBarLeftSide from "/component/NavigationBarLeftSide.js";
import MainTabBody from "/component/MainTabBody.js";
import Vue from "/js/vue.js";

// language=HTML
const template = `
    <div class="layui-layout layui-layout-admin">
        <navigation-bar-header></navigation-bar-header>
        <navigation-bar-left-side @OnNavSelect="onNavSelect"></navigation-bar-left-side>
        <div class="layui-body">
            <main-tab-body @OnTabDelete="onTabDelete" :tabEventNode="this"></main-tab-body>
        </div>
    </div>
`;

export const appName = "cpms-index";
export default {
    template: template,
    data: () => ({}),
    methods: {
        onNavSelect: function (comp, name) {
            this.$emit('tabEvent', {
                id: comp,
                title: name,
                content: comp,
            });
        },
        onTabDelete: function (data) {

        }

    }
}
