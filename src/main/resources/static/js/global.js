let userDate = null;

const GLOBAL = {
    ApplicationName: "连锁药店管理系统",
    LocalUser: func => {
        if (func) {
            if (userDate == null) {
                layui.use("jquery", () => layui.jquery.get("/api/user", re => {
                    userDate = re;
                    func(re);
                }));
            } else {
                func(userDate);
            }
        } else {
            return userDate
        }
    }
};

InitApp();

function InitApp() {
    layui.config({base: '/lay/modules/'}).extend({easyeditor: 'easyeditor'})
}

