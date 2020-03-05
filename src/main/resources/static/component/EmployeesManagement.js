import Vue from "/js/vue.js";
import LayuiForm from "/component/LayuiForm.js";

// language=Vue
const template = `
    <div style="padding: 20px;">
        <button type="button" class="layui-btn" @click="addNewEmployees">添加新员工</button>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>员工列表</legend>
        </fieldset>
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md4 layui-col-sm6 layui-col-xs12" v-for="(item,index) in employees">
                <fieldset class="layui-elem-field">
                    <legend><img :src="item.image" class="layui-circle" style="height: 60px" alt="头像缺少"></legend>
                    <div class="layui-field-box">
                        <table class="layui-table" lay-even="" lay-skin="nob">
                            <colgroup>
                                <col width="150">
                                <col>
                            </colgroup>
                            <tr>
                                <td>用户名</td>
                                <td>{{item.username}}</td>
                            </tr>
                            <tr>
                                <td>名字</td>
                                <td>{{item.name}}</td>
                            </tr>
                            <tr>
                                <td>职务</td>
                                <td>
                                    <div class="layui-form">
                                        <select :userindex="index" name="authority" v-model="item.authority"
                                                lay-filter="authority"
                                                v-bind="{disabled: item.authority==='ROLE_ADMIN'}">
                                            <option :value="r.authority" v-for="r in roles"
                                                    v-bind="{disabled: r.authority==='ROLE_ADMIN'}">{{r.name}}
                                            </option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>手机</td>
                                <td>{{item.phone}}</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <button type="button" class="layui-btn layui-btn-danger"
                                            @click="dismissedEmployees(item,index)">开除员工
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
`;

export const EmployeesManagement = "employees-management";
Vue.component(EmployeesManagement, {
    template: template,
    data: () => ({
        userInfoHead: {
            username: "用户名",
            name: "名字",
            storeName: "隶属商店",
            position: "职务",
            phone: "手机"
        },
        roles: [],
        employees: []
    }),
    props: {},
    created: function () {
        layui.use(['jquery', 'form'], () => layui.jquery.get(`/api/users/search/storeId?storeId=${GLOBAL.LocalUser().data.store.id}`,
            data => layui.jquery.get("/api/roles", rolesData => {
                Vue.set(this, 'roles', rolesData._embedded.roles);
                Vue.set(this, 'employees', data._embedded.users);
                for (let i = 0; i < this.employees.length; i++) {
                    layui.jquery.get(this.employees[i]._links.authorities.href, a =>
                        Vue.set(this.employees[i], 'authority', a._embedded.roles[0].authority))
                }
                layui.form.on('select(authority)', data => {
                    let employee = this.employees[layui.jquery(data.elem).attr("userindex")];
                    employee.authority = data.value;
                    axios.put(`/api/user/${employee.id}`, employee)
                        .then(response => {
                            if (response.status === 200) {
                                layer.msg("数据修改成功");
                            }
                        }).catch(function (error) {
                        layer.msg("数据修改失败");
                        console.log(error);
                    })
                });
                setTimeout(() => layui.form.render('select'), 200);
            })
        ))
    },
    methods: {
        dismissedEmployees: function (user, index) {
            layer.open({
                title: '开除员工',
                content: `开除员工${user.name}？`,
                btn: ['确定', '取消'],
                yes: () => axios.delete(`/api/user/${user.id}`)
                    .then(response => {
                        if (response.status === 204) {
                            layer.msg("数据删除成功");
                            Vue.delete(this.employees, index);
                        }
                    }).catch(function (error) {
                        layer.msg("数据删除失败，有数据引用当前数据，无法删除");
                        console.log(error);
                    })
            })
        },
        addNewEmployees: function () {
            let openUI = {
                id: `userForm`,
                type: 1,
                title: '添加新用户',
                area: ['500px', '400px'],
                shade: 0,
                maxmin: true,
                content: `
                <div id="userForm">
                    <layui-form table-name="user" api-field="user"></layui-form>
                </div>`,
                btn: ['保存', '取消'],
                yes: (index, layero) => {
                    let val = layui.form.val(`userForm`);
                    console.log(val);

                    // axios.post("", val).then(response => {
                    //     console.log(response);
                    //     if (response.status === 201) {
                    //         layer.msg("数据添加成功");
                    //     }
                    //     this.tableObj.reload();
                    // }).catch(function (error) {
                    //     layer.msg("数据添加失败");
                    //     console.log(error);
                    // });

                    layer.close(index);
                },
                vueObj: null,
                zIndex: layui.layer.zIndex,
                success: layero => {
                    layer.setTop(layero);
                    openUI.vueObj = new Vue({el: "#userForm"});
                },
                end: () => openUI.vueObj.$destroy()
            };
            layer.open(openUI);
        }
    }
});

export default EmployeesManagement
