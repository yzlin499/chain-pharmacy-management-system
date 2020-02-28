import Vue from "/js/vue.js"

// language=Vue
const template = `
    <div class="layui-tab" lay-allowClose="true" lay-filter="tabSelect">
        <ul class="layui-tab-title"></ul>
        <div class="layui-tab-content"></div>
    </div>
`;

export const MainTabBody = "main-tab-body";
Vue.component(MainTabBody, {
    template: template,
    data: () => ({
        selectedId: "index",
        tabs: {}
    }),
    props: {
        tabEventNode: Object,
        tab: Object
    },
    created: function () {
        this.tabEventNode.$on('tabEvent', data => {
            if (this.tabs[data.id] === undefined) {
                if (data.isComponent) {
                    data.content = `<div id="${data.id}"><${data.content}></${data.content}></div>`;
                    layui.element.tabAdd('tabSelect', data);
                    this.tabs[data.id] = new Vue({el: "#" + data.id});
                } else {
                    layui.element.tabAdd('tabSelect', data);
                    this.tabs[data.id] = {
                        $destroy: () => {
                        }
                    }
                }
            }
            layui.element.tabChange('tabSelect', data.id);
        });
        layui.use('element', () => {
            let context = this;
            layui.element.tabChange('tabSelect', this.selectedId);
            layui.element.on('tab(tabSelect)', function (data) {
                context.$emit('OnTabSelect', data, this.getAttribute("lay-id"));
            });
            layui.element.on('tabDelete(tabSelect)', function (data) {
                let layId = this.parentNode.getAttribute("lay-id");
                context.tabs[layId].$destroy();
                delete (context.tabs[layId]);
                context.$emit('OnTabDelete', data, layId);
            });
            this.tabEventNode.$emit('tabEvent', this.tab);
        });

    }
});
export default MainTabBody
