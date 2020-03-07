import Vue from "/js/vue.js";
import FullReductionActivity from "/component/FullReductionActivity.js";

// language=Vue
const template = `
    <div>
        <blockquote class="layui-elem-quote">
            <div class="layui-row">
                <div class="layui-col-sm6">
                    <full-reduction-activity :total="total"></full-reduction-activity>
                </div>
            </div>
        </blockquote>
    </div>
`;

export const ActivityPanel = "activity-panel";
Vue.component(ActivityPanel, {
    template: template,
    data: () => ({
        total: 0
    }),
    props: {
        shoppingTrolley: Object
    },
    created: function () {

    },
    methods: {},
    watch: {
        shoppingTrolley: {
            handler: function (val) {
                let t = 0;
                for (let valKey in val) {
                    t += val[valKey].price * val[valKey].buyCount
                }
                this.total = t;
            },
            deep: true
        },
    }
});

export default ActivityPanel
