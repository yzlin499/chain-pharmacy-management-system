const GLOBAL = {
    UserDate: null,
    ApplicationName: "连锁药店管理系统",
    LocalUser: LocalUser,
    GetQueryString: (name) => {
        let r = window.location.search.substr(1).match(new RegExp("(^|&)" + name + "=([^&]*)(&|$)"));
        if (r != null) return unescape(r[2]);
        return null;
    }
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
