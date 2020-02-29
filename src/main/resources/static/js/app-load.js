import Vue from "/js/vue.js";

//////////////---[[--配置头--]]---//////////////

////////////---加载文件---////////////////////
const cssList = [
    "/css/layui.css",
    "/css/easy-editor.css",
    "/css/icon.css"
];

const libList = [
    "/js/marked.min.js",
    "/js/axios.min.js",
    "/layui.js",
    "/js/global.js",
];

////////////---加载文件顺序---////////////////////
let loadObj = [
    {
        loadList: cssList,
        loadFunc: () => cssList.forEach(s => LoadCSS(s, LoadOver))
    }, {
        loadList: libList,
        loadFunc: () => libList.forEach(s => LoadJS(s, LoadOver))
    }, {
        loadList: [],
        loadFunc: () => {
            AppLoad();
            LoadOver();
        }
    }
];

//////////////---[[--配置尾--]]---//////////////

////////////---不能动就对了---////////////////////
let listLength = 1;
let loadObjIndex = -1;
LoadOver();
function LoadOver() {
    listLength--;
    if (listLength <= 0 && loadObjIndex < loadObj.length - 1) {
        loadObjIndex++;
        listLength = loadObj[loadObjIndex].loadList.length;
        loadObj[loadObjIndex].loadFunc();
    }
}

function LoadCSS(url, callback) {
    let script = document.createElement('link');
    script.rel = 'stylesheet';
    script.onload = callback;
    script.href = url;
    document.getElementsByTagName('head')[0].appendChild(script);
}
function LoadJS(url, callback) {
    let script = document.createElement('script');
    script.type = 'text/javascript';
    script.onload = callback;
    script.src = url;
    document.getElementsByTagName('head')[0].appendChild(script);
}

function AppLoad() {
    const appElement = document.getElementById("app");
    const appName = appElement.getAttribute("app");
    appElement.append(document.createElement(appName));
    import(`/app/${appName}.js`).then(model => {
        Vue.component(model.appName, model.default);
        new Vue({el: '#app'});
    })
}
