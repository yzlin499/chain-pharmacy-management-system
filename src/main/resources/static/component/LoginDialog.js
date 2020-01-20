import Vue from "/js/vue.js"

const template = `<div class="layui-card">
    <div class="layui-card-header">登录</div>
    <div class="layui-card-body">
        <div class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-block">
                    <input type="text" name="username" required lay-verify="required" placeholder="输入用户名"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="login">登录</button>
                    <button type="button" class="layui-btn layui-btn-primary">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>
`;


export const LoginDialog = "login-dialog";
Vue.component(LoginDialog, {
    template: template,
    data: () => ({}),
    props: {},
    created: () => {
        layui.use('form', function () {
            layui.form.on('submit(login)', function(data){
                console.log(data.field);
                return false;
            });
        });
    }
});
export default LoginDialog
