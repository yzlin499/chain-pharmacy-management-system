import Vue from "/js/vue.js";

const template = `
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item" v-for="i in leftMenus">
                <a :href="i.href" v-if="i.child===undefined">{{i.name}}</a>
                <div v-else>
                    <a href="javascript:;">{{i.name}}</a>
                    <dl class="layui-nav-child">
                        <dd v-for="ic in i.child"><a :href="ic.href">{{ic.name}}</a></dd>
                    </dl>
                </div>
            </li>
        </ul>
    </div>
</div>
`;

export const NavigationBarLeftSide = "navigation-bar-left-side";
Vue.component(NavigationBarLeftSide, {
    template: template,
    data: () => ({
        loginPath: "",
        registeredPath: "",
        systemName: GLOBAL.ApplicationName,
        leftMenus: [
            {name: "控制台", href: ""},
            {name: "商品管理", href: ""},
            {
                name: "用户",
                href: "",
                child: [
                    {name: "商品管理", href: ""},
                ]
            },
        ],
        rightMenus: [
            {name: "控制台", href: ""},
            {name: "商品管理", href: ""},
            {name: "退出登录", href: ""},
        ],
        user: {
            name: "测试名",
            username: "yyyyy",
            image: "//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
        }
    }),
    props: {
        isLogin: {
            type: Boolean,
            default: false
        }
    },
    created: () => {
        layui.use('element', function () {
        });
    }
});

export default NavigationBarLeftSide
