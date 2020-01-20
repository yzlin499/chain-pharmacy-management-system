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
    
    <ul class="layui-nav layui-layout-right" v-if="isLogin">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img :src="user.image" class="layui-nav-img">{{user.name}}
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
        loginPath:"",
        registeredPath:"",
        systemName:GLOBAL.ApplicationName,
        leftMenus:[
            {name:"控制台",href:""},
            {name:"商品管理",href:""},
            {
                name:"用户",
                href:"",
                child:[
                    {name:"商品管理",href:""},
                ]
            },
        ],
        rightMenus:[
            {name:"控制台",href:""},
            {name:"商品管理",href:""},
            {name:"退出登录",href:""},
        ],
        user:{
            name:"测试名",
            username:"yyyyy",
            image:"//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
        }
    }),
    props:{
        isLogin: {
            type:Boolean,
            default:false
        }
    },
    created:()=>{
        layui.use('element', function(){});
    }
});
export default NavigationBarHeader
