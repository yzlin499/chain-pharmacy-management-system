import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div>
        <input id="userSearch" type="text" name="title" required placeholder="搜索用户，点击进行操作" autocomplete="off"
               class="layui-input">
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">{{tempUser.name}}</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-block">
                    <input type="number" name="phone" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">隶属商店</label>
                <div class="layui-input-block">
                    <input id="storeSearch" type="text" name="store" required placeholder="搜索商店，点击进行操作"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">职位</label>
                <div class="layui-input-block">
                    <input id="storeSearch" type="text" name="store" required placeholder="搜索商店，点击进行操作"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </div>
`;

export const UserManagement = "user-management";
Vue.component(UserManagement, {
    template: template,
    data: () => ({
        tempUser: {}
    }),
    props: {},
    created: function () {
        layui.use(['yutons_sug', 'table', 'jquery'], () => {
            layui.yutons_sug.render({
                id: "userSearch",
                url: `/api/users/search/common?k=`,
                height: "300",
                cols: [[
                    {field: 'username', title: '用户名'},
                    {field: 'name', title: '姓名'},
                    {field: 'phone', title: '手机'}
                ]],
                type: 'sugTable',
                parseData: (res) => ({
                    "code": 0,
                    "msg": res.message,
                    "count": res.page.totalElements,
                    "data": res._embedded["users"]
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
        })
    },
    methods: {}
});

export default UserManagement
