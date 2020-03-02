import Vue from "/js/vue.js";

// language=Vue
const template = `
    <div>
        <ul class="layui-timeline" v-for="i in loggerData">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">{{i.date}}</h3>
                    <p>{{i.content}}</p>
                </div>
            </li>
        </ul>
    </div>
`;

export const LoggerShow = "logger-show";
Vue.component(LoggerShow, {
    template: template,
    data: () => ({}),
    props: {
        loggerData: Array
    },
    created: function () {

    },
    methods: {}
});

export default LoggerShow
