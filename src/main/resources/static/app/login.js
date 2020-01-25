import NavigationBarHeader from "/component/NavigationBarHeader.js";
import LoginDialog from "/component/LoginDialog.js";

const template = `
<div class="layui-layout layui-layout-admin">
    <navigation-bar-header></navigation-bar-header>
    <login-dialog></login-dialog>
</div>
`;


export const appName = "login";
export default {
    template: template,
}
