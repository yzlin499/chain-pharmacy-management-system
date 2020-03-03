import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div>
        <input id="saleMedicineSearch" type="text" name="title" required placeholder="搜索药品，点击加入购物车" autocomplete="off"
               class="layui-input">
        <table class="layui-hide" id="shoppingTrolley" lay-filter="salesPanelFilter"></table>
        <div class="layui-inline">
            <label class="layui-form-label">会员搜索</label>
            <div class="layui-input-inline" style="width: 400px;">
                <input id="custormerSearch" type="text" name="title" required placeholder="搜索会员手机号" autocomplete="off"
                       class="layui-input">
            </div>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" @click="settleAccounts">结算</button>
            </div>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn layui-btn-danger" @click="clean">清空购物车</button>
            </div>
        </div>
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
        tableObj: null
    }),
    props: {},
    created: function () {
        console.log(123);
        layui.use(['yutons_sug', 'table', 'jquery'], () => {
            let salesTable = layui.table.render({
                elem: '#shoppingTrolley',
                totalRow: true,
                cols: [[
                    {field: 'medicineId', width: 200, title: '条形码', sort: true},
                    {field: 'medicineName', title: '药品名', sort: true},
                    {field: 'price', width: 200, title: '价格'},
                    {field: 'buyCount', width: 200, title: '购买数量', edit: "text", totalRowText: "合计"},
                    {field: 'buyTotal', width: 200, title: '合计', totalRow: true},
                    {fixed: 'right', width: 200, align: 'center', toolbar: '#shoppingTrolleyBar'}
                ]],
                data: []
            });
            this.tableObj = salesTable;

            //顶部搜索
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
                itemOnClick: obj => {
                    let data = obj.data;
                    let shoppingTrolley = this.shoppingTrolley[data.id];
                    if (shoppingTrolley) {
                        shoppingTrolley.buyCount++;
                    } else {
                        data.buyCount = 1;
                        this.shoppingTrolley[data.id] = data;
                        shoppingTrolley = this.shoppingTrolley[data.id];
                        this.shopTable.data.push(data);
                    }
                    shoppingTrolley.buyTotal = shoppingTrolley.buyCount * shoppingTrolley.price;
                    salesTable.reload(this.shopTable);
                    layui.jquery("#saleMedicineSearch").val("")
                }
            });

            //手机
            layui.yutons_sug.render({
                id: "custormerSearch",
                url: "/api/customers/search/common?k=",
                height: "300",
                cols: [[
                    {field: 'phone', title: '手机号'},
                    {field: 'name', title: '姓名'}
                ]],
                type: 'sugTable',
                parseData: (res) => ({
                    "code": 0,
                    "msg": res.message,
                    "count": res.page.totalElements,
                    "data": res._embedded.customers
                }),
                request: {limitName: 'size'},
                itemOnClick: obj => layui.jquery("#custormerSearch").val(obj.data.phone)
            });

            //主要面板的数据的编辑
            layui.table.on("edit(salesPanelFilter)", obj => {
                let shoppingTrolley = this.shoppingTrolley[obj.data.id];
                if (shoppingTrolley.count >= obj.value && 1 <= obj.value) {
                    shoppingTrolley.buyCount = obj.value;
                    shoppingTrolley.buyTotal = shoppingTrolley.buyCount * shoppingTrolley.price;
                }
                salesTable.reload(this.shopTable);
            });

            //主要面板的按钮的监听
            layui.table.on(`tool(salesPanelFilter)`, obj => {
                let shoppingTrolley = this.shoppingTrolley[obj.data.id];
                if (obj.event === 'reduce') {
                    if (1 < shoppingTrolley.buyCount) {
                        shoppingTrolley.buyCount--;
                    }
                } else if (obj.event === 'increase') {
                    if (shoppingTrolley.count > shoppingTrolley.buyCount) {
                        shoppingTrolley.buyCount++;
                    }
                } else if (obj.event === 'delete') {
                    this.shoppingTrolley[obj.data.id] = null;
                    this.shopTable.data = this.shopTable.data.filter(i => i.id !== obj.data.id);
                }
                shoppingTrolley.buyTotal = shoppingTrolley.buyCount * shoppingTrolley.price;
                salesTable.reload(this.shopTable);
            });
        });
    },
    methods: {
        settleAccounts: function () {
            let val = {
                customerPhone: layui.jquery("#custormerSearch").val(),
                orderMap: {}
            };
            for (let key in this.shoppingTrolley) {
                val.orderMap[key] = this.shoppingTrolley[key].buyCount
            }
            axios.post("/api/salesOrders", val).then(response => {
                if (response.status === 201) {
                    layer.msg("订单创建成功，订单号：" + response.data.id);
                }
                this.clean();
            }).catch(error => {
                layer.msg("数据添加失败");
                console.log(error);
            });
        },
        clean: function () {
            this.shoppingTrolley = {};
            this.shopTable.data = [];
            this.tableObj.reload(this.shopTable);
        }

    }
});

export default SalesPanel
