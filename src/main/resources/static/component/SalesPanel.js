import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div>
        <input id="saleMedicineSearch" type="text" name="title" required placeholder="搜索药品，点击加入购物车" autocomplete="off"
               class="layui-input">
        <table class="layui-hide" id="shoppingTrolley" lay-filter="salesPanelFilter"></table>
        <button type="button" class="layui-btn">购买</button>
        <button type="button" class="layui-btn layui-btn-danger">清空购物车</button>
        <script type="text/html" id="shoppingTrolleyBar">
            <a class="layui-btn layui-btn-xs" lay-event="reduce">减少</a>
            <a class="layui-btn layui-btn-xs" lay-event="increase">增加</a>
            <a class="layui-btn layui-btn-xs" lay-event="delete">删除</a>
        </script>
    </div>
`;

export const SalesPanel = "sales-panel";
Vue.component(SalesPanel, {
    template: template,
    data: () => ({
        shoppingTrolley: {},
        shopTable: {
            data: []
        },
        tableObj: {}
    }),
    props: {},
    created: function () {
        console.log(123);
        layui.use(['yutons_sug', 'table', 'jquery'], () => {
            let salesTable = layui.table.render({
                elem: '#shoppingTrolley',
                cols: [[
                    {field: 'medicineId', width: 200, title: '条形码', sort: true},
                    {field: 'medicineName', title: '药品名', sort: true},
                    {field: 'price', width: 200, title: '价格'},
                    {field: 'buyCount', width: 200, title: '购买数量', edit: "text"},
                    {fixed: 'right', width: 200, align: 'center', toolbar: '#shoppingTrolleyBar'}
                ]],
                data: []
            });

            layui.yutons_sug.render({
                id: "saleMedicineSearch",
                url: `/api/storeGoods/${GLOBAL.LocalUser().data.store.id}/goods/search/common?k=`,
                height: "300",
                cols: [[
                    {field: 'medicineId', title: '药品条形码'},
                    {field: 'medicineName', title: '药品名'},
                    {field: 'price', title: '参考价格'}
                ]],
                type: 'sugTable',
                parseData: (res) => ({
                    "code": 0,
                    "msg": res.message,
                    "count": res.page.totalElements,
                    "data": res._embedded["goods"]
                }),
                request: {limitName: 'size'},
                page: true,
                limit: 15,
                limits: [15, 30, 45, 60],
                itemOnClick: obj => {
                    let data = obj.data;
                    if (this.shoppingTrolley[data.id]) {
                        this.shoppingTrolley[data.id].buyCount++;
                    } else {
                        data.buyCount = 1;
                        this.shoppingTrolley[data.id] = data;
                        this.shopTable.data.push(data);
                    }
                    salesTable.reload(this.shopTable);
                    layui.jquery("#saleMedicineSearch").val("")
                }
            });


            layui.table.on("edit(salesPanelFilter)", obj => this.shoppingTrolley[obj.data.id].buyCount = obj.value);
            layui.table.on(`tool(salesPanelFilter)`, obj => {
                if (obj.event === 'reduce') {
                    this.shoppingTrolley[obj.data.id].buyCount--;
                } else if (obj.event === 'increase') {
                    this.shoppingTrolley[obj.data.id].buyCount++;
                } else if (obj.event === 'delete') {
                    this.shoppingTrolley[obj.data.id] = null;
                    this.shopTable.data = this.shopTable.data.filter(i => i.id !== obj.data.id);
                }
                salesTable.reload(this.shopTable);
            });
        });
    },
    methods: {}
});

export default SalesPanel
