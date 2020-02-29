import Vue from "/js/vue.js";

const template=`
<div class="layui-header">
    <div class="layui-logo">{{systemName}}</div>
    <ul class="layui-nav layui-layout-left">
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
    
    <ul class="layui-nav layui-layout-right" v-if="user.isLogin">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img :src="user.data.image" class="layui-nav-img">{{user.data.name}}
            </a>
            <dl class="layui-nav-child">
                <dd v-for="i in rightMenus"><a :href="i.href">{{i.name}}</a></dd>
            </dl>
        </li>
    </ul>
    <ul class="layui-nav layui-layout-right" v-else>
        <li class="layui-nav-item"><a :href="loginPath">登录</a></li>
        <li class="layui-nav-item"><a :href="registeredPath">注册</a></li>
    </ul>
</div>
`;

export const NavigationBarHeader = "navigation-bar-header";
Vue.component(NavigationBarHeader, {
    template:template,
    data: () => ({
        loginPath: "/user/login",
        registeredPath:"",
        systemName:GLOBAL.ApplicationName,
        leftMenus:[
            {name:"控制台",href:""},
            {name: "活动界面", href: ""},
            {
                name: "常用功能",
                href:"",
                child:[
                    {name: "签到", href: ""},
                ]
            },
        ],
        rightMenus:[
            {name:"控制台",href:""},
            {name: "退出登录", href: "/api/user/logout"},
        ],
        user:{
            isLogin: false,
            data: {
                name: "",
                username: "",
                image: ""
            }
        }
    }),
    created: function () {
        layui.use('element');
        layui.use("jquery", () => GLOBAL.LocalUser(re => Vue.set(this, "user", re)));
    }
});
export default NavigationBarHeader
