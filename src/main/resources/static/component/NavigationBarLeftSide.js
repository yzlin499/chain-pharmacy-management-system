import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="leftNav">
                <li class="layui-nav-item" :class="{'layui-this':navSelected===i.comp}" v-for="i in menus">
                    <a :comp="i.comp" v-if="i.child===undefined">{{i.name}}</a>
                    <div v-else>
                        <a :comp="i.comp">{{i.name}}</a>
                        <dl class="layui-nav-child">
                            <dd v-for="ic in i.child" :class="{'layui-this':navSelected===ic.comp}"><a :comp="ic.comp">{{ic.name}}</a>
                            </dd>
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
        navSelected: "",
        systemName: GLOBAL.ApplicationName,
        menus: [
            {
                name: "公告",
                child: [
                    {name: "公告", comp: "LoginDialog"},
                    {name: "公告发布", comp: "NavigationBarHeader"},
                    {name: "商店信息", comp: "StoreInfo"},
                    {name: "个人信息", comp: "NavigationBarHeader"},
                ]
            }, {
                name: "销售面板",
                child: [
                    {name: "商品销售", comp: "NavigationBarHeader"},
                    {name: "会员管理", comp: "NavigationBarHeader"},
                    {name: "销售记录", comp: "NavigationBarHeader"},
                ]
            },
            {name: "人事管理", comp: "NavigationBarHeader"},
            {
                name: "药品管理",
                child: [
                    {name: "零售账单", comp: "NavigationBarHeader"},
                    {name: "库存管理", comp: "NavigationBarHeader"},
                    {name: "药品信息管理", comp: "MedicineManagement"},
                ]
            },
            {
                name: "活动管理",
                child: [
                    {name: "创建活动", comp: "NavigationBarHeader"},
                    {name: "活动列表", comp: "NavigationBarHeader"},
                    {name: "活动审核", comp: "NavigationBarHeader"},
                    {name: "活动申请", comp: "MedicineManagement"},
                ]
            },
            {
                name: "高级",
                child: [
                    {name: "商店管理", comp: "NavigationBarHeader"},
                ]
            }

        ],
    }),
    props: {
        selectCallBack: Object
    },
    created: function () {
        layui.use('element', () => {
            layui.element.on('nav(leftNav)', elem => {
                let message = elem.attr("comp");
                if (undefined !== message) {
                    this.navSelected = message;
                    this.$emit('OnNavSelect', message, elem.text(), true)
                }
            });
        });
        this.selectCallBack.$on('selectCallBack', data => this.navSelected = data);
    }
});

export default NavigationBarLeftSide
