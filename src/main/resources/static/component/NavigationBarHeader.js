const template=`
<div class="layui-header">
    <div class="layui-logo">{{systemName}}</div>
    <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item">控制台</li>
        <li class="layui-nav-item"><a href="">商品管理</a></li>
        <li class="layui-nav-item"><a href="">用户</a></li>
        <li class="layui-nav-item">
            <a href="javascript:;">其它系统</a>
            <dl class="layui-nav-child">
                <dd><a href="">邮件管理</a></dd>
                <dd><a href="">消息管理</a></dd>
                <dd><a href="">授权管理</a></dd>
            </dl>
        </li>
    </ul>
    
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                     class="layui-nav-img">
                贤心
            </a>
            <dl class="layui-nav-child">
                <dd><a href="">基本资料</a></dd>
                <dd><a href="">安全设置</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
</div>
`;

export const componentName="navigation-bar-header";
export default {
    template:template,
    data: () => ({
        systemName:GLOBAL.ApplicationName,
        leftMenus:[
            {name:"控制台",href:""},
            {name:"商品管理",href:""},
            {name:"用户",href:""},
        ],
        rightMenus:[

        ]
    }),
    props:{
        isLogin: Boolean
    },
    created:()=>{
        layui.use('element', function(){
        });
    }
}


