import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div class="layui-collapse" lay-filter="storeInfo">
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">商店重大事件</h2>
            <div class="layui-colla-content">
                <p>qwe</p>
            </div>
        </div>
        <div class="layui-colla-item">
            <h2 class="layui-colla-title">商店人员变动事件</h2>
            <div class="layui-colla-content" style="padding: 0">
                <table class="layui-table" lay-even="" lay-skin="nob">
                    <tr>
                        <td>贤心</td>
                        <td>汉族</td>
                        <td>1989-10-14</td>
                        <td>人生似修行</td>
                    </tr>
                    <tr>
                        <td>贤心</td>
                        <td>汉族</td>
                        <td>1989-10-14</td>
                        <td>人生似修行</td>
                    </tr>
                    <tr>
                        <td>贤心</td>
                        <td>汉族</td>
                        <td>1989-10-14</td>
                        <td>人生似修行</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
`;

export const StoreInfo = "store-info";
Vue.component(StoreInfo, {
    template: template,
    data: () => ({
        test: [
            {title: "123", content: "qwe"}, {title: "asd", content: "zxc"}
        ]
    }),
    props: {},
    created: function () {
        layui.use('element', () => {
            setTimeout(() => layui.element.render('collapse', 'storeInfo'), 50)
        })
    }
});

export default StoreInfo
