import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div>
        <input id="userSearch" type="text" name="user" required placeholder="搜索用户，点击进行操作" autocomplete="off"
               class="layui-input">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>员工信息</legend>
        </fieldset>
        <div class="layui-form" lay-filter="userManagementForm">
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">id</label>
                <div class="layui-input-block">
                    <input type="text" name="id" disabled class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" disabled class="layui-input">
                </div>
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
                    <input id="storeSearch" type="text" name="storeName" required placeholder="搜索商店，点击进行操作"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">职位</label>
                <div class="layui-input-block">
                    <select name="authority">
                        <option :value="r.authority" v-for="r in roles">{{r.name}}</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" @click="submit">保存更改</button>
                </div>
            </div>
        </div>
    </div>
`;

export const UserManagement = "user-management";
Vue.component(UserManagement, {
    template: template,
    data: () => ({
        tempUser: {},
        roles: [],
        selectStore: null

    }),
    props: {},
    created: function () {
        layui.use(['yutons_sug', 'table', 'jquery', 'form'], () => {
            layui.jquery.get("/api/roles", rolesData => {
                Vue.set(this, 'roles', rolesData._embedded.roles);
            });
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
                itemOnClick: obj => GLOBAL.GetResources(obj.data, ["store", "authorities"], data => {
                    data.storeName = data.store.name;
                    this.selectStore = data.store;
                    data.authority = data.authorities[0].authority;
                    layui.form.val('userManagementForm', data);
                    layui.jquery("#userSearch").val("")
                })
            });
            layui.yutons_sug.render({
                id: "storeSearch",
                url: `/api/stores/search/common?k=`,
                height: "300",
                cols: [[
                    {field: 'name', title: '商店名'},
                    {field: 'address', title: '地址'},
                    {field: 'des', title: '备注'}
                ]],
                type: 'sugTable',
                parseData: (res) => ({
                    "code": 0,
                    "msg": res.message,
                    "count": res.page.totalElements,
                    "data": res._embedded["stores"]
                }),
                request: {limitName: 'size'},
                itemOnClick: obj => {
                    this.selectStore = obj.data;
                    layui.jquery("#storeSearch").val(obj.data.name)
                }
            });
            layui.form.on('submit(userManagementFormSubmit)', function (data) {
                console.log(data.field);
                return false;
            });
        })
    },
    methods: {
        submit: function () {
            let val = layui.form.val("userManagementForm");
            val.store = this.selectStore;
            axios.put(`/api/user/${val.id}`, val)
                .then(response => {
                    if (response.status === 200) {
                        layer.msg("数据修改成功");
                    }
                }).catch(function (error) {
                layer.msg("数据修改失败");
                console.log(error);
            })
        }
    }
});

export default UserManagement
