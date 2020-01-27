import Vue from "/js/vue.js";

// language=Vue
const template = `
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
            <li class="layui-nav-item" v-for="i in menus">
                <a :comp="i.comp" v-if="i.child===undefined">{{i.name}}</a>
                <div v-else>
                    <a :comp="i.comp">{{i.name}}</a>
                    <dl class="layui-nav-child">
                        <dd v-for="ic in i.child"><a :comp="ic.comp">{{ic.name}}</a></dd>
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
        menus: [
            {name: "控制台", comp: "LoginDialog"},
            {name: "商品管理", comp: "NavigationBarHeader"},
            {
                name: "用户",
                child: [
                    {name: "商品管理", comp: "NavigationBarHeader"},
                ]
            },
        ],
    }),
    created: function () {
        layui.use('element', () => {
            layui.element.on('nav(leftNav)', elem => {
                let message = elem.attr("comp");
                if (undefined !== message) {
                    this.$emit('OnNavSelect', message, elem.text())
                }
            });
        });
    }
});

export default NavigationBarLeftSide
