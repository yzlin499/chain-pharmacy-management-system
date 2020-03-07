import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div class="layui-card ">
        <div class="layui-card-header">满减活动</div>
        <div class="layui-card-body" style="padding: 0">
            <table class="layui-table" lay-skin="row" style="margin: 0">
                <tr>
                    <td>原价</td>
                    <td>{{total}}</td>
                </tr>
                <tr>
                    <td>满减</td>
                    <td>{{-reduce}}</td>
                </tr>
                <tr>
                    <td>价格</td>
                    <td>{{total-reduce}}</td>
                </tr>
            </table>
        </div>
    </div>
`;

export const FullReductionActivity = "full-reduction-activity";
Vue.component(FullReductionActivity, {
    template: template,
    data: () => ({
        activity: {},
        type: "FullReductionActivity",
        fullReductionArray: [],
        reduce: 0
    }),
    props: {
        total: {
            type: Number,
            default: 100,
        }
    },
    created: function () {
        layui.jquery.get("http://localhost:8080/api/activities/46", data => {
            this.activity = data;
            for (let i = data.args.length - 1; i >= 0; i--) {
                let fr = data.args[i].split("-");
                this.fullReductionArray.push({
                    price: parseFloat(fr[0]),
                    reduce: parseFloat(fr[1])
                });
            }
            this.resetPrice();
        })
    },
    methods: {
        resetPrice: function () {
            for (let i = 0; i < this.fullReductionArray.length; i++) {
                if (this.total >= this.fullReductionArray[i].price) {
                    this.reduce = this.fullReductionArray[i].reduce;
                    break;
                }
            }
        }
    },
    watch: {
        total: function () {
            this.resetPrice();
        }
    }
});

export default FullReductionActivity
