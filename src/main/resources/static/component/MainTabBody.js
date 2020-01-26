import Vue from "/js/vue.js"

// language=Vue
const template = `
    <div class="layui-tab" lay-allowClose="true" lay-filter="tabSelect">
        <ul class="layui-tab-title">
            <li v-for="item in tabs" :lay-id="item.id">{{item.title}}</li>
        </ul>
        <div class="layui-tab-content">
            <div v-for="item in tabs" class="layui-tab-item">{{item.content}}</div>
        </div>
    </div>
`;

export const MainTabBody = "main-tab-body";
Vue.component(MainTabBody, {
    template: template,
    data: () => ({
        selectedId: "index",
        tabs: {
            'index': {
                id: 'index',
                title: '首页',
                content: '首页'
            }
        }
    }),
    props: {
        tabEventNode: Object
    },
    created: function () {
        layui.use('element', () => {
            let context = this;
            layui.element.tabChange('tabSelect', this.selectedId);
            layui.element.on('tab(tabSelect)', function (data) {
                context.$emit('OnTabDelete', data);
            });
            layui.element.on('tabDelete(tabSelect)', function (data) {
                let layId = this.parentNode.getAttribute("lay-id");
                Vue.delete(context.tabs, layId);
                context.$emit('OnTabDelete', data, layId);
            });
        });
        this.tabEventNode.$on('tabEvent', data => {
            if (this.tabs[data.id] === undefined) {
                Vue.set(this.tabs, data.id, data);
            }
            this.selectedId = data.id;
            setTimeout(() => {
                layui.element.render('tab');
                layui.element.tabChange('tabSelect', this.selectedId);
            }, 25);
        });
    },
    watch: {}
});
export default MainTabBody
