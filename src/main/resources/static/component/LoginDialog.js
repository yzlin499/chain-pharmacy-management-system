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
            <div v-if="errMsg" class="layui-form-item layui-anim layui-anim-fadein">
                <div class="layui-input-block">
                    <blockquote class="layui-elem-quote" style="border-left: 5px solid #ff6577">{{errMsg}}</blockquote>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-col-xs3 layui-col-xs-offset3" lay-submit lay-filter="login">登录</button>
                    <button type="button" class="layui-btn layui-btn-primary layui-col-xs3">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>
`;


export const LoginDialog = "login-dialog";
Vue.component(LoginDialog, {
    template: template,
    data: () => ({
        errMsg: false
    }),
    props: {},
    created: function () {
        layui.use('form', () =>
            layui.form.on('submit(login)', data => {
                layui.jquery.post("/api/user/login", data.field, response => {
                    if (response.isLogin) {
                        window.location.href = "/cpms/index.html"
                    } else {
                        this.errMsg = "登录失败"
                    }
                });
                return false;
            })
        );
    }
});
export default LoginDialog
