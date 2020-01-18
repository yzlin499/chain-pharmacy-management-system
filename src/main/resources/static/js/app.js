const cssList = [
    "/css/layui.css"
];

const libList = [
    "/js/global.js",
    "/js/layui.js",
    "/js/vue.min.js",
];

const componentList = [
    "NavigationBarHeader"
];

////////////////////////////////
const loadObj = [
    {
        loadList: cssList,
        loadFunc: () => cssList.forEach(s => LoadCSS(s, LoadOver))
    }, {
        loadList: libList,
        loadFunc: () => libList.forEach(s => LoadJS(s, LoadOver))
    }, {
        loadList: componentList,
        loadFunc: () => componentList.forEach(cn => import (`/component/${cn}.js`).then(mod => {
            Vue.component(mod.componentName, mod.default);
            LoadOver();
        }))
    }, {
        loadList: [],
        loadFunc: () => {
            new Vue({
                el: '#app'
            });
            LoadOver();
        }
    }
];

////////////////////////////////
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

function LoadJS(url, callback) {
    let script = document.createElement('script');
    script.type = 'text/javascript';
    script.onload = callback;
    script.src = url;
    document.getElementsByTagName('head')[0].appendChild(script);
}

function LoadCSS(url, callback) {
    let script = document.createElement('link');
    script.rel = 'stylesheet';
    script.onload = callback;
    script.href = url;
    document.getElementsByTagName('head')[0].appendChild(script);
}
