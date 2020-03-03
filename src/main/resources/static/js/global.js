const BarLeft = [
    {
        name: "公告",
        child: [
            {name: "公告列表", comp: "NoticeList"},
            {name: "公告发布", comp: "NoticePublish"},
            {name: "商店信息", comp: "StoreInfo"},
            {name: "个人信息", comp: "PersonalInfo"},
        ]
    }, {
        name: "销售面板",
        child: [
            {name: "商品销售", comp: "SalesPanel"},
            {name: "会员管理", comp: "CustomerManagement"},
            {name: "账单记录", comp: "OrderManagement"},
            {name: "销售记录", comp: "OrderCellManagement"},
        ]
    },
    {
        name: "人事管理",
        child: [
            {name: "权限调整", comp: "GoodsManagement"},
            {name: "人员变动", comp: "MedicineManagement"},
        ]
    },
    {
        name: "药品管理",
        child: [
            {name: "库存管理", comp: "GoodsManagement"},
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
];



const GLOBAL = {
    UserDate: null,
    ApplicationName: "连锁药店管理系统",
    BarLeft: BarLeft,
    LocalUser: LocalUser
};

InitApp();

function InitApp() {
    layui.config({base: '/lay/modules/'}).extend({easyeditor: 'easyeditor'})
}

/**
 * @return {null}
 */
function LocalUser(func) {
    if (func) {
        if (GLOBAL.UserDate == null) {
            layui.use("jquery", () => layui.jquery.get("/api/user", re => {
                GLOBAL.UserDate = re;
                func(re);
            }));
        } else {
            func(GLOBAL.UserDate);
        }
    } else {
        return GLOBAL.UserDate
    }
}
