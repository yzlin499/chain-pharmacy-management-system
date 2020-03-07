const GLOBAL = {
    UserDate: null,
    ApplicationName: "连锁药店管理系统",
    LocalUser: LocalUser,
    Activities: [
        {name: "满减活动", type: "FullReductionActivity"},
    ],
    GetQueryString: (name) => {
        let r = window.location.search.substr(1).match(new RegExp("(^|&)" + name + "=([^&]*)(&|$)"));
        if (r != null) return unescape(r[2]);
        return null;
    },
    GetResources: (urlData, properties, callBack) => {
        if (typeof urlData == "object") {
            GetResourcesByObject(urlData, properties, callBack)
        } else {
            layui.jquery.get(urlData, data => GetResourcesByObject(data, properties, callBack))
        }
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

function GetResourcesByObject(srcData, properties, callBack) {
    let count = properties.length;
    properties.forEach(p => layui.jquery.get(srcData._links[p].href, data => {
        if (data._embedded) {
            srcData[p] = [];
            for (let embeddedKey in data._embedded) {
                data._embedded[embeddedKey].forEach(i => srcData[p].push(i));
            }
        } else {
            srcData[p] = data;
        }
        count--;
        if (count === 0) {
            callBack(srcData);
        }
    }));
}
